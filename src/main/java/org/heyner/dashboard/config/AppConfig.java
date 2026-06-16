package org.heyner.dashboard.config;

import org.heyner.dashboard.application.service.DashboardQueryService;
import org.heyner.dashboard.application.service.ScanAllTargetsService;
import org.heyner.dashboard.application.service.TargetManagementService;
import org.heyner.dashboard.domain.port.in.GetDashboardDataUseCase;
import org.heyner.dashboard.domain.port.in.ManageTargetUseCase;
import org.heyner.dashboard.domain.port.in.ScanAllTargetsUseCase;
import org.heyner.dashboard.domain.port.out.*;
import org.heyner.dashboard.infrastructure.filesystem.JavaFolderScannerAdapter;
import org.heyner.dashboard.infrastructure.outlook.PstScannerAdapter;
import org.heyner.dashboard.infrastructure.persistence.sqlite.SqliteScanRunRepository;
import org.heyner.dashboard.infrastructure.persistence.sqlite.SqliteSnapshotRepository;
import org.heyner.dashboard.infrastructure.persistence.sqlite.SqliteTargetRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration Spring de l'application.
 *
 * <p>Cette classe relie les ports du domaine à leurs implémentations techniques.</p>
 */
@Configuration
public class AppConfig {

    @Bean
    public TargetRepository targetRepository() {
        return new SqliteTargetRepository();
    }

    @Bean
    public ScanRunRepository scanRunRepository() {
        return new SqliteScanRunRepository();
    }

    @Bean
    public SnapshotRepository snapshotRepository() {
        return new SqliteSnapshotRepository();
    }

    @Bean
    public FolderScannerPort folderScannerPort() {
        return new JavaFolderScannerAdapter();
    }

    @Bean
    public PstScannerPort pstScannerPort() {
        return new PstScannerAdapter();
    }

    @Bean
    public ScanAllTargetsUseCase scanAllTargetsUseCase(TargetRepository targetRepository,
                                                       ScanRunRepository scanRunRepository,
                                                       SnapshotRepository snapshotRepository,
                                                       FolderScannerPort folderScannerPort,
                                                       PstScannerPort pstScannerPort) {
        return new ScanAllTargetsService(targetRepository, scanRunRepository, snapshotRepository,
                folderScannerPort, pstScannerPort);
    }

    @Bean
    public GetDashboardDataUseCase getDashboardDataUseCase(TargetRepository targetRepository,
                                                           SnapshotRepository snapshotRepository) {
        return new DashboardQueryService(targetRepository, snapshotRepository);
    }

    @Bean
    public ManageTargetUseCase manageTargetUseCase(TargetRepository targetRepository) {
        return new TargetManagementService(targetRepository);
    }
}
