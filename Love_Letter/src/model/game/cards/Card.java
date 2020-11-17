package model.game.cards;

public abstract class Card {
  
  public abstract int getValue();
  public abstract String getDescription();
  
  public String getClassName() {
    return this.getClass().getSimpleName();
  }
}
