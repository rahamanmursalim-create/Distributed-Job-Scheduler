package io.distributedjobscheduler.common.dto;

import lombok.Data;
import io.distributedjobscheduler.common.constant.ShellTypeEnum;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.4
 */
@Data
public class ShellProcessorDTO {
    /**
     * Content
     */
    private String content;

    /**
     * Type
     *
     * @see ShellTypeEnum
     */
    private String type;
}
