package com.wheelseye.Blogging.controller;

import com.wheelseye.Blogging.Entity.Merge;
import com.wheelseye.Blogging.dto.MergeDTO;
import com.wheelseye.Blogging.request.MergeRequest;
import com.wheelseye.Blogging.service.MergeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mergecomment")
public class MergeController {
    @Autowired
    private MergeService mergeService;

    @PostMapping("")
    public MergeDTO create(@RequestBody MergeRequest request) throws Exception {
        return mergeService.create(request);
    }
}
