public class Station {
    private String name;
    private String locateOnLine;

    public Station(String name, String locateOnLine) {
        this.name = name;
        this.locateOnLine = locateOnLine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocateOnLine() {
        return locateOnLine;
    }

    public void setLocateOnLine(String locateOnLine) {
        this.locateOnLine = locateOnLine;
    }

    @Override
    public String toString() {
        return name.trim();
    }
}
