package com.wheelseye.Blogging.controller;

import com.wheelseye.Blogging.dto.CommentDTO;
import com.wheelseye.Blogging.dto.PageDTO;
import com.wheelseye.Blogging.dto.VlogDTO;
import com.wheelseye.Blogging.request.CreateVlog;
import com.wheelseye.Blogging.service.VlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vlog")
public class VlogController {
    @Autowired
    private VlogService vlogService;

    @GetMapping("")
    public PageDTO<VlogDTO> getAllVlogs(@RequestParam(defaultValue = "0")Integer pageNo,@RequestParam(defaultValue = "2")Integer pageSize) {
        return vlogService.getAllVlogs(pageNo,pageSize);
    }

    @PostMapping("")
    public VlogDTO createVlog(@RequestBody CreateVlog request) throws Exception {
        return vlogService.createVlog(request);
    }

    @GetMapping("/{id}/comments")
    public List<CommentDTO> getMyComment(@PathVariable("id") Integer id) throws Exception {
        return vlogService.getMyComment(id);
    }

    @PutMapping("/{id}/updatevlog")
    public VlogDTO updateVlog(@PathVariable("id") Integer id, @RequestBody CreateVlog request) throws Exception {
        return vlogService.updateVlog(id,request);
    }

    @GetMapping("/search")
    public List<VlogDTO> findVlogByTags(@RequestParam(value = "tags") List<String> searchTag){
        return vlogService.findVlogByTags(searchTag);
    }

    @PostMapping("/{id}/likes")
    public VlogDTO like(@PathVariable("id") Integer id) throws Exception {
        return vlogService.like(id);
    }

    @PostMapping("/{id}/dislikes")
    public VlogDTO disLike(@PathVariable("id") Integer id) throws Exception {
        return vlogService.disLike(id);
    }
}

