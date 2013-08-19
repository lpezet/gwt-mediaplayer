/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import fr.hd3d.html5.video.client.VideoWidget;

/**
 * @author luc
 *
 */
public class MediaControllerFactory {

	public MediaControllerFactory() {
		// TODO Auto-generated constructor stub
	}
	
	public MediaController newController(VideoWidget pVideoWidget, PlayItem pPlayItem) {
		MediaType oType = pPlayItem.getMedia().getType();
		switch (oType) {
			//case Audio:
			//
			case Video:
				return new VideoController(pVideoWidget, pPlayItem);
			case Image:
				return new ImageController(pVideoWidget, pPlayItem);
			default:
				throw new RuntimeException("Media type : " + oType);
		}
	}

}
