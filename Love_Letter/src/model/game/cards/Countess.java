package model.game.cards;

public class Countess extends Card{

  @Override
  public int getValue() {
    return 7;
  }

  @Override
  public String getDescription() {
    return "Must be played if you have King or Prince in hand.";
  }

}
