package com.wheelseye.Blogging.controller;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.dto.AuthorDTO;
import com.wheelseye.Blogging.request.Login;
import com.wheelseye.Blogging.request.SignUp;
import com.wheelseye.Blogging.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpSignInController {

    @Autowired
    private AuthorService authorService;

    @PostMapping("/signup")       //when author signups then this method will be called.
    private AuthorDTO create(@RequestBody SignUp request){ return authorService.create(request); }

    @GetMapping("/login")      //When user will login.
    private AuthorDTO login(@RequestBody Login request) throws Exception {
        return authorService.login(request);
    }
}
