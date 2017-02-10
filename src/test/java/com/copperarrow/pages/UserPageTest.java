package com.copperarrow.pages;

import com.copperarrow.WicketApplicationTest;
import com.copperarrow.WithMockUserAccount;
import com.copperarrow.auth.config.SecurityWebApplicationInitializer;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by dbeer on 10/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityWebApplicationInitializer.class)
public class UserPageTest extends WicketApplicationTest {

    private WicketTester tester;
    @Autowired
    private ApplicationContext ctx;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        tester = getTester();
        tester.startPage(UserPage.class);
    }

    /**
     * Subclasses can use this method to provide the configuration needed by
     * each test.
     */
    @Override
    protected void setupTest() {

    }

    @Test
    @WithAnonymousUser
    public void renderSuccessfullyWithErrorText() {
        tester.assertRenderedPage(UserPage.class);
        TagTester tagTester = tester.getTagByWicketId("username");
        assertNotNull(tagTester);
        assertEquals("Should be Logged in User error", "error", tagTester.getValue());
    }

    @Test
    @WithMockUserAccount
    public void renderSuccessfullyWithUserNameText() {
        tester.assertRenderedPage(UserPage.class);
        TagTester tagTester = tester.getTagByWicketId("username");
        assertNotNull(tagTester);
        assertEquals("Should be Logged in User user", "user", tagTester.getValue());
    }

}