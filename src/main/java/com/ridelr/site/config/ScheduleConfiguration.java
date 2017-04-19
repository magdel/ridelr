package com.ridelr.site.config;

import com.ridelr.site.scheduler.StatisticLogger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;


@Configuration
@EnableScheduling
public class ScheduleConfiguration {

    @Bean
    public StatisticLogger statisticLogger() {
        return new StatisticLogger();
    }
}
