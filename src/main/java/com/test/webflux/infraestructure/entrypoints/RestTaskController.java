package com.test.webflux.infraestructure.entrypoints;

import com.test.webflux.domain.dto.Tasks;
import com.test.webflux.domain.usecase.TaskUsecase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class RestTaskController {

    private final TaskUsecase taskUseCase;

    @GetMapping(path = "/gettask/{id}")
    public Mono<Tasks> getTaskById(@PathVariable("id") Integer data) {
        return taskUseCase.getById(data);
    }

    @GetMapping(path = "/gettask")
    public Flux<Tasks> getTaskById() {
        return taskUseCase.getAll();
    }



}
