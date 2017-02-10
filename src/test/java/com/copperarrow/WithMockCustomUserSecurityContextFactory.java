package com.copperarrow;

import com.copperarrow.auth.config.CustomUserAccountDetails;
import com.copperarrow.model.UserAccount;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbeer on 22/01/17.
 */
public class WithMockCustomUserSecurityContextFactory implements WithSecurityContextFactory<WithMockUserAccount> {
    @Override
    public SecurityContext createSecurityContext(WithMockUserAccount customUser) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        UserRole userRole = new UserRole();
//        userRole.setRole(Role.USER);
//        List<UserRole> userRoles = new ArrayList<>();
//        userRoles.add(userRole);
        UserAccount userAccount = new UserAccount(1L, "first", "last", "joe@example.com", "user", "password", 1);

        CustomUserAccountDetails principal =
                new CustomUserAccountDetails(userAccount, new ArrayList<>());
        Authentication auth =
                new UsernamePasswordAuthenticationToken(principal, "password", principal.getAuthorities());
        context.setAuthentication(auth);
        return context;
    }
}