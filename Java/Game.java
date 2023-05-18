import java.util.Scanner;

public class Game
{
  private Scanner sc;
  private char[][] board;
  private boolean winner;
  private int turn;

  public Game()
  {
    this.sc = new Scanner(System.in);

    this.board = new char[3][3];
    for (int i = 0; i < this.board.length; i++)
      for (int j = 0; j < this.board[0].length; j++)
        this.board[i][j] = '_';

    this.turn = 0;
  }

  public void play()
  { 
    while (!this.gameEnd() && !this.tie())
    {
      this.display();
      if (this.turn < 1)
      {
        System.out.println("Player 1 go!");
        this.playTurn();
        ++this.turn;
      }
      else
      {
        System.out.println("Player 2 go!");
        this.playTurn();
        --this.turn;
      }
    }

    this.display();
    if (this.winner)
    {
      if (this.turn < 1)
        System.out.println("Player 2 wins!");
      else
        System.out.println("Player 1 wins!");
    }
    else
      System.out.println("No winners!");
  }

  private void display()
  {
    System.out.println("~ 1 2 3");
    for (int i = 0; i < this.board.length; i++)
    {
      System.out.print((i + 1) + " ");
      for (char spot: this.board[i])
      {
        System.out.print(spot + " ");
      }
      System.out.println();
    }
  }

  private void playTurn()
  {
    System.out.println("Enter coordinates (row, column):");
    int row = this.sc.nextInt();
    int col = this.sc.nextInt();

    while (row <= 0 || row > this.board.length || col <= 0 || col > this.board[0].length || this.board[row-1][col-1] != '_')
    {
      System.out.println("Invalid coordinates!\nTry again:");
      row = this.sc.nextInt();
      col = this.sc.nextInt();
    }

    if (this.turn < 1)
      this.board[row-1][col-1] = 'X';
    else
      this.board[row-1][col-1] = 'O';
  }

  private boolean gameEnd()
  {
    boolean end = false;
    /* Goes in row-major order
     * to check for any wins
     */
    for (int i = 0; i < this.board.length; i++)
    {
      if (this.board[i][0] == '_')
        continue;
      
      end = true;
      char lookFor = this.board[i][0];

      for (int j = 1; j < this.board.length; j++)
      {
        if (lookFor != this.board[i][j])
        {
          end = false;
          break;
        }
      }

      if (end)
      {
        this.winner = true;
        return end;
      }
    }

    /* Goes in column-major order
     * to check for any wins
     */
    for (int i = 0; i < this.board[0].length; i++)
    {
      if (this.board[0][i] == '_')
        continue;

      end = true;
      char lookFor = this.board[0][i];

      for (int j = 0; j < this.board.length; j++)
      {
        if (lookFor != this.board[j][i])
        {
          end = false;
          break;
        }
      }

      if (end)
      {
        this.winner = true;
        return end;
      }
    }
    
    /* Checks for any wins
     * diagonally
     */
    if (this.board[0][0] != '_')
    {
      end = this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2];
      if (end)
      {
        this.winner = true;
        return end;
      }
    }

    if (this.board[2][0] != '_')
    {
      end = this.board[2][0] == this.board[1][1] && this.board[2][0] == this.board[0][2];
      if (end)
      {
        this.winner = true;
        return end;
      }
    }

    return end;
  }

  private boolean tie()
  {
    for (char[] row: this.board)
    {
      for (char spot: row)
      {
        if (spot == '_')
          return false;
      }
    }

    this.winner = false;
    return true;
  }
}
