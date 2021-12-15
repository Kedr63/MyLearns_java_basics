import java.io.File;

public class ParametersBag {
    private String path;
    private long limit;

    public ParametersBag(String args[]) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Укажите два параметра: -l (лимит по объему) " +
                    "и -d (путь к папке)");
        }
        limit = 0;
        path = "";
        for (int i = 0; i < 4; i = i + 2) {
            if (args[i].equals("-l")) {
                limit = SizeCalculator.getSizeFromHumanReadable(args[i + 1]);
            } else if (args[i].equals("-d")) {
                path = args[i + 1];
            }
        }
        if (limit <= 0) {
            throw new IllegalArgumentException("Лимит не указан или указан не верно");
        }
        File folder = new File(path);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new IllegalArgumentException("Путь к папке не указан или указан не верно");
        }

    }

    public long getLimit() {
        return limit;
    }

    public String getPath() {
        return path;
    }
}
