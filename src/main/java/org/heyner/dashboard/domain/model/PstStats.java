package org.heyner.dashboard.domain.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Statistiques calculées pour une archive PST Outlook.
 *
 * <p>Pour la V1, seules la taille du fichier et le nombre total de messages sont conservés.</p>
 */
public class PstStats {

    private final UUID targetId;
    private final long totalSizeBytes;
    private final long messageCount;

    /**
     * Construit les statistiques d'une archive PST.
     *
     * @param targetId identifiant de la cible
     * @param totalSizeBytes taille du fichier PST en octets
     * @param messageCount nombre total de messages trouvés
     */
    public PstStats(UUID targetId, long totalSizeBytes, long messageCount) {
        this.targetId = Objects.requireNonNull(targetId, "targetId");
        this.totalSizeBytes = totalSizeBytes;
        this.messageCount = messageCount;
    }

    public UUID getTargetId() { return targetId; }
    public long getTotalSizeBytes() { return totalSizeBytes; }
    public long getMessageCount() { return messageCount; }
}
