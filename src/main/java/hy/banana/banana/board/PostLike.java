package hy.banana.banana.board;

import hy.banana.banana.common.BaseEntity;
import hy.banana.banana.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "post_like",
        uniqueConstraints = @UniqueConstraint(name = "uk_like_user_post", columnNames = {"user_id", "board_id"}),
        indexes = {
                @Index(name = "idx_like_board", columnList = "board_id"),
                @Index(name = "idx_like_user", columnList = "user_id")
        })
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostLike extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "board_id")
    private MarketBoard board;
}
