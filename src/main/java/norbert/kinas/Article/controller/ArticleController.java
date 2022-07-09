package norbert.kinas.Article.controller;

import lombok.RequiredArgsConstructor;
import norbert.kinas.Article.model.Article;
import norbert.kinas.Article.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Article> getArticlesOrderedByPublicationDate() {
        return articleService.getArticlesOrderedByPublicationDate();
    }

    @GetMapping("/articles/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public Article getArticle(@PathVariable Long id) {
        return articleService.getArticle(id);
    }

    @GetMapping("/articles/keyword={keyword}")
    @ResponseStatus(code = HttpStatus.OK)
    public List<Article> getArticlesByKeyword(@PathVariable String keyword) {
        return articleService.getArticleByKeyword(keyword);
    }

    @PostMapping("/articles")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @PutMapping("/articles/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article article) {
        article.setId(id);
        article.setTimestamp(getArticle(id).getTimestamp());
        return articleService.updateArticle(article);
    }

    @DeleteMapping("/articles/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
}
