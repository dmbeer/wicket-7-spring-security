package com.copperarrow.model.dataproviders;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;

import com.copperarrow.dao.UserDAO;
import com.copperarrow.model.UserAccount;

/**
 * Created by dbeer on 11/02/17.
 */
@Stateless(name = "UserAcDataProvider")
public class UserAccountDataProvider extends SortableDataProvider<UserAccount, String> {

    @SpringBean
    private UserDAO userDAO;

    public UserAccountDataProvider() {
        Injector.get().inject(this);
        setSort("firstName", SortOrder.ASCENDING);
    }

    @Override
    public Iterator<UserAccount> iterator(long first, long last) {
        List<UserAccount> users = userDAO.sort(getSort().toString(), "firstName");

        return users.subList((int)first, (int)(first + last)).iterator();
    }

    @Override
    public long size() {
        return userDAO.size();
    }

    @Override
    public IModel<UserAccount> model(UserAccount userAccount) {
        return Model.of(userAccount);
    }
}
