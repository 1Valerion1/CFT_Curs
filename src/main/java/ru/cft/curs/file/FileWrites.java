package ru.cft.curs.file;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.cft.curs.configure.Parameters;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileWrites {
    private static final Logger logger = LogManager.getLogger(FileWrites.class);

    // Метод для записи данных в файл
    public String writeFile(List<?> fileSave, Parameters parameters) {
        logger.info("Moved to the FileWrites class");
        if (fileSave.isEmpty()) {
            logger.error("The list fileSave is empty.");
            throw new IllegalArgumentException("FileSave list cannot be empty.");
        }

        //Получаем параметры куда сохранять, префикс и перезаписывваем файл или нет(append)
        String outputPath = parameters.getOutputPath();
        String prefix = parameters.getPrefix();
        Boolean append = parameters.getAppend();

        String baseFileName = baseFileCreate(fileSave);
        String fileName = (prefix != null && !prefix.isEmpty()) ? prefix + baseFileName : baseFileName;
        String filePath = (outputPath != null && !outputPath.isEmpty()) ? outputPath + "/" + fileName : fileName;

        // проверяем есть ли путь для сохранения
        if(outputPath != null) {
            //если нет директории, то создаем
            File directory = new File(outputPath);
            if (!directory.exists() && !directory.mkdirs()) {
                logger.error("Failed to create directory: {}", outputPath);
                throw new RuntimeException("Could not create directory: " + outputPath);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            logger.info("Started recording to files.");
            for (Object item : fileSave) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            logger.error("An error occurred while executing FileWrites: {}", e.getMessage(), e);
        }
        return filePath;
    }

    public String baseFileCreate(List<?> fileSave) {
        logger.info("We check the types of what type of files.");
        String baseFileName = null;
        if (fileSave.get(0) instanceof String) {
            baseFileName = "strings.txt";
        } else if ((fileSave.get(0) instanceof Integer) || (fileSave.get(0) instanceof Long)) {
            baseFileName = "integers.txt";
        } else if ((fileSave.get(0) instanceof Float) || (fileSave.get(0) instanceof Double)) {
            baseFileName = "floats.txt";
        }
        return baseFileName;
    }
}
