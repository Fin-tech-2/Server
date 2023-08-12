package com.example.firstproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity //DB가 해당 객체를 인식 가능 (해당 클래스로 테이블을 만든다! )
@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자를 추가
@ToString
@Getter
public class Article {
    @Id //대표값을 지정! like a 주민번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성 어노테이션!
    private Long id;

    @Column
    private String title;

    @Column
    private String goalprice;

    @Column
    private String startdate;

    @Column
    private String enddate;

    @Column
    private String price;

    @Column
    private String thumbnail;

    @Column
    private String introductionimg ;

    @Column
    private String category;

    @Column
    private String content;

    public void patch(Article article) {
        if (article.title != null)
            this.title = article.title;
        if (article.goalprice != null)
            this.goalprice = article.goalprice;
        if (article.startdate != null)
            this.startdate = article.startdate;
        if (article.enddate != null)
            this.enddate = article.enddate;
        if (article.price != null)
            this.price = article.price;
        if (article.thumbnail != null)
            this.thumbnail = article.thumbnail;
        if (article.introductionimg != null)
            this.introductionimg = article.introductionimg;
        if (article.category != null)
            this.category = article.category;
        if (article.content != null)
            this.content = article.content;
    }
}
