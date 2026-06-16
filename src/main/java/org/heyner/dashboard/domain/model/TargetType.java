package org.heyner.dashboard.domain.model;

/**
 * Type d'une cible surveillée.
 *
 * <p>Une cible peut être un dossier Windows scanné récursivement ou une archive PST Outlook.</p>
 */
public enum TargetType {
    WINDOWS_FOLDER,
    PST_ARCHIVE
}
