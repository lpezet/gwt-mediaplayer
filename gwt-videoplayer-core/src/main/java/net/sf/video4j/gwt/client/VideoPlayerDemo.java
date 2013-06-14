/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.events.VideoPlayingEvent;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoPlayingHandler;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;
import fr.hd3d.html5.video.client.VideoWidget;

/**
 * @author luc
 *
 */
public class VideoPlayerDemo implements EntryPoint {
	
	private Logger mLogger = Logger.getLogger(VideoPlayerDemo.class.getName());

	@Override
	public void onModuleLoad() {
		VideoWidget videoPlayer = new VideoWidget(false, true, "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4");
        List<VideoSource> sources = new ArrayList<VideoSource>();
        sources.add(new VideoSource("http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4", VideoType.MP4));
        //sources.add(new VideoSource("fr_hd3d_html5_video_demo/videos/bbb_trailer_360p.webm", VideoType.WEBM));
        videoPlayer.setSources(sources);
        videoPlayer.setPixelSize(500, 400);
        videoPlayer.addPlayingHandler(new VideoPlayingHandler() {
			
			@Override
			public void onPlaying(VideoPlayingEvent event) {
				mLogger.log(Level.SEVERE, "Playing!!!");
			}
		});
        videoPlayer.setPoster("http://i.tripfilmsws.com/t/500x400/tripfilms_snapshots/720p/D93A130B1BC3E02EB7AB99812EFB8C00_0002.jpg");
        videoPlayer.addTimeUpdateHandler(new VideoTimeUpdateHandler() {
			
			@Override
			public void onTimeUpdated(VideoTimeUpdateEvent event) {
				mLogger.log(Level.INFO, "Time update!" + event.getCurrentTime());
			}
		});
        RootPanel.get().add(videoPlayer);
	}
}
