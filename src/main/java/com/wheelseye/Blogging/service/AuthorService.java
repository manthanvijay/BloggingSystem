package com.wheelseye.Blogging.service;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.converter.AuthorConverter;
import com.wheelseye.Blogging.converter.CommentConverter;
import com.wheelseye.Blogging.converter.VlogConverter;
import com.wheelseye.Blogging.dto.AuthorDTO;
import com.wheelseye.Blogging.dto.CommentDTO;
import com.wheelseye.Blogging.dto.VlogDTO;
import com.wheelseye.Blogging.repo.CommentRepository;
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

    @Autowired
    private CommentRepository commentRepository;

    public AuthorDTO create(SignUp request) throws Exception {
        Author findauthor = authorRepository.findByEmail(request.getEmail());
        if(findauthor!=null)
            throw new Exception("EmailId already registered");
        Author author = new Author();
        author.setName(request.getName());
        author.setDept(request.getDept());
        author.setEmail(request.getEmail());
        String pass = request.getPassword();
        if(pass.length()<8)
            throw new Exception("Password too short");
        if(!pass.matches("^[a-zA-Z0-9$&+,:;=?@#|'<>.^*()%!-]*$"))
            throw new Exception("Password can include alphabets,numerals or special characters only");
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
        if(updatePass.getOldPassword().equals(updatePass.getNewPassword()))
            throw new Exception("Old and new password can't be same");
        String pass = updatePass.getNewPassword();
        if(!pass.matches("^[a-zA-Z0-9$&+,:;=?@#|'<>.^*()%!-]*$"))
            throw new Exception("Password can include alphabets,numerals or special characters only");
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

    public List<VlogDTO> getMyVlogs(Integer id) throws Exception {
        Author author = authorRepository.findByAuthorId(id);
        if(author==null)
            throw new Exception("User not found");
        return vlogRepository.findByAuthorIdOrderByCreatedAtDesc(id).stream().map(VlogConverter::converter).collect(Collectors.toList());
    }

    public List<CommentDTO> getMyComments(Integer id) throws Exception {
        Author author = authorRepository.findByAuthorId(id);
        if(author==null)
            throw new Exception("User not found");
        return commentRepository.findByAuthorIdOrderByCreatedAtDesc(id).stream().map(CommentConverter::converter).collect(Collectors.toList());
    }
}
