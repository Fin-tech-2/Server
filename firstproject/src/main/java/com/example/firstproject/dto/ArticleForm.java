package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticleForm {
    private Long id;
    private String title;
    private String goalprice;
    private String startdate;
    private String enddate;
    private String price;
    private String thumbnail;
    private String introductionimg;
    private String category;
    private String content;
    private Integer student;

    public Article toEntity() {
        return new Article(id, title, goalprice, startdate, enddate, price,thumbnail, introductionimg, category, content, student);
    }

}

