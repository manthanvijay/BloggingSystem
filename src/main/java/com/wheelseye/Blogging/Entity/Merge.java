package com.wheelseye.Blogging.Entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "merge")
@Data
public class Merge implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vlog_id")
    private Vlog vlog;

    @Column(name ="vlog_id", insertable = false, updatable = false)
    private Integer vlogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name ="author_id", insertable = false, updatable = false)
    private Integer authorId;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Integer likes=0;

    @Column(name = "dislikes")
    private Integer dislikes=0;

    @Column(name ="created_at")
    private Date createdAt;

    @Column(name = "parent_id")
    private Integer parentId;
}
