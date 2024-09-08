package ru.cft.curs.statistics;

import java.util.ArrayList;
import java.util.List;

// Хранит поля и методы для работы со статистикой
public class Statistics {
    //Списки для хранения данных
    List<String> strings = new ArrayList<>();
    List<Long> integers = new ArrayList<>();
    List<Double> floats = new ArrayList<>();

    //Переменные для  целочисленных значени
    Long minInt = Long.MAX_VALUE;
    Long maxInt = Long.MIN_VALUE;
    Long sum = 0l;
    Long mean = 0l;
    //Переменные для чисел с плавающей точкой
    Double minFloat = Double.MAX_VALUE;
    Double maxFloat = Double.MIN_VALUE;
    Double sumFloat = 0d;
    Double meanFloat = 0d;

    //Переменные для  строк
    Long minStr = Long.MAX_VALUE;
    Long maxStr = Long.MIN_VALUE;


    public List<String> getStrings() {
        return strings;
    }

    public void setStrings(List<String> strings) {
        this.strings = strings;
    }

    public List<Long> getIntegers() {
        return integers;
    }

    public void setIntegers(List<Long> integers) {
        this.integers = integers;
    }

    public List<Double> getFloats() {
        return floats;
    }

    public void setFloats(List<Double> floats) {
        this.floats = floats;
    }

    public void addString(String str) {
        strings.add(str);
    }

    public void addInteger(Long integer) {
        integers.add(integer);
    }

    public void addFloat(Double floatValue) {
        floats.add(floatValue);
    }

    //Для вычисления min,max в файле со строкой
    public void stringStat() {

        for (String str : strings) {
            minStr = Math.min(minStr, str.length());
            maxStr = Math.max(maxStr, str.length());
        }

    }
    //Для вычисления min,max, sum и mean в integer файле
    public void integersStat() {
        for (Long strLong : integers) {
            minInt = Math.min(minInt, strLong);
            maxInt = Math.max(maxInt, strLong);
            sum = sum + strLong;

        }
        mean = sum / integers.size();

    }

    //Для вычисления min,max, sum и mean в float файле
    public void floatStat() {
        for (Double strFloat : floats) {
            minFloat = Math.min(minFloat, strFloat);
            maxFloat = Math.max(maxFloat, strFloat);
            sumFloat = sumFloat + strFloat;
        }
        meanFloat = sumFloat / floats.size();

    }


}