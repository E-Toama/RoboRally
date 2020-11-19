package model.game.cards;

public class King extends Card{

  @Override
  public int getValue() {
    return 6;
  }

  @Override
  public String getDescription() {
    return "Trade hands with another player.";
  }

}
