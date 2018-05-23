package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.SWEntity;
import starwars.actions.Take;
import starwars.actions.Throw;
/** An entity that has the affordance </code>Take</code>and </code>Throw</code> 
 * which will result damage to other entities around it in differing severity.
 * 
 * 
 * @author CUIYANG and kapo ho
 *
 */
public class Grenade extends SWEntity {
/**The constructor for SWEntity </code>Grenade</code> class
 * which create and initialize an instance of  </code>Grenade</code> class
 * by calling the constructor of super class SWEntity and set decriptions.
 * Enable the instance to be picked by adding </code>Take</code> affordance and 
 * to be thrown to cause damage by adding the </code>Throw</code>affordance
 * @param m
 */
	public Grenade(MessageRenderer m) {
		super(m);
		this.shortDescription="This is a Grenade";
		this.shortDescription="This is a Grenade that potentially has a lot of power!";
		this.addAffordance(new Take(this,this.messageRenderer));
		this.addAffordance(new Throw(this,this.messageRenderer));
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	/**accessor for the ShortDescription
	 * @return:the shortDescription of the </code>Grenade</code>
	 */
	@Override 
	public String getShortDescription() {
		return this.shortDescription;
	}
	
	/**accessor for the LongDescription
	 * @return:the longDescription of the </code>Grenade</code>
	 */
	@Override
	public String getLongDescription () {
		return this.longDescription;
	}

}
