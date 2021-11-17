

INSERT INTO user(id, username, password) VALUES('fa469d9f-8785-4b9a-9561-7f4123b28788', 'Test User', '$2a$10$goaOjFyvwRU2BeUQIlU9h.OtpVMlcgLkpTPZ8XPBIu4qcy9CcuLA.');

INSERT INTO book_wishlist(id, name, owner_id) VALUES('c39ce195-e6d1-473b-aabe-7023d6c92bc4', 'Test Wishlist', 'fa469d9f-8785-4b9a-9561-7f4123b28788');
INSERT INTO book_wishlist(id, name, owner_id) VALUES('8d283e9f-38b5-4619-99a4-7c043f6b24ce', 'Test Wishlist2', 'fa469d9f-8785-4b9a-9561-7f4123b28788');

INSERT INTO book(id, authors, isbn13, wishlist_id, price, subtitle, title, url, year) VALUES('402860817d0ee7ac017d0f5effd30002', 'David Coffin', '9781430238317', 'c39ce195-e6d1-473b-aabe-7023d6c92bc4', '$46.78', 'Programming Secure Oracle Database Applications With Java', 'Expert Oracle and Java Security', 'https://itbook.store/books/9781430238317', '2011')