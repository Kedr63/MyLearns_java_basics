import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Test2_archivingFiles {
    public static void main(String[] args) {
        // Архивирование файлов
        String path = "/Users/aleksandrshabalin/Desktop/ForWorkFiles/";
        String in = path + "trex.JPG";
        String out = path + "archive.zip";

        // Простой способ
        // Возьмем пакет java.util.zip.ZipOutputStream

        try {
            FileOutputStream outputStream = new FileOutputStream(out);
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            ZipEntry entry = new ZipEntry(new File(in).getName());
            zipOut.putNextEntry(entry);

            byte[] data = Files.readAllBytes(Paths.get(in));
            zipOut.write(data);
            zipOut.flush();
            zipOut.close();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
