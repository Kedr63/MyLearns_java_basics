import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Test3_archivingSomeFiles {
    public static void main(String[] args) {

        // Заархивировать несколько файлов
        String path = "/Users/aleksandrshabalin/Desktop/ForWorkFiles/";
        String[] in = {"trex.JPG", "text.txt", "textForWrite.txt"};
        String out = path + "archive.zip";
        try {
            FileOutputStream outputStream = new FileOutputStream(out);
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            for (String fileName : in) {
                File file1 = new File(path + fileName);
                ZipEntry entry = new ZipEntry(file1.getName());
                zipOut.putNextEntry(entry);
                Path pathFile = Paths.get(file1.getAbsolutePath());
                byte[] data = Files.readAllBytes(pathFile);
                zipOut.write(data);
            }
            zipOut.flush();
            zipOut.close();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
