package org.heyner.dashboard.domain.port.in;

import org.heyner.dashboard.domain.model.ScanRun;

/**
 * Cas d'utilisation de scan complet.
 *
 * <p>Lance un scan périodique de toutes les cibles actives et persiste le résultat.</p>
 */
public interface ScanAllTargetsUseCase {

    /**
     * Exécute le scan complet de toutes les cibles actives.
     *
     * @return le scan run créé pour cette exécution
     */
    ScanRun scanAllTargets();
}
