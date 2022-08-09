package v2;

import java.io.IOException;
import java.time.LocalDateTime;

public class ApplicationService {
    private final String directoryName;
    private final AuditManagerV2 auditManager;
    private final Persister persister;

    public ApplicationService(String directoryName, int maxEntriesPerFile) {
        this.directoryName = directoryName;
        this.auditManager = new AuditManagerV2(maxEntriesPerFile);
        this.persister = new Persister();
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) throws IOException {
        FileContent[] fileContents = persister.readDirectory(directoryName);
        FileUpdate fileUpdate = auditManager.addRecord(fileContents, visitorName, timeOfVisit);
        persister.applyUpdate(directoryName, fileUpdate);
    }

    public static void main(String[] args) {
        ApplicationService applicationService = new ApplicationService("/Users/user/Workspace/study-code/unit-test/file", 1);
        try {
            applicationService.addRecord("hi", LocalDateTime.now());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
