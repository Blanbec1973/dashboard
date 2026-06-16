package org.heyner.dashboard.infrastructure.outlook;

import org.heyner.dashboard.domain.model.PstStats;
import org.heyner.dashboard.domain.port.out.PstScannerPort;

import java.nio.file.Path;
import java.util.UUID;

/**
 * Adaptateur technique pour lire une archive PST Outlook.
 *
 * <p>Cette implémentation s'appuiera sur une librairie de lecture PST dans une seconde étape.</p>
 */
public class PstScannerAdapter implements PstScannerPort {

    @Override
    public PstStats scan(UUID targetId, Path pstPath) {
        throw new UnsupportedOperationException("À implémenter: lecture PST " + pstPath);
    }
}
