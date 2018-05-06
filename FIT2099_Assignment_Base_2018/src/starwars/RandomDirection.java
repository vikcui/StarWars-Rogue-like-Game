package starwars;

import java.util.Random;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;


public class RandomDirection {
	private Random rand = new Random();
	private int randNum = rand.nextInt(8)+1;
	CompassBearing d;
		
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
