package player;

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

    public String getRobotName() {

        switch (figure) {

            case 1:
                return "Hammer Bot";

            case 2:
                return "Hulk x90";

            case 3:
                return "Smash Bot";

            case 4:
                return "Spin Bot";

            case 5:
                return "Twonky";

            case 6:
                return "Zoom Bot";

            default:
                return "";

        }

    }

}
