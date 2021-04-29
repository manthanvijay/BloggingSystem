package com.wheelseye.Blogging.service;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Merge;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.converter.MergeConverter;
import com.wheelseye.Blogging.dto.MergeDTO;
import com.wheelseye.Blogging.repo.AuthorRepository;
import com.wheelseye.Blogging.repo.MergeRepository;
import com.wheelseye.Blogging.repo.VlogRepository;
import com.wheelseye.Blogging.request.MergeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MergeService {
    @Autowired
    private MergeRepository mergeRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private VlogRepository vlogRepository;

    public MergeDTO create(MergeRequest req) throws Exception {
        Author author = authorRepository.findByAuthorId(req.getAuthorId());
        Vlog vlog = vlogRepository.findByVlogId(req.getVlogId());
        if(null == author)
            throw new Exception("Invalid request");
        Merge merge = new Merge();
        merge.setCreatedAt(new Date());
        merge.setAuthor(author);
        merge.setContent(req.getContent());
        if(req.getVlogId()!=null)
            merge.setVlog(vlog);
        if(req.getParentId()!=null)
            merge.setParentId(req.getParentId());
        return MergeConverter.converter(mergeRepository.save(merge));
    }
}
