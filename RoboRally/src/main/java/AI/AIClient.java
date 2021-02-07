package AI;

import AI.logic.AIGameState;
import AI.logic.utilities.tablebases.CSVHandler;
import AI.network.AINetworkThread;
import game.utilities.PositionLookUp;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AIClient {

    private int port;
    private AIGameState aiGameState = new AIGameState();
    private AINetworkThread aiNetworkThread = new AINetworkThread(port, aiGameState);

    public AIClient(int port) throws IOException {

        this.port = port;

    }

    public static void main(String[] args) {

        try {

            PositionLookUp.createMaps();

            AIClient aiClient = new AIClient(9090);

            aiClient.startNetworkThread();
            aiClient.choosePlayerValues();

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    public void startNetworkThread() {
        Executor pool = Executors.newCachedThreadPool();
        pool.execute(aiNetworkThread);
    }

    public void choosePlayerValues() throws IOException {
        this.aiNetworkThread.choosePlayerValues();
    }

}
