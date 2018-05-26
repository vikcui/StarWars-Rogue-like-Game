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


/**
 * A new class Throw in the package starwars.entities.actors.behaviors.
 * Throw is a action of a actor, and in star war scenario, the throw that only can be performed by actor when item carried by actor is grenade.
 * The class has its own constructor and three more other functions : canDo(), act(), getDescription().
 * There are three constant attributes inside the class: maxExplosion, intermediateExplosion, minExplosion.
 * @author Kapo Ho and cuiyang
 *
 */
public class Throw extends SWAffordance implements SWActionInterface {
	
	// the int attribute representing the maximum explosion damage
	static final int maxExplosion=20;
	// the int attribute representing the intermediate explosion damage
	static final int intermediateExplosion=10;
	// the int attribute representing the minimum explosion damage
	static final int minExplosion=5;

	/**
	 * The constructor for Throw class, will initialize the the message renderer, the target and 
	 * set the priority of this <code>Action</code> to 1 (lowest priority is 0)
	 * 
	 * @param </code>theTarget</code>, a instance of SWEntityInterface used to initialize a new Throw object.
	 * @param </code>m</code>, a instance of MessageRenderer used to initialize a new Throw object.
	 */
	public Throw(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);	
		priority = 1;
		// TODO Auto-generated constructor stub
	}

	/**
	 * Return if or not this <code>Throw</code> can be performed by the  <code>SWActor a</code>.
	 * <p>
	 * This method returns true if and only if <code>a</code> is carrying a instance of grenade.
	 * 
	 * @author 	Ka Po Ho and Yang Cui
	 * @param a the <code>SWActor</code> being queried
	 * @return true if the item carried by the actor is a instance of grenade, false otherwise
	 * @see		{@link starwars.SWActor#getItemCarried()}
	 */
	@Override
	public boolean canDo(SWActor a) {
		// TODO Auto-generated method stub
		return a.getItemCarried() instanceof Grenade;
	}

	/**
	 * Perform the <code>Throw</code> action by the following steps:
	 * 1.Check if the item carried by the actor is Grenade or not, if yes goto 2, false do nothing.
	 * 2.Add all the possible directions into directions arrayList.
	 * 3.check whether the actor(grenade) and other entities in the world is in the same location, if yes, then set the damage taken by them be 20
	 * 4.Then, check if there are some entities in the neighbor location of the grenade(i.e.: can be reached in one step from the location where the grenade is thrown),
	 * if yes, then set the damage taken by them be 10, otherwise do nothing.
	 * 5.Then, check if there are some entities in the neighbor location of the neighbor location of the grenade(i.e.:can be reached in two steps from the location where the grenade is thrown)
	 * if yes, then set the damage taken by them be 5, otherwise do nothing.
	 * At the end, the item carried by the actor should be set to null as the grenade has already been thrown and cause damage.
	 */
	@Override
	public void act(SWActor a) {
		// TODO Auto-generated method stub
		if (this.canDo(a)){
			EntityManager<SWEntityInterface, SWLocation> em = SWAction.getEntitymanager();
			ArrayList <CompassBearing> directions = new ArrayList <CompassBearing>();
			for (CompassBearing d:CompassBearing.values()){
				directions.add(d);
			}
			// location where the Grenade exploded
			SWLocation locationGrenade =em.whereIs(a);
			//entities at the same location take damage of 20 hit points(maxExplosion)
			List<SWEntityInterface> entities = em.contents(em.whereIs(a));
			for (SWEntityInterface e : entities){
				if(e != a){
					e.takeDamage(this.maxExplosion);
				}
			}
			
			for (CompassBearing d:directions){
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

	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author Kapo Ho and YangCui
	 * @return String comprising "Throw" and the short description of the target of this <code>Throw</code>
	 */
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Throw" + target.getShortDescription();
	}

}
