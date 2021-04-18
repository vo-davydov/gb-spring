DELETE
from sys_role;
DELETE
from sys_user;
BEGIN;
insert into sys_user (user_name, password)
VALUES ('admin', '123456');
insert into sys_user (user_name, password)
VALUES ('manager', '123456');
COMMIT;
END;
BEGIN;
insert into sys_role (role, user_id)
VALUES ('ROLE_ADMIN', (select id from sys_user where user_name = 'admin'));
insert into sys_role (role, user_id)
VALUES ('ROLE_MANAGER', (select id from sys_user where user_name = 'manager'));
COMMIT;
END;
