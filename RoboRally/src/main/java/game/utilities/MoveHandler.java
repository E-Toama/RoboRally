package game.utilities;

import game.Game;
import game.gameboard.BoardElement;
import utilities.MessageHandler;
import utilities.messages.Movement;

public class MoveHandler {

    protected final MessageHandler messageHandler = new MessageHandler();

    public boolean move(Game game, GameState gameState, int playerID, Position oldPosition, Position newPosition, String movingOrientation, boolean isPlayerAction) {

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

                        return completeMove(game, gameState, playerID, newPosition1, currentBoardElement, destinationBoardElement, isPlayerAction);

                    } else {

                        if (move(game, gameState, destinationBoardElement.getRobot().getPlayerID(), newPosition1, getTargetPosition(newPosition, movingOrientation), movingOrientation, false)) {

                            return completeMove(game, gameState, playerID, newPosition1, currentBoardElement, destinationBoardElement, isPlayerAction);

                        } else {

                            if (isPlayerAction) {

                                gameState.nextRegisterList.add(gameState.registerList.remove(0));
                                game.nextPlayersTurn();

                            }

                            return false;

                        }

                    }

                }

            }

            if (isPlayerAction) {

                gameState.nextRegisterList.add(gameState.registerList.remove(0));
                game.nextPlayersTurn();

            }

            return false;

        }

    }

    private boolean completeMove(Game game, GameState gameState, int playerID, Position newPosition, BoardElement currentBoardElement, BoardElement destinationBoardElement, boolean isPlayerAction) {
        if (destinationBoardElement.isPit()) {

            gameState.playerMatHashMap.get(playerID).reboot(game, gameState, isPlayerAction);

        } else {

            currentBoardElement.setRobot(null);
            destinationBoardElement.setRobot(gameState.playerMatHashMap.get(playerID).getRobot());

            gameState.playerMatHashMap.get(playerID).getRobot().setXY(newPosition);

            String movement = messageHandler.buildMessage("Movement", new Movement(playerID, gameState.playerMatHashMap.get(playerID).getRobot().getRobotPosition()));
            gameState.server.sendMessageToAllUsers(movement);

            if (isPlayerAction) {

                gameState.nextRegisterList.add(gameState.registerList.remove(0));
                game.nextPlayersTurn();

            }

        }
        return true;
    }

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
