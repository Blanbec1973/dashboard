package org.heyner.dashboard.domain.model;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * Représente une exécution de scan périodique.
 *
 * <p>Cette entité permet d'historiser les passages du scheduler et de diagnostiquer les erreurs.</p>
 */
public class ScanRun {

    private final UUID id;
    private final Instant startedAt;
    private final Instant finishedAt;
    private final ScanStatus status;
    private final String errorMessage;

    /**
     * Construit un scan run.
     *
     * @param id identifiant unique
     * @param startedAt début du scan
     * @param finishedAt fin du scan, ou null si en cours
     * @param status statut du scan
     * @param errorMessage message d'erreur éventuel
     */
    public ScanRun(UUID id, Instant startedAt, Instant finishedAt, ScanStatus status, String errorMessage) {
        this.id = Objects.requireNonNull(id, "id");
        this.startedAt = Objects.requireNonNull(startedAt, "startedAt");
        this.finishedAt = finishedAt;
        this.status = Objects.requireNonNull(status, "status");
        this.errorMessage = errorMessage;
    }

    public UUID getId() { return id; }
    public Instant getStartedAt() { return startedAt; }
    public Instant getFinishedAt() { return finishedAt; }
    public ScanStatus getStatus() { return status; }
    public String getErrorMessage() { return errorMessage; }
}
