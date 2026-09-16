package state;

public class IntermediateState implements State {

    private static final int EXPERIENCE_TO_ADVANCE = 250;
    private static final int TRAINING_EXPERIENCE = 25;
    private static final int MEDITATION_HEALTH = 15;

    @Override
    public String getName() {
        return "Intermediate";
    }

    @Override
    public String getAvailableActions() {
        return "train, meditate";
    }

    @Override
    public void train(GameCharacter character) {
        character.addExperience(TRAINING_EXPERIENCE);
        System.out.println("Training complete. XP +" + TRAINING_EXPERIENCE);
        advanceIfReady(character);
    }

    @Override
    public void meditate(GameCharacter character) {
        character.changeHealth(MEDITATION_HEALTH);
        System.out.println("Meditation complete. HP +" + MEDITATION_HEALTH);
    }

    @Override
    public void fight(GameCharacter character) {
        unavailable("fight");
    }

    private void advanceIfReady(GameCharacter character) {
        if (character.getExperiencePoints() >= EXPERIENCE_TO_ADVANCE) {
            character.changeState(new ExpertState());
            System.out.println("You advanced to Expert level.");
        }
    }

    private void unavailable(String action) {
        System.out.println("You cannot " + action + " at Intermediate level.");
    }
}