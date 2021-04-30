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

@RestController    //combination of @Conntroller and @ResponseBody
@RequestMapping("/author")
public class AuthorController {

    @Autowired           //creating a singleton class and object.
    private AuthorService authorService;

    @GetMapping("")           //when you want to see all the users
    public List<AuthorDTO> getAllAuthors() { return authorService.getAllAuthors(); }

    @GetMapping("/{id}")  //getting particular author details
    public AuthorDTO findById(@PathVariable("id") Integer id){
        return authorService.findById(id);
    }

    @GetMapping("/{id}/vlogs")    //getting all the vlogs of a author
    public List<VlogDTO> getMyVlogs(@PathVariable("id") Integer id) throws Exception {
        return authorService.getMyVlogs(id);
    }

    @GetMapping("/{id}/comments")    //getting all the comments of a author
    public List<CommentDTO> getMyComments(@PathVariable("id") Integer id) throws Exception {
        return authorService.getMyComments(id);
    }

    @PutMapping("/{id}/password")    //password change
    public AuthorDTO updatePassword(@PathVariable("id") Integer id, @RequestBody ChangePassword updatePass) throws Exception {
        return authorService.updatePassword(id,updatePass);
    }

    @PutMapping("/{id}/updatedetails")   // personal details change
    public AuthorDTO updatePersonalInfo(@PathVariable("id") Integer id, @RequestBody SignUp updatereq) throws Exception {
        return authorService.updatePersonalInfo(id,updatereq);
    }

}

