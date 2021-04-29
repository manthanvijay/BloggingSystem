package com.wheelseye.Blogging.controller;

import com.wheelseye.Blogging.Entity.SubComment;
import com.wheelseye.Blogging.dto.CommentDTO;
import com.wheelseye.Blogging.dto.SubCommentDTO;
import com.wheelseye.Blogging.request.CreateSubComment;
import com.wheelseye.Blogging.service.SubCommentService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Data
@RequestMapping("/subcomment")
public class SubCommentController {
    @Autowired
    private SubCommentService subCommentService;

    @GetMapping("")
    public List<SubCommentDTO> getAllSubComments(){
        return subCommentService.getAllSubComments();
    }

    @PostMapping("")
    public SubCommentDTO createSubComment(@RequestBody CreateSubComment request) throws Exception {
        return subCommentService.createSubComment(request);
    }

    @PutMapping("/{id}/update")
    public SubCommentDTO updateSubComment(@PathVariable("id") Integer id, @RequestBody CreateSubComment request) throws Exception {
        return subCommentService.updateSubComment(id,request);
    }

    @PostMapping("/{id}/likes")
    public SubCommentDTO like(@PathVariable("id") Integer id) throws Exception {
        return subCommentService.like(id);
    }

    @PostMapping("/{id}/dislikes")
    public SubCommentDTO disLike(@PathVariable("id") Integer id) throws Exception {
        return subCommentService.disLike(id);
    }
}
