package com.ridelr.site;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = {RidelrApplication.class})
public class RidelrApplicationTests extends AbstractTestNGSpringContextTests {

    @Autowired
    Environment environment;

    @BeforeClass
    public void setupd() {
        System.out.println("server.port=" + environment.getProperty("server.port"));
    }

    @Test
    public void testContext() throws Exception {

    }

}
