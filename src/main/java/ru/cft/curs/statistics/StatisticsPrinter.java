package ru.cft.curs.statistics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StatisticsPrinter {
    private static final Logger logger = LogManager.getLogger(StatisticsPrinter.class);

    public void printStatistics(Statistics statistics, boolean shortVersion) {
        logger.info("We went to the StatisticsPrinter class to display statistics.\n");

        if (shortVersion) {
            printShortStatistics(statistics);
        } else {
            printFullStatistics(statistics);
        }
    }

    private void printShortStatistics(Statistics statistics) {
        System.out.println("Short Statistics:");
        if (!statistics.getStrings().isEmpty()) {
            System.out.println("Кол-во strings: " + statistics.getStrings().size());
        }
        if (!statistics.getIntegers().isEmpty()) {
            System.out.println("Кол-во integers: " + statistics.getIntegers().size());
        }
        if (!statistics.getFloats().isEmpty()) {
            System.out.println("Кол-во floats: " + statistics.getFloats().size());
        }
        System.out.println("\n");
    }

    private void printFullStatistics(Statistics statistics) {
        StringBuilder str = new StringBuilder();

        str.append("Full Statistics:\n");
        str.append("\n");
        if (!statistics.getIntegers().isEmpty()) {
            statistics.integersStat();
            str.append("integer:\n");
            str.append("Количество: ").append(statistics.integers.size()).append("\n");
            str.append("Min: ").append(statistics.minInt).append("\n");
            str.append("Max: ").append(statistics.maxInt).append("\n");
            str.append("Sum: ").append(statistics.sum).append("\n");
            str.append("Mean: ").append(statistics.mean).append("\n");
            str.append("\n");
        }

        if (!statistics.getFloats().isEmpty()) {
            statistics.floatStat();
            str.append("float:\n");
            str.append("Количество: ").append(statistics.floats.size()).append("\n");
            str.append("Min: ").append(statistics.minFloat).append("\n");
            str.append("Max: ").append(statistics.maxFloat).append("\n");
            str.append("Sum: ").append(statistics.sumFloat).append("\n");
            str.append("Mean: ").append(statistics.meanFloat).append("\n");
            str.append("\n");
        }

        if (!statistics.getStrings().isEmpty()) {
            statistics.stringStat();
            str.append("string:\n");
            str.append("Количество: ").append(statistics.strings.size()).append("\n");
            str.append("Min: ").append(statistics.minStr).append("\n");
            str.append("Max: ").append(statistics.maxStr).append("\n");
            str.append("\n");
        }
        System.out.println(str);
    }
}
