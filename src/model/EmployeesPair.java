package model;

import java.util.Objects;

public class EmployeesPair {
    private final String employeeX;
    private final String employeeY;

    public EmployeesPair(final String employeeX, final String employeeY) {
        this.employeeX = employeeX;
        this.employeeY = employeeY;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EmployeesPair that = (EmployeesPair) o;
        return Objects.equals(employeeX, that.employeeX) &&
                Objects.equals(employeeY, that.employeeY) ||
                Objects.equals(employeeX, that.employeeY) &&
                        Objects.equals(employeeY, that.employeeX);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeX, employeeY);
    }

    @Override
    public String toString() {
        return "EmpId { " +
                "\'" + employeeX + '\'' +
                ",\'" + employeeY + '\'' +
                '}';
    }
}
