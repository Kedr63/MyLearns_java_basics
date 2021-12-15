import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FolderSizeTest {
    @TempDir
    Path tempDirectory;

    @TempDir
    Path tempDirectoryWithSubDirs;

    final int maxAmountFiles = 100;
    final byte[] bytesInFiles = new byte[10];
    final String filenamePrefix = "file_";
    final String subDirectoryName = "sub";

    @Test
    void createDirectory() {
        assertTrue(Files.isDirectory(this.tempDirectory), "Should be a directory");
    }

    @Test
    @DisplayName("Подсчет размера всех файлов в директории без поддиректорий")
    void testFilesSizeInPlainDirectory()  {
        List<Path> files = fillDirectory(tempDirectory);
        writeContent(files);

        assertEquals((long) files.size() * bytesInFiles.length,
                FileUtils.calculateFolderSize(tempDirectory.toFile().getAbsolutePath()));
    }

    @Test
    @DisplayName("Подсчет размера всех файлов в директории с поддиректориями")
    void testFilesSizeDirectoryWithSubDir() throws IOException {
        List<Path> files = fillDirectory(tempDirectoryWithSubDirs);
        files.addAll(fillDirectory(createSubDirectory()));
        writeContent(files);

        assertEquals((long) files.size() * bytesInFiles.length,
                FileUtils.calculateFolderSize(tempDirectoryWithSubDirs.toFile().getAbsolutePath()));
    }

    private void writeContent(List<Path> files) {
        files.forEach(f -> write(f, bytesInFiles));
    }

    private Path createSubDirectory() throws IOException {
        Path subDir = Paths.get(tempDirectoryWithSubDirs.toAbsolutePath().toString(), subDirectoryName);
        Files.createDirectory(subDir);
        return subDir;
    }

    private void write(Path path, byte[] bytes) {
        try {
            Files.write(path, bytesInFiles);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось записать в данные в файл", e);
        }
    }

    private Path createFile(Path directory, String filename) {
        try {
            return Files.createFile(directory.resolve(addPrefix(filename)));
        } catch (IOException e) {
            throw new RuntimeException("Файл не удалось создать!", e);
        }
    }

    private String addPrefix(String filename) {
        return filenamePrefix + filename;
    }

    private List<Path> fillDirectory(Path dir) {
        return LongStream.range(10, (long) (Math.random() * maxAmountFiles))
                .distinct()
                .mapToObj(Long::toString)
                .map(l -> createFile(dir, l))
                .collect(Collectors.toList());
    }
}
