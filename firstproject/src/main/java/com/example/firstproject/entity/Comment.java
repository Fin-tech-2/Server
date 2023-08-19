package com.example.firstproject.entity;

import com.example.firstproject.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //댓글과 부모 게시글과의 관계, 해당 댓글 엔티티 여러개가, 하나의 Article에 연관된다
    @ManyToOne
    @JoinColumn(name = "article_id") // "article_id" 칼럼에 Article의 대표값을 지정
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;


    public static Comment createComment(CommentDto dto, Article article) {
        //예외 발생
        if (dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");

        //json 에서 받는 데이터랑 받아온 데이터랑 (id가) 다를 경우 처리
        if (dto.getArticleId() != article.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못되었습니다.");

        //정상적인 경우 return
        return new Comment(
                dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody()
        );

        //엔티티 생성 및 변환

    }

    public void patch(CommentDto dto) {
        //예외 발생 (json으로 던진 애랑 다를 때 )
        if (this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력되었습니다.");

        // 객체를 갱신
        if (dto.getNickname() != null)
            this.nickname = dto.getNickname();

        if (dto.getBody() != null)
            this.body = dto.getBody();
    }
}
