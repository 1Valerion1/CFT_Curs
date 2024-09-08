package ru.cft.curs.configure;

import java.util.List;

// Используется для хранения параметров, полученных из командной строки.
public class Parameters {
    private List<String> inputFiles;
    private String outputPath;
    private String prefix;
    private Boolean append;
    private Boolean shortStatistics;
    private Boolean fullStatistics;

    public List<String> getInputFiles() {
        return inputFiles;
    }

    public void setInputFiles(List<String> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Boolean getAppend() {
        return append;
    }

    public void setAppend(Boolean append) {
        this.append = append;
    }

    public Boolean getShortStatistics() {
        return shortStatistics;
    }

    public void setShortStatistics(Boolean shortStatistics) {
        this.shortStatistics = shortStatistics;
    }

    public Boolean getFullStatistics() {
        return fullStatistics;
    }

    public void setFullStatistics(Boolean fullStatistics) {
        this.fullStatistics = fullStatistics;
    }

}

