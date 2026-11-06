package io.distributedjobscheduler.worker.delay;

import io.distributedjobscheduler.common.request.WorkerDelayTopicPullRequest;
import io.distributedjobscheduler.common.response.ServerDelayTopicPullResponse;
import io.distributedjobscheduler.common.util.DateUtil;
import io.distributedjobscheduler.common.util.FutureUtil;
import io.distributedjobscheduler.worker.config.DistributedJobSchedulerConfig;
import io.distributedjobscheduler.worker.constant.WorkerConstant;
import io.distributedjobscheduler.worker.dao.DelayDAO;
import io.distributedjobscheduler.worker.entity.Delay;
import io.distributedjobscheduler.worker.util.WorkerUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Slf4j
public class DelayTaskMaster {
    private ExecutorService executorService;

    /**
     * Init
     */
    public void init() {
        executorService = new ThreadPoolExecutor(
                1,
                1,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1),
                r -> new Thread(r, "DistributedJobScheduler-delay-master")
        );

        this.executorService.submit(new DelayTaskMasterExecutor());
    }

    /**
     * Refresh delay task master.
     */
    public void refresh() {
        String appName = DistributedJobSchedulerConfig.getString(WorkerConstant.WORKER_APP_NAME);
        WorkerDelayTopicPullRequest pullRequest = new WorkerDelayTopicPullRequest();
        pullRequest.setAppName(appName);

        ServerDelayTopicPullResponse response = FutureUtil.mustAsk(WorkerUtil.getServerDelayInstanceActor(), pullRequest, ServerDelayTopicPullResponse.class, 3000L);
        Long timestamp = DateUtil.timestamp();

        // Delay topic
        List<Delay> saveTopicList = response.getTopicList().stream().map(t -> {
            Delay delay = new Delay();
            delay.setId(t.getId());
            delay.setTopic(t.getTopic());
            delay.setCreateTime(timestamp);
            delay.setUpdateTime(timestamp);
            delay.setPullSize(t.getConcurrency());
            delay.setPullTime(timestamp);
            return delay;
        }).collect(Collectors.toList());

        // Delete old topic list.
        Integer deleteRows = DelayDAO.INSTANCE.deleteAll();
        log.info("Delay task master refresh! deleteRows={}", deleteRows);

        DelayDAO.INSTANCE.batchSave(saveTopicList);
        log.info("Delay task master refresh! topicList={}", saveTopicList.stream().map(Delay::getTopic).collect(Collectors.toList()));
    }

    /**
     * Stop
     */
    public void stop() {
        this.executorService.shutdown();
    }
}
