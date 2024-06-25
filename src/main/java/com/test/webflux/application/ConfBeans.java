package com.test.webflux.application;

import com.test.webflux.domain.gateway.RepositoryTask;
import com.test.webflux.domain.usecase.TaskUsecase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfBeans {

    @Bean
    public TaskUsecase taskUsecase(RepositoryTask repositoryTask){
        return new TaskUsecase(repositoryTask);
    }

}
