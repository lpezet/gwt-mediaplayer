/**
 * 
 */
package net.sf.video4j.gwt.client.demo;

import java.util.logging.Logger;

import net.sf.video4j.gwt.client.demo.VideoPlayerCustomControlsDemo;


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
import com.kiouri.sliderbar.client.event.BarValueChangedEvent;
import com.kiouri.sliderbar.client.event.BarValueChangedHandler;
import com.kiouri.sliderbar.client.solution.adv.AdvancedSliderBar;
import com.kiouri.sliderbar.client.solution.adv.BasicSliderBar;


import fr.hd3d.html5.video.client.VideoSource;
import fr.hd3d.html5.video.client.VideoSource.VideoType;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;

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
	Panel controlContainer;
	@UiField
	Button playPauseButton;
	@UiField
	Button muteButton;
	@UiField
	Button fullScreenButton;
	@UiField
	AdvancedSliderBar volumeSlider;
	@UiField
	BasicSliderBar timelineSlider;

	private VideoWidget mVideoPlayer;
	private boolean mIsPlaying;
	
	@Override
	public void onModuleLoad() {
		HTMLPanel outer = binder.createAndBindUi(this);
		RootPanel.get("player").add(outer);
		mVideoPlayer = new VideoWidget();
		mVideoPlayer.setControls(false);
		mVideoPlayer.addSource(new VideoSource(VIDEO, VideoType.MP4));
		mVideoPlayer.setPixelSize(500, 400);
		videoContainer.add(mVideoPlayer);
		setUpTimelineSlider();
		setUpVolumeSlider();
		mVideoPlayer.addTimeUpdateHandler(new VideoTimeUpdateHandler() {
			@Override
			public void onTimeUpdated(VideoTimeUpdateEvent event) {
				timelineSlider.setValue((int)((event.getCurrentTime() / mVideoPlayer.getDuration()) * 100));
			}
		});
	}

	private void setUpTimelineSlider() {
		timelineSlider.setMaxValue(100);
		timelineSlider.setValue(0);
	}

	private void setUpVolumeSlider() {
		volumeSlider.drawMarks("black", 2);
		volumeSlider.setMaxValue(10);
		volumeSlider.setValue(10); 
		volumeSlider.addBarValueChangedHandler(new BarValueChangedHandler() {
			@Override
			public void onBarValueChanged(BarValueChangedEvent event) {
				mVideoPlayer.setVolume((double)event.getValue() / 10);
			}
		});
	}
	
	@UiHandler("playPauseButton")
	public void onPlayPauseClickEvent(ClickEvent pEvent) {
		mVideoPlayer.playPause();
		mIsPlaying = !mIsPlaying;
		if (mIsPlaying) playPauseButton.setText("Pause");
		else playPauseButton.setText("Play"); 
	}
	
	@UiHandler("muteButton")
	public void onMuteClickEvent(ClickEvent pEvent) {
		if (mVideoPlayer.isMuted()) {
			muteButton.setText("Mute");
			mVideoPlayer.unmute();
		} else {
			muteButton.setText("Unmute");
			mVideoPlayer.mute();
		}
	}
	
	@UiHandler("fullScreenButton")
	public void onFullScreenClickEvent(ClickEvent pEvent) {
		mVideoPlayer.fullScreen();
	}
	
}
