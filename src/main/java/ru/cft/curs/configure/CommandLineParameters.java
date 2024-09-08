package ru.cft.curs.configure;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.cft.curs.Main;

import java.util.List;

public class CommandLineParameters {
    private static final Logger logger = LogManager.getLogger(Main.class);

    private static Options createOptions() {
        logger.info("Moved to the CommandLineParser class to create options. ");
        Options options = new Options();
        options.addOption("o", "outputPath", true, "Specifies the path to save the results.\n");
        options.addOption("p", "prefix", true, "Specifies the prefix for output file names.");
        options.addOption("a", "append", false, "Allows you to add to existing files.\n");
        options.addOption("s", "shortStatistics", false, "Short statistics contain the number of elements written to outgoing files.");
        options.addOption("f", "fullStatistics", false, "Full statistics for numbers,min,max,sum and average." +
                "And for strings, count, min and max long string");
        return options;
    }

    public static Parameters parse(String[] args) {
        CommandLineParser parser = new DefaultParser();
        Parameters parameters = new Parameters();
        try {
            CommandLine cmd = parser.parse(createOptions(), args);

            parameters.setInputFiles(List.of(cmd.getArgs()));
            parameters.setOutputPath(cmd.getOptionValue("o"));
            parameters.setPrefix(cmd.getOptionValue("p"));
            parameters.setAppend(cmd.hasOption("a"));
            parameters.setShortStatistics(cmd.hasOption("s"));
            parameters.setFullStatistics(cmd.hasOption("f"));

            if (parameters.getInputFiles().get(0).isEmpty())  {
                handleError("Error: At least one input file must be specified.");
            }

        } catch (ParseException e) {
            logger.error("Error parsing the command line: " + e.getMessage());
            helper();
            System.exit(1);
        } catch (NullPointerException e) {
            logger.error("Error: " + e.getMessage());
            helper();
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException e){
            logger.error("Error: " + e.getMessage());
            helper();
            System.exit(1);
        }

        return parameters;
    }

    private static void handleError(String errorMessage) {
        logger.error(errorMessage);
        helper();
        throw new RuntimeException(errorMessage);
    }

    public static void helper() {
        StringBuilder sb = new StringBuilder();
        sb.append("Как использовать help:\n");
        sb.append("java -jar <название_вашего_jar_файла> [OPTIONS] <входные файлы>\n\n");
        sb.append("Пример:\n");
        sb.append("java -jar util.jar -f -a -o src/main/resources -p myPrefix- in1.txt in2.txt\n");
        sb.append("Опции:\n");
        sb.append("  -o Указывает путь для сохранения результатов.\n");
        sb.append("  -p Указывает префикс для имен выходных файлов.\n");
        sb.append("  -a Позволяет добавлять данные в существующие файлы.\n");
        sb.append("  -s Краткая статистика с количеством элементов, записанных в выходные файлы.\n");
        sb.append("  -f Полная статистика о числах: мин, макс, сумма и среднее. \n " +
                "Для строк: количество, минимальная и максимальная длина строки.\n\n");

        System.out.println(sb.toString());
    }
}
