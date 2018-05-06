package starwars.entities.actors.behaviors;

import java.util.List;
import edu.monash.fit2099.simulator.matter.EntityManager;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.entities.actors.Player;
/** a new class train in the package starwars.entities.actors.behaviors. 
 *  The Train class is a behavior of a actor, and in star world, the class will focus more on the training that Ben do to Luke,
 *  The class contains its constructor and a new function TrainLuke
 *  And it has two attributes in the Train class, one is SWActor actor and the other one is SWWorld world.
 * 	@author CUIYANG and Ka Po Ho
 *
 */
public class Train {
	private SWActor actor;
    private SWWorld world;

    /**
     * Constructor for Train class, to create a new Train object 
     * @param </code>SWActor</code>, a instance of SWActor used to initialize a new Train object
     * @param </code>SWWorld</code>, a instance of SWWorld used to initialize a new Train object
     */
	public Train(SWActor a, SWWorld w){
		this.actor = a;
		this.world = w;
	}
	
	/**
	 * Luke can only be trained by Ben when they are in the same location, thus we must check this condition before we set Luke force ability to strong. And this can be shown in detailed as following:
	 * Firstly, we should get the location of Ben by calling the function whereIs(Ben).
	 * Then, we should get all the entities in that location (Ben included) by passing the location of Ben to the function contents(loc).
	 * Since the contents(loc) will return a list containning all the entites in that location, so we need to go through each entity in that list and check that if there is a Player(Luke) instance, 
	 * also the entity we check should exclude Ben himself. If a Player object has been founded, then we know Ben and Luke are in the same location, and we can set Luke’s force ability to Strong so that he can wield a lightsaber. (This step should only happen when Luke’s force state is not strong, because there is no need to train Luke when he is already with a strong force.)
	 * @param </code>SWActor</code>
	 * @param </code>SWWorld</code>
	 */
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
