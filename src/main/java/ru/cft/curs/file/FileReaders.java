package ru.cft.curs.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileReaders {
    private static final Logger logger = LogManager.getLogger(FileReaders.class);

    public List<String> readFile(List<String> inputFiles) {
        logger.info("Moved to the FileReaders class");

        List<String> lines = new ArrayList<>();
        try {
            for (String filePath : inputFiles) {
                logger.info("We started reading from files.");
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));

                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();
            }
        } catch (IOException e) {
            logger.error("An error occurred while executing FileReaders: ", e.getMessage());
        }
        return lines;
    }

}
