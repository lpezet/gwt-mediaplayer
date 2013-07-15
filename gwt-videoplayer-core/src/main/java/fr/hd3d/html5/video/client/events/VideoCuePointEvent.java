/**
 * 
 */
package fr.hd3d.html5.video.client.events;

import net.sf.video4j.gwt.client.player.CuePoint;

import com.google.gwt.event.shared.GwtEvent;

import fr.hd3d.html5.video.client.handlers.VideoCuePointHandler;

/**
 * @author luc
 *
 */
public class VideoCuePointEvent extends GwtEvent<VideoCuePointHandler> {
    private static final Type<VideoCuePointHandler> TYPE = new Type<VideoCuePointHandler>();
    
    private CuePoint mCuePoint;
    
    public VideoCuePointEvent(CuePoint pCuePoint) {
		mCuePoint = pCuePoint;
	}

    public static Type<VideoCuePointHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(VideoCuePointHandler handler) {
        handler.onCuePoint(this);
    }

    @Override
    public Type<VideoCuePointHandler> getAssociatedType() {
        return TYPE;
    }
    
    public CuePoint getCuePoint() {
		return mCuePoint;
	}
}
