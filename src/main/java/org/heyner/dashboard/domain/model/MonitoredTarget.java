package org.heyner.dashboard.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Représente une cible surveillée par le tableau de bord.
 *
 * <p>Une cible correspond soit à un dossier Windows, soit à un fichier PST.
 * Le domaine ne connaît aucun détail technique de stockage ou de scan.</p>
 */
public class MonitoredTarget {

    private final UUID id;
    private final TargetType type;
    private final String displayName;
    private final String path;
    private final int displayOrder;
    private final boolean enabled;
    private final Instant createdAt;
    private final Instant updatedAt;

    /**
     * Construit une cible surveillée.
     *
     * @param id identifiant unique
     * @param type type de cible
     * @param displayName nom affiché dans le dashboard
     * @param path chemin complet du dossier ou du fichier PST
     * @param displayOrder ordre d'affichage
     * @param enabled vrai si la cible est active
     * @param createdAt date de création
     * @param updatedAt date de dernière mise à jour
     */
    public MonitoredTarget(UUID id, TargetType type, String displayName, String path, int displayOrder,
                           boolean enabled, Instant createdAt, Instant updatedAt) {
        this.id = Objects.requireNonNull(id, "id");
        this.type = Objects.requireNonNull(type, "type");
        this.displayName = Objects.requireNonNull(displayName, "displayName");
        this.path = Objects.requireNonNull(path, "path");
        this.displayOrder = displayOrder;
        this.enabled = enabled;
        this.createdAt = Objects.requireNonNull(createdAt, "createdAt");
        this.updatedAt = Objects.requireNonNull(updatedAt, "updatedAt");
    }

    public UUID getId() { return id; }
    public TargetType getType() { return type; }
    public String getDisplayName() { return displayName; }
    public String getPath() { return path; }
    public int getDisplayOrder() { return displayOrder; }
    public boolean isEnabled() { return enabled; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
