/**
 * 
 */
package fr.hd3d.html5.video.client.handlers;

import com.google.gwt.event.shared.EventHandler;

import fr.hd3d.html5.video.client.events.VideoCuePointEvent;

/**
 * @author luc
 *
 */
public interface VideoCuePointHandler extends EventHandler {
	
    void onCuePoint(VideoCuePointEvent pEvent);
}
