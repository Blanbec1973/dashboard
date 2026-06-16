package org.heyner.dashboard.domain.port.out;

import org.heyner.dashboard.domain.model.ScanRun;

/**
 * Port de persistance pour l'historique des scans.
 */
public interface ScanRunRepository {

    /**
     * Persiste un scan run.
     */
    ScanRun save(ScanRun scanRun);
}
