package org.heyner.dashboard.domain.model;

import java.time.Instant;
import java.util.UUID;

/**
 * Vue de lecture destinée au tableau de bord.
 *
 * <p>Cette classe agrège la configuration d'une cible et ses dernières statistiques connues.</p>
 */
public class DashboardItem {

    private final UUID targetId;
    private final TargetType type;
    private final String displayName;
    private final String path;
    private final int displayOrder;
    private final boolean enabled;
    private final Instant lastScanAt;
    private final long totalSizeBytes;
    private final Long fileCount;
    private final Long directoryCount;
    private final Long messageCount;

    public DashboardItem(UUID targetId, TargetType type, String displayName, String path, int displayOrder,
                         boolean enabled, Instant lastScanAt, long totalSizeBytes,
                         Long fileCount, Long directoryCount, Long messageCount) {
        this.targetId = targetId;
        this.type = type;
        this.displayName = displayName;
        this.path = path;
        this.displayOrder = displayOrder;
        this.enabled = enabled;
        this.lastScanAt = lastScanAt;
        this.totalSizeBytes = totalSizeBytes;
        this.fileCount = fileCount;
        this.directoryCount = directoryCount;
        this.messageCount = messageCount;
    }

    public UUID getTargetId() { return targetId; }
    public TargetType getType() { return type; }
    public String getDisplayName() { return displayName; }
    public String getPath() { return path; }
    public int getDisplayOrder() { return displayOrder; }
    public boolean isEnabled() { return enabled; }
    public Instant getLastScanAt() { return lastScanAt; }
    public long getTotalSizeBytes() { return totalSizeBytes; }
    public Long getFileCount() { return fileCount; }
    public Long getDirectoryCount() { return directoryCount; }
    public Long getMessageCount() { return messageCount; }
}
