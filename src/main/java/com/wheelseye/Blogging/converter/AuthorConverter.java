package com.wheelseye.Blogging.converter;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.dto.AuthorDTO;

public class AuthorConverter{
    public static AuthorDTO convertor(Author author){
        return new AuthorDTO().setDept(author.getDept()).setEmail(author.getEmail()).setName(author.getName());
    }
}
