package com.ridelr.site.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


@Configuration
@EnableScheduling
public class SchedulingConfiguration implements SchedulingConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingConfiguration.class);

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(2);
    }

   /* @Bean(destroyMethod = "shutdown")
    public DaemonStat ocuwebDaemonStat() {
        return new DaemonStat() {
            @Scheduled(initialDelay = 60000, fixedRate = 300000)
            public void printDaemonInfo() {
                StringBuilder message = new StringBuilder(1000);
                for (Map.Entry<String, String> e : getProperties().entrySet()) {
                    message.append(e.getKey()).append(" ").append(e.getValue()).append("\n");
                }
                LOGGER.info(message.toString());
            }
        };
    }*/

}
