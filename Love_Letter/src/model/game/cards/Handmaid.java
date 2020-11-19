package model.game.cards;

public class Handmaid extends Card{

  @Override
  public int getValue() {
    return 4;
  }

  @Override
  public String getDescription() {
    return "You cannot be chosen until your next turn.";
  }

}
