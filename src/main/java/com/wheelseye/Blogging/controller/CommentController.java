package com.wheelseye.Blogging.controller;

import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.request.CreateCommentRequest;
import com.wheelseye.Blogging.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("")
    private List<Comment> getAllComments(){ return commentService.getAllComments(); }

    @PostMapping("")
    private Comment createComment(@RequestBody CreateCommentRequest request) throws Exception {
        return commentService.createComment(request);
    }

    @PutMapping("/{id}/update")
    private Comment updateComment(@PathVariable("id")Integer id, @RequestBody CreateCommentRequest request) throws Exception {
        return commentService.updateComment(id,request);
    }
}
