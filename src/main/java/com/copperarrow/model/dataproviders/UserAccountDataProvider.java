package com.copperarrow.model.dataproviders;

import com.copperarrow.dao.UserDAO;
import com.copperarrow.model.UserAccount;
import org.apache.wicket.cdi.NonContextual;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dbeer on 11/02/17.
 */
@Stateless(name = "UserAcDataProvider")
public class UserAccountDataProvider extends SortableDataProvider<UserAccount, String> {

    @EJB(name = "UserDAO")
    private UserDAO userDAO;

    public UserAccountDataProvider() {
        setSort("firstName", SortOrder.ASCENDING);
    }

    private void getDAO() {
        if (userDAO == null) {
            NonContextual.of(UserAccountDataProvider.class).inject(this);
        }
    }

    @Override
    public Iterator<UserAccount> iterator(long first, long last) {
        getDAO();
        List<UserAccount> users = userDAO.sort(getSort().toString(), "firstName");

        return users.subList((int)first, (int)(first + last)).iterator();
    }

    @Override
    public long size() {
        getDAO();
        return userDAO.size();
    }

    @Override
    public IModel<UserAccount> model(UserAccount userAccount) {
        return Model.of(userAccount);
    }
}
