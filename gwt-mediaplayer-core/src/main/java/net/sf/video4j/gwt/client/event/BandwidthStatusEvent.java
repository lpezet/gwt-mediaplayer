/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public class BandwidthStatusEvent  extends GwtEvent<BandwidthStatusEvent.BandwidthStatusHandler> {
    
	private int mBitrateInKbps; // bps / 1024
	
    public BandwidthStatusEvent(int pBitrateInKbps) {
    	mBitrateInKbps = pBitrateInKbps;
    }
    
    public int getBitrateInKbps() {
		return mBitrateInKbps;
	}

    public static void fire(HasHandlers pSource, int pBitrateInKbps) {
    	BandwidthStatusEvent oEventInstance = new BandwidthStatusEvent(pBitrateInKbps);
        pSource.fireEvent(oEventInstance);
    }

    //public static void fire(HasHandlers pSource, PlayerPlayingEvent pEventInstance) {
    //    pSource.fireEvent(pEventInstance);
    //}

    public interface BandwidthStatusHandlers extends HasHandlers {
        HandlerRegistration addBandwidthStatusHandler(BandwidthStatusHandler pHandler);
    }

    public interface BandwidthStatusHandler extends EventHandler {
        public void onBandwidthStatusEvent(BandwidthStatusEvent pEvent);
    }

    private static final Type<BandwidthStatusHandler> TYPE = new Type<BandwidthStatusHandler>();

    public static Type<BandwidthStatusHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<BandwidthStatusHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(BandwidthStatusHandler pHandler) {
        pHandler.onBandwidthStatusEvent(this);
    }

}
