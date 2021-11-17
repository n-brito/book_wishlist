package br.com.inatel.book_wishlist.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.book_wishlist.model.User;
import br.com.inatel.book_wishlist.service.UserService;

@RestController
@RequestMapping("/signup")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody @Valid UserForm userForm) {
               
    	return ResponseEntity.status(201).body(service.createUser(userForm.createUser()));
    }
}
