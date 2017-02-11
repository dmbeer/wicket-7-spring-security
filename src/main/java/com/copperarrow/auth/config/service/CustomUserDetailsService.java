package com.copperarrow.auth.config.service;

import com.copperarrow.auth.config.CustomUserAccountDetails;
import com.copperarrow.dao.UserDAO;
import com.copperarrow.model.UserAccount;
import com.copperarrow.model.UserRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.ejb.EJB;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbeer on 18/01/17.
 */
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

    private static Logger logger = LogManager.getLogger(CustomUserDetailsService.class.getName());

    @EJB(mappedName = "java:module/UserDAO")
    private UserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount userAccount = userDAO.getByUserName(username);
        if (null == userAccount) {
            logger.fatal("Username {} not Found", username);
            throw new UsernameNotFoundException("No userAccount present with username: " + username);
        } else {
            List<UserRole> userRoles = userAccount.getUserRoles();
            return new CustomUserAccountDetails(userAccount, userRoles);
        }
//        return null;
    }
}