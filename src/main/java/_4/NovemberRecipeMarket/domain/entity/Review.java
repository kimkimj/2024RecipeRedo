package _4.NovemberRecipeMarket.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Review extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    private String title;
    private String content;

    public Review(Recipe recipe, User author, String title, String content) {
        this.recipe = recipe;
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void updateReview(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
