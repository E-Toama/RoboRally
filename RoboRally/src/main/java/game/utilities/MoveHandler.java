package game.utilities;

import game.Game;
import game.gameboard.BoardElement;
import game.player.PlayerMat;
import server.network.UserThread;
import utilities.MessageHandler;
import utilities.MyLogger;
import utilities.messages.Movement;

import java.util.logging.Logger;

/**
 * This method represents the movement of the robots on the game board.
 * 
 */
public class MoveHandler {

    protected final MessageHandler messageHandler = new MessageHandler();

    /**
     * The method checks if the move is possible to make, if not its the next players turn.
     * 
     * @param game
     *          an object of the Game class
     * @param gameState
     *          the current game state
     * @param playerID
     *          the player id
     * @param oldPosition
     *          the current position
     * @param newPosition
     *          the target position
     * @param movingOrientation
     *          the direction the robot is facing
     * @param isPlayerAction
     *          if its the player who initiated the move
     * @param isLastMovePart
     *          if its the last moving part
     *          
     * @return true if the move is possible 
     */
    public boolean move(Game game, GameState gameState, int playerID, Position oldPosition, Position newPosition, String movingOrientation, boolean isPlayerAction, boolean isLastMovePart) {

        if (newPosition.getX() == 13 || newPosition.getY() == 10 || newPosition.getX() == -1 || newPosition.getY() == -1) {

            gameState.playerMatHashMap.get(playerID).reboot(game, gameState, isPlayerAction);

            return true;

        } else {

            BoardElement currentBoardElement = gameState.gameBoard.getGameBoard()[oldPosition.getY()][oldPosition.getX()];
            BoardElement destinationBoardElement = gameState.gameBoard.getGameBoard()[newPosition.getY()][newPosition.getX()];
            Position newPosition1 = destinationBoardElement.getXY();

            if (currentBoardElement.checkIfElementCanLeftInThisDirection(movingOrientation)) {

                if (destinationBoardElement.checkIfElementCanEnteredInThisDirection(movingOrientation)) {

                    if (destinationBoardElement.getRobot() == null) {

                        return completeMove(game, gameState, playerID, newPosition1, currentBoardElement, destinationBoardElement, isPlayerAction, isLastMovePart);

                    } else {

                        if (move(game, gameState, destinationBoardElement.getRobot().getPlayerID(), newPosition1, getTargetPosition(newPosition, movingOrientation), movingOrientation, false, true)) {

                            return completeMove(game, gameState, playerID, newPosition1, currentBoardElement, destinationBoardElement, isPlayerAction, isLastMovePart);

                        } else {

                            if (isPlayerAction && isLastMovePart) {

                                PlayerMat playerMat = gameState.registerList.remove(0);

                                if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

                                    gameState.nextRegisterList.add(playerMat);

                                }

                                game.nextPlayersTurn();

                            }

                            return false;

                        }

                    }

                }

            }

            if (isPlayerAction && isLastMovePart) {

                PlayerMat playerMat = gameState.registerList.remove(0);

                if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

                    gameState.nextRegisterList.add(playerMat);

                }

                game.nextPlayersTurn();

            }

            return false;

        }

    }

    private boolean completeMove(Game game, GameState gameState, int playerID, Position newPosition, BoardElement currentBoardElement, BoardElement destinationBoardElement, boolean isPlayerAction, boolean isLastMovePart) {
        if (destinationBoardElement.isPit()) {

            gameState.playerMatHashMap.get(playerID).reboot(game, gameState, isPlayerAction);

        } else {

            currentBoardElement.setRobot(null);
            destinationBoardElement.setRobot(gameState.playerMatHashMap.get(playerID).getRobot());

            gameState.playerMatHashMap.get(playerID).getRobot().setXY(destinationBoardElement.getXY());

            String movement = messageHandler.buildMessage("Movement", new Movement(playerID, gameState.playerMatHashMap.get(playerID).getRobot().getRobotPosition()));
            gameState.server.sendMessageToAllUsers(movement);

            if (isPlayerAction && isLastMovePart) {

                PlayerMat playerMat = gameState.registerList.remove(0);

                if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

                    gameState.nextRegisterList.add(playerMat);

                }

                game.nextPlayersTurn();

            }

        }
        return true;
    }

    /**
     * This method returns the position that the robot will end on.
     * 
     * @param position
     *          the current position of the robot
     * @param movingDirection
     *          the moving direction of the robot
     *          
     * @return the target position of the robot
     */
    public Position getTargetPosition(Position position, String movingDirection) {

        return switch (movingDirection) {
            case "up" -> new Position(position.getX(), position.getY() - 1);
            case "left" -> new Position(position.getX() - 1, position.getY());
            case "down" -> new Position(position.getX(), position.getY() + 1);
            case "right" -> new Position(position.getX() + 1, position.getY());
            default -> null;
        };

    }

}
