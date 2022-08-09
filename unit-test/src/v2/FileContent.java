package v2;

public class FileContent {
    public final String fileName;
    public final String[] lines;

    public FileContent(String fileName, String[] lines) {
        this.fileName = fileName;
        this.lines = lines;
    }

    public String getFileName() {
        return fileName;
    }

    public String[] getLines() {
        return lines;
    }
}
