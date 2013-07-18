/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.util.logging.Logger;

import net.sf.video4j.gwt.client.player.PlayItem;
import net.sf.video4j.gwt.client.player.Playlist;
import net.sf.video4j.gwt.client.player.Track;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoDurationChangeEvent;
import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.handlers.VideoDurationChangeHandler;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;

/**
 * @author luc
 *
 */
public class VideoPlayerPlaylistDemo implements EntryPoint {

	private Logger mLogger = Logger.getLogger(VideoPlayerPlaylistDemo.class.getName());
	
	private static final String PRE_ROLL = "http://static.bouncingminds.com/ads/30secs/country_life_butter.mp4";
	private static final String VIDEO = "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4";
	
	@Override
	public void onModuleLoad() {
		final Playlist oPlaylist = new Playlist();
		
		final VideoWidget oVideoPlayer = new VideoWidget();
		
		Track oPreRoll = new Track();
		oPreRoll.setURI(PRE_ROLL);
		Track oFeature = new Track();
		oFeature.setURI(VIDEO);
		
		oPlaylist.add(oPreRoll);
		oPlaylist.add(oFeature);
		
		oVideoPlayer.addDurationChangeHandler(new VideoDurationChangeHandler() {
			@Override
			public void onDurationChange(VideoDurationChangeEvent pEvent) {
				mLogger.info("Duration changed: " + oVideoPlayer.getDuration());
			}
		});
		
		oVideoPlayer.addEndedHandler(new VideoEndedHandler() {
			@Override
			public void onVideoEnded(VideoEndedEvent pEvent) {
				if (oPlaylist.hasNext()) {
					PlayItem oItem = oPlaylist.next();
					oVideoPlayer.setSrc(oItem.getTrack().getURI());
					oVideoPlayer.playPause();
				} else {
					mLogger.info("End of playlist.");
				}
			}
		});
		
		PlayItem oFirst = oPlaylist.next();
		oVideoPlayer.setSrc(oFirst.getTrack().getURI());
		oVideoPlayer.setControls(true);
		mLogger.info("Starting playlist.");
		oVideoPlayer.playPause();
		
		RootPanel.get("player").add(oVideoPlayer);
	}

}
