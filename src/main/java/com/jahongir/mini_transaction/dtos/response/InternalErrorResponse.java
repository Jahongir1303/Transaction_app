package com.jahongir.mini_transaction.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author jahongir
 * @created 10/02/23 - 12:14
 * @project Mini_transaction/IntelliJ IDEA
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_ABSENT)
public class InternalErrorResponse {
    private String friendlyMessage;
    private String developerMessage;
    private Map<String, Object> errorFields;
    @Builder.Default
    private Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now(Clock.systemUTC()));
    private String requestPath;
}
