package com.wheelseye.Blogging.controller;

import com.wheelseye.Blogging.Entity.SubComment;
import com.wheelseye.Blogging.dto.CommentDTO;
import com.wheelseye.Blogging.dto.SubCommentDTO;
import com.wheelseye.Blogging.request.CreateComment;
import com.wheelseye.Blogging.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("")
    public List<CommentDTO> getAllComments(){ return commentService.getAllComments(); }

    @PostMapping("")
    public CommentDTO createComment(@RequestBody CreateComment request) throws Exception {
        return commentService.createComment(request);
    }

    @PutMapping("/{id}/update")
    public CommentDTO updateComment(@PathVariable("id")Integer id, @RequestBody CreateComment request) throws Exception {
        return commentService.updateComment(id,request);
    }

    @GetMapping("/{id}/mysubcomments")
    public List<SubCommentDTO> getMySubComments(@PathVariable("id") Integer id){
        return commentService.getMySubComments(id);
    }

    @PostMapping("/{id}/likes")
    public CommentDTO like(@PathVariable("id") Integer id) throws Exception {
        return commentService.like(id);
    }

    @PostMapping("/{id}/dislikes")
    public CommentDTO disLike(@PathVariable("id") Integer id) throws Exception {
        return commentService.disLike(id);
    }
}
