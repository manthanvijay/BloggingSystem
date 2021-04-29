package com.wheelseye.Blogging.repo;

import com.wheelseye.Blogging.Entity.Merge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MergeRepository extends JpaRepository<Merge,Integer> {

}
