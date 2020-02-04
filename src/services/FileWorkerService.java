package services;

import model.Employee;
import model.EmployeesPair;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Stream;

public class FileWorkerService {
    private TimeWorkerService timeWorkerService = new TimeWorkerService();

    public void combineEmployeePairsByWorkingTime(
            final HashMap<String, List<Employee>> projectList,
            final HashMap<EmployeesPair, Long> employeesPairMap) {
        projectList.forEach((key1, listEmployes) -> {
            for (int i = 0; i < listEmployes.size(); i++) {
                for (int j = i + 1; j < listEmployes.size(); j++) {
                    EmployeesPair key = new EmployeesPair(listEmployes.get(i).getEmpId(),
                            listEmployes.get(j).getEmpId());
                    employeesPairMap.put(key,
                            employeesPairMap.getOrDefault(key, 0L) +
                                    timeWorkerService
                                            .getDaysWorkedTogather(listEmployes, i, j));
                }
            }
        });
    }

    public void splitEmployeesByProject(String dateFormat, final HashMap<String, List<Employee>> projectList,
                                        final BufferedReader reader) throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            String[] parts = line.split(",");
            putEmployeesInProjectsMap(dateFormat, projectList, parts);
        }
    }

    public void putEmployeesInProjectsMap(final String dateFormat,
                                          final HashMap<String, List<Employee>> projectList,
                                          final String[] parts) {
        List<Employee> employees = new ArrayList<>();
        String projectId = parts[1].trim();
        if (projectList.containsKey(projectId)) {
            employees = projectList.get(projectId);
        }
        employees.add(new Employee(parts[0].trim(),
                timeWorkerService.getDateDifference(parts[2], parts[3], dateFormat)));
        projectList.put(projectId, employees);
    }

    public String readDateFormat() {
        //TO DO add different dates validation
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //   System.out.println("Enter date format :(by default is  yyyy-MM-dd):");
        //   String dateFormat = br.readLine();
        String dateFormat = "";
        if (dateFormat.isEmpty()) dateFormat = "yyyy-MM-dd";
        return dateFormat;
    }

    public static long getLineCount(File file) throws IOException {
        try (Stream<String> lines = Files.lines(file.toPath())) {
            return lines.count();
        }
    }

}
