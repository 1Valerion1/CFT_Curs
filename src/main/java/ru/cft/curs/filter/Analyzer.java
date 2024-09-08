package ru.cft.curs.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.cft.curs.configure.Parameters;
import ru.cft.curs.file.FileReaders;
import ru.cft.curs.file.FileWrites;
import ru.cft.curs.statistics.Statistics;
import ru.cft.curs.statistics.StatisticsPrinter;

import java.util.ArrayList;
import java.util.List;


public class Analyzer {
    private static final Logger logger = LogManager.getLogger(Analyzer.class);
    private final FileReaders fileReader;
    private final FileWrites fileWriter;
    private final DataFilter dataFilter;
    private final StatisticsPrinter statisticsPrinter;
    private final Statistics statistics;


    public Analyzer(FileReaders fileReader, FileWrites fileWriter, DataFilter dataFilter, StatisticsPrinter statisticsPrinter, Statistics statistics) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
        this.dataFilter = dataFilter;
        this.statisticsPrinter = statisticsPrinter;
        this.statistics = statistics;
    }

    public void start(Parameters param) {
        logger.info("We went to the Analyzer class ");
        try {
            List<String> allStrings = fileReader.readFile(param.getInputFiles());
            dataFilter.filterData(allStrings, statistics);

            List<String> filePathNames = new ArrayList<>(3);
            if (!statistics.getStrings().isEmpty()) {
                filePathNames.add(fileWriter.writeFile(statistics.getStrings(), param));
            }
            if (!statistics.getIntegers().isEmpty()) {
                filePathNames.add(fileWriter.writeFile(statistics.getIntegers(), param));
            }
            if (!statistics.getFloats().isEmpty()) {
                filePathNames.add(fileWriter.writeFile(statistics.getFloats(), param));
            }

            filePathNameWriteCmd(filePathNames);
            statisticsPrinter.printStatistics(statistics, param.getShortStatistics());

        } catch (ArithmeticException e) {
            logger.error("An error occurred while executing the Analyzer: {}", e.getMessage(), e);

        } catch (Exception e) {
            logger.error("An error occurred while executing the Analyzer: {}", e.getMessage(), e);
        }
    }

    private void filePathNameWriteCmd(List<String> filePaths) {
        System.out.println("\n");
        for (String path : filePaths) {
            System.out.println("File location and file name: " + path);
        }
        System.out.println("\n");
    }


}
