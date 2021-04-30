package com.wheelseye.Blogging.repo;

import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.dto.VlogDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;

public interface VlogRepository extends JpaRepository<Vlog, Integer> {

    //Page<VlogDTO> findAll(Pageable pageable);

    @Query("select v FROM Vlog v order by v.likes DESC")
    Page<Vlog> findAllOrderByVlogLikeDsc(Pageable pageable);

    Vlog findByVlogId(Integer vlogId);

    List<Vlog> findByAuthorIdOrderByCreatedAtDesc(Integer id);
}

