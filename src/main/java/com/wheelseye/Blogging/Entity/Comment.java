package com.wheelseye.Blogging.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "comments")
@Getter @Setter
public class Comment implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cmt_id", unique = true, nullable = false)
    private Integer cmtId;

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

    //@OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
    //private List<SubComment> subComments;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @Column(name ="created_at")
    private Date createdAt;

}
