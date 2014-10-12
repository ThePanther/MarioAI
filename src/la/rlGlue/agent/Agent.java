package la.rlGlue.agent;

import org.rlcommunity.rlglue.codec.types.Action;
import org.rlcommunity.rlglue.codec.types.Observation;

/**
 * RL Glue: Agent Interface
 * 
 * Der Agent beinhaltet den Lernalgorithmus.
 * 
 * Hier wird entschieden welche Action bei welchem Schritt (Step) gewählt wird
 * 
 * RL-Glue Beschreibung:
 * ---------------------------------------------------------------------------------------- 
 * 	The primary agent functions take some combination of observations and 
 * 	rewards as input and return actions. 
 *  
 *  The observations and rewards are created by the environment, 
 *  so the agent program needs to only read their values. 
 *  
 *  The actions, however, must be defined by the agent. 
 *  Actions can be any combination of a list of int, double, and character values.
 * ---------------------------------------------------------------------------------------- 
 * 
 */
public interface Agent {

	/**
	 * 
	 * RL-Glue Beschreibung:
	 * ---------------------------------------------------------------------------------------- 
	 * 	The agent_start function selects the first action at the 
	 * 	beginning of an episode based on the first observation from the environment. 
	 *  
	 *  The agent_start function does not receive a reward as input; 
	 *  agent_start usually contains no learning update code. 
	 *  
	 *  For example, the following function selects the first action based on 
	 *  the current value function estimate:
	 *  
	 *		1. agent_start (observation) --> action
	 * 		2.      lastObservation=observation
	 *		3.      for each action a
	 *		4.           if highest valued action valueFunction(observation,a)
	 *		5.           then store a as lastAction
	 * 		6. return lastAction
	 * ----------------------------------------------------------------------------------------
	 *
	 * @param observation 	: Beobachtung aus der Umwelt
	 * @return Action 		: letzte Action
	 */
	public Action agentStart(Observation observation);
	
	/**
	 * RL-Glue Beschreibung:
	 * ----------------------------------------------------------------------------------------
	 *  The agent_step function encodes the heart of the agent's learning algorithm and 
	 *  action selection mechanism. 
	 * 
	 *  At a minimum the step function must return an action every time it is called. 
	 * 
	 *  In most learning agents, the step function queries the agent's 
	 *  action selection function and performs a learning update based on 
	 *  the input observation and reward. 
	 * 
	 *  The following agent_step function shows pseudocode for a value update:
	 * 
	 * 		1. agent_step(reward, observation)-> action
	 * 		2.      update(valueFunction, lastObservation, lastAction, reward, observation)
	 * 		3.      newAction = select_action(observation, valueFunction)
	 * 		4.      lastObservation = observation
	 * 		5.      lastAction = newAction
	 * 		6. return newAction
	 * 
	 *  !!! Notice that the agent program must explicitly store the observation and 
	 *      action from the previous time step. 
	 *      RL-Glue does not make the history of actions, observations and 
	 *      rewards available to the agent or environment. !!!
	 * ----------------------------------------------------------------------------------------
	 * 
	 * @param reward 		: Double Wert (Reward)
	 * @param observation 	: Beobachtung aus der Umwelt
	 * @return Action 		: neue Action
	 */
	public Action agentStep(double reward, Observation observation);
	
	/**
	 * RL-Glue Beschreibung:
	 * ----------------------------------------------------------------------------------------
	 *  In an episodic task, the environment enters a terminal state that ends the episode. 
	 *  
	 *  RL-Glue responds to the end of an episode by calling the agent_end function, 
	 *  passing the reward produced on the last transition to the agent and signaling the end 
	 *  of the current episode. 
	 *  
	 *  The agent_end function usually performs a final learning update 
	 *  based on the last transition and also performs any other end-of-episode routines, 
	 *  such as clearing eligibility traces. 
	 *  
	 *  If the environment is non-episodic RL-Glue will never call agent_end.
	 *  Continuing with the SARSA example:
	 *  
	 *  	1. agent_end (reward) 
	 *  	2.      update(valueFunction, lastObservation, lastAction, reward)
	 *  
	 *  !!! The agent_end function does not receive the final observation from the environment. 
	 *  	In many learning problems this is of no consequence because the agent 
	 *  	does not make a decision in the terminal state. 
	 *  	If, however, the agent were learning a model of the environment, 
	 *  	information about the final transition would be important. 
	 *  	In this case, it is recommended that the environment be augmented with 
	 *  	a terminal state that has a reward of zero on the transition into it. 
	 *  	This choice was made to keep the RL-Glue interface as minimal and 
	 *  	light-weight as possible. !!!
	 * ----------------------------------------------------------------------------------------
	 * 
	 * @param reward 		: Double Wert (Reward)
	 */	
	public void agentEnd(double reward);
	
	/**
	 *  RL-Glue Beschreibung:
	 * ----------------------------------------------------------------------------------------
	 * The agent_init functions are called at the beginning and end of a learning experiment
	 * It receives the task_spec string as input. 
	 * 
	 * The agent_init function usually parses the task_spec and 
	 * stores various information encoded in the string. 
	 * 
	 * For example, after parsing the task_spec, the agent_init function can then initialize 
	 * the value function array to the size state space using the number of states from 
	 * the task_spec. 
	 * 
	 * Remember the task_spec is not required and could just be an empty string.
	 * ----------------------------------------------------------------------------------------
	 *
	 * @param taskSpec		: 
	 */
	public void agentInit(String taskSpec);
	
	/** 
	 * RL-Glue Beschreibung:
	 * ----------------------------------------------------------------------------------------
	 * The agent_cleanup functions are called at the beginning and end of a learning experiment.
	 * ----------------------------------------------------------------------------------------
	 * 
	 */
	public void agentCleanup();
	
	/**
	 * RL-Glue Beschreibung:
	 * ----------------------------------------------------------------------------------------
	 * The agent_message function is used to send an arbitrary string message 
	 * to the agent program. 
	 * 
	 * This function can be used to change agent parameters, notify the agent 
	 * that the exploration phase is over, and request the name of the agent program, 
	 * for example. 
	 * 
	 * People have created whole protocols that use agent_message to set agent learning 
	 * parameters, query their value functions, and more.
	 * 
	 * Here is a quick example of how you could query the current values of some 
	 * parameters of an agent:
	 * 
	 * 		agent_message(inMessage) --> outMessage
	 * 			if inMessage == "getCurrentStepSize"  
	 * 				return alpha
	 * 			end 
	 * 			if inMessage == "getCurrentExplorationRate"  
	 * 				return epsilon
	 * 			end 
	 * 		return ""
	 * 
	 * ----------------------------------------------------------------------------------------
	 * 
	 * @param inMessage		: Eingabe String
	 * @return String 		: Ausgabe String
	 */
	public String agentMessage(String inMessage);
}
