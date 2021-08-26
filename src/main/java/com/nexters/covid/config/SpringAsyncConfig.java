package com.nexters.covid.config;

import java.util.concurrent.Executor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class SpringAsyncConfig implements AsyncConfigurer {

  @Value("${async.pool.core_pool_size}")
  private Integer corePoolSize;
  @Value("${async.pool.max_pool_size}")
  private Integer maxPoolSize;
  @Value("${async.pool.queue_capacity}")
  private Integer queueCapacity;
  @Value("${async.pool.prefix_name}")
  private String prefixName;

  @Bean(name = "threadPoolTaskExecutor")
  public Executor threadPoolTaskExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setCorePoolSize(corePoolSize);
    taskExecutor.setMaxPoolSize(maxPoolSize);
    taskExecutor.setMaxPoolSize(queueCapacity);
    taskExecutor.setThreadNamePrefix(prefixName);
    taskExecutor.initialize();
    return taskExecutor;
  }
}
