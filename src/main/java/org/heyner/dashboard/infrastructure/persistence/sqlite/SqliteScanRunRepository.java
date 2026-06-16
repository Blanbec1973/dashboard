package org.heyner.dashboard.infrastructure.persistence.sqlite;

import org.heyner.dashboard.domain.model.ScanRun;
import org.heyner.dashboard.domain.port.out.ScanRunRepository;
import org.springframework.stereotype.Repository;

/**
 * Implémentation SQLite du repository des exécutions de scan.
 */
@Repository
public class SqliteScanRunRepository implements ScanRunRepository {

    @Override
    public ScanRun save(ScanRun scanRun) {
        throw new UnsupportedOperationException("À implémenter");
    }
}
