package com.epam.rd.autotask;

public class Demo {

	private static final System.Logger LOGGER = System.getLogger(Demo.class.getName());

	public static void main(String[] args) {

		{
			// Information Expert Violation
			Person alice = new Person("Alice", 20);
			LOGGER.log(System.Logger.Level.INFO, "Is Alice eligible for driving license? " + alice.isEligibleForDrivingLicense());

			Person john = new Person("John", 17);
			LOGGER.log(System.Logger.Level.INFO, "Is John eligible for driving license? " + john.isEligibleForDrivingLicense());
		}

		{
			// Low Coupling And High Cohesion Violation
			Employee employee = new Employee("John Doe");
			employee.setSalaryDetails(new Salary(3000, 10, 20));

			String[] nameParts = employee.splitName();
			LOGGER.log(System.Logger.Level.INFO, "First Name: " + nameParts[0]);
			LOGGER.log(System.Logger.Level.INFO, "Last Name: " + nameParts[1]);

			Salary salaryDetails = employee.getSalaryDetails();
			if (salaryDetails != null) {
				LOGGER.log(System.Logger.Level.INFO, "Total Salary:  " + salaryDetails.calculateTotalSalary());
				LOGGER.log(System.Logger.Level.INFO, "Hourly Salary: " + salaryDetails.calculateHourlySalary());
			} else {
				LOGGER.log(System.Logger.Level.INFO, "Salary details not set for employee.");
			}
		}
	}
}