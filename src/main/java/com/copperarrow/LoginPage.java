/*
 * Copyright 2017 dbeer.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.copperarrow;

import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.flow.RedirectToUrlException;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 *
 * @author dbeer
 */
public class LoginPage extends WebPage {
    
    private static Logger logger = LogManager.getLogger(LoginPage.class);

    public LoginPage(PageParameters parameters) {
        super(parameters);
        add(loginForm("loginForm"));
    }

    private Form<Void> loginForm(String id) {
        RequiredTextField<String> username = new RequiredTextField<>("username");
        PasswordTextField password = new PasswordTextField("password");
        Form<Void> loginForm = new Form<Void>(id) {
            @Override
            protected void onSubmit() {
                HttpServletRequest servletRequest = (HttpServletRequest) RequestCycle.get().getRequest().getContainerRequest();
//                String originalUrl = getOriginalUrl(servletRequest.getSession());
                AuthenticatedWebSession session = AuthenticatedWebSession.get();
                if (session.signIn(username.getValue(), password.getValue())) {
                    logger.info("Logged In");
                    setResponsePage(HomePage.class);
                } else {
                    logger.error("Login Failed");
                    error("Login failed due to invalid credentials");
                }
            }

        };
        loginForm.add(new FeedbackPanel("feedback"));
        loginForm.add(username);
        loginForm.add(password);

        return loginForm;
    }

}
