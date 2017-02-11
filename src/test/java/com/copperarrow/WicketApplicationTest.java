package com.copperarrow;

import org.apache.wicket.Page;
import org.apache.wicket.cdi.CdiConfiguration;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.springframework.stereotype.Component;
import org.wicketstuff.javaee.injection.JavaEEComponentInjector;
import org.wicketstuff.javaee.naming.global.ModuleJndiNamingStrategy;

/**
 * Created by dbeer on 15/01/17.
 */
@Component
public abstract class WicketApplicationTest {

    protected static final String USERDAO_BEAN_NAME = "UserDAO";
    protected static final String CUSTOM_USER_DETAILS = "customUserDetailsService";
    private ApplicationContextMock applicationContextMock;

    private WicketTester tester = null;

    @Before
    public void setUp() throws Exception {
        //Creates a new application context mock.
        applicationContextMock = new ApplicationContextMock();

        //Creates a new WicketTester
        tester = new WicketTester(newWebApplication());
        setupTest();
    }

    protected WebApplication newWebApplication() {
        return new WebApplication() {

            @Override
            protected void init() {
                super.init();

                getMarkupSettings().setStripWicketTags(false);
                //Configures the SpringBean annotation support to use the mock application context.
                //This ensures that the mock objects are injected instead of the actual bean classes.
                getComponentInstantiationListeners().add(new SpringComponentInjector(this, applicationContextMock));
                getComponentInstantiationListeners().add(new JavaEEComponentInjector(this, new ModuleJndiNamingStrategy()));
                WicketApplicationTest.this.init(this);
            }

            @Override
            public Class<? extends Page> getHomePage() {
                return WicketApplicationTest.this.getHomePage();
            }

        };
    }

    protected void init(WebApplication app) {}

    protected Class<? extends Page> getHomePage() {
        return Page.class;
    }

    /**
     * Subclasses can use this method to provide the configuration needed by
     * each test.
     */
    protected abstract void setupTest();

    /**
     * Adds mock to the mock application context.
     * @param beanName  The name of the mock bean.
     * @param mock  The mock object.
     */
    protected void addMock(String beanName, Object mock) {
        applicationContextMock.putBean(beanName, mock);
    }

    protected void addMock(Object mock) {
        applicationContextMock.putBean(mock);
    }

    protected ApplicationContextMock getApplicationContextMock() {
        return applicationContextMock;
    }

    protected WicketTester getTester() {
        return tester;
    }
}
