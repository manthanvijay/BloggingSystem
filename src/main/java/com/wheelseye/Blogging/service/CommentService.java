package com.wheelseye.Blogging.service;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.converter.CommentConverter;
import com.wheelseye.Blogging.converter.SubCommentConverter;
import com.wheelseye.Blogging.dto.CommentDTO;
import com.wheelseye.Blogging.dto.SubCommentDTO;
import com.wheelseye.Blogging.repo.AuthorRepository;
import com.wheelseye.Blogging.repo.CommentRepository;
import com.wheelseye.Blogging.repo.SubCommentRepository;
import com.wheelseye.Blogging.repo.VlogRepository;
import com.wheelseye.Blogging.request.CreateComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private VlogRepository vlogRepository;

    @Autowired
    private SubCommentRepository subCommentRepository;

    public List<CommentDTO> getAllComments(){
        return commentRepository.findAllOrderByCommentDateDsc().stream().map(CommentConverter::converter).collect(Collectors.toList());
    }

    public CommentDTO createComment(CreateComment request) throws Exception {
        Author author = authorRepository.findByAuthorId(request.getAuthorId());
        Vlog vlog = vlogRepository.findByVlogId(request.getVlogId());
        if(null == author || null == vlog)
            throw new Exception("Invalid request");
        Comment comment = new Comment();
        comment.setCreatedAt(new Date());
        comment.setAuthor(author);
        comment.setContent(request.getContent());
        comment.setVlog(vlog);
        String name = author.getName();
        return CommentConverter.converter(commentRepository.save(comment));
    }

    public CommentDTO updateComment(Integer id, CreateComment request) throws Exception {
        Comment comment=commentRepository.findByCmtId(id);
        if(comment==null)
            throw new Exception("Comment not found");
        if(comment.getAuthorId()!=request.getAuthorId())
            throw new Exception("Invalid attempt");
        if(request.getContent()!=null)
            comment.setContent(request.getContent());
        comment.setCreatedAt(new Date());
        return CommentConverter.converter(commentRepository.save(comment));
    }

    public List<SubCommentDTO> getMySubComments(Integer id){
        return subCommentRepository.findByCmtId(id).stream().map(SubCommentConverter::converter).collect(Collectors.toList());
    }

    public CommentDTO like(Integer id) throws Exception {
        Comment comment = commentRepository.findByCmtId(id);
        if(comment==null)
            throw new Exception("comment not found");
        comment.setLikes(comment.getLikes()+1);
        return CommentConverter.converter(commentRepository.save(comment));
    }

    public CommentDTO disLike(Integer id) throws Exception {
        Comment comment = commentRepository.findByCmtId(id);
        if(comment==null)
            throw new Exception("comment not found");
        comment.setDislikes(comment.getDislikes()+1);
        return CommentConverter.converter(commentRepository.save(comment));
    }
}
