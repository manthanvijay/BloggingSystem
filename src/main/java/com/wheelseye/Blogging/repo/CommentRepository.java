package com.wheelseye.Blogging.repo;

import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.Entity.Vlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    @Query("FROM Comment order by createdAt DESC")
    List<Comment> findAllOrderByCommentDateDsc();

    Comment findByCmtId(Integer id);

    List<Comment> findByVlogId(Integer id);

}
