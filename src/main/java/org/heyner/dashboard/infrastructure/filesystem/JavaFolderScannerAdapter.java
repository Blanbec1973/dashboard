package org.heyner.dashboard.infrastructure.filesystem;

import org.heyner.dashboard.domain.model.FolderStats;
import org.heyner.dashboard.domain.port.out.FolderScannerPort;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.UUID;

/**
 * Adaptateur technique pour scanner récursivement un dossier Windows.
 *
 * <p>Utilise Files.walkFileTree pour parcourir l'arborescence complète.</p>
 */
public class JavaFolderScannerAdapter implements FolderScannerPort {

    @Override
    public FolderStats scan(UUID targetId, Path folderPath) {
        var counters = new FileCounters();

        try {
            Files.walkFileTree(
                folderPath,
                EnumSet.noneOf(FileVisitOption.class),
                Integer.MAX_VALUE,
                new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                        counters.addFile(attrs.size());
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                        if (!dir.equals(folderPath)) {
                            counters.addDirectory();
                        }
                        return FileVisitResult.CONTINUE;
                    }

                    @Override
                    public FileVisitResult visitFileFailed(Path file, IOException exc) {
                        // Ignore les erreurs d'accès et continue le scan
                        return FileVisitResult.CONTINUE;
                    }
                }
            );
        } catch (IOException e) {
            throw new RuntimeException("Erreur lors du scan du dossier: " + folderPath, e);
        }

        return new FolderStats(targetId, counters.getTotalSize(), counters.getFileCount(), counters.getDirectoryCount());
    }

    /**
     * Classe interne pour comptabiliser les statistiques du scan.
     */
    private static class FileCounters {
        private long totalSize = 0;
        private long fileCount = 0;
        private long directoryCount = 0;

        void addFile(long size) {
            fileCount++;
            totalSize += size;
        }

        void addDirectory() {
            directoryCount++;
        }

        long getTotalSize() {
            return totalSize;
        }

        long getFileCount() {
            return fileCount;
        }

        long getDirectoryCount() {
            return directoryCount;
        }
    }
}
