package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j //로깅을 위한 어노테이션
public class ArticleController {

    @Autowired  // 스프링 부트가 미리 생성해놓은 객체를 가져다가 자동 연결시켜준다.!
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new") //브라우저에서 접속하는 주소
    public String NewArticleForm() {
        return "articles/new"; //view templates page
    }

    //post 방식으로 던지길 했기 때문이다.
    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form) {
        //System.out.println(form.toString());-> 로깅 기능으로 대체! 서버에서 이렇게 쓰면 안되고, 성능을 많이 떨어뜨린다.
        log.info(form.toString());

        //1.DTO를 변환! Entity로
        Article article = form.toEntity();
        log.info(article.toString());

        //2. Repository에게 Entity를 DB안에 저장하게 함
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/" + saved.getId();
    }
    @GetMapping("/articles/{id}")
    public  String show(@PathVariable Long id, Model model){
        log.info("id = "+id);
        // 1: id로 데이터를 가져옴 (데이터를 가져오는 주최가 누구냐 ? 레파지토리다.)
        //아래코드 해당 id값없다면 null을 반환해라.
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 2: 가져온 데이터를 모델에 등록!
        model.addAttribute("article", articleEntity);

        // 3: 보여줄 페이지를 설정
        return "articles/show";
    }


    @GetMapping("/articles")
    public String index(Model model) {
        // 1: 모든 Article을 가져온다.
        List<Article> articleEntityList = articleRepository.findAll();
        // 2: 가져온 Article 묶음을 뷰로 전달
        model.addAttribute( "articleList", articleEntityList);
        // 3: 뷰 페이지를 설정!

        return "articles/index";  //ariticles/index.mustache
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        //수정할 데이터를 가져오기 DB에 있는 데이터를 레파지토리를 통해서 꺼내오기
        Article articleEntity = articleRepository.findById(id).orElse(null);

        //모델에 데이터를 등록, 모델을 쓸려면 파라미터에 모델을 등록해줘야 한다.
        model.addAttribute("article", articleEntity);

        return "articles/edit";  //뷰페이지 설정
    }

    @PostMapping("/articles/update")
    public  String update(ArticleForm form){
        log.info(form.toString());
        //1.DTO를 엔티티로 변환한다.
        Article articleEntity= form.toEntity();
        log.info(articleEntity.toString());

        //2. 엔티티를 DB로 저장한다.
        //2-1 : DB에 기존 데이터를 가져온다.
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);
        //2-2 : 기존 데이터에 값을 갱신한다.!
        if (target != null) {
            articleRepository.save(articleEntity); // 엔티티가 DB로 갱신
        }
        //3. 수정 결과 페이지로 리다이렉트 한다.
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다. ");

        // 1) 삭제 대상을 가져온다.
        Article target = articleRepository.findById(id).orElse(null); //id는 URL에서 가져올 수 있다.
        //Long id 가 URL에 있는 {id}가 되기 위해서는 @Pathvariable 어토테이션을 추가하면 된다.
        log.info(target.toString());

        // 2) 그 대상을 삭제한다.
        if (target != null) {
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }
        // 3) 결과 페이지로 리다이렉트한다.
        return "redirect:/articles";
    }
}
