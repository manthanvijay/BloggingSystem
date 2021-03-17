package com.wheelseye.Blogging.controller;

import java.util.List;
import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.dto.AuthorDTO;
import com.wheelseye.Blogging.dto.CommentDTO;
import com.wheelseye.Blogging.dto.VlogDTO;
import com.wheelseye.Blogging.request.ChangePassword;
import com.wheelseye.Blogging.request.SignUp;
import com.wheelseye.Blogging.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("")           //when you want to see all the users
    private List<AuthorDTO> getAllAuthors() { return authorService.getAllAuthors(); }

    @GetMapping("/{id}")  //getting author details
    private AuthorDTO findById(@PathVariable("id") Integer id){
        return authorService.findById(id);
    }

    @GetMapping("/{id}/vlogs")
    private List<VlogDTO> getMyVlogs(@PathVariable("id") Integer id) throws Exception {
        return authorService.getMyVlogs(id);
    }

    @GetMapping("/{id}/comments")
    private List<CommentDTO> getMyComments(@PathVariable("id") Integer id) throws Exception {
        return authorService.getMyComments(id);
    }

    @PutMapping("/{id}/password")
    private AuthorDTO updatePassword(@PathVariable("id") Integer id, @RequestBody ChangePassword updatePass) throws Exception {
        return authorService.updatePassword(id,updatePass);
    }

    @PutMapping("/{id}/updatedetails")
    private AuthorDTO updatePersonalInfo(@PathVariable("id") Integer id, @RequestBody SignUp updatereq) throws Exception {
        return authorService.updatePersonalInfo(id,updatereq);
    }

}

