package utilities.messages;

public class SetStatus extends MessageBody {

    private final Boolean ready;

    public SetStatus(Boolean ready) {

        this.ready = ready;

    }

    public Boolean getReady() {
        return ready;
    }
}
