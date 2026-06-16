package org.heyner.dashboard.interfaceadapters.rest;

import org.heyner.dashboard.domain.model.MonitoredTarget;
import org.heyner.dashboard.domain.port.in.ManageTargetUseCase;
import org.heyner.dashboard.interfaceadapters.dto.TargetRequestDto;
import org.heyner.dashboard.interfaceadapters.dto.TargetResponseDto;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Contrôleur REST de gestion des cibles.
 */
@RestController
@RequestMapping("/api/targets")
public class TargetController {

    private final ManageTargetUseCase manageTargetUseCase;

    /**
     * Construit le contrôleur.
     */
    public TargetController(ManageTargetUseCase manageTargetUseCase) {
        this.manageTargetUseCase = manageTargetUseCase;
    }

    /**
     * Liste les cibles.
     */
    @GetMapping
    public List<TargetResponseDto> listTargets() {
        return manageTargetUseCase.listTargets().stream().map(this::toDto).toList();
    }

    /**
     * Crée une cible.
     */
    @PostMapping
    public TargetResponseDto createTarget(@Valid @RequestBody TargetRequestDto request) {
        return toDto(manageTargetUseCase.createTarget(
                request.getType(),
                request.getDisplayName(),
                request.getPath(),
                request.getDisplayOrder(),
                request.isEnabled()
        ));
    }

    /**
     * Modifie une cible.
     */
    @PutMapping("/{id}")
    public TargetResponseDto updateTarget(@PathVariable UUID id, @Valid @RequestBody TargetRequestDto request) {
        return toDto(manageTargetUseCase.updateTarget(
                id,
                request.getType(),
                request.getDisplayName(),
                request.getPath(),
                request.getDisplayOrder(),
                request.isEnabled()
        ));
    }

    /**
     * Active ou désactive une cible.
     */
    @PatchMapping("/{id}/enabled")
    public TargetResponseDto setEnabled(@PathVariable UUID id, @RequestParam boolean enabled) {
        return toDto(manageTargetUseCase.setEnabled(id, enabled));
    }

    /**
     * Supprime une cible.
     */
    @DeleteMapping("/{id}")
    public void deleteTarget(@PathVariable UUID id) {
        manageTargetUseCase.deleteTarget(id);
    }

    private TargetResponseDto toDto(MonitoredTarget target) {
        TargetResponseDto dto = new TargetResponseDto();
        dto.setId(target.getId());
        dto.setType(target.getType());
        dto.setDisplayName(target.getDisplayName());
        dto.setPath(target.getPath());
        dto.setDisplayOrder(target.getDisplayOrder());
        dto.setEnabled(target.isEnabled());
        dto.setCreatedAt(target.getCreatedAt());
        dto.setUpdatedAt(target.getUpdatedAt());
        return dto;
    }
}
