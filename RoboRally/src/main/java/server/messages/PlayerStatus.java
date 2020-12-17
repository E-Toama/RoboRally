package server.messages;

public class PlayerStatus extends MessageBody {

    private final double id;
    private final Boolean ready;

    public PlayerStatus(double id, Boolean ready) {

        this.id = id;
        this.ready = ready;

    }

    public double getId() {
        return id;
    }

    public Boolean getReady() {
        return ready;
    }
}
