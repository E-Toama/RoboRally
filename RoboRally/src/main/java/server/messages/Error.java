package server.messages;

public class Error extends MessageBody {

    private final String error;

    public Error(String error) {

        this.error = error;

    }

    public String getError() {
        return error;
    }
}
