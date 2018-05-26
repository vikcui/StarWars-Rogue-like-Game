package starwars.entities.actors.behaviors;

import java.util.ArrayList;
import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.actions.Move;
import starwars.entities.actors.Droid;

/*
 * A new class DroidMove added in the starwars.entities.actors.behaviors package.
 * The class is used to implement the behavior that the droid reach the wall of the Grid and it will randomly pick a direction and keep moving
 * in that direction.
 * @author kapoho and cuiyang
 *
 */
public class RandomDroidMove {
	/**
	 * there are four attributes in the class:
	 * 1. myWord is a instance of SWWorld.
	 * 2. maxLength is a integer that represent the maximum value of the height of the Grid and the width of the Grid, to ensure it is in a safe
	 * location inside the Grid after the droid keep moving in a direction.
	 * 3. moves is a arrayList that contains enum class ComassBearing object (the all possible directions)
	 * 4. position is a integer to represent the index of direction.(to make the droid keep moving in one direction, we need to make that droid
	 * keep moving until it reach the wall (this can use the maxlength to make sure it will not move out the wall)
	 */
	private SWWorld myWorld;
	private int maxLength;
	private ArrayList<CompassBearing> moves;
	private int position = 0;

	
	/**
	 * The constructor with one argument to instantiate a new DroidMove object
	 * it initializes the key attributes of the Droid move class.
	 * @param  </code>world </code>, a word object of SWWordld to represent the position of Droid in the world.
	 */
	public RandomDroidMove(SWWorld world){
		this.myWorld=world;
		this.maxLength=Math.max(myWorld.height(), myWorld.width());
//		for(int i=0;i<maxLength;i++){
//			this.moves.add(newCompassBearing);
		this.moves=new ArrayList<CompassBearing>();
		
//		}
		
	}
	
	/**
	 * This class is used to get the next position that the droid should move.
	 * As the droid should get a random direction first and keep moving at that position until it hit the wall(function seeExit() can guarantee that the droid should always move into the place that has neighbors)
	 * After the droid hit the wall, we clear the moves arrayList and repeat the process above.
	 * @param </code>d</code>,  
	 * @return the next direction that the droid needs to move.
	 */
	public CompassBearing getNext(Droid d){

		if (moves.size()==0){
			Grid.CompassBearing nextRD = CompassBearing.getRandomBearing();
			for(int i=0;i<maxLength;i++){
				this.moves.add(nextRD);
			}	
			
		}
		while (!(this.myWorld.getEntityManager().seesExit(d,this.moves.get(position)))){
			this.moves.clear();
			this.position=0;
			Grid.CompassBearing nextRD = CompassBearing.getRandomBearing();
			for(int i=0;i<maxLength;i++){
				this.moves.add(nextRD);
			}	
		}
		CompassBearing nextMove=moves.get(position);
		position++;
		return nextMove;
			
	
		}
		
		
	}




