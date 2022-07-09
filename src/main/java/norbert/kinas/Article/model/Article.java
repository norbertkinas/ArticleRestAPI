package norbert.kinas.Article.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Comparator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idarticle", nullable = false, unique = true)
    private Long id;
    private String releaseTitle;
    private String releaseBody;
    @Column(nullable = false)
    private Date publicationDate;
    private String magazineTitle;
    private String author;
    @CreationTimestamp
    @Column(name = "timestamp")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Timestamp timestamp;

    public static Comparator<Article> getComparatorPublicationDate() {
        return (o1, o2) -> {
            return o1.publicationDate.compareTo(o2.publicationDate) * (-1); //desc
        };
    }
}
