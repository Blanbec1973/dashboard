package org.heyner.dashboard.application.service;

import lombok.extern.slf4j.Slf4j;
import org.heyner.dashboard.domain.model.*;
import org.heyner.dashboard.domain.port.in.ScanAllTargetsUseCase;
import org.heyner.dashboard.domain.port.out.*;

import java.nio.file.Path;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * Implémentation applicative du scan complet périodique.
 *
 * <p>Cette classe orchestre la lecture des cibles actives, le lancement du scan technique
 * adapté au type de cible et la persistance des résultats.</p>
 */
@Slf4j
public class ScanAllTargetsService implements ScanAllTargetsUseCase {

    private final TargetRepository targetRepository;
    private final ScanRunRepository scanRunRepository;
    private final SnapshotRepository snapshotRepository;
    private final FolderScannerPort folderScannerPort;
    private final PstScannerPort pstScannerPort;

    /**
     * Construit le service de scan.
     */
    public ScanAllTargetsService(TargetRepository targetRepository,
                                 ScanRunRepository scanRunRepository,
                                 SnapshotRepository snapshotRepository,
                                 FolderScannerPort folderScannerPort,
                                 PstScannerPort pstScannerPort) {
        this.targetRepository = targetRepository;
        this.scanRunRepository = scanRunRepository;
        this.snapshotRepository = snapshotRepository;
        this.folderScannerPort = folderScannerPort;
        this.pstScannerPort = pstScannerPort;
    }

    @Override
    public ScanRun scanAllTargets() {
        log.debug("scanAllTargets");
        ScanRun scanRun = new ScanRun(UUID.randomUUID(), Instant.now(), null, ScanStatus.SUCCESS, null);
        scanRunRepository.save(scanRun);

        List<MonitoredTarget> targets = targetRepository.findAllOrdered();
        for (MonitoredTarget target : targets) {
            if (!target.isEnabled()) {
                continue;
            }
            scanTarget(target, scanRun);
        }

        return scanRun;
    }

    /**
     * Scanne une cible unique selon son type.
     */
    protected void scanTarget(MonitoredTarget target, ScanRun scanRun) {
        Path path = Path.of(target.getPath());

        switch (target.getType()) {
            case WINDOWS_FOLDER -> {
                FolderStats stats = folderScannerPort.scan(target.getId(), path);
                snapshotRepository.save(new TargetSnapshot(
                        UUID.randomUUID(),
                        target.getId(),
                        scanRun.getId(),
                        Instant.now(),
                        stats.getTotalSizeBytes(),
                        stats.getFileCount(),
                        stats.getDirectoryCount(),
                        null
                ));
            }
            case PST_ARCHIVE -> {
                PstStats stats = pstScannerPort.scan(target.getId(), path);
                snapshotRepository.save(new TargetSnapshot(
                        UUID.randomUUID(),
                        target.getId(),
                        scanRun.getId(),
                        Instant.now(),
                        stats.getTotalSizeBytes(),
                        null,
                        null,
                        stats.getMessageCount()
                ));
            }
        }
    }
}
