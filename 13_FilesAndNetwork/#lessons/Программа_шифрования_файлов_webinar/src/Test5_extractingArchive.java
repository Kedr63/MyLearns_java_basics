import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Test5_extractingArchive {
    public static void main(String[] args) {

        // Extracting archive

        String path = "/Users/aleksandrshabalin/Desktop/ForWorkFiles/";
        String in = path + "archiveXX.zip";
        String out = path + "result";

        {
            try {
                FileInputStream inputStream = new FileInputStream(in);
                ZipInputStream zipInput = new ZipInputStream(inputStream);
                while (true) {
                    ZipEntry entry = zipInput.getNextEntry();
                    if (entry == null) {
                        break;
                    }
                    File file = new File(out + entry.getName());
                    if (entry.isDirectory()) {
                        file.mkdir();
                    } else {
                        byte[] bytes = zipInput.readAllBytes();
                        Files.write(Paths.get(file.getAbsolutePath()), bytes, StandardOpenOption.CREATE);


                    /*FileOutputStream outputStream = new FileOutputStream(file);
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = zipInput.read(buffer)) > 0){
                        outputStream.write(buffer, 0, len);*/
                    }
                }
                /*zipInput.closeEntry();
                zipInput.close();
                inputStream.close();*/

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
