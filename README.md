# The GRASP Demo

The purpose of this exercise is to practice using GRASP (Information Expert, Low Coupling and High Cohesion).

Estimated workload of this exercise is *30 min*.

### Description

In this exercise you should modify the provided Java code according to the **Information Expert** principle. The code must comply with the principles of **Low Coupling and High Cohesion**. This will help you learn how to apply these principles in Java programs.

### Details

1) Modify the `Person` class according to the basic principles of OOP.
2) Driving licenses are available to persons over 18 years of age. Provide driver's license eligibility checks for objects of the `Person` class.
3) Implement the `isEligibleForDrivingLicense` method according to the **Information Expert** principle.
4) After making changes, the `InformationExpertViolation` class will no longer be needed. Get rid of it.
5) Modify the `Employee` and `Salary` classes according to the principles of **Low Coupling and High Cohesion**.
6) Implement the `Employee.splitName()` method. It should return an array where the first element is the first name and the second is the last name.

###  Example

An example of a violation of the **Information Expert** principle is shown below.

```java
Person alice = new Person("Alice", 20);
boolean isAliceEligible = InformationExpertViolation.isEligibleForDrivingLicense(alice);
LOGGER.log(System.Logger.Level.INFO, "Is Alice eligible for driving license? " + isAliceEligible);

Person john = new Person("John", 17);
boolean isJohnEligible = InformationExpertViolation.isEligibleForDrivingLicense(john);
LOGGER.log(System.Logger.Level.INFO, "Is John eligible for driving license? " + isJohnEligible);
```

An example of the output of the previous code is shown below.

```
...
INFO: Is Alice eligible for driving license? true
...
INFO: Is John eligible for driving license? false
...
```

An example of a violation of the **Low Coupling and High Cohesion** principles is shown below.

```java
DefectiveEmployee employee = new DefectiveEmployee("John Doe", 3000, 10, 20);

String[] nameParts = employee.splitName();
LOGGER.log(System.Logger.Level.INFO, "First Name: " + nameParts[0]);
LOGGER.log(System.Logger.Level.INFO, "Last Name: " + nameParts[1]);

LOGGER.log(System.Logger.Level.INFO, "Total Salary:  " + employee.calculateTotalSalary());
LOGGER.log(System.Logger.Level.INFO, "Hourly Salary: " + employee.calculateHourlySalary());
```

An example of the output of the previous code is shown below..

```
...
INFO: First Name: John
...
INFO: Last Name: Doe
...
INFO: Total Salary:  3200
...
INFO: Hourly Salary: 200
...
```
