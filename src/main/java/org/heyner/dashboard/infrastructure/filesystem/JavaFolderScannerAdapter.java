package org.heyner.dashboard.infrastructure.filesystem;

import org.heyner.dashboard.domain.model.FolderStats;
import org.heyner.dashboard.domain.port.out.FolderScannerPort;

import java.nio.file.Path;
import java.util.UUID;

/**
 * Adaptateur technique pour scanner récursivement un dossier Windows.
 *
 * <p>La version finale utilisera Files.walkFileTree et les compteurs nécessaires.</p>
 */
public class JavaFolderScannerAdapter implements FolderScannerPort {

    @Override
    public FolderStats scan(UUID targetId, Path folderPath) {
        throw new UnsupportedOperationException("À implémenter: scan récursif du dossier " + folderPath);
    }
}
