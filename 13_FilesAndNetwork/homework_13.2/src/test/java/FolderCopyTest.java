import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Копирование содержимого директории, целевая директория создана")
public class FolderCopyTest {
    @TempDir
    Path sourceTempDirectory;

    final static String HASH_ALGORITHM = "MD5";
    private static final int MAX_AMOUNT_FILES = 100;
    private static final int MIN_AMOUNT_FILES = 10;
    private static final byte[] BYTES_IN_FILE = new byte[10];
    private static final String FILENAME_PREFIX = "file_";
    private static final String SUB_DIRECTORY_NAME = "sub";
    private static final String SOURCE_FOLDER_NAME = "source";
    private static final String DESTINATION_FOLDER_NAME = "destination";

    @Test
    @DisplayName("Один файл в директории")
    void testCopyOneFile() throws IOException {
        Path sourceDirectory = createSubDirectory(sourceTempDirectory, SOURCE_FOLDER_NAME);
        Path destinationDirectory = createSubDirectory(sourceTempDirectory, DESTINATION_FOLDER_NAME);

        List<Path> filesSourceDirectory = fillDirectoryWithEmptyFiles(sourceDirectory, 1);
        writeContent(filesSourceDirectory);

        FileUtils.copyFolder(sourceDirectory.toString(), destinationDirectory.toString());

        assertPaths(filesSourceDirectory, sourceDirectory, destinationDirectory);
    }

    @Test
    @DisplayName("Несколько файлов, без подпапок")
    void testCopyPlainDirectory() throws IOException {
        Path sourceDirectory = createSubDirectory(sourceTempDirectory, SOURCE_FOLDER_NAME);
        Path destinationDirectory = createSubDirectory(sourceTempDirectory, DESTINATION_FOLDER_NAME);

        List<Path> filesSourceDirectory = fillDirectoryWithEmptyFiles(sourceDirectory);
        writeContent(filesSourceDirectory);

        FileUtils.copyFolder(sourceDirectory.toString(), destinationDirectory.toString());

        assertPaths(filesSourceDirectory, sourceDirectory, destinationDirectory);
    }

    @Test
    @DisplayName("Несколько файлов, и вложенная директория с файлами")
    void testCopyWithSubDirectory() throws IOException {
        Path sourceDirectory = createSubDirectory(sourceTempDirectory, SOURCE_FOLDER_NAME);
        Path sourceSubDirectory = createSubDirectory(sourceDirectory, SUB_DIRECTORY_NAME);
        Path destinationDirectory = createSubDirectory(sourceTempDirectory, DESTINATION_FOLDER_NAME);

        List<Path> filesSourceDirectory = fillDirectoryWithEmptyFiles(sourceDirectory);
        writeContent(filesSourceDirectory);

        List<Path> filesSourceSubDirectory = fillDirectoryWithEmptyFiles(sourceSubDirectory);
        writeContent(filesSourceSubDirectory);

        List<Path> allSourceFiles = Stream
                .concat(filesSourceDirectory.stream(), filesSourceSubDirectory.stream())
                .collect(Collectors.toList());

        FileUtils.copyFolder(sourceDirectory.toString(), destinationDirectory.toString());

        assertPaths(allSourceFiles, sourceDirectory, destinationDirectory);
    }

    private void assertPaths(Collection<Path> sourcesFiles, Path sourceDir, Path destinationDir) {
        for (Path path : sourcesFiles) {
            Path fileSource = sourceDir.relativize(path);
            Path expectedCopyPath = destinationDir.resolve(fileSource);
            Path sourceFilePath = sourceDir.resolve(fileSource);

            CopyResult copyResult = new CopyResult(sourceFilePath, expectedCopyPath);
            System.out.format("\tИсходный файл:  %s%n\tОжидаемая копия:%s%n",
                    path.toAbsolutePath(), expectedCopyPath.toAbsolutePath());

            assertTrue(copyResult.isCopiedFileExist(),
                    String.format("Не найдена копия файла.%n" +
                                    "\tИсходный файл:  %s%n\tОжидаемая копия:%s;",
                            path.toAbsolutePath(), expectedCopyPath.toAbsolutePath()));

            assertTrue(copyResult.isCopiedFileEqualsContent(),
                    String.format("Содержимое файла копии на совпадает с оригиналом.%n" +
                                    "\tИсходный файл:  %s%n\tОжидаемая копия:%s;",
                            path.toAbsolutePath(), expectedCopyPath.toAbsolutePath()));
        }
    }

    private void writeContent(List<Path> files) {
        files.forEach(f -> write(f, BYTES_IN_FILE));
    }

    private Path createSubDirectory(Path directory, String subdirectoryName) throws IOException {
        Path subDir = Paths.get(directory.toString(), subdirectoryName);
        Files.createDirectory(subDir);
        return subDir;
    }

    private void write(Path path, byte[] bytes) {
        try {
            Files.write(path, BYTES_IN_FILE);
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
        return FILENAME_PREFIX + filename;
    }

    private List<Path> fillDirectoryWithEmptyFiles(Path dir) {
        return fillDirectoryWithEmptyFiles(dir, generateLong(MIN_AMOUNT_FILES, MAX_AMOUNT_FILES));
    }

    private List<Path> fillDirectoryWithEmptyFiles(Path dir, long count) {
        return LongStream.range(0, count)
                .distinct()
                .mapToObj(Long::toString)
                .map(l -> createFile(dir, l))
                .collect(Collectors.toList());
    }

    private long generateLong(long min, long max) {
        return (long) (Math.random() * (max - min) + min);
    }
}

class CopyResult {
    private final Path sourceFilePath;
    private final Path copiedFilePath;

    public CopyResult(Path sourceFilePath, Path copiedFilePath) {
        this.sourceFilePath = sourceFilePath;
        this.copiedFilePath = copiedFilePath;
    }

    public boolean isCopiedFileExist() {
        return Files.exists(copiedFilePath);
    }

    public boolean isCopiedFileEqualsContent() {
        return Arrays.equals(hashMD5(sourceFilePath), hashMD5(copiedFilePath));
    }

    private byte[] hashMD5(Path path) {
        try {
            return MessageDigest.getInstance(FolderCopyTest.HASH_ALGORITHM).digest(bytes(path));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(String.format("Не найден алгоритм хеширования<%s>", FolderCopyTest.HASH_ALGORITHM), e);
        }
    }

    private byte[] bytes(Path path) {
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось прочитать файл: " + path.toString(), e);
        }
    }
}
