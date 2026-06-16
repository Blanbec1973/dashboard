package org.heyner.dashboard.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Instantané des statistiques calculées pour une cible.
 *
 * <p>Une ligne de snapshot correspond au dernier résultat connu pour une cible donnée et un scan donné.</p>
 */
public class TargetSnapshot {

    private final UUID id;
    private final UUID targetId;
    private final UUID scanRunId;
    private final Instant scannedAt;
    private final Long totalSizeBytes;
    private final Long fileCount;
    private final Long directoryCount;
    private final Long messageCount;

    /**
     * Construit un snapshot de cible.
     *
     * @param id identifiant unique
     * @param targetId identifiant de la cible
     * @param scanRunId identifiant de l'exécution de scan
     * @param scannedAt date de calcul
     * @param totalSizeBytes taille totale en octets
     * @param fileCount nombre de fichiers, ou null pour un PST
     * @param directoryCount nombre de sous-dossiers, ou null pour un PST
     * @param messageCount nombre de messages, ou null pour un dossier
     */
    public TargetSnapshot(UUID id, UUID targetId, UUID scanRunId, Instant scannedAt, Long totalSizeBytes,
                          Long fileCount, Long directoryCount, Long messageCount) {
        this.id = Objects.requireNonNull(id, "id");
        this.targetId = Objects.requireNonNull(targetId, "targetId");
        this.scanRunId = Objects.requireNonNull(scanRunId, "scanRunId");
        this.scannedAt = Objects.requireNonNull(scannedAt, "scannedAt");
        this.totalSizeBytes = Objects.requireNonNull(totalSizeBytes, "totalSizeBytes");
        this.fileCount = fileCount;
        this.directoryCount = directoryCount;
        this.messageCount = messageCount;
    }

    public UUID getId() { return id; }
    public UUID getTargetId() { return targetId; }
    public UUID getScanRunId() { return scanRunId; }
    public Instant getScannedAt() { return scannedAt; }
    public Long getTotalSizeBytes() { return totalSizeBytes; }
    public Long getFileCount() { return fileCount; }
    public Long getDirectoryCount() { return directoryCount; }
    public Long getMessageCount() { return messageCount; }
}
