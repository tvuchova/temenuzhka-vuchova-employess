package employeewithui;

import model.Employee;
import services.FolderService;
import services.FileWorkerService;

import javax.swing.SwingUtilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class EmployeesLongestPeriodWorkedWithUI {
    private static final int NUM_COLUMN = 4;

    public static void main(String[] args) throws IOException {

        FileWorkerService fileWorkerService = new FileWorkerService();
        String dateFormat = fileWorkerService.readDateFormat();
        HashMap<String, List<Employee>> projectList = new HashMap<>();

        String fileName = FolderService.selectFile();
        if (fileName == null) {
            System.out.println("File isn't selected exiting...");
            return;
        }

        int rows = 0;
        Object[][] data = new Object[(int) FileWorkerService.getLineCount(new File(fileName))][NUM_COLUMN];

        try (BufferedReader reader = Files.newBufferedReader(
                Paths.get(fileName), Charset.forName("UTF-8"))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                String[] parts = line.split(",");
                fillDataGridArray(rows++, data, parts);
                fileWorkerService.putEmployeesInProjectsMap(dateFormat, projectList, parts);
            }
            SwingUtilities.invokeLater(() -> new EmployeesLayout(data, projectList));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void fillDataGridArray(final int k, final Object[][] data,
                                          final String[] parts) {
        data[k][0] = parts[0].trim();
        data[k][1] = parts[1].trim();
        data[k][2] = parts[2].trim();
        data[k][3] = parts[3].trim();
    }
}
