package org.heyner.dashboard.domain.port.out;

import org.heyner.dashboard.domain.model.PstStats;

import java.nio.file.Path;
import java.util.UUID;

/**
 * Port d'infrastructure pour lire une archive PST Outlook.
 */
public interface PstScannerPort {

    /**
     * Analyse l'archive PST et calcule ses statistiques.
     *
     * @param targetId identifiant métier de la cible
     * @param pstPath chemin du fichier PST
     * @return statistiques calculées
     */
    PstStats scan(UUID targetId, Path pstPath);
}
