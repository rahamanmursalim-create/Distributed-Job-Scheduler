package io.distributedjobscheduler.server.openapi.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * @author zhenghongyang sakuraovq@gmail.com
 * @since 1.0.0
 */
@Data
public class WorkerJobInstanceTaskLogRequest {

    @NotNull
    @Schema(description = "Field list", required = true)
    private List<List<WorkerJobInstanceTaskLogFieldRequest>> fieldList;

    @Data
    public static class WorkerJobInstanceTaskLogFieldRequest {

        @NotBlank
        @Schema(description = "name", required = true)
        private String name;

        @NotBlank
        @Schema(description = "value", required = true)
        private String value;
    }
}
