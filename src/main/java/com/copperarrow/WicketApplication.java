package com.copperarrow;

import com.copperarrow.auth.SecureWebSession;
import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * Application object for your web application.
 * If you want to run this application without deploying, run the Start class.
 *
 * @see com.copperarrow.Start#main(String[])
 */
public class WicketApplication extends AuthenticatedWebApplication {
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

        getComponentInstantiationListeners().add(new SpringComponentInjector(this));
        mountPage("login", LoginPage.class);
        mountPage("user", UserPage.class);
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
