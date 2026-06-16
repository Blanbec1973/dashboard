package org.heyner.dashboard.infrastructure.filesystem;

import org.heyner.dashboard.domain.model.FolderStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests pour l'adaptateur JavaFolderScannerAdapter.
 */
class JavaFolderScannerAdapterTest {

    private JavaFolderScannerAdapter adapter;
    private UUID targetId;

    @BeforeEach
    void setUp() {
        adapter = new JavaFolderScannerAdapter();
        targetId = UUID.randomUUID();
    }

    @Test
    void scanEmptyFolder(@TempDir Path tempDir) {
        // Arrange & Act
        FolderStats stats = adapter.scan(targetId, tempDir);

        // Assert
        assertNotNull(stats);
        assertEquals(targetId, stats.getTargetId());
        assertEquals(0L, stats.getTotalSizeBytes());
        assertEquals(0L, stats.getFileCount());
        assertEquals(0L, stats.getDirectoryCount());
    }

    @Test
    void scanFolderWithSingleFile(@TempDir Path tempDir) throws IOException {
        // Arrange
        Path file = Files.createFile(tempDir.resolve("test.txt"));
        Files.write(file, "Hello World".getBytes());

        // Act
        FolderStats stats = adapter.scan(targetId, tempDir);

        // Assert
        assertNotNull(stats);
        assertEquals(targetId, stats.getTargetId());
        assertEquals(11L, stats.getTotalSizeBytes());
        assertEquals(1L, stats.getFileCount());
        assertEquals(0L, stats.getDirectoryCount());
    }

    @Test
    void scanFolderWithMultipleFiles(@TempDir Path tempDir) throws IOException {
        // Arrange
        Files.write(tempDir.resolve("file1.txt"), "Content1".getBytes());
        Files.write(tempDir.resolve("file2.txt"), "Content2Extra".getBytes());
        Files.write(tempDir.resolve("file3.txt"), "C".getBytes());

        // Act
        FolderStats stats = adapter.scan(targetId, tempDir);

        // Assert
        assertNotNull(stats);
        assertEquals(targetId, stats.getTargetId());
        assertEquals(8L + 13L + 1L, stats.getTotalSizeBytes());
        assertEquals(3L, stats.getFileCount());
        assertEquals(0L, stats.getDirectoryCount());
    }

    @Test
    void scanFolderWithSubdirectories(@TempDir Path tempDir) throws IOException {
        // Arrange
        Path subDir1 = Files.createDirectory(tempDir.resolve("subdir1"));
        Path subDir2 = Files.createDirectory(tempDir.resolve("subdir2"));
        Files.createDirectory(subDir1.resolve("nested"));

        Files.write(tempDir.resolve("root.txt"), "Root".getBytes());
        Files.write(subDir1.resolve("file1.txt"), "File1".getBytes());
        Files.write(subDir2.resolve("file2.txt"), "File2Data".getBytes());

        // Act
        FolderStats stats = adapter.scan(targetId, tempDir);

        // Assert
        assertNotNull(stats);
        assertEquals(targetId, stats.getTargetId());
        assertEquals(4L + 5L + 9L, stats.getTotalSizeBytes()); // Root + File1 + File2Data
        assertEquals(3L, stats.getFileCount()); // 3 fichiers
        assertEquals(3L, stats.getDirectoryCount()); // subdir1, subdir2, nested
    }

    @Test
    void scanFolderWithDeepNesting(@TempDir Path tempDir) throws IOException {
        // Arrange
        Path current = tempDir;
        for (int i = 0; i < 5; i++) {
            current = Files.createDirectory(current.resolve("level" + i));
            Files.write(current.resolve("file" + i + ".txt"), ("Content" + i).getBytes());
        }

        // Act
        FolderStats stats = adapter.scan(targetId, tempDir);

        // Assert
        assertNotNull(stats);
        assertEquals(targetId, stats.getTargetId());
        assertEquals(5L, stats.getFileCount());
        assertEquals(5L, stats.getDirectoryCount());
        assertTrue(stats.getTotalSizeBytes() > 0);
    }

    @Test
    void scanReturnsCorrectTargetId(@TempDir Path tempDir) {
        // Arrange
        UUID customTargetId = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

        // Act
        FolderStats stats = adapter.scan(customTargetId, tempDir);

        // Assert
        assertEquals(customTargetId, stats.getTargetId());
    }

    @Test
    void scanWithLargerFiles(@TempDir Path tempDir) throws IOException {
        // Arrange
        byte[] largeContent = new byte[1024]; // 1 KB
        Files.write(tempDir.resolve("large1.bin"), largeContent);
        Files.write(tempDir.resolve("large2.bin"), largeContent);

        // Act
        FolderStats stats = adapter.scan(targetId, tempDir);

        // Assert
        assertEquals(2048L, stats.getTotalSizeBytes());
        assertEquals(2L, stats.getFileCount());
    }
}

