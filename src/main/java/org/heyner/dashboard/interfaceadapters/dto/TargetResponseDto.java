package org.heyner.dashboard.interfaceadapters.dto;

import org.heyner.dashboard.domain.model.TargetType;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO de sortie pour une cible.
 */
public class TargetResponseDto {

    private UUID id;
    private TargetType type;
    private String displayName;
    private String path;
    private int displayOrder;
    private boolean enabled;
    private Instant createdAt;
    private Instant updatedAt;

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
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
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}
