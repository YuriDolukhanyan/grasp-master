package com.epam.rd.autotask;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import com.epam.rd.autotask.helper.ReplaceCamelCase;

import spoon.Launcher;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

@DisplayNameGeneration(ReplaceCamelCase.class)
public class InformationExpertTest {

	@Test
	void personHasAMethodToCheckEligibilityForADriverLicense() {
		Class<?> clazz = Person.class;
		try {
			Method method = clazz.getDeclaredMethod("isEligibleForDrivingLicense");
			assertAll("You should implement the Person.isEligibleForDrivingLicense() method",
					() -> assertTrue(Modifier.isPublic(method.getModifiers()), "It must be public"),
					() -> assertFalse(Modifier.isStatic(method.getModifiers()), "It must be non-static"));
		} catch (NoSuchMethodException e) {
			fail("You should implement the " + e.getMessage() + " method");
		}
	}

	@Test
	void drivingLicenseEligibilityMethodDoesNotHavePersonsAsParameters() {
		Launcher launcher = new Launcher();
		launcher.addInputResource("src/main/java");
		launcher.buildModel();
		CtModel model = launcher.getModel();

		List<CtMethod<?>> methods = new ArrayList<>();
		model.getElements(new TypeFilter<>(CtMethod.class)).stream()
				.filter(method -> method.getSimpleName().equals("isEligibleForDrivingLicense"))
				.forEach(method -> methods.add(method));

		methods.forEach(method -> {
			method.getParameters()
					.forEach(parameter -> assertFalse(parameter.getType().getSimpleName().equals("Person"),
							"Class " + method.getDeclaringType().getSimpleName() + " contains the method "
									+ method.getSimpleName() + " that takes " + parameter.getType().getSimpleName()
									+ " as a parameter"));
		});
	}
}