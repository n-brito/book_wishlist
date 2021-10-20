

INSERT INTO USER(id, username, password) VALUES(1, 'Test User', '123');

INSERT INTO BOOK_WISHLIST(id, name, owner_id) VALUES(1, 'Test Wishlist', 1);
INSERT INTO BOOK_WISHLIST(id, name, owner_id) VALUES(2, 'Test Wishlist', 1);

INSERT INTO BOOK(id, isbn13, wishlist_id) VALUES(1, '123456789', 1);
INSERT INTO BOOK(id, isbn13, wishlist_id) VALUES(2, '111111111', 1);
INSERT INTO BOOK(id, isbn13, wishlist_id) VALUES(3, '222222222', 2);
