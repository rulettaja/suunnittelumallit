import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors extends Game {

    private static final List<String> MOVES = List.of("rock", "paper", "scissors");

    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private String playerMove;
    private String computerMove;
    private boolean finished;

    public static void main(String[] args) {
        new RockPaperScissors().play(2);
    }

    @Override
    public void initializeGame(int numberOfPlayers) {
        if (numberOfPlayers != 2) {
            throw new IllegalArgumentException("Rock Paper Scissors requires two players.");
        }
        System.out.println("Rock, paper, or scissors?");
    }

    @Override
    public boolean endOfGame() {
        return finished;
    }

    @Override
    public void playSingleTurn(int player) {
        if (player == 0) {
            playerMove = readPlayerMove();
        } else {
            computerMove = MOVES.get(random.nextInt(MOVES.size()));
            finished = true;
        }
    }

    @Override
    public void displayWinner() {
        System.out.println("You chose " + playerMove + "; the computer chose " + computerMove + ".");
        if (playerMove.equals(computerMove)) {
            System.out.println("It's a tie!");
        } else if (beats(playerMove, computerMove)) {
            System.out.println("You win!");
        } else {
            System.out.println("The computer wins!");
        }
    }

    private String readPlayerMove() {
        while (true) {
            String move = scanner.nextLine().trim().toLowerCase();
            if (MOVES.contains(move)) {
                return move;
            }
            System.out.println("Please enter rock, paper, or scissors:");
        }
    }

    private boolean beats(String first, String second) {
        return first.equals("rock") && second.equals("scissors")
                || first.equals("paper") && second.equals("rock")
                || first.equals("scissors") && second.equals("paper");
    }
}