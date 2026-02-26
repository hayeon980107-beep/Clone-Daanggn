package hy.banana.banana.board;

import hy.banana.banana.category.Category;
import hy.banana.banana.common.BaseEntity;
import hy.banana.banana.neighborhood.Neighborhood;
import hy.banana.banana.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "market_board",
        indexes = {
                @Index(name = "idx_board_nei_created", columnList = "nei_id, created_at"),
                @Index(name = "idx_board_category", columnList = "category_id")
        })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MarketBoard extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user; // 작성자

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "nei_id")
    private Neighborhood neighborhood;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private int viewCount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private BoardState state; // SELLING, RESERVED, SOLD

    @Column(nullable = false)
    private int price;

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void changeState(BoardState state) {
        this.state = state;
    }
}
