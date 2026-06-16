package org.heyner.dashboard.domain.port.out;

import org.heyner.dashboard.domain.model.MonitoredTarget;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Port de persistance pour les cibles surveillées.
 */
public interface TargetRepository {

    /**
     * Sauvegarde une cible.
     */
    MonitoredTarget save(MonitoredTarget target);

    /**
     * Recherche une cible par identifiant.
     */
    Optional<MonitoredTarget> findById(UUID id);

    /**
     * Retourne les cibles ordonnées.
     */
    List<MonitoredTarget> findAllOrdered();

    /**
     * Supprime une cible par identifiant.
     */
    void deleteById(UUID id);
}
