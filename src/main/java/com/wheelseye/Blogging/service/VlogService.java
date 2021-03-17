package com.wheelseye.Blogging.service;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.converter.CommentConverter;
import com.wheelseye.Blogging.converter.VlogConverter;
import com.wheelseye.Blogging.dto.CommentDTO;
import com.wheelseye.Blogging.dto.VlogDTO;
import com.wheelseye.Blogging.repo.AuthorRepository;
import com.wheelseye.Blogging.repo.CommentRepository;
import com.wheelseye.Blogging.repo.VlogRepository;
import com.wheelseye.Blogging.request.CreateVlog;
import org.springframework.beans.factory.annotation.Autowired;
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

    //public Page<Vlog> getAllVlogs(){
      //  Pageable sortedByLikesDesc = PageRequest.of(0,1);//, Sort.by("likes").descending());
     //   Page<Vlog> result = vlogRepository.findAll(sortedByLikesDesc).stream().map(VlogConverter::converter).collect(Collectors.toList());
    //    return result;
    //}
    public List<VlogDTO> getAllVlogs(){
        //Pageable sortedByLikesDesc = (Pageable) PageRequest.of(0,10, Sort.by("likes").descending());
        //Page<Vlog> result = vlogRepository.findAll((org.springframework.data.domain.Pageable) sortedByLikesDesc);
        return vlogRepository.findAllOrderByVlogLikeDsc().stream().map(VlogConverter::converter).collect(Collectors.toList());
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
        return commentRepository.findByVlogId(id).stream().map(CommentConverter::converter).collect(Collectors.toList());
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
        return result.stream().distinct().map(VlogConverter::converter).collect(Collectors.toList());
    }

    public VlogDTO like(Integer id) throws Exception {
        Vlog vlog = vlogRepository.findByVlogId(id);
        if(vlog==null)
            throw new Exception("Vlog not found");
        Integer temp=vlog.getLikes();
        temp+=1;
        vlog.setLikes(temp);
        return VlogConverter.converter(vlogRepository.save(vlog));
    }

    public VlogDTO disLike(Integer id) throws Exception {
        Vlog vlog = vlogRepository.findByVlogId(id);
        if(vlog==null)
            throw new Exception("Vlog not found");
        Integer flag=vlog.getDislikes();
        flag+=1;
        vlog.setDislikes(flag);
        return VlogConverter.converter(vlogRepository.save(vlog));
    }
}
