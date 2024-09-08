import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cft.curs.configure.Parameters;
import ru.cft.curs.file.FileReaders;
import ru.cft.curs.file.FileWrites;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileWritesTest {
    private final String testDirectory = "test_output";
    private FileWrites fileWrites;
    private FileReaders fileReaders;
    private Parameters parameters;

    @BeforeEach
    void setUp() {
        fileWrites = new FileWrites();
        fileReaders = new FileReaders();

        parameters = new Parameters();
        parameters.setOutputPath(testDirectory);
        parameters.setPrefix("test-");
        parameters.setAppend(false);

        File directory = new File(testDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    @AfterEach
    void tearDown() {
        File directory = new File(testDirectory);
        for (File file : directory.listFiles()) {
            file.delete();
        }
        directory.delete();
    }

    @Test
    void testWriteFile_withStrings() {
        List<String> data = Arrays.asList("line1", "тест", "Тест test");
        String filePath = fileWrites.writeFile(data, parameters);

        File writtenFile = new File(filePath);
        assertTrue(writtenFile.exists(), "Файл должен быть создан");

        List<String> lines = fileReaders.readFile(List.of(filePath)); // Чтение содержимого файла
        assertArrayEquals(data.toArray(), lines.toArray(), "Содержимое файла должно совпадать с исходными данными");
    }

    @Test
    void testWriteFile_withIntegers() {
        List<Long> data = Arrays.asList(2231l, 1234567890123456789l, -123l);
        String filePath = fileWrites.writeFile(data, parameters);

        File writtenFile = new File(filePath);
        assertTrue(writtenFile.exists(), "Файл должен быть создан");

        List<String> lines = fileReaders.readFile(List.of(filePath)); // Чтение содержимого файла
        List<Long> readData = lines.stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        assertArrayEquals(data.toArray(), readData.toArray(), "Содержимое файла должно совпадать с исходными данными");
    }

    @Test
    void testWriteFile_withFloats() {
        List<Double> data = Arrays.asList(0.2121411, 3.121232, -32232.1313313243413);
        String filePath = fileWrites.writeFile(data, parameters);

        File writtenFile = new File(filePath);
        assertTrue(writtenFile.exists(), "Файл должен быть создан");

        List<String> lines = fileReaders.readFile(List.of(filePath)); // Чтение содержимого файла
        List<Double> readData = lines.stream()
                .map(Double::parseDouble)
                .collect(Collectors.toList());
        assertArrayEquals(data.toArray(), readData.toArray(), "Содержимое файла должно совпадать с исходными данными");
    }
}

