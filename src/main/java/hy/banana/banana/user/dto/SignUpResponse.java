package hy.banana.banana.user.dto;

public record SignUpResponse(
        Long userId,
        String email,
        String nickName
) {
}
