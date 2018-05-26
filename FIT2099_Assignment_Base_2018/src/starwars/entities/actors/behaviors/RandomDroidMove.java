package starwars.entities.actors.behaviors;



import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import starwars.SWWorld;
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
	// the next possible direction to move
	private CompassBearing moves;

	
	/**
	 * The constructor with one argument to instantiate a new DroidMove object
	 * it initializes the key attributes of the Droid move class.
	 * @param  </code>world </code>, a word object of SWWordld to represent the position of Droid in the world.
	 */
	public RandomDroidMove(SWWorld world){
		this.myWorld=world;	
	}
	
	/**
	 * This class is used to get the next position that the droid should move.
	 * As the droid should get a random direction first and keep moving at that position until it hit the wall(function seeExit() can guarantee that the droid should always move into the place that has neighbors)
	 * After the droid hit the wall, get a new Ramdom direction one that will not lead to Driod hitting the wall.
	 * @param </code>d</code>,  
	 * @return the next direction that the droid needs to move.
	 */
	public CompassBearing getNext(Droid d){

		if (moves==null){
			moves = CompassBearing.getRandomBearing();
		}

		while (!(this.myWorld.getEntityManager().seesExit(d,this.moves))){
			moves= CompassBearing.getRandomBearing();
		}
		return moves;
			
	
		}
		
		
	}




