package ru.java.fambudg.hw.service;

import ru.java.fambudg.constraints.DataConstraints;

public interface Report {
    String getReportId();
    String generate(DataConstraints constraints);
}
