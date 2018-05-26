package starwars.entities.actors;

import java.util.ArrayList;
import java.util.List;

import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.space.Direction;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWGrid;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.entities.actors.behaviors.Patrol;
import java.util.Random;
/**
 * This class represents the SWActor </code>Sandcrawler</code>
 * @author hopo and yangcui
 *
 */
public class Sandcrawler extends SWActor {
	
	//decision array used alternate between two scenarios 
	ArrayList<Integer> decisionarray=new ArrayList<Integer>();
	//decision int attribute representing first out of two turns 
	static final int decision1=1;
	//decision int attribute representing second out of two turns 
	static final int decision2=2;
	
	//x coordinate of the door
	static final int door_x = 4;
	//y coordinate of the door
	static final int door_y = 3;
	
	//decision Type Patrol attribute used to store the movements that </code>Sandcrawler</code> follows
	private Patrol path;
	// SWGrid type attribute representing the interior grid inside the </code>Sandcrawler</code>
	private SWGrid interiorGrid;
	
	/**
	 * Construtor which creates and initialize an </code>Sandcrawler</code> object.
	 * 
	 * @param team, the team which </code>Sandcrawler</code> is in. Good or Bad
	 * @param hitpoints, the default hitpoint of </code>Sandcrawler</code>.
	 * @param m ,the messagerender of </code>Sandcrawler</code>
	 * @param world, the world of which that </code>Sandcrawler</code> are in.
	 * @param moves, the movements of which </code>Sandcrawler</code> follows
	 */
	public Sandcrawler(Team team, int hitpoints, MessageRenderer m, SWWorld world,Direction [] moves) {
		super(team, hitpoints, m, world);
		this.decisionarray.add(decision1);
		this.decisionarray.add(decision2);
		path = new Patrol(moves);
		this.setShortDescription("sandcrawler");
		this.setLongDescription("vehicles that Jawas travel in");
		SWLocation.SWLocationMaker factory = SWLocation.getMaker();
		interiorGrid = new SWGrid(factory);
		
	}
	
	/**This method allow the </code>Sandcrawler</code> to act as per specification 
	 * first, the method will check the location of which that </code>Sandcrawler</code> is in, if a Droid happens to be 
	 * in the same location, the </code>Droid</code> will be taken into the interior grid by calling the </code>setInterior</code>
	 * method. 
	 * Then the method will check if a SWActor who could use force is in that location, if there is, the </code>SWActor</code> will 
	 * be taken into the interoir grid by calling the </code>setInterior</code> method. 
	 * After that,the method will collect all the SWEntityInterface entities that is the same location as the door symble'd' if there 
	 * is SWActor with enough force in it , the actor will be taken out of the interior grid and put back into the original grid.
	 * After all that, the method will check if it is still alive, if  </code>Sandcrawler</code> is, then move otherwise stay.
	 * by calling the method alternateDecisionArray.
	 */

	@Override
	public void act() {
		EntityManager<SWEntityInterface, SWLocation> em = SWAction.getEntitymanager();
		List<SWEntityInterface> entities = em.contents(em.whereIs(this));
		for (SWEntityInterface e : entities){
			if(e instanceof Droid){
				this.setInterior(e, em);
			}
			else if (e instanceof SWActor){
				if (((SWActor)e).canUseForce()){
					this.setInterior(e, em);
				}
			}
		}
		
		List<SWEntityInterface> entitiesAtDoor = em.contents((this.world.getInteriorGrid()).getLocationByCoordinates(door_x,door_y));
		if (entitiesAtDoor!=null){
			for (SWEntityInterface e : entitiesAtDoor){
				if (((SWActor)e).canUseForce()){
					em.remove(e);
					SWLocation loc=em.whereIs(this);
					em.setLocation(e, loc);
				}
			}
		}
		if (this.getHitpoints()>0){	
		// TODO Auto-generated method stub
			if (this.decisionarray.get(0)==decision1){
				this.alternateDecisionArray(decision1);
	
			}else{
				this.alternateDecisionArray(decision2);
				//schedule for move
				Direction newdirection = path.getNext();
				say(getShortDescription() + " moves " + newdirection);
				Move myMove = new Move(newdirection, messageRenderer, world);
				scheduler.schedule(myMove, this, 1);
				}
		}
		
	}
	/**
	 * This method will take the input SWEntityInterface object out of the original grid and
	 * set the input SWEntityInterface object into the interior grid 
	 * @param newSWEntityInterface,the SWEntityInterface object to be set into the interior grid.
	 * @param newEntityManager, the EntityManager that manages all the entities.
	 */
	private void setInterior(SWEntityInterface newSWEntityInterface,EntityManager<SWEntityInterface, SWLocation> newEntityManager){
		Random random = new Random();
		SWLocation loc=(this.world.getInteriorGrid()).getLocationByCoordinates(random.nextInt(8),random.nextInt(8));
		newEntityManager.remove(newSWEntityInterface);
		newEntityManager.setLocation(newSWEntityInterface, loc);
		}
	/**
	 * This method keeps the decision array at size two and alternate between the two decision integers.
	 * @param newInteger the decision integer used at a particular turn.
	 */
	private void alternateDecisionArray(int newInteger){
		this.decisionarray.remove(0);
		this.decisionarray.add(newInteger);
		
	}

	}
