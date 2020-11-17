package model.game.cards;

public class Princess extends Card{

  @Override
  public int getValue() {
    return 8;
  }

  @Override
  public String getDescription() {
    return "Lose if discarded.";
  }

}
