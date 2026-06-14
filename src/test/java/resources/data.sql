-- Updated User: Ensure password is at least 6 characters to satisfy DTO validation

INSERT INTO users (id, email, password, name) VALUES (1, 'test@example.com', 'password123', 'Test User');

-- Accounts covering the IDs Specmatic is testing

INSERT INTO accounts (id, balance, user_id) VALUES (331, 1000.0, 1);
INSERT INTO accounts (id, balance, user_id) VALUES (207, 1000.0, 1);
INSERT INTO accounts (id, balance, user_id) VALUES (146, 1000.0, 1);
INSERT INTO accounts (id, balance, user_id) VALUES (810, 1000.0, 1);
INSERT INTO accounts (id, balance, user_id) VALUES (723, 1000.0, 1);
INSERT INTO accounts (id, balance, user_id) VALUES (456, 1000.0, 1);
INSERT INTO accounts (id, balance, user_id) VALUES (535, 1000.0, 1);
INSERT INTO accounts (id, balance, user_id) VALUES (805, 1000.0, 1);
INSERT INTO accounts (id, balance, user_id) VALUES (528, 1000.0, 1);
INSERT INTO accounts (id, balance, user_id) VALUES (106, 1000.0, 1);