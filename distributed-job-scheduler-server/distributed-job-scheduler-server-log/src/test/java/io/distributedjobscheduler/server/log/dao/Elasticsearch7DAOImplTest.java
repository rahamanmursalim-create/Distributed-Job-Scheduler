package io.distributedjobscheduler.server.log.dao;

import io.distributedjobscheduler.common.constant.LogFieldConstant;
import io.distributedjobscheduler.common.util.DateUtil;
import io.distributedjobscheduler.server.common.dto.PageDTO;
import io.distributedjobscheduler.server.log.autoconfigure.LogProperties;
import io.distributedjobscheduler.server.log.client.Elasticsearch7Client;
import io.distributedjobscheduler.server.log.dao.impl.Elasticsearch7DAOImpl;
import io.distributedjobscheduler.server.log.dto.ProcessorLogDTO;
import io.distributedjobscheduler.server.log.dto.ProcessorLogFieldDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.2
 */
public class Elasticsearch7DAOImplTest {
    private static Elasticsearch7DAOImpl elasticsearch7DAO;

    @Test()
    public void testBatchAdd() throws InterruptedException {
        List<ProcessorLogDTO> jobInstanceTaskLogs = new ArrayList<>();
        ProcessorLogDTO processorLogDTO = new ProcessorLogDTO();
        processorLogDTO.setTime(DateUtil.milliLongTime());
        processorLogDTO.setWorkerAddress("worker2Address");
        processorLogDTO.setTaskId("taskId");

        List<ProcessorLogFieldDTO> fieldList = new ArrayList<>();
        ProcessorLogFieldDTO processorLogFieldDTO = new ProcessorLogFieldDTO("name", "jobbId");
        ProcessorLogFieldDTO processorLogFieldDTO2 = new ProcessorLogFieldDTO(LogFieldConstant.MESSAGE, "elasticsearch7 test");
        fieldList.add(processorLogFieldDTO);
        fieldList.add(processorLogFieldDTO2);
        processorLogDTO.setFields(fieldList);
        jobInstanceTaskLogs.add(processorLogDTO);

        elasticsearch7DAO.batchAdd(jobInstanceTaskLogs);

        Thread.sleep(5000L);
    }

    @Test
    public void testQueryByScroll() throws Exception {
        List<ProcessorLogDTO> processorLogDTOList = elasticsearch7DAO.queryByScroll("taskId", 0L, 3);
        Assertions.assertNotNull(processorLogDTOList);
    }

    @Test
    public void testQueryByPageSize() throws Exception {
        PageDTO<ProcessorLogDTO> pageDTO = elasticsearch7DAO.queryByPageSize("taskId", "test", 1, 3);
        Assertions.assertNotNull(pageDTO);
    }

    @Test
    public void testDeleteByLastTime() throws Exception {
        elasticsearch7DAO.deleteByLastTime(DateUtil.timestamp());
    }

    @Test
    public void testDoCreateIndex() {
        elasticsearch7DAO.doCreateIndex("distributedjobscheduler_test");
    }

    @BeforeAll
    public static void CreateElasticsearch7() throws Exception {
        LogProperties.Elasticsearch7Properties elasticsearch7Properties = new LogProperties.Elasticsearch7Properties();
        elasticsearch7Properties.setClusterNodes("localhost:9200");
        elasticsearch7Properties.setProtocol("http");
        elasticsearch7Properties.setSocketTimeout(3000);
        elasticsearch7Properties.setConnectTimeout(3000);
        elasticsearch7Properties.setResponseTimeout(3000);
        elasticsearch7Properties.setBufferLimit(104857600);

        Elasticsearch7Client elasticsearch7Client = new Elasticsearch7Client(elasticsearch7Properties);
        elasticsearch7Client.afterPropertiesSet();
        elasticsearch7DAO = new Elasticsearch7DAOImpl(elasticsearch7Client, elasticsearch7Properties);
    }
}
