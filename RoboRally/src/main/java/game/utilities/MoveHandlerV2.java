package game.utilities;

import game.Game;
import game.gameboard.BoardElement;
import game.player.PlayerMat;
import utilities.MessageHandler;
import utilities.messages.Movement;

public class MoveHandlerV2 {

    protected final MessageHandler messageHandler = new MessageHandler();

    public boolean move(Game game, GameState gameState, int playerID, Position oldPosition, Position newPosition, String movingOrientation, boolean isPlayerAction, boolean isLastMovePart) {

        boolean playerCouldBeMoved;

        if (newPosition.getX() >= 13 || newPosition.getX() <= -1 || newPosition.getY() >= 10 || newPosition.getY() <= -1) {

            gameState.playerMatHashMap.get(playerID).reboot(game, gameState, isPlayerAction);

            playerCouldBeMoved = true;

        } else {

            playerCouldBeMoved = completeMove(game, gameState, playerID, oldPosition, newPosition, movingOrientation, isPlayerAction, isLastMovePart);

            if (isPlayerAction && isLastMovePart) {

                PlayerMat playerMat = gameState.registerList.remove(0);

                if (!gameState.playerMatHashMap.get(playerID).getWasRebootedThisRound()) {

                    gameState.nextRegisterList.add(playerMat);

                }

                game.nextPlayersTurn();

            }

        }

        return playerCouldBeMoved;

    }

    private boolean completeMove(Game game, GameState gameState, int playerID, Position oldPosition, Position newPosition, String movingOrientation, boolean isPlayerAction, boolean isLastMovePart) {

        boolean playerCouldBeMoved = false;

        BoardElement currentBoardElement = gameState.gameBoard.getGameBoard()[oldPosition.getY()][oldPosition.getX()];
        BoardElement nextBoardElement = gameState.gameBoard.getGameBoard()[newPosition.getY()][newPosition.getX()];

        if (nextBoardElement.isPit()) {

            gameState.playerMatHashMap.get(playerID).reboot(game, gameState, isPlayerAction);

            playerCouldBeMoved = true;

        } else {

            if (currentBoardElement.checkIfElementCanLeftInThisDirection(movingOrientation)) {

                if (nextBoardElement.checkIfElementCanEnteredInThisDirection(movingOrientation)) {

                    if (nextBoardElement.getRobot() == null) {

                        setRobotPosition(currentBoardElement, nextBoardElement, gameState, playerID);

                        playerCouldBeMoved = true;

                    } else {

                        if (move(game, gameState, nextBoardElement.getRobot().getPlayerID(), nextBoardElement.getXY(), getTargetPosition(nextBoardElement.getXY(), movingOrientation), movingOrientation, false, true)) {

                            setRobotPosition(currentBoardElement, nextBoardElement, gameState, playerID);

                            playerCouldBeMoved = true;

                        }

                    }

                }

            }

        }

        return playerCouldBeMoved;

    }

    private void setRobotPosition(BoardElement currentBoardElement, BoardElement nextBoardElement, GameState gameState, int playerID) {

        currentBoardElement.setRobot(null);
        nextBoardElement.setRobot(gameState.playerMatHashMap.get(playerID).getRobot());

        gameState.playerMatHashMap.get(playerID).getRobot().setXY(nextBoardElement.getXY());

        String movement = messageHandler.buildMessage("Movement", new Movement(playerID, gameState.playerMatHashMap.get(playerID).getRobot().getRobotPosition()));
        gameState.server.sendMessageToAllUsers(movement);

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
