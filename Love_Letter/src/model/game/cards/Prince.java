package model.game.cards;

public class Prince extends Card{

  @Override
  public int getValue() {
    return 5;
  }

  @Override
  public String getDescription() {
    return "Choose a player. They discard their hand and draw a new card.";
  }

}
