package game.Robots;

public abstract class Robot {

   /* private String RobotImageUp;
    private String RobotImageLeft;
    private String RobotImageDown;
    private String RobotImageRight;*/
    //private int Figure;

    public static String getRobotName(int figure) {

        switch (figure) {

            case 0:
                return "Hammer Bot";

            case 1:
                return "Hulk x90";

            case 2:
                return "Smash Bot";

            case 3:
                return "Spin Bot";

            case 4:
                return "Twonky";

            case 5:
                return "Zoom Bot";

            default:
                return "";

        }

    }

    public static Robot getRobotByFigure(int figure) {

        switch (figure) {

            case 0:
                return new HammerBot();

            case 1:
                return new Hulk();

            case 2:
                return new SmashBot();

            case 3:
                return new SpinBot();

            case 4:
                return new Twonky();

            case 5:
                return new ZoomBot();

            default:
                return null;

        }

    }

}
