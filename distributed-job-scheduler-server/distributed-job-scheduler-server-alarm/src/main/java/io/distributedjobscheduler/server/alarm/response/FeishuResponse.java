package io.distributedjobscheduler.server.alarm.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
@Data
public class FeishuResponse {
    @JsonProperty(value = "StatusCode")
    private Integer statusCode;

    @JsonProperty(value = "StatusMessage")
    private String statusMessage;

    @JsonProperty(value = "code")
    private Integer code;

    @JsonProperty(value = "msg")
    private String msg;

    @JsonProperty(value = "data")
    private Object data;
}
