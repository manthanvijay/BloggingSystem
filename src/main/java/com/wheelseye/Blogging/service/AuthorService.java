package com.wheelseye.Blogging.service;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.converter.AuthorConverter;
import com.wheelseye.Blogging.dto.AuthorDTO;
import com.wheelseye.Blogging.repo.VlogRepository;
import com.wheelseye.Blogging.request.ChangePassword;
import com.wheelseye.Blogging.request.Login;
import com.wheelseye.Blogging.request.SignUp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wheelseye.Blogging.repo.AuthorRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

    @Autowired //for making the class as singleton so that only single instance of this class can be created.
    private AuthorRepository authorRepository;

    @Autowired
    private VlogRepository vlogRepository;

    public AuthorDTO create(SignUp request){
        Author author = new Author();
        author.setName(request.getName());
        author.setDept(request.getDept());
        author.setEmail(request.getEmail());
        author.setPassword(request.getPassword());
        return AuthorConverter.convertor(authorRepository.save(author));
    }

    public List<AuthorDTO> getAllAuthors(){
        return authorRepository.findAll().stream().map(AuthorConverter::convertor).collect(Collectors.toList());
    }

    public AuthorDTO findById(Integer id ){
        return AuthorConverter.convertor(authorRepository.findByAuthorId(id));
    }

    public AuthorDTO login(Login request) throws Exception {
        Author author = authorRepository.findByEmail(request.getEmail());
        if(author==null)
            throw new Exception("User not found");
        if(!(author.getPassword().equals(request.getPassword())))
            throw new Exception("Password doesn't match");
        return AuthorConverter.convertor(author);
    }

    public AuthorDTO updatePassword(Integer id, ChangePassword updatePass) throws Exception {
        Author author=authorRepository.findByAuthorId(id);
        if(author==null)
            throw new Exception("User doesn't exists");
        if(!(author.getPassword().equals(updatePass.getOldPassword())))
            throw new Exception("Old Password incorrect");
        author.setPassword(updatePass.getNewPassword());
        System.out.println("Password updated successfully");
        return AuthorConverter.convertor(authorRepository.save(author));
    }

    public AuthorDTO updatePersonalInfo(Integer id, SignUp updatereq) throws Exception {
        Author author=authorRepository.findByAuthorId(id);
        if(author==null)
            throw new Exception("User not found");
        if(updatereq.getName()!=null)
            author.setName(updatereq.getName());
        if(updatereq.getDept()!=null)
            author.setDept(updatereq.getDept());
        return AuthorConverter.convertor(authorRepository.save(author));
    }

    public List<Vlog> getMyVlog(Integer id){
        return vlogRepository.findByAuthorId(id);
    }
}
