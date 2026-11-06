package io.distributedjobscheduler.worker.util;

import io.distributedjobscheduler.common.util.TaskUtil;
import io.distributedjobscheduler.worker.DistributedJobSchedulerWorker;
import io.distributedjobscheduler.worker.context.JobContext;
import io.distributedjobscheduler.worker.dto.LogContentDTO;
import io.distributedjobscheduler.worker.init.WorkerConfig;

import java.util.Objects;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
public class LogUtil {

    /**
     * Get log content.
     *
     * @return LogContentDTO
     */
    public static LogContentDTO getLogContent() {
        // Job context
        JobContext context = ThreadLocalUtil.getJobContext();

        // Not job context
        if (Objects.isNull(context)) {
            return null;
        }

        // Cron job.
        LogContentDTO logContent = new LogContentDTO();
        logContent.addWorkerAddressField(WorkerConfig.getWorkerAddress());
        if (Objects.isNull(context.getDelayTaskId())) {
            logContent.addJobIdField(context.getJobId());
            logContent.addJobInstanceIdField(context.getJobInstanceId());
            logContent.addCircleIdField(context.getCircleId());
            logContent.addJobInstanceTaskIdField(context.getTaskId());
            logContent.addJobDispatchVersionTaskIdField(context.getDispatchVersion());
            String taskId = TaskUtil.getRandomUniqueId(context.getJobId(), context.getJobInstanceId(), context.getDispatchVersion(), context.getCircleId(), context.getTaskId());
            logContent.addTaskIdField(taskId);
            return logContent;
        }

        // Delay job.
        logContent.addTaskIdField(context.getDelayTaskId());
        logContent.addDelayTopic(context.getDelayTopic());
        return logContent;
    }
}
