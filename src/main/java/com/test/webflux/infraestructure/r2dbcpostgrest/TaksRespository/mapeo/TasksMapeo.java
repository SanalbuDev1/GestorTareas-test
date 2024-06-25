package com.test.webflux.infraestructure.r2dbcpostgrest.TaksRespository.mapeo;

import com.test.webflux.domain.dto.Tasks;
import io.r2dbc.spi.Readable;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Component
public class TasksMapeo {
    public Tasks map(Readable fila){
        return Tasks.builder()
                .id(fila.get("ID",Integer.class))
                .title(fila.get("TITLE", String.class))
                .description(fila.get("DESCRIPTION", String.class))
                .status(fila.get("STATUS", String.class))
                .createdAt(fila.get("CREATEAT", LocalDateTime.class))
                .build();
    }
}
