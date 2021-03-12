package com.wheelseye.Blogging.service;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.repo.AuthorRepository;
import com.wheelseye.Blogging.repo.CommentRepository;
import com.wheelseye.Blogging.repo.VlogRepository;
import com.wheelseye.Blogging.request.CreateCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private VlogRepository vlogRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAllOrderByCommentDateDsc();
    }

    public Comment createComment(CreateCommentRequest request) throws Exception {
        Author author = authorRepository.findByAuthorId(request.getAuthorId());
        Vlog vlog = vlogRepository.findByVlogId(request.getVlogId());
        if(null == author || null == vlog)
            throw new Exception("Invalid request");
        Comment comment = new Comment();
        comment.setCreatedAt(new Date());
        comment.setAuthor(author);
        comment.setContent(request.getContent());
        comment.setVlog(vlog);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Integer id, CreateCommentRequest request) throws Exception {
        Comment comment=commentRepository.findByCmtId(id);
        if(comment==null)
            throw new Exception("Comment not found");
        if(comment.getAuthorId()!=request.getAuthorId() || comment.getVlogId()!=request.getVlogId())
            throw new Exception("Invalid attempt");
        if(request.getContent()!=null)
            comment.setContent(request.getContent());
        comment.setCreatedAt(new Date());
        return commentRepository.save(comment);
    }
}
