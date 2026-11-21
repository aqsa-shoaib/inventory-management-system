# Connect Four — Python Terminal Game

A terminal-based Connect Four game written in Python. Play against another person or a computer opponent.

## Features

- Classic 6-row, 7-column board
- Player vs. Computer and Player vs. Player modes
- Horizontal, vertical, and diagonal win detection
- Input validation and full-column handling
- Draw detection and replay option
- Computer opponent that prioritizes winning, blocks immediate threats, and prefers the centre column
- Uses only Python's standard library

## Requirements

Python 3.8 or newer.

## Run the game

Open a terminal in the project directory and run:

```bash
python connect_four.py
```

On systems where Python is called `python3`, use `python3 connect_four.py`.

Choose `1` to play against the computer or `2` to play against another person. Enter a column number from `1` to `7` to drop a piece, or `Q` to end the game. The first player to connect four pieces horizontally, vertically, or diagonally wins.

## Example board

```text
  1   2   3   4   5   6   7
+---+---+---+---+---+---+---+
| . | . | . | . | . | . | . |
+---+---+---+---+---+---+---+
| . | . | . | . | . | . | . |
+---+---+---+---+---+---+---+
| . | . | . | . | . | . | . |
+---+---+---+---+---+---+---+
| . | . | . | . | . | . | . |
+---+---+---+---+---+---+---+
| . | . | . | O | . | . | . |
+---+---+---+---+---+---+---+
| . | . | X | X | . | . | . |
+---+---+---+---+---+---+---+
```

`X` represents Player 1, `O` represents Player 2 or the computer, and `.` indicates an empty cell.

## Project structure

```text
connect-four-python/
├── connect_four.py
├── tests/
│   └── test_connect_four.py
├── README.md
└── .gitignore
```

## Run the tests

```bash
python -m unittest discover -s tests -v
```

The unit tests cover board creation, piece placement, invalid moves, win detection, and the computer opponent's decisions.

## Implementation

The game board uses a two-dimensional Python list. Pieces are placed in the lowest available row of a chosen column. Each turn checks four directions for a winning sequence. The computer evaluates available columns to take an immediate win, prevent an opponent's immediate win, or select a centre-preferred move.

## Concepts used

Python functions, loops, lists, conditional logic, input validation, algorithms, and unit testing.
