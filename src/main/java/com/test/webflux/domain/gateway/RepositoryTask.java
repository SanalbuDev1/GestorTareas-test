package com.test.webflux.domain.gateway;

import com.test.webflux.domain.dto.Tasks;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RepositoryTask {

    Mono<Tasks> getById(int id);
    Flux<Tasks> getAll();
    Mono<Void> createTask(Tasks task);
    Mono<Void> deleteTask(int id);


}
