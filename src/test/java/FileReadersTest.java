import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.cft.curs.file.FileReaders;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileReadersTest {
    private final String testFile = "test_input.txt";
    private FileReaders fileReaders;

    @BeforeEach
    void setUp() throws IOException {
        fileReaders = new FileReaders();
        File file = new File(testFile);
        if (file.exists()) {
            file.delete();
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            writer.write("line1\n");
            writer.write("тест 2\n");
            writer.write("123\n");
            writer.write("0.2342\n");
        }
    }

    @Test
    void testReadFile() {
        List<String> inputFiles = Arrays.asList(testFile);
        List<String> lines = fileReaders.readFile(inputFiles);

        assertEquals(4, lines.size(), "Количество строк должно быть 3");
        assertEquals("line1", lines.get(0), "Первая строка должна быть 'line1'");
        assertEquals("тест 2", lines.get(1), "Вторая строка должна быть 'тест 2'");
        assertEquals("123", lines.get(2), "Третья строка должна быть '123'");
        assertEquals("0.2342", lines.get(3), "Третья строка должна быть '0.2342'");

    }
}
