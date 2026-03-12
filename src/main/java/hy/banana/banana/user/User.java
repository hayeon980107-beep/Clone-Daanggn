package hy.banana.banana.user;

import hy.banana.banana.board.BoardState;
import hy.banana.banana.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, length = 100, unique = true)
    private String email;

    @Column(nullable = false, length = 200)
    private String passwordHash;

    @Column(nullable = false, length = 8, unique = true)
    private String nickName;

    @Column(length = 12)
    private String phoneNum;

    private String profileImage;

    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private UserStatus status;

    private LocalDateTime lastLoginAt;

    private LocalDateTime bannedAt;

    private LocalDateTime withdrawnAt;

    public static User create(
            String email,
            String passwordHash,
            String nickName,
            String phoneNum,
            String address
    ) {
        User user = new User();
        user.email = email;
        user.passwordHash = passwordHash;
        user.nickName = nickName;
        user.phoneNum = phoneNum;
        user.address = address;
        user.status = UserStatus.ACTIVE;
        user.lastLoginAt = null;
        return user;
    }

    public void ban() {
        this.status = UserStatus.BAN;
        this.bannedAt = LocalDateTime.now();
        this.withdrawnAt = null;
    }

    public void withdraw() {
        this.status = UserStatus.WITHDRAW;
        this.withdrawnAt = LocalDateTime.now();
        this.bannedAt = null;
    }

    public void activate() {
        this.status = UserStatus.ACTIVE;
        this.bannedAt = null;
        this.withdrawnAt = null;
    }

    public boolean isActive() {
        return this.status == UserStatus.ACTIVE;
    }

    public boolean isBanned() {
        return this.status == UserStatus.BAN;
    }

    public boolean isWithdrawn() {
        return this.status == UserStatus.WITHDRAW;
    }
}
