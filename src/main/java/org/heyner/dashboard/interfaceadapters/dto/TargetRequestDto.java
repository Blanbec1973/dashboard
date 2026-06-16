package org.heyner.dashboard.interfaceadapters.dto;

import org.heyner.dashboard.domain.model.TargetType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * DTO d'entrée pour créer ou modifier une cible.
 */
public class TargetRequestDto {

    @NotNull
    private TargetType type;

    @NotBlank
    private String displayName;

    @NotBlank
    private String path;

    private int displayOrder;

    private boolean enabled;

    public TargetType getType() { return type; }
    public void setType(TargetType type) { this.type = type; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getPath() { return path; }
    public void setPath(String path) { this.path = path; }
    public int getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(int displayOrder) { this.displayOrder = displayOrder; }
    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}
