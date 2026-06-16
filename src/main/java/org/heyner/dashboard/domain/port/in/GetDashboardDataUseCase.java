package org.heyner.dashboard.domain.port.in;

import org.heyner.dashboard.domain.model.DashboardItem;

import java.util.List;

/**
 * Cas d'utilisation de lecture des données du tableau de bord.
 */
public interface GetDashboardDataUseCase {

    /**
     * Retourne les éléments du tableau de bord déjà ordonnés.
     *
     * @return liste d'éléments à afficher
     */
    List<DashboardItem> getDashboardData();
}
