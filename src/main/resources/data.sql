CREATE TABLE IF NOT EXISTS NOTE (
                                    id INT AUTO_INCREMENT PRIMARY KEY,
                                    owner VARCHAR(255),
    title VARCHAR(255),
    content VARCHAR(255)
    );


INSERT INTO NOTE (owner, title, content) VALUES ('alice', 'Shopping', 'Buy milk and eggs');
INSERT INTO NOTE (owner, title, content) VALUES ('max', 'Shopping', 'Buy milk and eggs');
INSERT INTO NOTE (owner, title, content) VALUES ('luca', 'Gaming', 'Fortineiti');