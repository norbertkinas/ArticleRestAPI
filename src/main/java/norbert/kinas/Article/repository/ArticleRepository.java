package norbert.kinas.Article.repository;

import norbert.kinas.Article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findByReleaseBodyContaining(String keyword);
    List<Article> findByReleaseTitleContaining(String keyword);
}
