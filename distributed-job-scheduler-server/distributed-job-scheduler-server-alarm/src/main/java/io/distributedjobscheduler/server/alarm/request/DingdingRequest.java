package io.distributedjobscheduler.server.alarm.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
@Data
public class DingdingRequest {
    @JsonProperty(value = "msgtype")
    private String msgType = "markdown";

    @JsonProperty(value = "markdown")
    private MarkdownRequest markdown = new MarkdownRequest();

    @Data
    public static class MarkdownRequest {
        @JsonProperty(value = "title")
        private String title;

        @JsonProperty(value = "text")
        private String text;
    }
}
