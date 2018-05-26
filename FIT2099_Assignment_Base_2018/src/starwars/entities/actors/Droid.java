package starwars.entities.actors;


import java.util.Random;

import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.matter.Affordance;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Adopt;
import starwars.actions.Move;
import starwars.entities.actors.behaviors.RandomDroidMove;


/*
 * A new class Droid in the package starwars.entites.actors
 * The Droid is one of the actor belongs to SWWorld, so it should be in the actor package and extend form SWActor.
 * There are three attributes inside the class: Owner::SWActor, em::EntityManager<SWEntityInterface, SWLocation>, newDroidMove::DroidMove
 * There are 5 methods inside the class: its constructor, act(), setOwner(SWActor a), takeDamage(int damage), moveInBadLand(SWLocation loc)
 * @author KaPoHo and CuiYang
 *
 */

public class Droid extends SWActor {
	/**
	 * Owner is used to indicate each droid’s owner,
	 * and the entitymanager can allow us easily to manipulate on association of both entity and their relevant location.
	 * and the newDroidmove is a new object created from Droidmove class, it is used to execute some movement manners for Droid object.
	 */
	
	private SWActor owner = null;
	protected EntityManager<SWEntityInterface, SWLocation> em;
	private RandomDroidMove newRandomDroidMove;
	
	/**
	 * A constructor with four arguments to instantiate a new Droid object, as it inherits from SWActor, it can use its parent's constructor(super()) to initialize their common attributes.
	 * @param  </code>team </code>, a new instance of team class used to initialize a new Droid object.
	 * @param hitpoints, a new hitpoint with integer type, used to initialize a new Droid object.
	 * @param  </code>m </code>, a new instance of MessageRenderer class used to initialize a new Droid object.
	 * @param  </code>world </code>, a new instance of SWWorld class used to initialize a new Droid object.
	 */
	public Droid(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(team, hitpoints, m, world);
		this.em = world.getEntitymanager();
		this.newRandomDroidMove=new RandomDroidMove(this.world);
	}

	
	/**
	 * a act function allows Droid actor to act, which is overriding from Actor class.
	 * As Droid inherits from SWActor and SWActor inherits from Actor, plus the act is a abstract method inside Actor,so we need to override this method whenever create a new class that inherit from it.
	 * To realize the first behavior, the follow behavior:
	 * Firstly, we should compare droid’s position and its owner position by using the whereis() function through the entitymanager, if they are in the same location, then droid will stand still. 
	 * Also, the droid will follow its owner when droid’s owner is in a neighbouring position, which can use a flag (boolean isNear, initially set as False) to indicate whether the droid is neighbor to its owner. 
	 * If droid is near to its owner, then we set the flag to be true. Otherwise, we remain the False flag. 
	 * To check whether the droid is neighbor to its owner, we can use the getNeighbor () function through entity manager, and we pass all the possible neighbor directions from the enum class CompassBearing in the Grid class. 
	 * If there is one neighbor direction match the position of owner, then we let the droid move to that position by creating a new move instance and add it as a new event to the queue of droid’s events through the schdule() method inside the Scheduler class.
	 * 
	 * To realize the second behavior, the droid that cannot find its owner in its current or neighbouring locations:
	 * we face the difficulity to get a random direction, as we can only get a random number straightly.
	 * we create a RandomDirection class to solve this problem. In this class, we have a convertNumCompass method that take a integer as an argument. 
	 * The method return type is CompassBearing, as we want to return a direction eventually. Inside the method, a switch statement is used to convert each integer into a specific direction (e.g,: case 1 represent North, case 2 represent Northeast, etc), and we have 8 possible directions. 
	 * Next, we can get the random number by Random class from Java.util. Since there are 8 possible directions, so we need 8 random number (1-8) by the nextInt function (nextInt(8) + 1). Next, we need to convert each random number to be a direction out of 8 possible directions. 
	 * This can be done by passing each random number to the convertNumCompassing method inside our RandomDirection class.
	 * Initially, the droid pick a random direction by above method, and keep moving in that position until it finds its owner. The seesExit from the entitymanger should be useful at this stage, as it can ensure that we always move to position that has a neighbouring location. 
	 * If the droid can no longer move in its current direction, then we randomly pick a direction again and keep moving in this direction. We repeat this process until the droid find its owner.
	 * 
	 * For the droid without owner, it should not move, and we should use return to stop its action.
	 * The droid is also allowed to be adopted by the player if they are in the same location. (a adopt addfordance is added correspondly)
	 * The adopt affordance will not be added if the adopt affordance has been added for droid before, as the droid can not be adopted if it has a owner already.
	 */
	@Override
	public void act() {
		if (this.getHitpoints()<=0){
			return;
			
		}
		this.moveInBadlands(em.whereIs(this));
		if(this.owner !=null){
			if(em.whereIs(this) == em.whereIs(this.owner)){
				return ;
			}
			boolean isNear=false;
			for(Grid.CompassBearing d: Grid.CompassBearing.values()){
				if(em.whereIs(this).getNeighbour(d) == em.whereIs(this.owner)){
					isNear=true;
					Move dMove1 = new Move(d,this.messageRenderer,this.world);
					this.scheduler.schedule(dMove1, this, dMove1.getDuration());
					return;
				}
			}
			if (!isNear){
				Grid.CompassBearing nextRD=newRandomDroidMove.getNext(this);
				Move dMove2 = new Move(nextRD,this.messageRenderer,this.world);
				this.scheduler.schedule(dMove2, this, dMove2.getDuration());
				return;
			}
					
		}
		else{
			boolean alreadyHasAdoptAffordance=false;
			Affordance Array[]=this.getAffordances();
			for (int i=0;i<Array.length;i++){
				if (Array[i] instanceof Adopt){
					alreadyHasAdoptAffordance=true;
					
				}
				
			}
			if (alreadyHasAdoptAffordance==false){
				this.addAffordance(new Adopt(this,this.messageRenderer));
			}
			return;
		}
		
		
	}
	
	/**
	 * A setter for Droid's owner.
	 * @param  </code>a </code>, a new instance of SWActor class, used to set the owner for Droid object.
	 */
	
	public void setOwner(SWActor a){
		this.owner = a;
	}
	
	/**
	 * A getter for Droid's owner.
	 * @return 
	 * @returns  </code>SWActor </code>, the owner of a droid.
	 */
	
	public SWActor getOwner(){
		return this.owner;
	}
	
	

	/**
	 * A method that override from its parent class, return type is void.
	 * @param damage, a new integer damage to be used to set the damage to droid object's hitpoint.
	 */
	@Override
	public void takeDamage(int damage){
		super.takeDamage(damage);
	}
	
	/**
	 * since a droid object will lose health when they move in badlands. 
	 * We need to know whether a droid object has moved in the badland or not, sow we need to pass a location of a droid object as argument and check the symbol at that position is “b” or not. 
	 * If the character that method return is “b” then we know a droid object has moved in the badland and it will lose hitpoints correspondingly by using the takedamage() method above. 
	 * (we assume it will lose two hitpoints at each time it move in badland.). 
	 * @param loc, a new instance of SWLocation, used to get the symbol of droid object's current position.
	 */
	public void moveInBadlands(SWLocation loc){
		if(loc.getSymbol()==('b'))
			this.takeDamage(2);
			this.say("loc: " +loc.getSymbol() + " "+ this.getSymbol() +" hitpoints: "+this.getHitpoints());
	
		
	}
}
