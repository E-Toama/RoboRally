package AI.logic.simplecards.simpleprogrammingcards;

import AI.logic.AIGameState;
import AI.logic.simplecards.CardS;

public class AgainS extends CardS {

    public AgainS() {

        this.name = "Again";

    }

    @Override
    public void action(AIGameState aiGameState, int currentRegisterNumber) {

        if (aiGameState.getIntermediateRegister()[currentRegisterNumber - 1].getName().equals("Again")) {

            aiGameState.getIntermediateRegister()[currentRegisterNumber - 2].action(aiGameState, currentRegisterNumber);

        } else {

            aiGameState.getIntermediateRegister()[currentRegisterNumber - 1].action(aiGameState, currentRegisterNumber);

        }

    }

}
