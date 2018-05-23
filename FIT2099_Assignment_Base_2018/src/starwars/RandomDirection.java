package starwars;

import java.util.Random;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;

/*
 * A new class RandomDirection in the starwars package.
 * This class is used to create random number and convert each random number we passed in to a direction so that we can get the random 
 * direction.
 * @author KaPoHo
 *
 */
public class RandomDirection {
	public RandomDirection(){
		
	}
	/**
	 * There are three attributes in the class: rand, randNum and d.
	 * rand and randNum both are used to create a random number.
	 * d is instance of enum class CompassBearing, which is used to set the direction.
	 */
	private Random rand = new Random();
	private int randNum = rand.nextInt(8)+1;
	CompassBearing d;
		
	/**
	 * This is the method that using switch statement to convert each possible random number to a direction.(there are 8 possible directions so we need to 8 random number in total)
	 * @return d, the direction that has been converted from a random number.
	 */
	public CompassBearing convertNumCompass(){
	switch(randNum){
		case 1:
			d = Grid.CompassBearing.NORTH;
			break;
		case 2:
			d= Grid.CompassBearing.NORTHEAST;
			break;
		case 3:
			d= Grid.CompassBearing.EAST;
			break;
		case 4:
			d= Grid.CompassBearing.SOUTHEAST;
			break;
		case 5:
			d= Grid.CompassBearing.SOUTH;
			break;
		case 6:
			d=Grid.CompassBearing.SOUTHWEST;
			break;
		case 7:
			d=Grid.CompassBearing.WEST;
			break;
		case 8:
			d=Grid.CompassBearing.NORTHWEST;
			break;
		}
	return d;
	}	
}
