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
    private List<CommentDTO> getAllComments(){ return commentService.getAllComments(); }

    @PostMapping("")
    private CommentDTO createComment(@RequestBody CreateComment request) throws Exception {
        return commentService.createComment(request);
    }

    @PutMapping("/{id}/update")
    private CommentDTO updateComment(@PathVariable("id")Integer id, @RequestBody CreateComment request) throws Exception {
        return commentService.updateComment(id,request);
    }

    @GetMapping("/{id}/mysubcomments")
    private List<SubCommentDTO> getMySubComments(@PathVariable("id") Integer id){
        return commentService.getMySubComments(id);
    }

    @PostMapping("/{id}/likes")
    private CommentDTO like(@PathVariable("id") Integer id) throws Exception {
        return commentService.like(id);
    }

    @PostMapping("/{id}/dislikes")
    private CommentDTO disLike(@PathVariable("id") Integer id) throws Exception {
        return commentService.disLike(id);
    }
}
