package ru.cft.curs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.cft.curs.configure.CommandLineParameters;
import ru.cft.curs.configure.Parameters;
import ru.cft.curs.file.FileReaders;
import ru.cft.curs.file.FileWrites;
import ru.cft.curs.filter.Analyzer;
import ru.cft.curs.filter.DataFilter;
import ru.cft.curs.statistics.Statistics;
import ru.cft.curs.statistics.StatisticsPrinter;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("The application is running.");
        // Разбиваем строку на массив строк на основе пробелов
        Parameters parameters = CommandLineParameters.parse(args);
        // Создаем  классы для чтения, записи файлов, фильтрации данных, сбора статистики и печати статистики
        FileReaders fileReaders = new FileReaders();
        FileWrites fileWrites = new FileWrites();
        DataFilter dataFilter = new DataFilter();
        Statistics statistics = new Statistics();
        StatisticsPrinter statisticsPrinter = new StatisticsPrinter();
        // Создаем класс Analyzer. Отвечает за анализ данных на основе предоставленных параметров
        Analyzer analyzer = new Analyzer(fileReaders, fileWrites, dataFilter, statisticsPrinter, statistics);
        // Запускаем процесс анализа данных
        analyzer.start(parameters);
        logger.info("The application has terminated.");
    }
}

