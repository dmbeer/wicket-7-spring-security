package com.copperarrow.pages;

import com.copperarrow.pages.components.UsersPanel;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;

/**
 * Created by dbeer on 11/02/17.
 */
@AuthorizeInstantiation("ROLE_ADMIN")
public class AdminPage extends WebPage {

    public AdminPage() {
        add(new UsersPanel("users-panel"));
    }
}