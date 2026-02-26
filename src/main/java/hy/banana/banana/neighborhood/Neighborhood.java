package hy.banana.banana.neighborhood;

import hy.banana.banana.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "neighborhood")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Neighborhood {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long neiId;

    @Column(nullable = false, length = 50)
    private String name;

    @Entity
    @Table(name = "user_neighborhood",
            uniqueConstraints = @UniqueConstraint(name = "uk_user_nei", columnNames = {"user_id", "nei_id"}))
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UserNeighborhood {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "user_id")
        private User user;

        @ManyToOne(fetch = FetchType.LAZY, optional = false)
        @JoinColumn(name = "nei_id")
        private Neighborhood neighborhood;

        @Column(nullable = false)
        private boolean isPrimary;
    }
}
