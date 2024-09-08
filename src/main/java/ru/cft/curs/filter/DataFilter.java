package ru.cft.curs.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.cft.curs.statistics.Statistics;

import java.util.List;

public class DataFilter {
    private static final Logger logger = LogManager.getLogger(DataFilter.class);

    // Отвечает за распределение данных по 3 типам
    public void filterData(List<String> stringList, Statistics statistics) {
        logger.info("Moved to the DataFilter class to filter files");

        for (String item : stringList) {
            if (isInteger(item)) {
                statistics.addInteger(Long.parseLong(item));
            } else if (isFloat(item)) {
                statistics.addFloat(Double.parseDouble(item));
            } else if (item instanceof String && !item.isEmpty()) {
                statistics.addString((String) item);
            }
        }

    }

    private boolean isInteger(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isFloat(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
