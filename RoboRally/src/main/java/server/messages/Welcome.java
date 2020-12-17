package server.messages;

public class Welcome extends MessageBody {

    private final int id;

    public Welcome(int id) {

        this.id = id;

    }

    public int getId() {
        return id;
    }
}
