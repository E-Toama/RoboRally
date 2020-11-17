package model.game.cards;

public class Priest extends Card{

  @Override
  public int getValue() {
    return 2;
  }

  @Override
  public String getDescription() {
    return "look at a player's hand.";
  }

}
