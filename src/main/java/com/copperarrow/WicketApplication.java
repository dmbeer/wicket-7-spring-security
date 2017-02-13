package com.copperarrow;

import com.copperarrow.auth.SecureWebSession;
import com.copperarrow.dao.UserDAO;
import com.copperarrow.pages.AdminPage;
import com.copperarrow.pages.HomePage;
import com.copperarrow.pages.LoginPage;
import com.copperarrow.pages.UserPage;
import org.apache.wicket.Application;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.apache.wicket.spring.test.ApplicationContextMock;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 */
public class WicketApplication extends AuthenticatedWebApplication {

    public WicketApplication() {
        super();
    }

    /**
     * Get Application for current thread.
     *
     * @return The current thread's Application
     */
    public static WicketApplication get() {
        return (WicketApplication) Application.get();
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        configureCDIEJB();

        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        mountPage("login", LoginPage.class);
        mountPage("user", UserPage.class);
        mountPage("admin", AdminPage.class);
    }

    private void configureCDIEJB() {
//        getComponentInstantiationListeners().add(new JavaEEComponentInjector(this, new ModuleJndiNamingStrategy()));
//        new CdiConfiguration().configure(this);
    }

    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return SecureWebSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }
}
