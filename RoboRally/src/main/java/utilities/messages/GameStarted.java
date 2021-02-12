package utilities.messages;

import game.gameboard.GameBoardMapObject;

/**
 * The class GameStarted represents the map of the game board.
 * 
 */
public class GameStarted extends MessageBody {

  private final GameBoardMapObject[] map;

  /**
   * Constructor for initializing the game board map.
   * 
   * @param map
   *          is the game board
   */
  public GameStarted(GameBoardMapObject[] map) {

    this.map = map;

  }

  /**
   * The method returns the map of the game board.
   * 
   * @return an array of the game board map
   */
  public GameBoardMapObject[] getMap() {
    return map;
  }
}
