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

public class Sandcrawler extends SWActor {
	
	ArrayList<Integer> decisonarray=new ArrayList<Integer>();
	static final int decision1=1;
	static final int decision2=2;
	private Patrol path;
	private SWGrid interiorGrid;

	public Sandcrawler(Team team, int hitpoints, MessageRenderer m, SWWorld world,Direction [] moves) {
		super(team, hitpoints, m, world);
		this.decisonarray.add(decision1);
		this.decisonarray.add(decision2);
		path = new Patrol(moves);
		this.setShortDescription("sandcrawler");
		this.setLongDescription("vehicles that Jawas travel in");
		SWLocation.SWLocationMaker factory = SWLocation.getMaker();
		interiorGrid = new SWGrid(factory);
		

		// TODO Auto-generated constructor stub
		
	}

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
		
		List<SWEntityInterface> entitiesAtDoor = em.contents((this.world.getInteriorGrid()).getLocationByCoordinates(4,3));
		if (entitiesAtDoor!=null){
			for (SWEntityInterface e : entitiesAtDoor){
				if (((SWActor)e).canUseForce()){
					em.remove(e);
					SWLocation loc=em.whereIs(this);
					em.setLocation(e, loc);
				}
			}
		}
			
		// TODO Auto-generated method stub
		if (this.decisonarray.get(0)==decision1){
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
	private void setInterior(SWEntityInterface newSWEntityInterface,EntityManager<SWEntityInterface, SWLocation> newEntityManager){
		Random random = new Random();
		SWLocation loc=(this.world.getInteriorGrid()).getLocationByCoordinates(random.nextInt(8),random.nextInt(8));
		newEntityManager.remove(newSWEntityInterface);
		newEntityManager.setLocation(newSWEntityInterface, loc);
		}
	
	private void alternateDecisionArray(int newInteger){
		this.decisonarray.remove(0);
		this.decisonarray.add(newInteger);
		
	}

	}
