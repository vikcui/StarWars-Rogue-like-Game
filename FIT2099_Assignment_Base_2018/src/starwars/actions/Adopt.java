package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.actors.Droid;
import starwars.entities.actors.Player;

public class Adopt extends SWAffordance {

	public Adopt(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public boolean canDo(SWActor a) {
		// TODO Auto-generated method stub
		return a instanceof Player;
	}

	@Override
	public void act(SWActor a) {


		// TODO Auto-generated method stub
		if (target instanceof Droid){
			if (this.canDo(a)){
				Droid thetarget = (Droid)(target);
				if (thetarget.getOwner()==null){
					thetarget.setOwner(a);
					target.removeAffordance(this);
				}
				}
			}
		}
		

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return"Adopt "+target.getShortDescription() ;
	}

}
