package model;

import java.util.Objects;

public class Employee {
    private final String empId;
    private final long daysWorkedPerProject;


    public Employee(final String empId, final long daysWorkedPerProject) {
        this.empId = empId;
        this.daysWorkedPerProject = daysWorkedPerProject;
    }

    public String getEmpId() {
        return empId;
    }

    public long getDaysWorkedPerProject() {
        return daysWorkedPerProject;
    }


    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return daysWorkedPerProject == employee.daysWorkedPerProject &&
                Objects.equals(empId, employee.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId, daysWorkedPerProject);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", daysWorkedPerProject=" + daysWorkedPerProject +
                '}';
    }
}
