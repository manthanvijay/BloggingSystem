package com.wheelseye.Blogging.repo;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.Entity.SubComment;
import com.wheelseye.Blogging.dto.SubCommentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubCommentRepository extends JpaRepository<SubComment,Integer> {
    List<SubComment> findByCmtId(Integer id);

    SubComment findBySubCommentId(Integer id);

}
