package com.ouharri.aftas.core;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * Configuration class for customizing the task scheduler in a Spring Boot application.
 * Enables scheduling if the property "spring.scheduler.enabled" is set to true.
 *  @author <a href="mailto:ouharri.outman@gmail.com">ouharri</a>
 */
@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "spring.scheduler.enabled")
public class SchedulerConfiguration implements SchedulingConfigurer {

    /**
     * Configures the task scheduler for the application.
     *
     * @param taskRegistrar The scheduled task registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setTaskScheduler(taskScheduler());
    }

    /**
     * Creates and configures a ThreadPoolTaskScheduler bean for task scheduling.
     *
     * @return The configured TaskScheduler bean.
     */
    @Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setPoolSize(10);
        scheduler.setThreadNamePrefix("ThreadScheduler-");
        scheduler.initialize();
        return scheduler;
    }

    /**
     * Creates and configures a Quartz Scheduler bean.
     *
     * @param factory The SchedulerFactoryBean used to create the Scheduler.
     * @return The configured Quartz Scheduler bean.
     * @throws SchedulerException If an error occurs while starting the scheduler.
     */
    @Bean
    public Scheduler scheduler(SchedulerFactoryBean factory) throws SchedulerException {
        Scheduler scheduler = factory.getScheduler();
        scheduler.start();
        return scheduler;
    }
}