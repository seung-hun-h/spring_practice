package v2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;

public class Persister {
    public FileContent[] readDirectory(String directoryName) {
        return Arrays.stream(Objects.requireNonNull(Paths.get(directoryName).toFile().listFiles()))
                .map(file -> {
                    try {
                        return new FileContent(file.getName(), Files.lines(file.toPath()).toArray(String[]::new));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                .toArray(FileContent[]::new);
    }

    public void applyUpdate(String directoryName, FileUpdate update) {
        File file = Path.of(directoryName, update.fileName).toFile();
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(update.newContent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
