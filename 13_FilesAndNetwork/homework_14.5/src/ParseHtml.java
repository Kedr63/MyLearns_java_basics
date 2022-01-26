public class ParseHtml {
    private String url;
    private String pathForDownloads;

    public ParseHtml(String url, String pathForDownloads) {
        this.pathForDownloads = pathForDownloads;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPathForDownloads() {
        return pathForDownloads;
    }

    public void setPathForDownloads(String pathForDownloads) {
        this.pathForDownloads = pathForDownloads;
    }
}
