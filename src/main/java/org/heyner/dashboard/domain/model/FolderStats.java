package org.heyner.dashboard.domain.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Statistiques calculées pour un dossier Windows.
 *
 * <p>Les données sont le résultat d'un scan complet périodique.</p>
 */
public class FolderStats {

    private final UUID targetId;
    private final long totalSizeBytes;
    private final long fileCount;
    private final long directoryCount;

    /**
     * Construit les statistiques d'un dossier.
     *
     * @param targetId identifiant de la cible
     * @param totalSizeBytes taille totale en octets
     * @param fileCount nombre total de fichiers
     * @param directoryCount nombre total de sous-dossiers
     */
    public FolderStats(UUID targetId, long totalSizeBytes, long fileCount, long directoryCount) {
        this.targetId = Objects.requireNonNull(targetId, "targetId");
        this.totalSizeBytes = totalSizeBytes;
        this.fileCount = fileCount;
        this.directoryCount = directoryCount;
    }

    public UUID getTargetId() { return targetId; }
    public long getTotalSizeBytes() { return totalSizeBytes; }
    public long getFileCount() { return fileCount; }
    public long getDirectoryCount() { return directoryCount; }
}
