INSERT INTO test.cass_user_role(id, role) VALUES (1, 'USER');

INSERT INTO test.cass_users(userid, email_address, enabled, firstname, lastname, password, username) VALUES (1, 'email', 1, 'first', 'last', 'password', 'user');

INSERT INTO test.cass_users_cass_user_role(useraccount_userid, userroles_id) VALUES (1,1);

INSERT INTO test.cass_user_role(id, role) VALUES (2, 'ADMIN');

INSERT INTO test.cass_users(userid, email_address, enabled, firstname, lastname, password, username) VALUES (2, 'email', 1, 'first', 'last', 'password', 'admin');

INSERT INTO test.cass_users_cass_user_role(useraccount_userid, userroles_id) VALUES (2,2);
