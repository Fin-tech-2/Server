package com.example.firstproject.api;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController //일반적으로 데이터를 (보통 JSON을) 반환한다 .
public class ArticleApiController {
    @Autowired //DI dependency injection(외부에서 가져온다.), Spring boot에서 땡겨와야한다.
//깃허브 연습
    private ArticleRepository articleRepository;
    //GET
    @GetMapping("/api/articles")
    public List<Article> index() { //article의 list를 반환!
        return articleRepository.findAll();
    }

    //GET 단일 article
    @GetMapping("/api/articles/{id}")
    public Article index(@PathVariable Long id) { //list를 반환하는게 아니라 단일 article 반환하기 때문에 반환타입을 Article로 선언 !
        //URL 요청을 통해서 Long id 값을 가져올 때는 @PathVariable 사용
        return articleRepository.findById(id).orElse(null);
    }


    //POST (create)
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto) {
        //dto를 entitiy로 변환
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    //PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto) {
        //1: 수정용 엔티티 생성 (dto를 entitiy로 변환)
        Article article = dto.toEntity();
        log.info("id: {}, article: {}", id, article.toString());

        //2: 대상 엔테티를 조회
        Article target= articleRepository.findById(id).orElse(null);

        //3: 잘못된 요청 처리(대상이 없거나, id가 다른 경우)
        if (target ==null || id != article.getId()) {
            //400, 잘못된 요청 응답!
            log.info("잘못된 요청! id: {}, article: {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //4: 업데이트 및 정상 응답(200)
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);

    }

    //DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Article> delete(@PathVariable long id) {
        //1. 대상 찾기
        Article target = articleRepository.findById(id).orElse(null);

        //2. 잘못된 요청 처리
        if (target == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        //3. 대상 삭제
        articleRepository.delete(target);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
