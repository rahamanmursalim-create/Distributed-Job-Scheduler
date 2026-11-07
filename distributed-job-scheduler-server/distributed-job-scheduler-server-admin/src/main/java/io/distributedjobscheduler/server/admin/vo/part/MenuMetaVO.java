package io.distributedjobscheduler.server.admin.vo.part;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @author Distributed Job Scheduler
 * @since 1.0.0
 */
@Data
public class MenuMetaVO {
    @Schema(description = "I18n title name")
    private String title;

    @Schema(description = "Link")
    private String isLink;

    @Schema(description = "Hide")
    private Boolean isHide;

    @Schema(description = "Keep alive")
    private Boolean isKeepAlive;

    @Schema(description = "Affix")
    private Boolean isAffix;

    @Schema(description = "Iframe")
    private Boolean isIframe;

    @Schema(description = "Roles")
    private List<String> roles;

    @Schema(description = "icon name")
    private String icon;
}
