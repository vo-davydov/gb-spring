DELETE
from sys_role
where user_id = (select id from sys_user where user_name = 'customer');
DELETE
from sys_user
where user_name = 'customer';
BEGIN;
insert into sys_user (user_name, password)
VALUES ('customer', '123456');
COMMIT;
END;
BEGIN;
insert into sys_role (role, user_id)
VALUES ('ROLE_CUSTOMER', (select id from sys_user where user_name = 'customer'));
COMMIT;
END;
