package org.heyner.dashboard.infrastructure.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.heyner.dashboard.domain.port.in.ScanAllTargetsUseCase;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Déclenche le scan complet à intervalle régulier.
 */
@Component
@Slf4j
public class PeriodicScanScheduler {

    private final ScanAllTargetsUseCase scanAllTargetsUseCase;

    /**
     * Construit le scheduler.
     */
    public PeriodicScanScheduler(ScanAllTargetsUseCase scanAllTargetsUseCase) {
        this.scanAllTargetsUseCase = scanAllTargetsUseCase;
        log.debug("Building PeriodicScanScheduler");
    }

    /**
     * Lance le scan périodique.
     *
     * <p>La fréquence est paramétrable via une expression cron dans la configuration Spring.</p>
     */
    @Scheduled(cron = "${app.scan.cron:0 */30 * * * *}")
    public void run() {
        scanAllTargetsUseCase.scanAllTargets();
    }
}
