package org.heyner.dashboard.domain.port.out;

import org.heyner.dashboard.domain.model.TargetSnapshot;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port de persistance pour les instantanés de résultats de scan.
 */
public interface SnapshotRepository {

    /**
     * Persiste un snapshot.
     */
    TargetSnapshot save(TargetSnapshot snapshot);

    /**
     * Retourne le dernier snapshot d'une cible.
     */
    Optional<TargetSnapshot> findLatestByTargetId(UUID targetId);

    /**
     * Retourne tous les derniers snapshots en ordre de cible.
     */
    List<TargetSnapshot> findLatestSnapshotsOrdered();
}
