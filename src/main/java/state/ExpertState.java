package state;

public class ExpertState implements State {

    private static final int EXPERIENCE_TO_ADVANCE = 400;
    private static final int TRAINING_EXPERIENCE = 25;
    private static final int MEDITATION_HEALTH = 15;
    private static final int FIGHT_EXPERIENCE = 50;
    private static final int FIGHT_HEALTH_COST = 20;

    @Override
    public String getName() {
        return "Expert";
    }

    @Override
    public String getAvailableActions() {
        return "train, meditate, fight";
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
        character.changeHealth(-FIGHT_HEALTH_COST);
        character.addExperience(FIGHT_EXPERIENCE);
        System.out.println("Fight complete. HP -" + FIGHT_HEALTH_COST
                + ", XP +" + FIGHT_EXPERIENCE);
        advanceIfReady(character);
    }

    private void advanceIfReady(GameCharacter character) {
        if (character.getExperiencePoints() >= EXPERIENCE_TO_ADVANCE) {
            character.changeState(new MasterState());
            System.out.println("You advanced to Master level!");
        }
    }
}