package org.heyner.dashboard.application.service;

import org.heyner.dashboard.domain.model.DashboardItem;
import org.heyner.dashboard.domain.port.in.GetDashboardDataUseCase;
import org.heyner.dashboard.domain.port.out.SnapshotRepository;
import org.heyner.dashboard.domain.port.out.TargetRepository;

import java.util.List;

/**
 * Service applicatif de lecture des données du tableau de bord.
 */
public class DashboardQueryService implements GetDashboardDataUseCase {

    private final TargetRepository targetRepository;
    private final SnapshotRepository snapshotRepository;

    /**
     * Construit le service de lecture.
     */
    public DashboardQueryService(TargetRepository targetRepository, SnapshotRepository snapshotRepository) {
        this.targetRepository = targetRepository;
        this.snapshotRepository = snapshotRepository;
    }

    @Override
    public List<DashboardItem> getDashboardData() {
        return List.of();
    }
}
