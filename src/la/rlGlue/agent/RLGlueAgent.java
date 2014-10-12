package la.rlGlue.agent;

import java.util.Random;

import org.rlcommunity.rlglue.codec.taskspec.TaskSpec;
import org.rlcommunity.rlglue.codec.taskspec.ranges.DoubleRange;
import org.rlcommunity.rlglue.codec.taskspec.ranges.IntRange;
import org.rlcommunity.rlglue.codec.types.Action;
import org.rlcommunity.rlglue.codec.types.Observation;

public class RLGlueAgent implements Agent {

	Random randGenerator = new Random();
	Action lastAction;
	Observation lastObservation;

	@Override
	public Action agentStart(Observation observation) {
		/**
		 * Choose a random action (0 or 1)
		 */
		int theIntAction = randGenerator.nextInt(2);
		/**
		 * Create a structure to hold 1 integer action and set the value
		 */
		Action returnAction = new Action(1, 0, 0);
		returnAction.intArray[0] = theIntAction;

		lastAction = returnAction.duplicate();
		lastObservation = observation.duplicate();

		return returnAction;
	}

	@Override
	public Action agentStep(double reward, Observation observation) {
		/**
		 * Choose a random action (0 or 1)
		 */
		int theIntAction = randGenerator.nextInt(2);
		/**
		 * Create a structure to hold 1 integer action and set the value
		 * (alternate method)
		 */
		Action returnAction = new Action();
		returnAction.intArray = new int[] { theIntAction };

		lastAction = returnAction.duplicate();
		lastObservation = observation.duplicate();

		return returnAction;
	}

	@Override
	public void agentEnd(double reward) {
	}

	@Override
	public void agentInit(String taskSpec) {

		TaskSpec theTaskSpec = new TaskSpec(taskSpec);
		System.out.println("Agent parsed the task spec.");
		System.out.println("Observation have "
				+ theTaskSpec.getNumDiscreteObsDims() + " integer dimensions");
		System.out.println("Actions have "
				+ theTaskSpec.getNumDiscreteActionDims()
				+ " integer dimensions");

		IntRange theObsRange = theTaskSpec.getDiscreteObservationRange(0);
		System.out.println("Observation (state) range is: "
				+ theObsRange.getMin() + " to " + theObsRange.getMax());

		IntRange theActRange = theTaskSpec.getDiscreteActionRange(0);
		System.out.println("Action range is: " + theActRange.getMin() + " to "
				+ theActRange.getMax());

		DoubleRange theRewardRange = theTaskSpec.getRewardRange();
		System.out.println("Reward range is: " + theRewardRange.getMin()
				+ " to " + theRewardRange.getMax());

		// In more complex agents, you would also check for continuous
		// observations and actions, discount factors, etc.
		// Also, these ranges can have special values like
		// "NEGINF, POSINF, UNSPEC (unspecified)". There is no guarantee
		// that they are all specified and that they are all nice numbers.
	}

	@Override
	public void agentCleanup() {
		lastAction = null;
		lastObservation = null;
	}

	@Override
	public String agentMessage(String inMessage) {
		if (inMessage.equals("what is your name?"))
			return "my name is skeleton_agent, Java edition!";

		return "I don't know how to respond to your message";
	}

}
