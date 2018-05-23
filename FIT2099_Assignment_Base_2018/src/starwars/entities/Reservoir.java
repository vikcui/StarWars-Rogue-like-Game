package starwars.entities;

import edu.monash.fit2099.simulator.userInterface.MessageRenderer;
import starwars.Capability;
import starwars.SWAffordance;
import starwars.SWEntity;
import starwars.actions.Dip;

/**
 * Class to represent a water reservoir.  <code>Reservoirs</code> are currently pretty passive.
 * They can be dipped into to fill fillable entities (such as <code>Canteens</code>.  They
 * are assumed to have infinite capacity.
 * 
 * @author 	ram
 * @see 	{@link starwars.entities.Canteen}
 * @see {@link starwars.entites.Fillable}
 * @see {@link starwars.actions.Fill} 
 */
public class Reservoir extends SWEntity {

	/**
	 * Constructor for the <code>Reservoir</code> class. This constructor will,
	 * <ul>
	 * 	<li>Initialize the message renderer for the <code>Reservoir</code></li>
	 * 	<li>Set the short description of this <code>Reservoir</code> to "a water reservoir</li>
	 * 	<li>Set the long description of this <code>Reservoir</code> to "a water reservoir..."</li>
	 * 	<li>Add a <code>Dip</code> affordance to this <code>Reservoir</code> so it can be taken</li> 
	 *	<li>Set the symbol of this <code>Reservoir</code> to "T"</li>
	 * </ul>
	 * 
	 * @param 	m <code>MessageRenderer</code> to display messages.
	 * @see 	{@link starwars.actions.Dip} 
	 */
	public Reservoir(MessageRenderer m) {
		super(m);
		SWAffordance dip = new Dip(this, m);
		this.addAffordance(dip);	
		//set the default hit points to 40
		this.hitpoints=40;
		
		this.setLongDescription("a water reservoir.");
		this.setShortDescription("a water reservoir, full of cool, clear, refreshing water");
		this.setSymbol("W");
	}

	@Override 
	public String getShortDescription() {
		return shortDescription;
	}
	
	public String getLongDescription() {
		return longDescription;
	}
	
	/**
	 * This method determines if the Descriptions has already been changed
	 * @return true if they have, false otherwise
	 */
	private boolean descriptionChanged(){
		return (this.getShortDescription().equals("a water reservoir.")&&this.getLongDescription().equals("a water reservoir, full of cool, clear, refreshing water"));
		
	}

	@Override
	/**This method call the super class's takeDamage method and change the decriptions and symbols if needed. 
	 * 
	 */
	public void takeDamage(int damage) {
		super.takeDamage(damage);
		
		if (this.hitpoints<=20&&this.hitpoints>0) {
			if (!this.descriptionChanged()){
				//set the short&long Descriptions
				this.setShortDescription("a damaged water reservoir");
				this.setLongDescription("a damaged water reservoir, leaking slowly");
				//set the symbol to V
				this.setSymbol("V");
			}
		}
		else if (this.hitpoints<=0) {
			if (!this.descriptionChanged()){
				//set the short&long Descriptions
				this.setShortDescription("the wreck-age of a water reservoir");
				this.setLongDescription("the wreckage of a water reservoir, surrounded by slightly damp soil");
				//set the symbol to X
				this.setSymbol("X");
				//remove the dip affordance
				this.removeAffordance(new Dip(this, this.messageRenderer));	
			}
		}
	} 
}
