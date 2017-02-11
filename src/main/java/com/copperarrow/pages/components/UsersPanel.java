package com.copperarrow.pages.components;

import com.copperarrow.model.UserAccount;
import com.copperarrow.model.dataproviders.UserAccountDataProvider;
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable;
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn;
import org.apache.wicket.extensions.markup.html.repeater.data.table.PropertyColumn;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbeer on 11/02/17.
 */
public class UsersPanel extends Panel {

    public UsersPanel(String id) {
        super(id);
        FeedbackPanel feedbackPanel = new FeedbackPanel("feedback");
        feedbackPanel.setOutputMarkupId(true);
        add(feedbackPanel);
        add(usersTable());
    }

    private DataTable<UserAccount, String> usersTable() {
        List<IColumn<UserAccount, String>> columns = new ArrayList<>();
        columns.add(new PropertyColumn<>(Model.of("First Name"), "firstName", "firstName"));
        columns.add(new PropertyColumn<>(Model.of("Last Name"), "lastName"));
        columns.add(new PropertyColumn<>(Model.of("Email Address"), "email"));
        columns.add(new PropertyColumn<>(Model.of("Username"), "userName"));

        DataTable<UserAccount, String> dataTable = new DataTable<>("users-table", columns, new UserAccountDataProvider(), 10);
        dataTable.setOutputMarkupId(true);
        return dataTable;
    }
}
