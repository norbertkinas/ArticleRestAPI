package norbert.kinas.Article.service;

import lombok.RequiredArgsConstructor;
import norbert.kinas.Article.model.Article;
import norbert.kinas.Article.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public List<Article> getArticlesOrderedByPublicationDate() {
        List<Article> allArticles = getArticles();
        allArticles.sort(Article.getComparatorPublicationDate());
        return allArticles;
    }

    public Article getArticle(Long id) {
        return articleRepository.findById(id).orElseThrow();
    }

    public List<Article> getArticleByKeyword(String keyword) {
        Set<Article> set = new HashSet<>(articleRepository.findByReleaseBodyContaining(keyword));
        set.addAll(articleRepository.findByReleaseTitleContaining(keyword));

        return set.stream().toList();
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Article createArticle(Article article) {
        return articleRepository.save(article);
    }

    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }

    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }
}