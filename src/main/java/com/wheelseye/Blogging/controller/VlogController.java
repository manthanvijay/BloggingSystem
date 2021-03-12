package com.wheelseye.Blogging.controller;

import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.request.CreateVlogRequest;
import com.wheelseye.Blogging.service.VlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vlog")
public class VlogController {
    @Autowired
    private VlogService vlogService;

    @GetMapping("")
    private List<Vlog> getAllVlogs(){ return vlogService.getAllVlogs(); }

    @PostMapping("")
    private Vlog createVlog(@RequestBody CreateVlogRequest request) throws Exception {
        return vlogService.createVlog(request);
    }

    @GetMapping("/{id}/comments")
    private List<Comment> getMyComment(@PathVariable("id") Integer id){
        return vlogService.getMyComment(id);
    }


    @PutMapping("/{id}/updatevlog")
    private Vlog updateVlog(@PathVariable("id") Integer id, @RequestBody CreateVlogRequest request) throws Exception {
        return vlogService.updateVlog(id,request);
    }


   /* @PostMapping("/{id}/likes")
    private Vlog likeDislike(@PathVariable("id") Integer id , @RequestBody )
*/
    @GetMapping("/search")
    private List<Vlog> findVlogByTags(@RequestParam(value = "tags") List<String> searchTag){
        return vlogService.findVlogByTags(searchTag);
    }

}

