package com.wheelseye.Blogging.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO<T> {
    private Integer pageNo;
    private Integer pageSize;
    private Long totalcount;
    private List<T> data;
}
