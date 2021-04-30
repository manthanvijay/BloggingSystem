package com.wheelseye.Blogging.service;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.Entity.SubComment;
import com.wheelseye.Blogging.converter.CommentConverter;
import com.wheelseye.Blogging.converter.SubCommentConverter;
import com.wheelseye.Blogging.dto.CommentDTO;
import com.wheelseye.Blogging.dto.SubCommentDTO;
import com.wheelseye.Blogging.repo.AuthorRepository;
import com.wheelseye.Blogging.repo.CommentRepository;
import com.wheelseye.Blogging.repo.SubCommentRepository;
import com.wheelseye.Blogging.request.CreateSubComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class SubCommentService {
    @Autowired
    private SubCommentRepository subCommentRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public List<SubCommentDTO> getAllSubComments(){
        return subCommentRepository.findAll().stream().map(SubCommentConverter::converter).collect(Collectors.toList());
    }

    public SubCommentDTO createSubComment(CreateSubComment request) throws Exception {
        Comment comment = commentRepository.findByCmtId(request.getCommentId());
        Author author = authorRepository.findByAuthorId(request.getAuthorId());
        if(comment == null || author == null)
            throw new Exception("Invalid request");
        if(request.getContent()==null)
            throw new Exception("Sub Comment can't be empty");
        SubComment subComment=new SubComment();
        subComment.setCreatedAt(new Date());
        subComment.setComment(comment);
        subComment.setAuthor(author);
        subComment.setContent(request.getContent());
        return SubCommentConverter.converter(subCommentRepository.save(subComment));
    }

    public SubCommentDTO updateSubComment(Integer id, CreateSubComment request) throws Exception {
        SubComment subComment = subCommentRepository.findBySubCommentId(id);
        if(subComment.getAuthorId()!=request.getAuthorId())
            throw new Exception("Invalid request");
        subComment.setCreatedAt(new Date());
        subComment.setContent(request.getContent());
        return SubCommentConverter.converter(subCommentRepository.save(subComment));
    }

    public SubCommentDTO like(Integer id) throws Exception {
        SubComment subComment = subCommentRepository.findBySubCommentId(id);
        if(subComment==null)
            throw new Exception("comment not found");
        subComment.setLikes(subComment.getLikes()+1);
        return SubCommentConverter.converter(subCommentRepository.save(subComment));
    }

    public SubCommentDTO disLike(Integer id) throws Exception {
        SubComment subComment = subCommentRepository.findBySubCommentId(id);
        if(subComment==null)
            throw new Exception("comment not found");
        subComment.setDislikes(subComment.getDislikes()+1);
        return SubCommentConverter.converter(subCommentRepository.save(subComment));
    }
}
