package com.wheelseye.Blogging.service;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.converter.CommentConverter;
import com.wheelseye.Blogging.converter.VlogConverter;
import com.wheelseye.Blogging.dto.CommentDTO;
import com.wheelseye.Blogging.dto.PageDTO;
import com.wheelseye.Blogging.dto.VlogDTO;
import com.wheelseye.Blogging.repo.AuthorRepository;
import com.wheelseye.Blogging.repo.CommentRepository;
import com.wheelseye.Blogging.repo.VlogRepository;
import com.wheelseye.Blogging.request.CreateVlog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VlogService {

    @Autowired
    VlogRepository vlogRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;

    public PageDTO<VlogDTO> getAllVlogs(Integer pageNo,Integer pageSize){
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Vlog> vlogs = vlogRepository.findAllOrderByVlogLikeDsc(pageable);

        List<VlogDTO> dtos = vlogs.getContent().stream().map(VlogConverter::converter).collect(Collectors.toList());

        return new PageDTO<VlogDTO>(pageable.getPageNumber(),pageable.getPageSize(),(Long) vlogs.getTotalElements(),dtos);
    }

    public VlogDTO createVlog(CreateVlog request) throws Exception {
        Author author = authorRepository.findByAuthorId(request.getAuthorId());
        if(null==author)
            throw new Exception("User not found");
        Vlog vlog = new Vlog();
        vlog.setSubj(request.getSubj());
        vlog.setContent(request.getContent());
        vlog.setAuthor(author);
        vlog.setCreatedAt(new Date());
        vlog.setTags(request.getTags());
        return VlogConverter.converter(vlogRepository.save(vlog));
    }

    public VlogDTO updateVlog(Integer id, CreateVlog request) throws Exception {
        Vlog vlog=vlogRepository.findByVlogId(id);
        if(vlog==null)
            return null;
        if(request.getSubj()!=null)
            vlog.setSubj(request.getSubj());
        if(request.getContent()!=null)
            vlog.setContent(request.getContent());
        if(request.getTags()!=null)
            vlog.setTags(request.getTags());
        vlog.setCreatedAt(new Date());
        return VlogConverter.converter(vlogRepository.save(vlog));
    }

    public List<CommentDTO> getMyComment(Integer id) throws Exception {
        Vlog vlog = vlogRepository.findByVlogId(id);
        if(vlog==null)
            throw new Exception("Vlog not found");
        return commentRepository.findByVlogIdOrderByCreatedAtDesc(id).stream().map(CommentConverter::converter).collect(Collectors.toList());
    }

    public List<VlogDTO> findVlogByTags(List<String> searchTag) {
        List<Vlog> result = new ArrayList<>();
        searchTag.forEach(s->{
            String queryString = "{" + s + "}";
            Query query = entityManager.createNativeQuery(
                    "SELECT * from vlogs V where V.tags @> CAST(?1 AS text[])",
                    Vlog.class
            ).setParameter(1, queryString);
            result.addAll(query.getResultList());
        });
        //Native query means hibernate doesn't convert the query and it will as it is paste the query to DB
        //JPA query is first converted to native query through hibernate and then it performs function on the DB.
        //Entity manager is used to make a connection with your DB just like Repo.
        //Query is used for creating a query.
        //Set parameter is used to map the querystring with ?1
        return result.stream().distinct().map(VlogConverter::converter).collect(Collectors.toList());
    }

    public VlogDTO like(Integer id) throws Exception {
        Vlog vlog = vlogRepository.findByVlogId(id);
        if(vlog==null)
            throw new Exception("Vlog not found");
        vlog.setLikes(vlog.getLikes()+1);
        return VlogConverter.converter(vlogRepository.save(vlog));
    }

    public VlogDTO disLike(Integer id) throws Exception {
        Vlog vlog = vlogRepository.findByVlogId(id);
        if(vlog==null)
            throw new Exception("Vlog not found");
        vlog.setDislikes(vlog.getDislikes()+1);
        return VlogConverter.converter(vlogRepository.save(vlog));
    }
}
