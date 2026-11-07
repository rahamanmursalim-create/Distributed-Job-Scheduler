package io.distributedjobscheduler.worker.processor;

import io.distributedjobscheduler.common.constant.TaskStatusEnum;
import lombok.Data;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class ProcessResult {
    private TaskStatusEnum status;
    private String result;

    public ProcessResult(TaskStatusEnum status) {
        this.status = status;
    }

    public ProcessResult(Boolean result) {
        this.status = result ? TaskStatusEnum.SUCCESS : TaskStatusEnum.FAILED;
    }

    public ProcessResult(Boolean status, String result) {
        this.status = status ? TaskStatusEnum.SUCCESS : TaskStatusEnum.FAILED;
        this.result = result;
    }

    public static ProcessResult success() {
        return new ProcessResult(true);
    }

    public static ProcessResult fail() {
        return new ProcessResult(false);
    }
}
