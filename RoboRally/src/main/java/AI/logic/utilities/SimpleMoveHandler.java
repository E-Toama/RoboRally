package AI.logic.utilities;

import AI.logic.AIGameState;
import game.gameboard.BoardElement;
import game.utilities.Position;

public class SimpleMoveHandler {

    public SimpleMoveHandler() {}

    public void move(AIGameState aiGameState, String movingDirection) {

        Position position = aiGameState.getIntermediatePosition();
        Position targetPosition = getTargetPosition(position, movingDirection);

        RebootHandler rebootHandler = new RebootHandler();

        if (targetPosition.getX() == 13 || targetPosition.getY() == 10 || targetPosition.getX() == -1 || targetPosition.getY() == -1) {

            rebootHandler.handleReboot(aiGameState);

        } else {

            BoardElement currentBoardElement = aiGameState.getIntermediateBoardElement();
            BoardElement targetBoardElement = aiGameState.getGameBoard().getGameBoard()[targetPosition.getY()][targetPosition.getX()];

            if (currentBoardElement.checkIfElementCanLeftInThisDirection(movingDirection)) {

                if (targetBoardElement.checkIfElementCanEnteredInThisDirection(movingDirection)) {

                    if (targetBoardElement.isPit()) {

                        rebootHandler.handleReboot(aiGameState);

                    } else {

                        aiGameState.setIntermediatePosition(targetBoardElement.getXY());

                    }

                }

            }

        }

    }

    private Position getTargetPosition(Position position, String movingDirection) {

        return switch (movingDirection) {
            case "up" -> new Position(position.getX(), position.getY() - 1);
            case "left" -> new Position(position.getX() - 1, position.getY());
            case "down" -> new Position(position.getX(), position.getY() + 1);
            case "right" -> new Position(position.getX() + 1, position.getY());
            default -> null;
        };

    }

}
