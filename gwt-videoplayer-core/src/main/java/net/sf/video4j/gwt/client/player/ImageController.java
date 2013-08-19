/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import fr.hd3d.html5.video.client.VideoWidget;

/**
 * @author luc
 *
 */
public class ImageController extends AbstractTimedController {

	public ImageController(VideoWidget pVideoWidget, PlayItem pPlayItem) {
		super(pVideoWidget, pPlayItem);
	}

	@Override
	protected ITimeTracker newTimeTracker(VideoWidget pVideoWidget, PlayItem pPlayItem) {
		return new TimerBasedTimeTracker(pPlayItem);
	}

}
