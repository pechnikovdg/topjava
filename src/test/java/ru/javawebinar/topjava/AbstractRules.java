package ru.javawebinar.topjava;

import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.rules.ExternalResource;
import org.junit.rules.TestName;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public abstract class AbstractRules {

    private static long startTime;
    private static final StringBuilder testsTime = new StringBuilder();

    @Rule
    public final TestName testName = new TestName();

    @Rule
    public final TestRule watchman = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            super.starting(description);
            startTime = System.currentTimeMillis();
        }

        @Override
        protected void finished(Description description) {
            super.finished(description);
            String testTime = String.format("Time for test \"%s\" - %d ms\n", testName.getMethodName(), (System.currentTimeMillis() - startTime));
            testsTime.append(testTime);
            System.out.print(testTime);
        }
    };

    @ClassRule
    public static final ExternalResource externalResource = new ExternalResource() {
        @Override
        protected void after() {
            System.out.println(testsTime.toString());
        }
    };
}
