package net.igorkromin;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public abstract class Exercise {

    public void out() {
     out("");
    }

    public void out(Object message) {
        System.out.println(message);
    }

    /**
     * Runs all public methods exposed on the Exercise object
     */
    static protected void run(Exercise exercise) {
        Class clazz = exercise.getClass();
        Method[] methods = clazz.getDeclaredMethods();

        System.out.println("== Exercise: " + clazz.getSimpleName() + " ==");

        for (Method method : methods) {
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && !Modifier.isStatic(modifiers)) {
                try {
                    System.out.println("Solution: " + method.getName());
                    System.out.println();

                    long startTime = System.currentTimeMillis();
                    method.invoke(exercise);

                    System.out.println("\nCompleted in " + (System.currentTimeMillis() - startTime) + " ms\n");
                }
                catch (Exception e) {
                    System.err.println("Could not run method: " + method.getName());
                }
            }
        }
    }

}
