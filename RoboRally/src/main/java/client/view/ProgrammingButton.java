package client.view;


import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProgrammingButton extends Button {
    private String cardString;
    private boolean chosen;
    private final int number;

    public ProgrammingButton(int number, String cardString) {
        this.number = number;
        this.cardString = cardString;
        this.setGraphic(createCardImage(cardString));
        chosen = false;
    }

    public boolean isChosen() {
        return chosen;
    }

    public int getNumber() {
        return number;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }

    public ImageView createCardImage(String cardType) {
        Image cardImage;
        switch (cardType) {
            case "MoveI":
                cardImage = new Image("Cards/MoveOne.png");
                return adjustToSlot(cardImage);
            case "MoveII":
                cardImage = new Image("Cards/MoveTwo.png");
                return adjustToSlot(cardImage);
            case "MoveIII":
                cardImage = new Image("Cards/MoveThree.png");
                return adjustToSlot(cardImage);
            case "TurnLeft":
                cardImage = new Image("Cards/LeftTurn.png");
                return adjustToSlot(cardImage);
            case "TurnRight":
                cardImage = new Image("Cards/RightTurn.png");
                return adjustToSlot(cardImage);
            case "UTurn":
                cardImage = new Image("Cards/UTurn.png");
                return adjustToSlot(cardImage);
            case "BackUp":
                cardImage = new Image("Cards/MoveBack.png");
                return adjustToSlot(cardImage);
            case "PowerUp":
                cardImage = new Image("Cards/PowerUp.png");
                return adjustToSlot(cardImage);
            case "Again":
                cardImage = new Image("Cards/Again.png");
                return adjustToSlot(cardImage);
            default:
                cardImage = new Image("Cards/PlayerDeckBack.png");
                return adjustToSlot(cardImage);
        }
    }

    private static ImageView adjustToSlot(Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(80);
        imageView.setPreserveRatio(true);
        return imageView;

    }

    public String getCardString() {
        return cardString;
    }
}
