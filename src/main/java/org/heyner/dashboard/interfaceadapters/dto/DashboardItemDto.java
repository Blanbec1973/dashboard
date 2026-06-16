package org.heyner.dashboard.interfaceadapters.dto;

import org.heyner.dashboard.domain.model.TargetType;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO de sortie pour le tableau de bord.
 */
public class DashboardItemDto {

    private UUID targetId;
    private TargetType type;
    private String displayName;
    private String path;
    private int displayOrder;
    private boolean enabled;
    private Instant lastScanAt;
    private long totalSizeBytes;
    private Long fileCount;
    private Long directoryCount;
    private Long messageCount;

    public UUID getTargetId() { return targetId; }
    public void setTargetId(UUID targetId) { this.targetId = targetId; }
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
    public Instant getLastScanAt() { return lastScanAt; }
    public void setLastScanAt(Instant lastScanAt) { this.lastScanAt = lastScanAt; }
    public long getTotalSizeBytes() { return totalSizeBytes; }
    public void setTotalSizeBytes(long totalSizeBytes) { this.totalSizeBytes = totalSizeBytes; }
    public Long getFileCount() { return fileCount; }
    public void setFileCount(Long fileCount) { this.fileCount = fileCount; }
    public Long getDirectoryCount() { return directoryCount; }
    public void setDirectoryCount(Long directoryCount) { this.directoryCount = directoryCount; }
    public Long getMessageCount() { return messageCount; }
    public void setMessageCount(Long messageCount) { this.messageCount = messageCount; }
}
