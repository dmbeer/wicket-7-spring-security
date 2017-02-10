package com.copperarrow.dao;

import com.copperarrow.model.UserAccount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by dbeer on 19/01/17.
 */
@Stateless(name = "UserDAO")
public class UserDAO extends AbstractDAO<UserAccount> {

    private static Logger logger = LogManager.getLogger(UserDAO.class.getName());

    @PersistenceContext(unitName = "postgresql")
    private EntityManager em;

    public UserDAO() {
        super(UserAccount.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserAccount getByUserName(String username) {
        UserAccount userAccount = null;
//        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(getEntityManager());
//        QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(UserAccount.class).get();
//        Query luceneQuery = queryBuilder.keyword().onFields("userName").matching(username).createQuery();
        Session session = (Session) getEntityManager().getDelegate();
        Criteria criteria = session.createCriteria(UserAccount.class);
        List<UserAccount> results = criteria.add(Restrictions.eq("userName", username)).list();
        if (results.size() == 1) {
            userAccount = results.get(0);
        }
        return userAccount;
    }

}
