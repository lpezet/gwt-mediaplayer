package net.sf.video4j.gwt.plugin.ads;

import net.sf.video4j.gwt.plugin.ads.shared.vast.TrackingEvent;

/**
 * @author Gustavo Matias
 *
 */
public interface IAdTracker {

	void track(TrackingEvent pEvent);

}
