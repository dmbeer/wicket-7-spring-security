package com.copperarrow.pages;

import com.copperarrow.WicketApplicationTest;
import com.copperarrow.auth.config.SecurityWebApplicationInitializer;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by dbeer on 11/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityWebApplicationInitializer.class)
public class AdminPageTest extends WicketApplicationTest {

    private WicketTester tester;
    @Autowired
    private ApplicationContext ctx;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tester = getTester();
        tester.startPage(AdminPage.class);
    }

    /**
     * Subclasses can use this method to provide the configuration needed by
     * each test.
     */
    @Override
    protected void setupTest() {

    }

    @Test
    public void renderSuccessfully() {
        tester.assertRenderedPage(AdminPage.class);
    }
}