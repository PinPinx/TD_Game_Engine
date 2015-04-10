package engine.goals;

import java.util.Observable;
import engine.fieldsetting.Settable;
import View.ChangeableSpeed;

/**
 * This goal object listens to a changeable speed object.
 * 
 * @author Sierra
 * @author Cosette
 *
 */
public abstract class ChangeableSpeedGoal extends Goal {

    private ChangeableSpeed myChangeableSpeed;
    
    public ChangeableSpeedGoal(){
    }
    
    public ChangeableSpeedGoal(ChangeableSpeed changer){
        myChangeableSpeed = changer;
    }
    
    public ChangeableSpeed getChangeableSpeed(){
        return myChangeableSpeed;
    }
    
    @Settable
    public void setChangeableSpeed(ChangeableSpeed speedy){
        myChangeableSpeed = speedy;
    }
    
    @Override
    public void update (Observable o, Object arg) {
        if(myChangeableSpeed.equals(o)){
            updateSatisfied();
        }
    }
    
    public abstract void updateSatisfied();
}
