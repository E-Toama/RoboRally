package AI;

import AI.logic.AIGameState;
import AI.logic.utilities.tablebases.CSVHandler;
import AI.network.AINetworkThread;
import game.utilities.PositionLookUp;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Starter Class for the AI.
 */
public class AIClient {

    private int port;
    private AIGameState aiGameState = new AIGameState();
    private AINetworkThread aiNetworkThread;

    public AIClient(int port) throws IOException {

        this.port = port;

    }

    public static void main(String[] args) {

        try {

            PositionLookUp.createMaps();

            AIClient aiClient = new AIClient(9090);

            aiClient.startNetworkThread();

            TimerTask timerEndedTask = new TimerTask() {

                @Override
                public void run() {

                    try {

                        aiClient.choosePlayerValues();

                    } catch (IOException e) {

                        e.printStackTrace();

                    }

                }

            };

            Timer timer = new Timer();

            timer.schedule(timerEndedTask, 1000);


        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void startNetworkThread() throws IOException {

        aiNetworkThread = new AINetworkThread(port, aiGameState);

        Thread thread = new Thread(aiNetworkThread);
        thread.start();

    }

    public void choosePlayerValues() throws IOException {

        aiNetworkThread.choosePlayerValues();

    }

}
