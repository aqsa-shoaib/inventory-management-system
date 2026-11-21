import random

ROWS = 6
COLUMNS = 7
EMPTY = "."
PLAYER_ONE = "X"
PLAYER_TWO = "O"


def create_board():
    """Return an empty 6-row by 7-column board."""
    return [[EMPTY for _ in range(COLUMNS)] for _ in range(ROWS)]


def display_board(board):
    """Print the current board with numbered columns."""
    print("\n  " + "   ".join(str(col) for col in range(1, COLUMNS + 1)))
    print("+" + "---+" * COLUMNS)
    for row in board:
        print("| " + " | ".join(row) + " |")
        print("+" + "---+" * COLUMNS)
    print()


def valid_moves(board):
    """Return zero-based column indexes that are not full."""
    return [col for col in range(COLUMNS) if board[0][col] == EMPTY]


def drop_piece(board, column, piece):
    """Drop a piece into a column, returning the row where it landed.

    Return None if the column is outside the board or already full.
    """
    if column not in range(COLUMNS) or column not in valid_moves(board):
        return None
    for row in range(ROWS - 1, -1, -1):
        if board[row][column] == EMPTY:
            board[row][column] = piece
            return row
    return None


def check_winner(board, piece):
    """Check for four matching pieces horizontally, vertically, or diagonally."""
    directions = ((0, 1), (1, 0), (1, 1), (1, -1))
    for row in range(ROWS):
        for col in range(COLUMNS):
            if board[row][col] != piece:
                continue
            for delta_row, delta_col in directions:
                if all(
                    0 <= row + step * delta_row < ROWS
                    and 0 <= col + step * delta_col < COLUMNS
                    and board[row + step * delta_row][col + step * delta_col] == piece
                    for step in range(4)
                ):
                    return True
    return False


def is_draw(board):
    """A game is drawn when no columns remain and nobody has won."""
    return not valid_moves(board) and not any(
        check_winner(board, piece) for piece in (PLAYER_ONE, PLAYER_TWO)
    )


def get_human_move(board, player_name):
    """Read and validate a move from the terminal; q quits the game."""
    while True:
        answer = input(f"{player_name}, choose a column (1-7) or Q to quit: ").strip()
        if answer.lower() == "q":
            return None
        if not answer.isdigit():
            print("Please enter a number from 1 to 7 (or Q).")
            continue
        col = int(answer) - 1
        if col not in range(COLUMNS):
            print("That column doesn't exist. Choose 1 to 7.")
        elif col not in valid_moves(board):
            print("That column is full. Choose another one.")
        else:
            return col


def find_winning_move(board, piece):
    """Return a winning column for piece, or None if no win is available."""
    for col in valid_moves(board):
        trial = [row[:] for row in board]
        drop_piece(trial, col, piece)
        if check_winner(trial, piece):
            return col
    return None


def computer_move(board):
    """Easy computer strategy: win, block, prefer centre, otherwise random."""
    winning = find_winning_move(board, PLAYER_TWO)
    if winning is not None:
        return winning
    blocking = find_winning_move(board, PLAYER_ONE)
    if blocking is not None:
        return blocking
    choices = valid_moves(board)
    if COLUMNS // 2 in choices:
        return COLUMNS // 2
    return random.choice(choices)


def play_game():
    """Run one complete Connect Four game. Return when it finishes."""
    print("=" * 35)
    print("      WELCOME TO CONNECT FOUR")
    print("=" * 35)
    print("Connect four Xs or Os in a row to win!")
    print("Pieces fall to the lowest empty spot in a column.\n")

    while True:
        mode = input("Choose 1 for vs Computer or 2 for Two Players: ").strip()
        if mode in ("1", "2"):
            break
        print("Please enter 1 or 2.")

    board = create_board()
    current_piece = PLAYER_ONE
    while True:
        display_board(board)
        if mode == "1" and current_piece == PLAYER_TWO:
            col = computer_move(board)
            print(f"Computer chooses column {col + 1}.")
        else:
            name = "Player 1 (X)" if current_piece == PLAYER_ONE else "Player 2 (O)"
            col = get_human_move(board, name)
            if col is None:
                print("Game ended. Thanks for playing!")
                return

        drop_piece(board, col, current_piece)
        if check_winner(board, current_piece):
            display_board(board)
            winner = "Computer" if mode == "1" and current_piece == PLAYER_TWO else (
                "Player 1" if current_piece == PLAYER_ONE else "Player 2"
            )
            print(f"🎉 {winner} wins! Four in a row!")
            return
        if is_draw(board):
            display_board(board)
            print("It's a draw! The board is full.")
            return
        current_piece = PLAYER_TWO if current_piece == PLAYER_ONE else PLAYER_ONE


def main():
    """Allow replaying without restarting the script."""
    while True:
        play_game()
        again = input("Play again? (y/n): ").strip().lower()
        if again not in ("y", "yes"):
            print("Goodbye!")
            break


if __name__ == "__main__":
    try:
        main()
    except (EOFError, KeyboardInterrupt):
        print("\nGame closed. Goodbye!")
