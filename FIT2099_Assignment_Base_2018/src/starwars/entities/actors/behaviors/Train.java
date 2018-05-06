package starwars.entities.actors.behaviors;

import java.util.List;

import edu.monash.fit2099.simulator.matter.EntityManager;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.entities.actors.Player;

public class Train {
	private SWActor actor;
    private SWWorld world;
    
	public Train(SWActor a, SWWorld w){
		this.actor = a;
		this.world = w;
	}
	
	public void TrainLuke(SWActor a, SWWorld w){
		SWLocation location = w.getEntityManager().whereIs(a);
		EntityManager<SWEntityInterface, SWLocation> em = w.getEntityManager();	
		List<SWEntityInterface> entities = em.contents(location);
		
		for (SWEntityInterface e : entities){
			if(e != a && e instanceof Player){
				if(((Player) e).getForcestate()<((Player) e).getStrongForce() ){
					((Player) e).setForcestate(((Player) e).getFullForce());
					a.say("Ben says: After trainning, the force ability of luke becomes strong and he is able to take the lightsaber !");
					a.say("Luke's forcestate: "+(((Player) e).getForcestate()));
				}
				
			}
		}
	}
}
