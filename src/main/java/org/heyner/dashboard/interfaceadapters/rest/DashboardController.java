package org.heyner.dashboard.interfaceadapters.rest;

import org.heyner.dashboard.domain.model.DashboardItem;
import org.heyner.dashboard.domain.port.in.GetDashboardDataUseCase;
import org.heyner.dashboard.interfaceadapters.dto.DashboardItemDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Contrôleur REST exposant les données du tableau de bord.
 */
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    private final GetDashboardDataUseCase getDashboardDataUseCase;

    /**
     * Construit le contrôleur.
     */
    public DashboardController(GetDashboardDataUseCase getDashboardDataUseCase) {
        this.getDashboardDataUseCase = getDashboardDataUseCase;
    }

    /**
     * Récupère les données du dashboard.
     */
    @GetMapping
    public List<DashboardItemDto> getDashboard() {
        return getDashboardDataUseCase.getDashboardData().stream().map(this::toDto).toList();
    }

    private DashboardItemDto toDto(DashboardItem item) {
        DashboardItemDto dto = new DashboardItemDto();
        dto.setTargetId(item.getTargetId());
        dto.setType(item.getType());
        dto.setDisplayName(item.getDisplayName());
        dto.setPath(item.getPath());
        dto.setDisplayOrder(item.getDisplayOrder());
        dto.setEnabled(item.isEnabled());
        dto.setLastScanAt(item.getLastScanAt());
        dto.setTotalSizeBytes(item.getTotalSizeBytes());
        dto.setFileCount(item.getFileCount());
        dto.setDirectoryCount(item.getDirectoryCount());
        dto.setMessageCount(item.getMessageCount());
        return dto;
    }
}
