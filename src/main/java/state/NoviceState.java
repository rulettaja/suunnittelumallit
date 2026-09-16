package state;

public class NoviceState implements State {

    private static final int EXPERIENCE_TO_ADVANCE = 100;
    private static final int TRAINING_EXPERIENCE = 25;

    @Override
    public String getName() {
        return "Novice";
    }

    @Override
    public String getAvailableActions() {
        return "train";
    }

    @Override
    public void train(GameCharacter character) {
        character.addExperience(TRAINING_EXPERIENCE);
        System.out.println("Training complete. XP +" + TRAINING_EXPERIENCE);
        if (character.getExperiencePoints() >= EXPERIENCE_TO_ADVANCE) {
            character.changeState(new IntermediateState());
            System.out.println("You advanced to Intermediate level.");
        }
    }

    @Override
    public void meditate(GameCharacter character) {
        unavailable("meditate");
    }

    @Override
    public void fight(GameCharacter character) {
        unavailable("fight");
    }

    private void unavailable(String action) {
        System.out.println("You cannot " + action + " at Novice level.");
    }
}