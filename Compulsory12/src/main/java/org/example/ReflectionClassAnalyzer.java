package org.example;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Test {
}

class SampleTestClass {

    private String privateField = "secret";
    public static int publicStaticField = 100;

    public SampleTestClass() {
        System.out.println("SampleTestClass constructor called.");
    }

    public SampleTestClass(String message) {
        System.out.println("SampleTestClass constructor called with message: " + message);
    }

    @Test
    public static void staticTestMethod1() {
        System.out.println("Executing staticTestMethod1 - PASSED");
    }

    public void instanceMethod() {
        System.out.println("Executing instanceMethod.");
    }

    @Test
    public static void staticTestMethod2() {
        System.out.println("Executing staticTestMethod2 - PASSED");
    }

    @Test
    public void nonStaticTestMethod() {
        System.out.println("Executing nonStaticTestMethod - This should not be called by the framework if it only calls static methods.");
    }

    @Test
    public static void staticTestMethodWithException() {
        System.out.println("Executing staticTestMethodWithException - This will throw an exception.");
        throw new RuntimeException("Deliberate exception from staticTestMethodWithException");
    }

    public static void anotherStaticMethod() {
        System.out.println("Executing anotherStaticMethod (not annotated with @Test).");
    }

    @Test
    public static String staticTestMethodWithParams(int a) {
        System.out.println("Executing staticTestMethodWithParams - This should not be called if only no-arg methods are considered.");
        return "Parameter was: " + a;
    }
}

public class ReflectionClassAnalyzer {

    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java ReflectionClassAnalyzer <fully_qualified_class_name>");
            System.err.println("Example: java ReflectionClassAnalyzer org.example.SampleTestClass");
            System.out.println("\nNo class name provided, defaulting to 'org.example.SampleTestClass'.\n");
            args = new String[]{"org.example.SampleTestClass"};
        }

        String className = args[0];

        try {
            Class<?> loadedClass = Class.forName(className);
            System.out.println("Successfully loaded class: " + loadedClass.getName() + "\n");
            System.out.println("## Class Information ##");
            System.out.println("Simple Name: " + loadedClass.getSimpleName());
            System.out.println("Canonical Name: " + loadedClass.getCanonicalName());
            System.out.println("Package: " + loadedClass.getPackage());
            System.out.println("Superclass: " + loadedClass.getSuperclass());
            System.out.println("Modifiers: " + Modifier.toString(loadedClass.getModifiers()));
            System.out.println("Is Interface: " + loadedClass.isInterface());
            System.out.println("Is Array: " + loadedClass.isArray());
            System.out.println("Is Primitive: " + loadedClass.isPrimitive());

            System.out.println("\n--- Interfaces ---");
            Class<?>[] interfaces = loadedClass.getInterfaces();
            if (interfaces.length == 0) {
                System.out.println("  None");
            } else {
                for (Class<?> iface : interfaces) {
                    System.out.println("  " + iface.getName());
                }
            }

            System.out.println("\n--- Constructors ---");
            Constructor<?>[] constructors = loadedClass.getDeclaredConstructors();
            if (constructors.length == 0) {
                System.out.println("  None");
            } else {
                for (Constructor<?> constructor : constructors) {
                    System.out.println("  " + constructor.toString());
                }
            }

            System.out.println("\n--- Fields ---");
            Field[] fields = loadedClass.getDeclaredFields();
            if (fields.length == 0) {
                System.out.println("  None");
            } else {
                for (Field field : fields) {
                    System.out.println("  " + Modifier.toString(field.getModifiers()) + " " +
                            field.getType().getSimpleName() + " " + field.getName());
                }
            }

            Field[] publicFields = loadedClass.getFields();
            if (publicFields.length > fields.length) {
                System.out.println("  (Including inherited public fields)");
                for (Field field : publicFields) {
                    boolean alreadyListed = false;
                    for(Field declaredField : fields) {
                        if (field.getName().equals(declaredField.getName())) {
                            alreadyListed = true;
                            break;
                        }
                    }
                    if(!alreadyListed) {
                        System.out.println("  " + Modifier.toString(field.getModifiers()) + " " +
                                field.getType().getSimpleName() + " " + field.getName() + " (inherited)");
                    }
                }
            }


            System.out.println("\n--- Methods ---");
            Method[] methods = loadedClass.getDeclaredMethods();
            if (methods.length == 0) {
                System.out.println("  None");
            } else {
                for (Method method : methods) {
                    System.out.println("  " + Modifier.toString(method.getModifiers()) + " " +
                            method.getReturnType().getSimpleName() + " " + method.getName() +
                            Arrays.toString(method.getParameterTypes()));
                    // Display annotations for each method
                    Arrays.stream(method.getAnnotations()).forEach(ann -> System.out.println("    Annotation: " + ann.annotationType().getSimpleName()));
                }
            }

            System.out.println("\n## Invoking @Test static methods (no arguments) ##");
            int testsFound = 0;
            int testsPassed = 0;
            int testsFailed = 0;

            for (Method method : loadedClass.getMethods()) {
                if (method.isAnnotationPresent(Test.class)) {
                    testsFound++;
                    boolean isStatic = Modifier.isStatic(method.getModifiers());
                    boolean hasNoParams = method.getParameterCount() == 0;

                    if (isStatic && hasNoParams) {
                        System.out.println("\nFound @Test static method with no arguments: " + method.getName());
                        try {
                            method.invoke(null);
                            System.out.println("Status: PASSED");
                            testsPassed++;
                        } catch (Exception e) {
                            System.out.println("Status: FAILED");
                            Throwable cause = e.getCause();
                            if (cause != null) {
                                System.err.println("  Test " + method.getName() + " failed: " + cause.getMessage());
                            } else {
                                System.err.println("  Test " + method.getName() + " failed: " + e.getMessage());
                            }
                            testsFailed++;
                        }
                    } else {
                        System.out.println("\nSkipping @Test method: " + method.getName() +
                                (isStatic ? "" : " (not static)") +
                                (hasNoParams ? "" : " (has parameters)"));
                    }
                }
            }

            System.out.println("\n--- Test Execution Summary ---");
            if (testsFound == 0) {
                System.out.println("No methods annotated with @Test found.");
            } else {
                System.out.println("Total @Test methods considered (static, no-args): " + (testsPassed + testsFailed));
                System.out.println("Passed: " + testsPassed);
                System.out.println("Failed: " + testsFailed);
                int skipped = testsFound - (testsPassed + testsFailed);
                if (skipped > 0) {
                    System.out.println("Skipped (non-static or with parameters): " + skipped);
                }
            }

        } catch (ClassNotFoundException e) {
            System.err.println("Error: Class '" + className + "' not found. Ensure it is in the classpath and the name is fully qualified if it's in a package.");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}