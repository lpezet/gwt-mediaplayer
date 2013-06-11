/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;

/**
 * @author luc
 *
 */
public class VideoPlayerDemo implements EntryPoint {

	@Override
	public void onModuleLoad() {
		VideoWidget videoPlayer = new VideoWidget(false, true, "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4");
        List<VideoSource> sources = new ArrayList<VideoSource>();
        sources.add(new VideoSource("http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4", VideoType.MP4));
        //sources.add(new VideoSource("fr_hd3d_html5_video_demo/videos/bbb_trailer_360p.webm", VideoType.WEBM));
        videoPlayer.setSources(sources);
        videoPlayer.setPixelSize(500, 400);
        RootPanel.get().add(videoPlayer);
	}
}
