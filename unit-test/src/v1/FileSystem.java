package v1;

import java.io.File;
import java.util.List;

public interface FileSystem {
    List<File> getFiles(String directoryName);

    void write(String newRecord, File file);

    List<String> readLines(File file);
}
