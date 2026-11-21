import unittest

from connect_four import (
    COLUMNS,
    EMPTY,
    PLAYER_ONE,
    PLAYER_TWO,
    ROWS,
    check_winner,
    computer_move,
    create_board,
    drop_piece,
    find_winning_move,
    is_draw,
    valid_moves,
)


class ConnectFourTests(unittest.TestCase):
    def setUp(self):
        self.board = create_board()

    def test_board_dimensions(self):
        self.assertEqual(len(self.board), ROWS)
        self.assertTrue(all(len(row) == COLUMNS for row in self.board))
        self.assertTrue(all(cell == EMPTY for row in self.board for cell in row))

    def test_piece_falls_to_bottom_then_stacks(self):
        self.assertEqual(drop_piece(self.board, 0, PLAYER_ONE), ROWS - 1)
        self.assertEqual(drop_piece(self.board, 0, PLAYER_TWO), ROWS - 2)

    def test_full_column_is_rejected(self):
        for _ in range(ROWS):
            drop_piece(self.board, 0, PLAYER_ONE)
        self.assertEqual(drop_piece(self.board, 0, PLAYER_TWO), None)
        self.assertNotIn(0, valid_moves(self.board))

    def test_outside_board_rejected(self):
        self.assertIsNone(drop_piece(self.board, -1, PLAYER_ONE))
        self.assertIsNone(drop_piece(self.board, COLUMNS, PLAYER_ONE))

    def test_horizontal_win(self):
        for col in range(4):
            drop_piece(self.board, col, PLAYER_ONE)
        self.assertTrue(check_winner(self.board, PLAYER_ONE))

    def test_vertical_win(self):
        for _ in range(4):
            drop_piece(self.board, 2, PLAYER_TWO)
        self.assertTrue(check_winner(self.board, PLAYER_TWO))

    def test_diagonal_down_right_win(self):
        for r, c in ((2, 0), (3, 1), (4, 2), (5, 3)):
            self.board[r][c] = PLAYER_ONE
        self.assertTrue(check_winner(self.board, PLAYER_ONE))

    def test_diagonal_down_left_win(self):
        for r, c in ((2, 5), (3, 4), (4, 3), (5, 2)):
            self.board[r][c] = PLAYER_TWO
        self.assertTrue(check_winner(self.board, PLAYER_TWO))

    def test_initial_board_is_not_draw(self):
        self.assertFalse(is_draw(self.board))

    def test_computer_takes_immediate_win(self):
        for col in range(3):
            drop_piece(self.board, col, PLAYER_TWO)
        self.assertEqual(find_winning_move(self.board, PLAYER_TWO), 3)
        self.assertEqual(computer_move(self.board), 3)

    def test_computer_blocks_immediate_loss(self):
        for col in range(3):
            drop_piece(self.board, col, PLAYER_ONE)
        self.assertEqual(computer_move(self.board), 3)

    def test_computer_prefers_centre(self):
        self.assertEqual(computer_move(self.board), COLUMNS // 2)


if __name__ == "__main__":
    unittest.main()
