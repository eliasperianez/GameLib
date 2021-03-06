package gameLib.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/*
 *Creado por Elias Peria�ez
 *31 ene. 2019
 *Como parte del proyecto GameLib
 *Este archivo esta bajo la licencia de Creative Commons Reconocimiento 4.0 Internacional (M�s informacion https://creativecommons.org/licenses/by/4.0/)
________________________________________________________________________________________________________________________________________________________
 *Created by Elias Peria�ez
 *31 ene. 2019
 *As part of the project GameLib
 *This file is under the Creative Commons Attribution 4.0 International (More info here https://creativecommons.org/licenses/by/4.0/)
 */

abstract public class Turn implements Serializable {

	private static final long serialVersionUID = -7901032368453232885L;
	/**
	 * @author Elias Peria�ez All the actions that can be done in this turn
	 */
	private Action[] possibleActions;

	private Unit[] turnUnits;

	private Menu menu;

	/**
	 * @author Elias Peria�ez
	 * @param <strong> possibleActions: </strong> All the actions that can be done
	 *                 in this turn
	 */
	public Turn(Action[] possibleActions, Unit[] units, Menu menu) {
		this.setPossibleActions(possibleActions);
		this.setTurnUnits(units);
		this.setMenu(menu);
	}

	public void update(Action[] possibleActions, Unit[] units) {
		this.setPossibleActions(possibleActions);
		this.setTurnUnits(units);
	}
	
	// TODO Javadoc
	public abstract Section[][] onCall(Section[][] table);

	/**
	 * @param <strong> game: </strong> The game object, use it to find out the
	 *                 current state of the game and filter
	 * @return An array list of possible actions in the current state of the game,
	 *         by default every action is considered as possible so it will return
	 *         possibleActions param. Use this method to check possible actions for
	 *         the current turn
	 */
	public Action[] filterCurrentActions(Game game) {
		ArrayList<Action> tmpPerUnitType = new ArrayList<Action>();
		Set<Action> tmpPerUnit = new HashSet<Action>();
		for (Unit u : turnUnits) {
			tmpPerUnit.addAll(Arrays.asList(u.getActionsPerUnit()));
			tmpPerUnitType.addAll(Arrays.asList(u.getActionsPerUnitType()));
		}
		ArrayList<Action> tmpPossibleActions = new ArrayList<Action>();
		tmpPossibleActions.addAll(tmpPerUnit);
		tmpPossibleActions.addAll(tmpPerUnitType);
		return (Action[]) tmpPossibleActions.toArray();
	}
	
	public void addUnit(Unit unit) {
		List<Unit> tmpList = Arrays.asList(this.turnUnits);
		tmpList.add(unit);
		this.turnUnits = (Unit[]) tmpList.toArray();
	}

	public Unit[] getTurnUnits() {
		return turnUnits;
	}

	public void setTurnUnits(Unit[] turnUnits) {
		this.turnUnits = turnUnits;
	}

	public Action[] getPossibleActions() {
		return possibleActions;
	}

	public void setPossibleActions(Action[] possibleActions) {
		this.possibleActions = possibleActions;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}
}
