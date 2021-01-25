package game.player;

public class Player {

    private final int id;
    private final String name;
    private final int figure;

    private Boolean status = false;

    public Player(int id, String name, int figure) {

        this.id = id;
        this.name = name;
        this.figure = figure;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getFigure() {
        return figure;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }



    //Additional values for PlayerState including getters/setter

    private int currentPosition;
    private int cardsInDeck;
    private String[] registers = new String[5];


    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getCardsInDeck() {
        return cardsInDeck;
    }

    public void setCardsInDeck(int cardsInDeck) {
        this.cardsInDeck = cardsInDeck;
    }

    public String[] getRegisters() {
        return registers;
    }

    public void setRegisters(String[] registers) {
        this.registers = registers;
    }

    public void setSingleRegister(String card, int register) {
        this.registers[register] = card;
    }


}
