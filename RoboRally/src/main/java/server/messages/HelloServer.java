package server.messages;

public class HelloServer extends MessageBody{

    private final double protocol;
    private final String group;
    private final Boolean isAI;

    public HelloServer(double protocol, String group, Boolean isAI) {

        this.protocol = protocol;
        this.group = group;
        this.isAI = isAI;

    }

    public double getProtocol() {
        return protocol;
    }

    public String getGroup() {
        return group;
    }

    public Boolean getAI() {
        return isAI;
    }
}
