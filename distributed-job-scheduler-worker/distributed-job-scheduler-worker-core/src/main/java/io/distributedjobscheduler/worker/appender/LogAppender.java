package io.distributedjobscheduler.worker.appender;

import io.distributedjobscheduler.worker.dto.LogContentDTO;
import io.distributedjobscheduler.worker.task.LogTaskConsumer;
import io.distributedjobscheduler.common.task.TaskQueue;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Slf4j
public class LogAppender {
    public static final LogAppender INSTANCE = new LogAppender();

    protected TaskQueue<LogContentDTO> logQueue;

    protected LogTaskConsumer logTaskConsumer;

    private LogAppender() {
        logQueue = new TaskQueue<>(0L, 10240);
        logTaskConsumer = new LogTaskConsumer(
                0L,
                1,
                1,
                "DistributedJobScheduler-log-consumer",
                100,
                "DistributedJobScheduler-log-consumer-poll",
                logQueue
        );

        logTaskConsumer.start();
    }

    /**
     * Append content.
     *
     * @param logContentDTO logContentDTO
     */
    public void append(LogContentDTO logContentDTO) {
        try {
            logQueue.submit(logContentDTO);
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
