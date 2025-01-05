package org.example;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for simple App.
 */
@SpringBootTest(classes = TaskManager_App.class)
public class AppTest {
    @Test
    public void testAppContextLoads() {
        assertThat(true).isTrue();
    }
}
