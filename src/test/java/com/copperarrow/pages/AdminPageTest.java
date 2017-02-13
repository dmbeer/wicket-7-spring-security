package com.copperarrow.pages;

import com.copperarrow.WicketApplicationTest;
import com.copperarrow.auth.config.SecurityWebApplicationInitializer;
import com.copperarrow.dao.EntitiesDAO;
import com.copperarrow.dao.UserDAO;
import com.copperarrow.model.UserAccount;
import com.copperarrow.model.dataproviders.UserAccountDataProvider;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by dbeer on 11/02/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SecurityWebApplicationInitializer.class)
public class AdminPageTest extends WicketApplicationTest {

    private WicketTester tester;
//    @SpringBean
    private EntitiesDAO userDAO;
    private UserAccountDataProvider userAccountDataProvider;

//    @Before
//    public void setUp() throws Exception {
//        super.setUp();
//        tester = getTester();
//        tester.startPage(AdminPage.class);
//    }

    /**
     * Subclasses can use this method to provide the configuration needed by
     * each test.
     */
    @Override
    protected void setupTest() {
//        MockitoAnnotations.initMocks(this);
        userAccountDataProvider = mock(UserAccountDataProvider.class);
        userDAO = mock(UserDAO.class);

        when(userDAO.size()).thenReturn(1);
        when(userDAO.sort(anyString(), anyString())).thenReturn(Collections.singletonList(new UserAccount()));
        addMock("java:module/UserAcDataProvider", userAccountDataProvider);
        addMock(userDAO);
    }

    @Test
    public void renderSuccessfully() {
        tester = getTester();
        tester.startPage(AdminPage.class);
        tester.assertRenderedPage(AdminPage.class);
    }
}
