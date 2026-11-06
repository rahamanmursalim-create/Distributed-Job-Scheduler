package io.distributedjobscheduler.worker.init;

import com.fasterxml.jackson.core.type.TypeReference;
import io.distributedjobscheduler.common.response.Result;
import io.distributedjobscheduler.common.util.IpUtil;
import io.distributedjobscheduler.common.util.ResultUtil;
import io.distributedjobscheduler.worker.config.DistributedJobSchedulerConfig;
import io.distributedjobscheduler.worker.constant.WorkerConstant;
import io.distributedjobscheduler.worker.dto.ClusterDTO;
import io.distributedjobscheduler.worker.util.HttpClientUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.config.RequestConfig;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
@Slf4j
@Getter
public class WorkerConfig {

    /**
     * Server split size
     */
    private static final Integer SERVER_SPLIT_SIZE = 2;

    /**
     * Worker host name.
     */
    private static String workerHost;

    /**
     * Worker port.
     */
    private static Integer workerPort;

    /**
     * Worker address.
     */
    private static String workerAddress;

    /**
     * Register app name.
     */
    private static String appName;

    /**
     * Delay enable.
     */
    private static Boolean delayEnable;

    /**
     * Server address
     */
    private static String serverAddress;

    /**
     * Server host.
     */
    private static volatile String serverHost;

    /**
     * Server port
     */
    private static volatile Integer serverPort;

    /**
     * Init
     */
    public void init() throws UnknownHostException {
        // App name.
        appName = DistributedJobSchedulerConfig.getString(WorkerConstant.WORKER_APP_NAME);
        if (Objects.isNull(appName)) {
            throw new RuntimeException(String.format("%s can not be null!", WorkerConstant.WORKER_APP_NAME));
        }

        workerHost = DistributedJobSchedulerConfig.getString(WorkerConstant.WORKER_HOST, IpUtil.getLocalAddress());
        workerPort = DistributedJobSchedulerConfig.getInteger(WorkerConstant.WORKER_PORT, WorkerConstant.DEFAULT_WORKER_PORT);
        workerAddress = String.format("%s:%d", workerHost, workerPort);
        delayEnable = DistributedJobSchedulerConfig.getBoolean(WorkerConstant.WORKER_DELAY_ENABLE, false);

        // Server address
        String defaultAddress = String.format("%s://%s:%d", "http", IpUtil.getLocalAddress(), WorkerConstant.DEFAULT_SERVER_ADDRESS_PORT);
        serverAddress = DistributedJobSchedulerConfig.getString(WorkerConstant.SERVER_ADDRESS, defaultAddress);

        // Refresh server
        refreshServer();
    }

    /**
     * Refresh server
     */
    public static synchronized void refreshServer() {
        // Request config Mills
        RequestConfig.Builder config = RequestConfig.custom();
        config.setConnectTimeout(3000);
        config.setSocketTimeout(3000);
        config.setConnectionRequestTimeout(3000);

        try {
            // Request
            String url = String.format("%s%s", serverAddress, WorkerConstant.SERVER_ADDRESS_URI);
            Result<ClusterDTO> result = HttpClientUtil.get(config.build(), url, new TypeReference<Result<ClusterDTO>>() {
            });

            // Result check
            if (!ResultUtil.isSuccess(result)) {
                throw new RuntimeException("Refresh server failed! result=" + result);
            }

            // No servers
            List<String> servers = result.getData().getServers();
            if (CollectionUtils.isEmpty(servers)) {
                throw new RuntimeException("No servers are available!");
            }

            // Select one server
            Random random = new Random();
            int n = random.nextInt(servers.size());
            String selectServer = servers.get(n);
            String[] serverSplit = selectServer.split(":");
            if (!SERVER_SPLIT_SIZE.equals(serverSplit.length)) {
                throw new RuntimeException("Select server format error!server=" + selectServer);
            }

            // Refresh
            serverHost = serverSplit[0];
            serverPort = Integer.valueOf(serverSplit[1]);
            log.info("Refresh server success! server={} port={}", serverHost, serverPort);
        } catch (Throwable e) {
            log.error("Refresh server failed!", e);
        }
    }

    public static String getWorkerHost() {
        return workerHost;
    }

    public static Integer getWorkerPort() {
        return workerPort;
    }

    public static String getWorkerAddress() {
        return workerAddress;
    }

    public static String getAppName() {
        return appName;
    }

    public static Boolean getDelayEnable() {
        return delayEnable;
    }

    public static String getServerHost() {
        return serverHost;
    }

    public static Integer getServerPort() {
        return serverPort;
    }
}
