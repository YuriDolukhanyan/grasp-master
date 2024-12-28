package com.epam.rd.autotask;

public class Employee {
	private String fullName;
	private Salary salaryDetails;

	public Employee(String fullName) {
		this.fullName = fullName;
		this.salaryDetails = null;
	}

	public String getFullName() {
		return fullName;
	}

	public String[] splitName() {
		if (fullName == null || fullName.trim().isEmpty()) {
			return new String[]{"", ""};
		}
		String[] nameParts = fullName.split(" ", 2);
		return new String[]{
				nameParts.length > 0 ? nameParts[0] : "",
				nameParts.length > 1 ? nameParts[1] : ""
		};
	}

	public Salary getSalaryDetails() {
		return salaryDetails;
	}

	public void setSalaryDetails(Salary salaryDetails) {
		this.salaryDetails = salaryDetails;
	}
}