INSERT INTO users (display_name,enabled,"password",username) VALUES
	 ('User 1',true,'$2a$10$XWAUnoKVTOGftJmWpYNO6OXD6UiO0y2B.GOwN2laeOFoEqXZbmYsO','user1'),
	 ('User 2',true,'$2a$10$RQP0cNJStovOrAmL8fWP7.PItxK0cggHQeabAyFjIIBtd5pE8FMbW','user2'),
	 ('User 3',true,'$2a$10$96EhnBXjsQxosivUwxX9b.L.1LndMVUUl.cbIZE6HcFKUmh6A7Zne','user3'),
	 ('User 4',true,'$2a$10$luxuOKZ7sDwqR9fisRTaf.GAiZxdEUZZWY4C1AFUX5WhtohBn22g2','user4'),
	 ('User 5',true,'$2a$10$Lo6WfXyOXYdwTPIqiLSsWO3wxiVS8s7S0qT5/AvNWHnaRCRxtt69e','user5'),
	 ('User 6',true,'$2a$10$BOMEmxLsgy5J4ETegOc/NezEs8kVbbQlpcGIosoi7AHxk82Aqa0rC','user6');

INSERT INTO authorities (username, authority) VALUES
    ('user1', 'ROLE_USER'),
    ('user2', 'ROLE_USER'),
    ('user3', 'ROLE_USER'),
    ('user4', 'ROLE_USER'),
    ('user5', 'ROLE_USER'),
    ('user6', 'ROLE_USER');
