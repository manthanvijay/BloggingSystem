package com.wheelseye.Blogging.service;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.repo.AuthorRepository;
import com.wheelseye.Blogging.repo.CommentRepository;
import com.wheelseye.Blogging.repo.VlogRepository;
import com.wheelseye.Blogging.request.CreateVlogRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VlogService {

    @Autowired
    VlogRepository vlogRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    public List<Vlog> getAllVlogs(){
        return vlogRepository.findAllOrderByVlogLikeDsc();
    }

    public Vlog createVlog(CreateVlogRequest request) throws Exception {
        Author author = authorRepository.findByAuthorId(request.getAuthorId());
        if(null==author)
            throw new Exception("User not found");
        Vlog vlog = new Vlog();
        vlog.setSubj(request.getSubj());
        vlog.setContent(request.getContent());
        vlog.setAuthor(author);
        vlog.setCreatedAt(new Date());
        vlog.setTags(request.getTags());
        return vlogRepository.save(vlog);
    }

    public Vlog updateVlog(Integer id, CreateVlogRequest request) throws Exception {
        Vlog vlog=vlogRepository.findByVlogId(id);
        if(vlog==null)
            return null;
        if(request.getSubj()!=null)
            vlog.setSubj(request.getSubj());
        if(request.getContent()!=null)
            vlog.setContent(request.getContent());
        if(request.getTags()!=null)
            vlog.setTags(request.getTags());
        vlog.setCreatedAt(new Date());
        return vlogRepository.save(vlog);
    }

    public List<Comment> getMyComment(Integer id){
        return commentRepository.findByVlogId(id);
    }


    public List<Vlog> findVlogByTags(List<String> searchTag) {
        List<Vlog> result = new ArrayList<>();
        searchTag.forEach(s->{
            String queryString = "{" + s + "}";
            Query query = entityManager.createNativeQuery(
                    "SELECT * from vlogs V where V.tags @> CAST(?1 AS text[])",
                    Vlog.class
            ).setParameter(1, queryString);
            result.addAll(query.getResultList());
        });
        return result.stream().distinct().collect(Collectors.toList());
    }
}
