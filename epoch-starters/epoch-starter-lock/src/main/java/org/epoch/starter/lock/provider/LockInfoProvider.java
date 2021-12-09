package org.epoch.starter.lock.provider;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Iterators;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.epoch.starter.lock.annotation.Lock;
import org.epoch.starter.lock.annotation.LockKey;
import org.epoch.starter.lock.constant.LockConstants;
import org.springframework.context.expression.MethodBasedEvaluationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

/**
 * @author Marshal
 * @date 2021/12/7
 */
public class LockInfoProvider {

    private static final ParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
    private static final ExpressionParser parser = new SpelExpressionParser();

    public LockInfoProvider() {
    }

    /**
     * Get lock info.
     *
     * @param joinPoint join point
     * @param lock      lock
     * @return lockInfo
     */
    public LockInfo getLockInfo(JoinPoint joinPoint, Lock lock) {
        // Get running method.
        Method method = getMethod(joinPoint);
        // Resolve lock key.
        List<String> keySet = resolveKeyExpressions(method, joinPoint, lock);
        // Parse Lock Name.
        String lockName = parseLockName(method, joinPoint, lock.name(), keySet);

        return new LockInfo(LockConstants.KEY_PREFIX + lockName, lock.waitTime(), lock.leaseTime(), lock.timeUnit(), keySet);
    }


    /**
     * Get running method.
     *
     * @param joinPoint join point
     * @return
     */
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        if (method.getDeclaringClass().isInterface()) {
            try {
                method = joinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), method.getParameterTypes());
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return method;
    }

    private List<String> resolveKeyExpressions(Method method, JoinPoint joinPoint, Lock lock) {
        List<String> result = new ArrayList<>();
        if (lock.keys().length > 0) {
            result.addAll(parseExpression(method, joinPoint.getArgs(), lock.keys()));
        }
        result.addAll(resolveParameterKeys(method.getParameters(), joinPoint.getArgs()));

        return result;
    }


    private List<String> resolveParameterKeys(Parameter[] parameters, Object[] parameterValues) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            if (!parameters[i].isAnnotationPresent(LockKey.class)) {
                continue;
            }
            LockKey keyAnnotation = parameters[i].getAnnotation(LockKey.class);
            if (keyAnnotation.value().isEmpty()) {
                list.add(parameterValues[i].toString());
            } else {
                StandardEvaluationContext context = new StandardEvaluationContext(parameterValues[i]);
                Object value = parser.parseExpression(keyAnnotation.value()).getValue(context);
                if (value != null) {
                    list.add(value.toString());
                }
            }
        }
        return list;
    }


    private String parseLockName(Method method, JoinPoint joinPoint, String nameExpression, List<String> keySet) {
        if (nameExpression.isEmpty()) {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            return String.format("%s.%s.%s", signature.getDeclaringTypeName(), signature.getMethod().getName(), StringUtils.collectionToDelimitedString(keySet, "", "-", ""));
        } else {
            return parseExpression(method, joinPoint.getArgs(), nameExpression);
        }
    }

    private String parseExpression(Method method, Object[] parameterValues, String expression) {
        List<String> result = parseExpression(method, parameterValues, new String[]{expression});
        String value = Iterators.getOnlyElement(result.iterator());
        return value == null ? expression : value;
    }

    private List<String> parseExpression(Method method, Object[] parameterValues, String[] expressions) {
        EvaluationContext context = new MethodBasedEvaluationContext(null, method, parameterValues, nameDiscoverer);

        List<String> list = new ArrayList<>();
        for (String expression : expressions) {
            if (StringUtils.isEmpty(expression)) {
                continue;
            }
            Object value = parser.parseExpression(expression).getValue(context);
            if (value != null) {
                list.add(value.toString());
            }
        }
        return list;
    }
}
