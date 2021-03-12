package com.wheelseye.Blogging.repo;

import com.wheelseye.Blogging.Entity.Vlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.List;

public interface VlogRepository extends JpaRepository<Vlog, Integer> {
    @Query("select v FROM Vlog v order by v.likes DESC")
    List<Vlog> findAllOrderByVlogLikeDsc();

    Vlog findByVlogId(Integer vlogId);

    List<Vlog> findByAuthorId(Integer id);
}

