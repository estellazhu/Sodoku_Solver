package src;
// LeetCode 37 - Sudoku Solver
// Write a program to solve a Sudoku puzzle by filling the empty cells.
//
// Empty cells are indicated by the character '.'.
//
// You may assume that there will be only one unique solution.

//DFS + backtracking.
//n = the number of empty cells, time = O(9 ^ n)
public void solveSudoku(char[][] board) {
  if(board == null || board.length < 9 || board[0].length < 9) {
    return;
  }
  int[] start = nextEmptyCell(board, 0, 0);
  solve(board, start[0], start[1]);
}

private boolean solve(char[][] board, int r, int c) {
  if(r == -1 && c == -1) { return true; }
  //enumerate 1 - 9 for empty cell.
  for(char ch = '1'; ch <= '9'; ch++) {
    if(isValid(board, r, c, ch)) {
      board[r][c] = ch;
      int[] nextEmpty = nextEmptyCell(board, r, c);
      //DFS
      if(solve(board, nextEmpty[0], nextEmpty[1])) {
        return true;
      }
      //backtracking
      board[r][c] = '.';
    }
  }
  return false;
}

private int[] nextEmptyCell(char[][] board, int startRow, int startCol) {
  int[] position = new int[2];
  Arrays.fill(position, -1);
  for(int c = startCol; c < 9; c++) {
    if(board[startRow][c] == '.') {
      return new int[] {startRow, c};
    }
  }
  for(int r = startRow+1; r < 9; r++) {
    for(int c = 0; c < 9; c++) {
      if(board[r][c] == '.') {
        return new int[] {r, c};
      }
    }
  }
  return position;
}

private boolean isValid(char[][] board, int r, int c, char ch) {
  for(int i = 0; i < 9; i++) {
    if(board[r][i] == ch) { return false; }
  }
  for(int i = 0; i < 9; i++) {
    if(board[i][c] == ch) { return false; }
  }
  for(int i = 0; i < 3; i++) {
    for(int j = 0; j < 3; j++) {
      if(board[i + 3 * (r/3)][j + 3 * (c/3)] == ch) {
        return false;
      }
    }
  }
  return true;
}
