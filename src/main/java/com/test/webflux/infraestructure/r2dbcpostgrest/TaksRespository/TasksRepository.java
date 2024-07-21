package com.test.webflux.infraestructure.r2dbcpostgrest.TaksRespository;

import com.test.webflux.domain.dto.Tasks;
import com.test.webflux.domain.gateway.RepositoryTask;
import com.test.webflux.infraestructure.r2dbcpostgrest.TaksRespository.mapeo.TasksMapeo;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import io.r2dbc.spi.Connection;

@Repository
@RequiredArgsConstructor
public class TasksRepository implements RepositoryTask {

    private final ConnectionFactory connectionFactory;
    private final TasksMapeo tasksMapeo;    

    String sqlBuscarTaskId="SELECT ID, TITLE, DESCRIPTION, STATUS, CREATED_AT " +
            "FROM public.tasks where ID= $1";

    String sqlBuscarTask="SELECT ID, TITLE, DESCRIPTION, STATUS, CREATED_AT " +
            "FROM public.tasks";

    String sqlPrueba = "select 1 as \"campo1\"";

    @Override
    public Mono<Tasks> getById(int id) {

        return Mono.usingWhen(connectionFactory.create(),
                connection -> Mono.from(connection.createStatement(sqlBuscarTaskId)
                                .bind("$1" , id)
                                .execute())
                        .map( result -> result.map(tasksMapeo::map) )
                        .flatMap(Mono::from),
                Connection::close);
    }
   
    @Override
    public Flux<Tasks> getAll() {
        System.out.println(connectionFactory);
        return Flux.usingWhen(connectionFactory.create(),
                        (connection) -> Flux.from(connection.createStatement(sqlBuscarTask)
                                        .execute())
                                .flatMap(result -> result.map(tasksMapeo::map))
                                .concatWith(Flux.just(new Tasks(1, "1", "1", "1", null, null)))                              
                                .doOnNext(task -> System.out.println("Task ID: " + task.getId())),
                        Connection::close)
                .onErrorMap(ex -> new RuntimeException("Mensaje de error", ex));
    }

    @Override
    public Mono<Void> createTask(Tasks task) {
        return null;
    }

    @Override
    public Mono<Void> deleteTask(int id) {
        return null;
    }
}
