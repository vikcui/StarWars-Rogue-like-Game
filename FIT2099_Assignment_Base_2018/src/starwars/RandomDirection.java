package starwars;

import java.util.Random;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;


public class RandomDirection {
	CompassBearing d;
		
	public CompassBearing convertNumCompass(int Num){
	switch(Num){
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
