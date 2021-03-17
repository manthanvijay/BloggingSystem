package com.wheelseye.Blogging.Entity;

import com.vladmihalcea.hibernate.type.array.ListArrayType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.springframework.beans.factory.annotation.Value;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@TypeDef(
        name = "list-array",
        typeClass = ListArrayType.class
)
@Table(name = "vlogs")
@Getter @Setter
public class Vlog implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "vlog_id", unique = true, nullable = false)
    private Integer vlogId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="author_id")
    private Author author;

    @Column(name ="author_id", insertable = false, updatable = false)
    private Integer authorId;

    @Column(name = "subj")
    private String subj;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Integer likes=0;

    @Column(name = "dislikes")
    private Integer dislikes=0;

    @Column(name ="created_at")
    private Date createdAt;

    @Type(type="list-array")
    @Column(name = "tags",columnDefinition = "text[]")
    private List<String> tags;
}
