package org.epoch.rulengine;

import java.io.IOException;

import org.epoch.rulengine.properties.RuleEngineProperties;
import org.epoch.rulengine.service.RuleEngineService;
import org.epoch.rulengine.service.impl.RuleEngineServiceImpl;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.utils.KieHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * <p>name:RuleEngineAutoConfiguration</p>
 * <pre>
 *      description:rule engine config file
 * </pre>
 *
 * @author Marshal Yuan
 * @date 2020/8/9
 */
@EnableConfigurationProperties(RuleEngineProperties.class)
public class RuleEngineAutoConfiguration {

    @Autowired
    private RuleEngineProperties ruleEngineProperties;

    private final KieServices kieServices = KieServices.Factory.get();

    @Bean
    @ConditionalOnMissingBean
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        ResourcePatternResolver resourcePatternResolver =
                new PathMatchingResourcePatternResolver();
        Resource[] files =
                resourcePatternResolver.getResources("classpath*:" + ruleEngineProperties.getRulesPath() + "*.*");
        String path = null;
        for (Resource file : files) {
            path = ruleEngineProperties.getRulesPath() + file.getFilename();
            kieFileSystem.write(ResourceFactory.newClassPathResource(path, "UTF-8"));
        }
        return kieFileSystem;
    }

    @Bean
    @ConditionalOnMissingBean
    public KieContainer kieContainer() throws IOException {
        KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        return kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
    }

    @Bean
    @ConditionalOnMissingBean
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public KModuleBeanFactoryPostProcessor kiePostProcessor() {
//        return new KModuleBeanFactoryPostProcessor();
//    }

    @Bean
    @ConditionalOnMissingBean
    private RuleEngineService ruleEngineService() throws IOException {
        RuleEngineService ruleEngineService = new RuleEngineServiceImpl();
        return ruleEngineService;
    }
}
