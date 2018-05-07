package starwars.entities.actors.behaviors;

import java.util.ArrayList;
import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.space.Direction;
import starwars.RandomDirection;
import starwars.SWWorld;
import starwars.actions.Move;
import starwars.entities.actors.Droid;

public class DroidMove {
	private SWWorld myWorld;
	private int maxLength;
	private ArrayList<CompassBearing> moves;
	private int position = 0;
	
	public DroidMove(SWWorld world){
		this.myWorld=world;
		this.maxLength=Math.max(myWorld.height(), myWorld.width());
//		for(int i=0;i<maxLength;i++){
//			this.moves.add(newCompassBearing);
		this.moves=new ArrayList<CompassBearing>();
		
//		}
		
	}
	public CompassBearing getNext(Droid d){
		if (moves.size()==0){
			
			RandomDirection rd = new RandomDirection();
			Grid.CompassBearing nextRD = rd.convertNumCompass();
			for(int i=0;i<maxLength;i++){
				this.moves.add(nextRD);
			}	
			
		}
		while (!(this.myWorld.getEntityManager().seesExit(d,this.moves.get(position)))){
			this.moves.clear();
			this.position=0;
			RandomDirection rd = new RandomDirection();
			Grid.CompassBearing nextRD = rd.convertNumCompass();
			for(int i=0;i<maxLength;i++){
				this.moves.add(nextRD);
			}	
		}
		CompassBearing nextMove=moves.get(position);
		position++;
		return nextMove;
			
	
		}
		
		
	}




