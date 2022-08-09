package v1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileSystemImpl implements FileSystem {
    @Override
    public List<File> getFiles(String directoryName) {
        return new ArrayList<>(List.of(Paths.get(directoryName).toFile().listFiles()));
    }

    @Override
    public void write(String newRecord, File file) {
        try(FileWriter writer = new FileWriter(file)) {
            writer.write(newRecord);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> readLines(File file) {
        try(Stream<String> lineStream = Files.lines(file.toPath())) {
            return new ArrayList<>(lineStream.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
