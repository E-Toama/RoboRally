package utilities.messages;

public class HelloClient extends MessageBody{

    private final double protocol;

    public HelloClient(double protocol) {

        this.protocol = protocol;

    }

    public double getProtocol() {
        return protocol;
    }
}
