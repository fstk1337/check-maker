package com.fstk1337.check.maker;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CheckMakerTest {
    @Test void appHasAGreeting() {
        CheckMaker classUnderTest = new CheckMaker();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
}
