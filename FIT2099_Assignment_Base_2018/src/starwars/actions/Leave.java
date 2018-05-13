package starwars.actions;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWAction;
import starwars.SWActor;
import starwars.SWAffordance;
import starwars.SWEntityInterface;

/**
 * <code>SWAction</code> that lets a <code>SWActor</code> leave an object.
 * 
 * @author ram
 */
/*
 * Changelog
 * 2018/05/06	- candDo method changed. An actor can only leave if it's currently holding something already.
 * 				- act method modified. Leave affordance appeared from the item picked up, since an item picked up
 * 				  cannot be taken. This is just a safe guard.
 * 				- canDo method changed to return true only if the actor is  carrying an item (asel)
 */
public class Leave extends SWAffordance {

	/**
	 * Constructor for the <code>Take</code> Class. Will initialize the message renderer, the target and 
	 * set the priority of this <code>Action</code> to 1 (lowest priority is 0).
	 * 
	 * @param theTarget a <code>SWEntity</code> that is being left
	 * @param m the message renderer to display messages
	 */
	public Leave(SWEntityInterface theTarget, MessageRenderer m) {
		super(theTarget, m);
		priority = 1;
	}


	/**
	 * Returns if or not this <code>Leave</code> can be performed by the <code>SWActor a</code>.
	 * <p>
	 * This method returns true if and only if <code>a</code> is not carrying any item already.
	 *  
	 * @author 	ram
	 * @author 	Yang Cui (06/05/2018)
	 * @param 	a the <code>SWActor</code> being queried
	 * @return 	true if the <code>SWActor</code> is can take this item, false otherwise
	 * @see		{@link starwars.SWActor#getItemCarried()}
	 */
	@Override
	public boolean canDo(SWActor a) {
		return a.getItemCarried()!=null;
	}

	/**
	 * Perform the <code>Leave</code> action by setting the item carried by the <code>SWActor</code> to the None (
	 * the <code>SWActor a</code>'s item carried would be the target of this <code>Leave</code> and the target would be
	 * appear on the location of where the <code>SWActor</code> is).
	 * Then remove the </code>Leave</code> affordance and add </code>Take</code> affordance to
	 * the target because it should be able to be picked up again.
	 * <p>
	 * This method should only be called if the <code>SWActor a</code> is alive.
	 * 
	 * @author 	ram
	 * @author 	Yang Cui (06/05/2018)
	 * @param 	a the <code>SWActor</code> that is leaving the target
	 * @see 	{@link #theTarget}
	 * @see		{@link starwars.SWActor#isDead()}
	 */
	@Override
	public void act(SWActor a) {
		if (target instanceof SWEntityInterface) {
			SWEntityInterface theItem = (SWEntityInterface) target;
			SWAction.getEntitymanager().setLocation(theItem,SWAction.getEntitymanager().whereIs(a));//make the target reappear from the entity manager since it's now not held by the SWActor
			a.setItemCarried(null);//set the itemCarried to be null again 
			//remove the Leave affordance
			target.removeAffordance(this);
			//add the take affordance
			target.addAffordance(new Take(theItem,this.messageRenderer));
			assert a.getItemCarried()==null:"Leave failed!";

		}
	}

	/**
	 * A String describing what this action will do, suitable for display in a user interface
	 * 
	 * @author Kapo Ho and YangCui
	 * @return String comprising "Leave" and the short description of the target of this <code>Leave</code>
	 */
	@Override
	public String getDescription() {
		return "Leave " + target.getShortDescription();
	}

}


