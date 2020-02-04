package services;

import model.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

class TimeWorkerService {
    long getDaysWorkedTogather(final List<Employee> listEmployes,
                               final int i, final int j) {
        return listEmployes.get(i).getDaysWorkedPerProject() > listEmployes.get(j).getDaysWorkedPerProject()
                ? listEmployes.get(j).getDaysWorkedPerProject()
                : listEmployes.get(i).getDaysWorkedPerProject();
    }

    long getDateDifference(String date1, String date2, String dateFormat) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        final LocalDate firstDate = LocalDate.parse(date1.trim(), formatter);
        LocalDate secondDate;
        if ("NULL".equalsIgnoreCase(date2.trim())) {
            secondDate = LocalDate.now();
        } else {
            secondDate = LocalDate.parse(date2.trim(), formatter);
        }

        long days = ChronoUnit.DAYS.between(firstDate, secondDate);
        System.out.println("Days between: " + days);
        return days;
    }
}
