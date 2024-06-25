package com.test.webflux.domain.usecase;

import com.test.webflux.domain.dto.Tasks;
import com.test.webflux.domain.gateway.RepositoryTask;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class TaskUsecase {

    private final RepositoryTask repositoryTask;

    public Mono<Tasks> getById(int id) {
        return repositoryTask.getById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("no se encontro la tarea")))
                .onErrorResume(e -> Mono.error(new RuntimeException("Error al obtener las tareas", e)));

    }


    public Flux<Tasks> getAll() {
        return repositoryTask.getAll()
                .switchIfEmpty(Flux.error(new RuntimeException("No se encontraron tareas")))
                .onErrorResume(e -> Flux.error(new RuntimeException("Error al obtener las tareas", e)));
    }

    public Mono<Void> createTask(Tasks task) {
        return repositoryTask.createTask(task)
                .onErrorResume(e -> Mono.error(new RuntimeException("Error al crear la tarea", e)));
    }

    public Mono<Void> deleteTask(int id) {
        return repositoryTask.deleteTask(id)
                .switchIfEmpty(Mono.error(new RuntimeException("No se encontró la tarea para eliminar")))
                .onErrorResume(e -> Mono.error(new RuntimeException("Error al eliminar la tarea", e)));
    }
}
