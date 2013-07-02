/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;

/**
 * 
 * @author gumatias
 *
 */
public class VideoPlayerCustomControlsDemo implements EntryPoint {

	private Logger mLogger = Logger.getLogger(VideoPlayerCustomControlsDemo.class
			.getName());

	interface Binder extends UiBinder<HTMLPanel, VideoPlayerCustomControlsDemo> {
	}

	private static final Binder binder = GWT.create(Binder.class);

	private static final String VIDEO = "http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4";

	@UiField
	Panel container;
	@UiField
	Panel videoContainer;
	@UiField
	Panel overlayContainer;
	@UiField
	Button playPauseButton;
	@UiField
	Button muteButton;
	@UiField
	Button fullScreenButton;

	VideoWidget videoPlayer;
	
	@Override
	public void onModuleLoad() {
		HTMLPanel outer = binder.createAndBindUi(this);
		RootPanel.get("player").add(outer);
		videoPlayer = new VideoWidget();
		videoPlayer.setControls(false);
		videoPlayer.setAutoPlay(true);
		videoPlayer.addSource(new VideoSource(VIDEO, VideoType.MP4));
		videoPlayer.setPixelSize(500, 400);
		videoContainer.add(videoPlayer);
	}
	
	@UiHandler("playPauseButton")
	public void onPlayPauseClickEvent(ClickEvent pEvent) {
		videoPlayer.playPause();
	}
	
	@UiHandler("muteButton")
	public void onMuteClickEvent(ClickEvent pEvent) {
		if (videoPlayer.isMuted()) videoPlayer.unmute(); 
		else videoPlayer.mute();
	}
	
	@UiHandler("fullScreenButton")
	public void onFullScreenClickEvent(ClickEvent pEvent) {
		videoPlayer.fullScreen();
	}

}
