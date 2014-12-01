package la.rlGlue;

import la.application.configManagement.Config;

public class SARSAAgent extends RLGlueAgent {
	public static final String NAME = "SARSA";

	private double sarsa_stepsize = 0.1;
	private double sarsa_gamma = Config.DISCOUNT_FACTOR;

	int calculateNewAction(double reward, double[] newValueFunction) {
    	return egreedy(newValueFunction);
    }

    double calculateNewReward(double reward, double[] newValueFunction) {
        double Q_sa = valueFunction[lastAction];

        double Q_sprime_aprime = newValueFunction[calculateNewAction(reward, newValueFunction)];

    	return Q_sa + sarsa_stepsize * (reward + sarsa_gamma * Q_sprime_aprime - Q_sa);
    }

    double calculateLastReward(double reward) {
        double Q_sa = valueFunction[lastAction];

    	return Q_sa + sarsa_stepsize * (reward - Q_sa);
    }
}