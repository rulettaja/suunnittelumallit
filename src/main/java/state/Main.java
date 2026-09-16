package state;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your character's name: ");
        String name = scanner.nextLine().trim();
        GameCharacter character = new GameCharacter(name.isEmpty() ? "Player" : name);

        while (!character.isMaster()) {
            System.out.println();
            System.out.println(character.getStatus());
            System.out.println("Available actions: " + character.getAvailableActions());
            System.out.print("Choose an action (or quit): ");
            String action = scanner.nextLine().trim().toLowerCase();

            switch (action) {
                case "train" -> character.train();
                case "meditate" -> character.meditate();
                case "fight" -> character.fight();
                case "quit", "q" -> {
                    System.out.println("Game ended.");
                    return;
                }
                default -> System.out.println("Unknown action. Choose one of the available actions.");
            }
        }

        System.out.println();
        System.out.println(character.getStatus());
        System.out.println("You reached Master level. Congratulations!");
    }
}