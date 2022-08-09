package v1;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

public class AuditManagerV1 {
    private final int maxEntriesPerFile;
    private final String directoryName;
    private final FileSystem fileSystem;

    public AuditManagerV1(int maxEntriesPerFile, String directoryName, FileSystem fileSystem) {
        this.maxEntriesPerFile = maxEntriesPerFile;
        this.directoryName = directoryName;
        this.fileSystem = fileSystem;
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) {
        List<File> files = fileSystem.getFiles(directoryName);
        files.sort(Comparator.comparing(File::getName));

        String newRecord = String.format("%s;%s", visitorName, timeOfVisit);

        if (files.isEmpty()) {
            File file = Path.of(directoryName, "audit_1.txt").toFile();
            fileSystem.write(newRecord, file);
            return;
        }

        int currentFileIndex = files.size();
        File currentFile = files.get(files.size() - 1);
        List<String> lines = fileSystem.readLines(currentFile);

        if (lines.size() < maxEntriesPerFile) {
            lines.add(newRecord);
            String newContent = String.join("\r\n", lines);
            fileSystem.write(newContent, currentFile);
        } else {
            int newIndex = currentFileIndex + 1;
            String newName = String.format("audit_%d.txt", newIndex);
            Path path = Path.of(directoryName, newName);
            fileSystem.write(newRecord, path.toFile());
        }

    }
}
