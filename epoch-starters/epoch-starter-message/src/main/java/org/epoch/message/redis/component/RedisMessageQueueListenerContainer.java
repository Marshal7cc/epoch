/*
 * #{copyright}#
 */

package org.epoch.message.redis.component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.epoch.message.redis.annotation.QueueMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.SmartLifecycle;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Marshal
 * @date 2020/4/10
 *
 */
public class RedisMessageQueueListenerContainer implements DisposableBean, SmartLifecycle {

    private Logger logger = LoggerFactory.getLogger(RedisMessageQueueListenerContainer.class);

    private volatile boolean running = false;

    private RedisTemplate redisTemplate;

    private ExecutorService executorService;

    private List<IQueueMessageListener> listeners;

    private List<MonitorTask> monitorTaskList = new ArrayList<>();

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void init(ApplicationContext applicationContext) {
        if (listeners == null) {
            listeners = new ArrayList<>();
        }

        Map<String, Object> lts = applicationContext.getBeansWithAnnotation(QueueMonitor.class);
        lts.forEach((k, v) -> {
            Class clazz = v.getClass();
            QueueMonitor qm = (QueueMonitor) clazz.getAnnotation(QueueMonitor.class);
            final String queue = qm.queue();
            Method method = null;
            try {
                method = clazz.getDeclaredMethod(qm.method(), Object.class);
            } catch (NoSuchMethodException e) {
                logger.error("no method matched found");
            }

            IQueueMessageListener qml = new SimpleQueueListener(queue, v, method);
            listeners.add(qml);
        });

        if (listeners.size() > 0) {
            executorService = Executors.newFixedThreadPool(listeners.size());
            for (IQueueMessageListener receiver : listeners) {
                MonitorTask task = new MonitorTask(receiver, redisTemplate);
                monitorTaskList.add(task);
                executorService.execute(task);
            }
        }
    }

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        stop();
        callback.run();
    }

    @Override
    public void start() {
        if (!running) {
            running = true;

            if (logger.isDebugEnabled()) {
                logger.debug("startup success");
            }
        }
    }

    @Override
    public void destroy() {
        stop();
    }

    @Override
    public void stop() {
        if (isRunning()) {
            running = false;
            monitorTaskList.forEach(MonitorTask::stop);
            executorService.shutdownNow();
            if (logger.isDebugEnabled()) {
                logger.debug("shutdown complete");
            }
        }
    }

    @Override
    public boolean isRunning() {
        return running;
    }


    private static class SimpleQueueListener implements IQueueMessageListener {
        private String queue;
        private Object target;
        private Method method;
        private Logger logger;

        SimpleQueueListener(String queue, Object target, Method method) {
            this.queue = queue;
            this.target = target;
            this.method = method;
            this.logger = LoggerFactory.getLogger(target.getClass());
        }

        @Override
        public String getQueue() {
            return queue;
        }

        @Override
        public void onQueueMessage(Object message, String queue) {
            try {
                method.invoke(target, message);
            } catch (Exception e) {
                Throwable thr = e;
                while (thr.getCause() != null) {
                    thr = thr.getCause();
                }
                if (logger.isErrorEnabled()) {
                    logger.error(thr.getMessage(), thr);
                }
            }
        }
    }
}
