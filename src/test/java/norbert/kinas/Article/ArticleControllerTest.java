package norbert.kinas.Article;

import norbert.kinas.Article.model.Article;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class ArticleControllerTest extends AbstractTest {

    @Test
    void getArticlesOrderedByPublicationDate() throws Exception {
        String uri = "/articles";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status); // 200 - OK

        String content = mvcResult.getResponse().getContentAsString();
        Article[] articles = mapFromJson(content,Article[].class);
        assertTrue(articles.length > 0);
    }

    @Test
    void getArticle() throws Exception {
        int id = 28;
        String uri = "/articles/" + id;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,
                "{\"id\":28,\"releaseTitle\":\"Poczatki\",\"releaseBody\":\"krok pierwszy ........\"," +
                        "\"publicationDate\":\"2021-11-28\",\"magazineTitle\":\"HoopNews3.0\"," +
                        "\"author\":\"Jan Kowalski\",\"timestamp\":\"2022-07-08T14:16:18.194+00:00\"}");
    }

    @Test
    void getArticlesByKeyword() throws Exception {
        String keyword = "krok pierwszy ........";
        String uri = "/articles/keyword=" + keyword;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,
                "[{\"id\":28,\"releaseTitle\":\"Poczatki\",\"releaseBody\":\"krok pierwszy ........\"," +
                        "\"publicationDate\":\"2021-11-28\",\"magazineTitle\":\"HoopNews3.0\"," +
                        "\"author\":\"Jan Kowalski\",\"timestamp\":\"2022-07-08T14:16:18.194+00:00\"}]");
    }

    @Test
    void createArticle() throws Exception {
        String uri = "/articles";
        Article article = new Article();
        article.setAuthor("Norbert Kinas");
        article.setMagazineTitle("Rest api article");
        article.setReleaseTitle("ArticleControllerTest");
        article.setReleaseBody("POST createArticle test....");
        article.setPublicationDate(new Date(System.currentTimeMillis()));

        String inputJson = mapToJson(article);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201,status); // 201 - created
    }

    @Test
    void updateArticle() throws Exception {
        Random random = new Random();
        Article article = new Article();

        int id = 30;
        String uri = "/articles/" + id;
        String author = "G" + random.nextInt();
        String magazineTitle = "R" + random.nextInt();
        String releaseTitle = "A" + random.nextInt();
        String releaseBody = "P" + random.nextInt();
        Date date = new Date(System.currentTimeMillis());

        article.setAuthor(author);
        article.setMagazineTitle(magazineTitle);
        article.setReleaseTitle(releaseTitle);
        article.setReleaseBody(releaseBody);
        article.setPublicationDate(date);

        String inputJson = mapToJson(article);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status);

        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content,
                "{\"id\":30,\"releaseTitle\":\"" + releaseTitle + "\",\"releaseBody\":\"" + releaseBody + "\"," +
                        "\"publicationDate\":\"" + date + "\",\"magazineTitle\":\"" + magazineTitle + "\"," +
                        "\"author\":\"" + author + "\",\"timestamp\":\"2022-07-08T14:16:38.280+00:00\"}");
    }

    @Test
    void deleteArticle() throws Exception {
        int id = 51;
        String uri = "/articles/" + id;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200,status); // 200 - OK
    }
}