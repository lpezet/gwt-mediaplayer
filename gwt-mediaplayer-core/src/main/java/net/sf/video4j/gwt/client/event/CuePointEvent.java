/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import net.sf.video4j.gwt.client.player.CuePoint;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author luc
 *
 */
public class CuePointEvent extends GwtEvent<CuePointEvent.CuePointHandler> {
    private static final Type<CuePointHandler> TYPE = new Type<CuePointHandler>();
    
    private CuePoint mCuePoint;
    
    public CuePointEvent(CuePoint pCuePoint) {
		mCuePoint = pCuePoint;
	}
    
    public static void fire(HasHandlers pSource, CuePoint pCuePoint) {
    	CuePointEvent oEventInstance = new CuePointEvent(pCuePoint);
        pSource.fireEvent(oEventInstance);
    }
    
    public static interface CuePointHandler extends EventHandler {
    	
        void onCuePoint(CuePointEvent pEvent);
    }

    public static Type<CuePointHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(CuePointHandler handler) {
        handler.onCuePoint(this);
    }

    @Override
    public Type<CuePointHandler> getAssociatedType() {
        return TYPE;
    }
    
    public CuePoint getCuePoint() {
		return mCuePoint;
	}
}
