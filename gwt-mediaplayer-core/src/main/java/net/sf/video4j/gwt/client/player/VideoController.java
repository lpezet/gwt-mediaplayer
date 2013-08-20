/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import fr.hd3d.html5.video.client.VideoWidget;

/**
 * @author luc
 *
 */
public class VideoController extends AbstractTimedController {

	public VideoController(VideoWidget pVideoWidget, PlayItem pPlayItem) {
		super(pVideoWidget, pPlayItem);
	}

	@Override
	protected ITimeTracker newTimeTracker(VideoWidget pVideoWidget, PlayItem pPlayItem) {
		return new SimpleTimeTracker(pPlayItem);
	}

}
