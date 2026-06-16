package org.heyner.dashboard.application.service;

import org.heyner.dashboard.domain.model.MonitoredTarget;
import org.heyner.dashboard.domain.model.TargetType;
import org.heyner.dashboard.domain.port.in.ManageTargetUseCase;
import org.heyner.dashboard.domain.port.out.TargetRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Service applicatif de gestion des cibles surveillées.
 */
public class TargetManagementService implements ManageTargetUseCase {

    private final TargetRepository targetRepository;

    /**
     * Construit le service de gestion.
     */
    public TargetManagementService(TargetRepository targetRepository) {
        this.targetRepository = targetRepository;
    }

    @Override
    public MonitoredTarget createTarget(TargetType type, String displayName, String path, int displayOrder, boolean enabled) {
        MonitoredTarget target = new MonitoredTarget(UUID.randomUUID(), type, displayName, path, displayOrder, enabled,
                Instant.now(), Instant.now());
        return targetRepository.save(target);
    }

    @Override
    public MonitoredTarget updateTarget(UUID id, TargetType type, String displayName, String path, int displayOrder, boolean enabled) {
        MonitoredTarget target = new MonitoredTarget(id, type, displayName, path, displayOrder, enabled,
                Instant.now(), Instant.now());
        return targetRepository.save(target);
    }

    @Override
    public MonitoredTarget setEnabled(UUID id, boolean enabled) {
        MonitoredTarget current = targetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cible introuvable: " + id));
        MonitoredTarget updated = new MonitoredTarget(current.getId(), current.getType(), current.getDisplayName(),
                current.getPath(), current.getDisplayOrder(), enabled, current.getCreatedAt(), Instant.now());
        return targetRepository.save(updated);
    }

    @Override
    public void deleteTarget(UUID id) {
        targetRepository.deleteById(id);
    }

    @Override
    public List<MonitoredTarget> listTargets() {
        return targetRepository.findAllOrdered();
    }

    @Override
    public Optional<MonitoredTarget> findTargetById(UUID id) {
        return targetRepository.findById(id);
    }
}
