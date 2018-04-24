package tests;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public class Main {

    public static void main(String[] args) {


        JUnitCore jUnit = new JUnitCore();
        jUnit.addListener(new TextListener(System.out));
        jUnit.run(tests.My_math_interfaceTest.class);
        jUnit.run(tests.Translator_test.class);
        jUnit.run(tests.ProffilingTest.class);


    }

}
