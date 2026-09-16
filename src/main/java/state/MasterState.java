package state;

public class MasterState implements State {

    @Override
    public String getName() {
        return "Master";
    }

    @Override
    public String getAvailableActions() {
        return "none";
    }

    @Override
    public void train(GameCharacter character) {
        finished();
    }

    @Override
    public void meditate(GameCharacter character) {
        finished();
    }

    @Override
    public void fight(GameCharacter character) {
        finished();
    }

    private void finished() {
        System.out.println("The game is already complete.");
    }
}