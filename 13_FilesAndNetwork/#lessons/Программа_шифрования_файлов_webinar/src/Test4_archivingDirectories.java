import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Test4_archivingDirectories {
    public static void main(String[] args) {

        // Как архивировать папки
        String path = "/Users/aleksandrshabalin/Desktop/ForWorkFiles/";
        String in = path + "XX";
        String out = path + "archiveXX.zip"; // архивируем папку /ХХ/

        try {
            // Creating archive
            FileOutputStream outputStream = new FileOutputStream(out);
            ZipOutputStream zipOut = new ZipOutputStream(outputStream);
            writeFileToZip(new File(in), zipOut, "");
            zipOut.flush();
            zipOut.close();
            outputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void writeFileToZip(File file, ZipOutputStream zipOutput, String path) throws Exception {
        if (file.isDirectory()) {
            String folder = path + file.getName() + "/";
            ZipEntry entry = new ZipEntry(folder);
            zipOutput.putNextEntry(entry);
            zipOutput.closeEntry();
            File[] files = file.listFiles();
            for (File subFile : files) {
                writeFileToZip(subFile, zipOutput, folder);
            }
            return;
        }
        ZipEntry entry = new ZipEntry(path + file.getName());
        zipOutput.putNextEntry(entry);
        byte[] bytes = Files.readAllBytes(Paths.get(file.getAbsolutePath()));
        zipOutput.write(bytes);
    }
}

