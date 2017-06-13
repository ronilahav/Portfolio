package sokobanAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import Model.Actions.MySokobanPolicy;
import Model.Actions.Policy;
import Model.Data.Level;
import searchable.Searchable;
import sokobanAdapter.actions.SokoActionS;
import sokobanAdapter.state.SokoState;
import state.State;

public class SokoSearchable implements Searchable<char[][]> {
	
	private Level lvl = new Level();
	private Policy policy = new MySokobanPolicy();
	private State<char[][]> initialState;
	private State<char[][]> goalState;
	private HashMap<Integer, State<char[][]>> allCreatedStates = new HashMap<>();

	public SokoSearchable(Level goal, Level initial) {
		initialState = new SokoState(Util.generateCharArrayFromLevel(initial));
		initialState = isContains(initialState);
		initialState.setCost(0);
		goalState = new SokoState(Util.generateCharArrayFromLevel(goal));
		goalState = isContains(goalState);
	}

	public SokoSearchable(char[][] goal, char[][] initial) {
		initialState = new SokoState(initial);
		initialState = isContains(initialState);
		initialState.setCost(0);
		goalState = new SokoState(goal);
		goalState = isContains(goalState);
	}

	@Override
	public State<char[][]> getInitialState() {
		return initialState;
	}

	@Override
	public State<char[][]> getGoalState() {
		return goalState;
	}

	@Override
	public ArrayList<State<char[][]>> getAllPossibleStates(State<char[][]> s) {
		ArrayList<State<char[][]>> possibleStates = new ArrayList<>();
		lvl = Util.generateLevelFromCharArray(s.getState());
		double cost = s.getCost();
		int movment;
		State<char[][]> tempState;

		if ((movment = policy.checkAndMove(lvl, "up")) > 0) {
			tempState = new SokoState(Util.generateCharArrayFromLevel(lvl));
			possibleStates.add(addPossibleStates(tempState, cost, new SokoActionS("Move up", movment)));
			lvl = Util.generateLevelFromCharArray(s.getState());
		}
		if ((movment = policy.checkAndMove(lvl, "down")) > 0) {
			tempState = new SokoState(Util.generateCharArrayFromLevel(lvl));
			possibleStates.add(addPossibleStates(tempState, cost, new SokoActionS("Move down", movment)));
			lvl = Util.generateLevelFromCharArray(s.getState());
		}
		if ((movment = policy.checkAndMove(lvl, "left")) > 0) {
			tempState = new SokoState(Util.generateCharArrayFromLevel(lvl));
			possibleStates.add(addPossibleStates(tempState, cost, new SokoActionS("Move left", movment)));
			lvl = Util.generateLevelFromCharArray(s.getState());
		}
		if ((movment = policy.checkAndMove(lvl, "right")) > 0) {
			tempState = new SokoState(Util.generateCharArrayFromLevel(lvl));
			possibleStates.add(addPossibleStates(tempState, cost, new SokoActionS("Move right", movment)));
		}
		return possibleStates;
	}

	private State<char[][]> addPossibleStates(State<char[][]> newState, double cost, SokoActionS action) {
		newState = isContains(newState);
		double oldCost = newState.getCost();
		if (oldCost == Double.POSITIVE_INFINITY) {
			newState.setActionThisCameFrom(action);
			newState.setCost(cost + 1);
			newState.setBetterPath(true);
		} else {
			if (cost + 1 < oldCost) {
				newState.setActionThisCameFrom(action);
				newState.setCost(cost + 1);
				newState.setBetterPath(true);
			} else
				newState.setBetterPath(false);
		}
		return newState;
	}

	private State<char[][]> isContains(State<char[][]> state) {
		
		if (allCreatedStates.containsKey(state.hashCode())) state =
			allCreatedStates.get(state.hashCode()); 
		else
			allCreatedStates.put(state.hashCode(),state);
		return state;
	}

	@Override
	public SokoActionS getAction(State<char[][]> source, State<char[][]> destination) {
		return (SokoActionS) destination.getActionThisCameFrom();
	}
}