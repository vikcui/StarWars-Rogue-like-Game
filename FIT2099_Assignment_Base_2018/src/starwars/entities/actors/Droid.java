package starwars.entities.actors;



import edu.monash.fit2099.gridworld.Grid;
import edu.monash.fit2099.simulator.matter.EntityManager;
import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWEntityInterface;
import starwars.SWLocation;
import starwars.SWWorld;
import starwars.Team;
import starwars.actions.Move;
import starwars.entities.actors.behaviors.DroidMove;

public class Droid extends SWActor {
	private SWActor owner = null;
	protected EntityManager<SWEntityInterface, SWLocation> em;
	private DroidMove newDroidMove;
	
	public Droid(Team team, int hitpoints, MessageRenderer m, SWWorld world) {
		super(team, hitpoints, m, world);
		this.em = world.getEntitymanager();
		this.newDroidMove=new DroidMove(this.world);
	}

	@Override
	public void act() {
		if(this.owner !=null){
			if(em.whereIs(this) == em.whereIs(this.owner)){
				return ;
			}
			boolean isNear=false;
			for(Grid.CompassBearing d: Grid.CompassBearing.values()){
				if(em.whereIs(this).getNeighbour(d) == em.whereIs(owner)){
					isNear=true;
					Move dMove1 = new Move(d,this.messageRenderer,this.world);
					this.scheduler.schedule(dMove1, this, dMove1.getDuration());
					return;
				}
			}
			if (!isNear){
//				ArrayList<CompassBearing> moves=new ArrayList<CompassBearing>();
//				Random rand = new Random();
//				int randNum = rand.nextInt(8)+1;
//				RandomDirection rd = new RandomDirection();
//				Grid.CompassBearing nextRD = rd.convertNumCompass(randNum);
//				this.say("compass "+nextRD);
				Grid.CompassBearing nextRD=newDroidMove.getNext(this);
				Move dMove2 = new Move(nextRD,this.messageRenderer,this.world);
				this.scheduler.schedule(dMove2, this, dMove2.getDuration());
				return;
			}
					


			

		}
		else{
			return;
		}
		
	}
	
	public void setOwner(SWActor a){
		this.owner = a;
	}

	@Override
	public void takeDamage(int damage){
		super.takeDamage(damage);
	}
	
	public void moveInBadlands(SWLocation loc){
		if (loc.getSymbol() =='b'){
			this.takeDamage(2);
		}
		
	}
}
