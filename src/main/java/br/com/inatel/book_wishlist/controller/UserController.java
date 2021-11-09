package br.com.inatel.book_wishlist.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.book_wishlist.model.User;
import br.com.inatel.book_wishlist.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public Object createUser(@RequestBody @Valid UserForm userForm) {
        return service.createUser(userForm.createUser());
    }
}
