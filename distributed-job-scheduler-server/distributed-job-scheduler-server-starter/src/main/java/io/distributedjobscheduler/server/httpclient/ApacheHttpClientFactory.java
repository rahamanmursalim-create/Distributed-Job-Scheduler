package io.distributedjobscheduler.server.httpclient;

import org.apache.http.impl.client.HttpClientBuilder;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.6
 */
public interface ApacheHttpClientFactory {
    /**
     * Create builder
     *
     * @return HttpClientBuilder
     */
    HttpClientBuilder createBuilder();
}
