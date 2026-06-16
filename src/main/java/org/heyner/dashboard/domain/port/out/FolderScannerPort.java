package org.heyner.dashboard.domain.port.out;

import org.heyner.dashboard.domain.model.FolderStats;

import java.nio.file.Path;
import java.util.UUID;

/**
 * Port d'infrastructure pour scanner récursivement un dossier Windows.
 */
public interface FolderScannerPort {

    /**
     * Analyse récursivement le dossier et calcule ses statistiques.
     *
     * @param targetId identifiant métier de la cible
     * @param folderPath chemin du dossier
     * @return statistiques calculées
     */
    FolderStats scan(UUID targetId, Path folderPath);
}
