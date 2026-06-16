package org.heyner.dashboard.domain.port.in;

import org.heyner.dashboard.domain.model.MonitoredTarget;
import org.heyner.dashboard.domain.model.TargetType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Cas d'utilisation de gestion des cibles surveillées.
 */
public interface ManageTargetUseCase {

    /**
     * Crée une nouvelle cible surveillée.
     *
     * @param type type de cible
     * @param displayName nom affiché
     * @param path chemin complet
     * @param displayOrder ordre d'affichage
     * @param enabled état initial
     * @return cible créée
     */
    MonitoredTarget createTarget(TargetType type, String displayName, String path, int displayOrder, boolean enabled);

    /**
     * Met à jour une cible existante.
     */
    MonitoredTarget updateTarget(UUID id, TargetType type, String displayName, String path, int displayOrder, boolean enabled);

    /**
     * Active ou désactive une cible.
     */
    MonitoredTarget setEnabled(UUID id, boolean enabled);

    /**
     * Supprime une cible.
     */
    void deleteTarget(UUID id);

    /**
     * Liste toutes les cibles configurées.
     */
    List<MonitoredTarget> listTargets();

    /**
     * Recherche une cible par son identifiant.
     */
    Optional<MonitoredTarget> findTargetById(UUID id);
}
