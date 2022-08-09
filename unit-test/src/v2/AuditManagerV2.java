package v2;

import v1.FileSystem;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class AuditManagerV2 {
    private final int maxEntriesPerFile;

    public AuditManagerV2(int maxEntriesPerFile) {
        this.maxEntriesPerFile = maxEntriesPerFile;
    }

    public FileUpdate addRecord(FileContent[] files, String visitorName, LocalDateTime timeOfVisit) throws IOException {
        Arrays.sort(files, Comparator.comparing(FileContent::getFileName));

        String newRecord = String.format("%s;%s", visitorName, timeOfVisit);

        if (files.length == 0) {
            new FileUpdate("audit_1.txt", newRecord);
        }

        int currentFileIndex = files.length;
        FileContent currentFile = files[files.length - 1];

        List<String> lines = Arrays.asList(currentFile.getLines());

        if (lines.size() < maxEntriesPerFile) {
            lines.add(newRecord);
            String newContent = String.join("\r\n", lines);
            return new FileUpdate(currentFile.getFileName(), newContent);
        } else {
            int newIndex = currentFileIndex + 1;
            String newName = String.format("audit_%d.txt", newIndex);
            return new FileUpdate(newName, newRecord);
        }
    }
}
