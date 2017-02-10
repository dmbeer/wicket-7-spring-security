package com.copperarrow;

import com.copperarrow.auth.config.SecurityWebApplicationInitializer;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Simple test using the WicketTester
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityWebApplicationInitializer.class)
@WithAnonymousUser
public class TestHomePage extends WicketApplicationTest {
	private WicketTester tester;
	@Autowired
	private ApplicationContext ctx;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		tester = getTester();
		tester.startPage(HomePage.class);
	}

	/**
	 * Subclasses can use this method to provide the configuration needed by
	 * each test.
	 */
	@Override
	protected void setupTest() {

	}

	@Test
	public void homepageRendersSuccessfully() {
		//assert rendered page class
		tester.assertRenderedPage(HomePage.class);
	}
}
