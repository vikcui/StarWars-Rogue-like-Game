package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;
import starwars.entities.actors.Droid;
import starwars.entities.actors.Player;
/**
 * 
 * @author CUIYANG and kapo Ho
 * Command to allow a player adopt a </code>Droid</code> when they meet.
 * Modified date:13/05/2018
 */
public class Adopt extends SWAffordance {
	/**
	 * This is the constructor for the </code>Adopt</code> affordance .
	 * @param theTarget instance of </code>SWEntityInterface</code> used to initialize the </code>Adopt</code> affordance
	 * @param m instance of </code>MessageRenderer</code> used to initialize the </code>Adopt</code> affordance
	 */
	public Adopt(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		// TODO Auto-generated constructor stub
		
	}
/**
 * @param: instance of </code>SWActor</code>
 * @return: true if a SWActor could adopt a </code>Droid</code> false otherwise.
 */
	@Override
	public boolean canDo(SWActor a) {
		// TODO Auto-generated method stub
		return a instanceof Player;
	}
/**
 * Perform the </code>Adopt</code> action by setting the owner of the </code>Droid</code> to that </code>SWActor</code> 
 * and reomove the </code>Adopt</code> affordance.
 * @param:instance of </code>SWActor</code>
 * 
 * 
 */
	@Override
	public void act(SWActor a) {
		// TODO Auto-generated method stub
		if (target instanceof Droid){
			if (this.canDo(a)){
				Droid thetarget = (Droid)(target);
				if (thetarget.getOwner()==null){
					thetarget.setOwner(a);
					assert thetarget.getOwner()==a:"Adoption failed!";
					target.removeAffordance(this);
				}
			}
		}
		
	}
	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author kapa Ho and YangCui
	 * @return String comprising "Adopt" and the short description of the target of this <code>Adopt</code>
	 */

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return"Adopt "+target.getShortDescription() ;
	}

}
