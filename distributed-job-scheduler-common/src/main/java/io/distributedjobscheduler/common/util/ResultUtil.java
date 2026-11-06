package io.distributedjobscheduler.common.util;

import io.distributedjobscheduler.common.constant.StatusEnum;
import io.distributedjobscheduler.common.response.Result;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.0
 */
public class ResultUtil {
    public static Boolean isSuccess(Result<?> result) {
        return StatusEnum.SUCCESS.getStatus().equals(result.getStatus());
    }
}
