package state;

import java.util.Objects;

public class GameCharacter {

    private static final int INITIAL_HEALTH = 100;

    private final String name;
    private int experiencePoints;
    private int healthPoints;
    private State state;

    public GameCharacter(String name) {
        this.name = Objects.requireNonNull(name, "name");
        if (name.isBlank()) {
            throw new IllegalArgumentException("name cannot be blank");
        }
        healthPoints = INITIAL_HEALTH;
        state = new NoviceState();
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return state instanceof NoviceState ? 1
                : state instanceof IntermediateState ? 2
                : state instanceof ExpertState ? 3 : 4;
    }

    public String getLevelName() {
        return state.getName();
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public String getAvailableActions() {
        return state.getAvailableActions();
    }

    public void train() {
        state.train(this);
    }

    public void meditate() {
        state.meditate(this);
    }

    public void fight() {
        state.fight(this);
    }

    void addExperience(int points) {
        experiencePoints += points;
    }

    void changeHealth(int points) {
        healthPoints = Math.max(0, healthPoints + points);
    }

    void changeState(State state) {
        this.state = state;
    }

    public boolean isMaster() {
        return state instanceof MasterState;
    }

    public String getStatus() {
        return String.format("%s | Level: %s | XP: %d | HP: %d",
                name, getLevelName(), experiencePoints, healthPoints);
    }
}