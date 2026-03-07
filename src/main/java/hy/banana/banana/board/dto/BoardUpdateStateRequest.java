package hy.banana.banana.board.dto;

import hy.banana.banana.board.BoardState;

public record BoardUpdateStateRequest(
        BoardState state
) {
}
