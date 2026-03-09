package hy.banana.banana.common.response;

public record ErrorResponse(
        String code,
        String message
) {
}
