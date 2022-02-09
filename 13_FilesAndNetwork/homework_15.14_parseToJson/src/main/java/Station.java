public class Station implements Comparable<Station>{
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
        return "\"" + name.trim() + "\"";
    }

    @Override
    public int compareTo(Station anotherStation) {
        int res = this.name.compareTo(anotherStation.name);
        if (res == 0){
            res = this.locateOnLine.compareTo(anotherStation.locateOnLine);
        }
        return res;
    }
}
