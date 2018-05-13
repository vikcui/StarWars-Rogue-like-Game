Design change:
For feature 2 force ability:
The original design of it is to create a enum force class to set the forcestate for each star world actors,
however, we think this approach may increase the depencies of the codes, as everytime we need to set the force or retrive a particular forcestate, we need to import the force enum class, which makes all the swactors depend on the force enum class. Also, this approach would need a lot of repeated code to fullfill its functionality, this will break the dry rule.
Therefore, we decide to represent the force as a integer attribute, and we use two constant final integers attributes to represent two forcestates (fullforce, strongforce) instead of using the actual figure (In the train class). Also, we use every forcestate below 8 is weak force (all swactors(luke) with weak force is waited to be trained )
Player has been set to 2 originally, Ben and TuskenRaider have been set to 10.


For feature 5 droids:
A adopt affordance has been added to package starwars.actions.
To realize this new affordance, a constructor and three methods(canDo(SWActor a),act(SWActor a),getDescription()) overiding from its parent class are needed.
This affordance allows a player to adpot a droid when they meets. The details would be shown as the following steps:
1.we need to check whether the target is droid or not, if yes, then it is be able to be adopted, otherwise, the droid should stand still.
2.The actor to adopt the droid should be an instance of player only.
3.we also need to ensure that the droid currently doesn't have a owner, and set the owner for the droid and remove the adopt affordance correspondingly.
 