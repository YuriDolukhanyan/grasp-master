package com.epam.rd.autotask;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.epam.rd.autotask.helper.ReplaceCamelCase;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class LowCouplingAndHighCohesionTest {

	static private Method[] salaryMethods;
	static private Field[] salaryFields;
	static private Constructor<?>[] salaryConstructors;

	@BeforeAll
	static void init() {
		Class<?> salaryClass = Salary.class;
		salaryMethods = salaryClass.getDeclaredMethods();
		salaryFields = salaryClass.getDeclaredFields();
		salaryConstructors = salaryClass.getDeclaredConstructors();
	}

	@ParameterizedTest
	@ValueSource(strings = { "calculateHourlySalary", "calculateTotalSalary" })
	void salaryHasAllAppropriateMethods(String methodName) {
		assertTrue(
				Stream.of(salaryMethods).anyMatch(method -> method.getName().equals(methodName)
						&& Modifier.isPublic(method.getModifiers()) && !Modifier.isStatic(method.getModifiers())),
				"Salary does not have public non-static method \"" + methodName + "\"");
	}

	@ParameterizedTest
	@ValueSource(strings = { "salary", "hoursWorked", "hourlyRate" })
	void salaryHasAllAppropriateFields(String fieldName) {
		assertTrue(
				Stream.of(salaryFields)
						.anyMatch(field -> field.getName().equals(fieldName) && Modifier.isPrivate(field.getModifiers())
								&& !Modifier.isStatic(field.getModifiers())),
				"Salary does not have private non-static field \"" + fieldName + "\"");
	}

	@Test
	void salaryHasAnAppropriateConstructor() {
		assertTrue(
				Stream.of(salaryConstructors)
						.anyMatch(constructor -> constructor.getParameterCount() == 3
								&& Modifier.isPublic(constructor.getModifiers())),
				"Salary should have a public constructor to initialize all of its fields");
	}

	@ParameterizedTest
	@CsvSource({ "John Doe, John, Doe", "a b, a, b", "123 456, 123, 456" })
	void EmployeeHasTheSplitNameMethod(String fullName, String firstName, String lastName) {
		Employee employee = new Employee(fullName);
		assertAll("You should implement the Employee.splitName() method",
				() -> assertTrue(employee.splitName().length == 2, "Incorrect array length"),
				() -> assertEquals(firstName, employee.splitName()[0], "First name does not match"),
				() -> assertEquals(lastName, employee.splitName()[1], "Last name doesn't match"));
	}
}