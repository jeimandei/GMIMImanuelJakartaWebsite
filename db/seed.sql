INSERT INTO ROLES (ROLE_NAME, DESCRIPTION) VALUES ('ROLE_SUPER_ADMIN', 'Full access to everything');
INSERT INTO ROLES (ROLE_NAME, DESCRIPTION) VALUES ('ROLE_ADMIN', 'Manage CMS, events, media, users except super admin');
INSERT INTO ROLES (ROLE_NAME, DESCRIPTION) VALUES ('ROLE_EDITOR', 'Manage CMS pages, announcements, sermons, events');
INSERT INTO ROLES (ROLE_NAME, DESCRIPTION) VALUES ('ROLE_MEMBER', 'Logged-in church member');
INSERT INTO ROLES (ROLE_NAME, DESCRIPTION) VALUES ('ROLE_GUEST', 'Public visitor');

INSERT INTO SITE_SETTINGS (SETTING_KEY, SETTING_VALUE, DESCRIPTION) VALUES ('church.name', 'GMIM Imanuel Jakarta', 'Church display name');
INSERT INTO SITE_SETTINGS (SETTING_KEY, SETTING_VALUE, DESCRIPTION) VALUES ('church.email', 'info@gmimimanueljakarta.or.id', 'Church public email');
INSERT INTO SITE_SETTINGS (SETTING_KEY, SETTING_VALUE, DESCRIPTION) VALUES ('church.phone', '+62-21-0000000', 'Church phone number');
INSERT INTO SITE_SETTINGS (SETTING_KEY, SETTING_VALUE, DESCRIPTION) VALUES ('church.youtube', 'https://www.youtube.com/@gmimimanueljakarta', 'Church YouTube channel');
