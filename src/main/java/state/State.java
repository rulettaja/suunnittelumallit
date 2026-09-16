package state;

public interface State {

    String getName();

    String getAvailableActions();

    void train(GameCharacter character);

    void meditate(GameCharacter character);

    void fight(GameCharacter character);
}