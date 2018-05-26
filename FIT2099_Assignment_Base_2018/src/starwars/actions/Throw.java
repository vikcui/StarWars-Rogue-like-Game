package starwars.actions;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.gridworld.Grid.CompassBearing;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActionInterface;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.entities.Grenade;


public class Throw extends SWAffordance implements SWActionInterface {
	
	
	static final int maxExplosion=20;
	static final int intermediateExplosion=10;
	static final int minExplosion=5;

	public Throw(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);	
		priority = 1;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canDo(SWActor a) {
		// TODO Auto-generated method stub
		return a.getItemCarried() instanceof Grenade;
	}

	@Override
	public void act(SWActor a) {
		// TODO Auto-generated method stub
		if (this.canDo(a)){
			EntityManager<SWEntityInterface, SWLocation> em = SWAction.getEntitymanager();
			ArrayList <CompassBearing> directions = new ArrayList <CompassBearing>();
			for (CompassBearing d:CompassBearing.values()){
				directions.add(d);
			}
			a.say(directions.toString());
			// location where the Grenade exploded
			SWLocation locationGrenade =em.whereIs(a);
			//entities at the same location take damage of 20 hit points(maxExplosion)
			List<SWEntityInterface> entities = em.contents(em.whereIs(a));
			for (SWEntityInterface e : entities){
				if(e != a){
					e.takeDamage(this.maxExplosion);
				}
			}
			System.out.println(locationGrenade.getNeighbour(directions.get(0))==null);
			
			for (CompassBearing d:directions){
				System.out.println(d);
				List<SWEntityInterface> entities1 = em.contents( (SWLocation) locationGrenade.getNeighbour(d));
				List<SWEntityInterface> entities2 = em.contents((SWLocation) ((SWLocation) em.whereIs(a).getNeighbour(d)).getNeighbour(d));
				if (entities1!=null){
					
				
				// entities at the location that can be reached in q step 
				// take damage of 20 hit points(maxExplosion)
					for (SWEntityInterface e1 : entities1){
							e1.takeDamage(this.intermediateExplosion);
					
					}
				}
				if (entities2!=null){
					for (SWEntityInterface e2 : entities2){
							e2.takeDamage(this.minExplosion);
					}
				}
			}
			//make the Grenade disappear
			a.setItemCarried(null);
			//make the Grenade disappear on map
			
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Throw" + target.getShortDescription();
	}

}
