package io.distributedjobscheduler.worker.constant;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
public class WorkerConstant {

    /**
     * Server address
     */
    public static final String SERVER_ADDRESS = "distributedjobscheduler.server.address";

    /**
     * Server address uri
     */
    public static final String SERVER_ADDRESS_URI = "/openapi/cluster/online";

    /**
     * Worker config key.
     */
    public static final String WORKER_HOST = "distributedjobscheduler.worker.host";
    public static final String WORKER_PORT = "distributedjobscheduler.worker.port";
    public static final String WORKER_APP_NAME = "distributedjobscheduler.worker.app.name";
    public static final String WORKER_DELAY_ENABLE = "distributedjobscheduler.worker.delay.enable";
    public static final String WORKER_AKKA_CONFIG_FILE = "distributedjobscheduler.worker.akka.file";
    public static final String WORKER_HEARTBEAT_ACTOR_NUM = "distributedjobscheduler.worker.heartbeat.actor.num";
    public static final String WORKER_TASK_MASTER_ACTOR_NUM = "distributedjobscheduler.worker.task.master.actor.num";
    public static final String WORKER_TASK_CONTAINER_ACTOR_NUM = "distributedjobscheduler.worker.task.container.actor.num";
    public static final String WORKER_TASK_MAP_BATCH_SIZE = "distributedjobscheduler.worker.task.map.batch.size";
    public static final String WORKER_TASK_PERSISTENT_ACTOR_NUM = "distributedjobscheduler.worker.persistent.actor.num";
    public static final String WORKER_HEARTBEAT_INTERVAL = "distributedjobscheduler.worker.heartbeat.interval";
    public static final String WORKER_HEARTBEAT_FAIL_TIMES = "distributedjobscheduler.worker.heartbeat.fail.times";
    public static final String WORKER_DELAY_MASTER_ACTOR_NUM = "distributedjobscheduler.worker.delay.master.actor.num";
    public static final String WORKER_DELAY_PULL_SIZE = "distributedjobscheduler.worker.delay.pull.size";
    public static final String WORKER_DELAY_PULL_SLEEP = "distributedjobscheduler.worker.delay.pull.sleep";
    public static final String WORKER_DELAY_PULL_STEP = "distributedjobscheduler.worker.delay.pull.step";
    public static final String WORKER_DELAY_TIMEOUT = "distributedjobscheduler.worker.delay.timeout";


    /**
     * Default worker config.
     */
    public static final String DEFAULT_WORKER_AKKA_CONFIG_FILENAME = "akka-worker.conf";
    public static final Integer DEFAULT_SERVER_ADDRESS_PORT = 8080;
    public static final Integer DEFAULT_WORKER_PORT = 25588;
    public static final Integer DEFAULT_WORKER_HEARTBEAT_ACTOR_NUM = 1;
    public static final Integer DEFAULT_WORKER_TASK_MASTER_ACTOR_NUM = 32;
    public static final Integer DEFAULT_WORKER_TASK_CONTAINER_ACTOR_NUM = 32;
    public static final Integer DEFAULT_WORKER_TASK_MAP_BATCH_SIZE = 128;
    public static final Integer DEFAULT_WORKER_PERSISTENT_ACTOR_NUM = 2;

    /**
     * Max 5 seconds
     */
    public static final Integer DEFAULT_WORKER_HEARTBEAT_INTERVAL = 5;
    public static final Integer DEFAULT_WORKER_HEARTBEAT_FAIL_TIMES = 2;
    public static final Integer DEFAULT_WORKER_DELAY_MASTER_ACTOR_NUM = 1;
    public static final Integer DEFAULT_WORKER_DELAY_PULL_SIZE = 8;
    public static final Long DEFAULT_WORKER_DELAY_PULL_SLEEP = 500L;
    public static final Long DEFAULT_WORKER_DELAY_PULL_STEP = 500L;
    public static final Long DEFAULT_WORKER_DELAY_TIMEOUT = 3000L;

    /**
     * Check worker retry times
     */
    public static final Integer CHECK_WORKER_RETRY_TIMES = 3;
}
