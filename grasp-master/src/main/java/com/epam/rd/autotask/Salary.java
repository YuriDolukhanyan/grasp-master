package com.epam.rd.autotask;

public class Salary {
    private int salary;
    private int hoursWorked;
    private int hourlyRate;

    public Salary(int salary, int hoursWorked, int hourlyRate) {
        this.salary = salary;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    public int calculateHourlySalary() {
        return hoursWorked * hourlyRate;
    }

    public int calculateTotalSalary() {
        return salary + calculateHourlySalary();
    }
}