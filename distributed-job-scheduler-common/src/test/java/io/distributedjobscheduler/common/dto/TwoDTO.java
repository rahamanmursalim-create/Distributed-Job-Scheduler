package io.distributedjobscheduler.common.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class TwoDTO {
    private String name;
    private Integer id;
    private ChildDTO childDTO;
    private List<ChildDTO> childDTOList;
}
