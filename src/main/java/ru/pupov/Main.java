package ru.pupov;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.pupov.converter.Converter;
import ru.pupov.converter.TLVConverter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws IOException {
        String inputFileStr= args[0];
        String outputFileStr= args[1];

        Path inputPath = Paths.get(inputFileStr);
        Path outputPath = Paths.get(outputFileStr);

        byte[] bytes = Files.readAllBytes(inputPath);

        Converter converter = new TLVConverter();
        String json = converter.getJson(bytes);

        Files.write(outputPath, json.getBytes(StandardCharsets.UTF_8));

        logger.info("File " + outputPath.toAbsolutePath() + " was created");
    }
}
