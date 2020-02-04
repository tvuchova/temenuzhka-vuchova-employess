package employeewithui;

import model.Employee;
import model.EmployeesPair;
import services.FileWorkerService;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

class EmployeesLayout extends JFrame {

    EmployeesLayout(Object[][] data, HashMap<String, List<Employee>> projectList) throws HeadlessException {

        String[] columnNames = {"EmpID",
                "ProjectID",
                "DateFrom",
                "DateTo"};

        HashMap<EmployeesPair, Long> employeesPairMap = new HashMap<>();
        FileWorkerService fileWorkerService = new FileWorkerService();
        fileWorkerService.combineEmployeePairsByWorkingTime(projectList, employeesPairMap);

        String textFieldValue;
        Long value = 0L;
        Optional<Map.Entry<EmployeesPair, Long>> first = employeesPairMap.entrySet().stream()
                .min((k1, k2) -> -k1.getValue().compareTo(k2.getValue()));
        if (first.isPresent()) {
            value = first.get().getValue();
            textFieldValue = "RESULT PAIR IS :" + first.get().getKey() + " with longest period : " + value;

        } else {
            textFieldValue = "Cannot retrieve longest period.Please upload correct file!";
        }
        System.out.println(textFieldValue);

        JLabel resultLabel = new JLabel("Employees pair worked on projects together for longest period " + value + "  is ");
        JTextField resultValue = new JTextField(20);
        resultValue.setText(textFieldValue);
        resultValue.setEditable(false);
        JPanel panel = new JPanel();
        panel.add(resultLabel);
        panel.add(resultValue);

        JTable table = new JTable(data, columnNames);
        this.add(new JScrollPane(table), BorderLayout.CENTER);
        this.add(panel, BorderLayout.SOUTH);
        this.setTitle("Table Employees");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
}
