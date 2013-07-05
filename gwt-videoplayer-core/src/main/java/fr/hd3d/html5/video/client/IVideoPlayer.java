package fr.hd3d.html5.video.client;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent;

import fr.hd3d.html5.video.client.VideoWidget.TypeSupport;
import fr.hd3d.html5.video.client.handlers.HasVideoHandlers;

public interface IVideoPlayer extends HasVideoHandlers {

	/**
	 * @param poster
	 *            represent the address of an image file that the user agent can show while no video data is available
	 */
	public void setPoster(String poster);

	/**
	 * @return <li><b>The image file address</b> that the user agent can show while no video data is available</li> <br/>
	 *         <li><b>null</b> if no image has been set</li>
	 * 
	 */
	public String getPoster();

	/**
	 * @param autoPlay
	 *            <b>true</b> if you want the user agent automatically begin playback of the media resource as soon as
	 *            it can do so without stopping. <br/>
	 *            <b>false</b> otherwise
	 * @throws IllegalArgumentException
	 *             if autoPlay is <b>null</b>
	 */
	public void setAutoPlay(Boolean autoPlay);

	/**
	 * @param controls
	 *            <b>false</b> if you want to have custom scripted controller, <br/>
	 *            <b>true</b> if you would like the user agent to provide its own set of controls.
	 * @throws IllegalArgumentException
	 *             if controls is <b>null</b>
	 */
	public void setControls(Boolean controls);

	/**
	 * @return <b>true</b> if the user agent automatically begin playback. <br/>
	 *         <b>false</b> otherwise
	 */
	public boolean isAutoPlay();

	/**
	 * @return <b>false</b> if you want to have custom scripted controller <br/>
	 *         <b>true</b> if you would like the user agent to provide its own set of controls.
	 */
	public boolean isControls();

	/**
	 * @param sources
	 *            list of {@link VideoSource} that represent all the available sources for the video element
	 */
	public void setSources(List<VideoSource> sources);

	public void addSource(String src);

	public void addSource(VideoSource videoSource);

	/**
	 * Switch the playback status between paused and played
	 */
	public void playPause();

	/**
	 * @return <b>true</b> if playback is paused<br/>
	 *         <b>false</b> otherwise
	 */
	public boolean isPaused();

	/**
	 * @return <b>true</b> if playback is played <br/>
	 *         <b>false</b> otherwise
	 */
	public boolean isPlayed();

	/**
	 * @return <b>true</b> if the user agent is currently seeking. <br/>
	 *         <b>false</b> otherwise
	 */
	public boolean isSeeking();

	/**
	 * @param time
	 *            the time where user agent want to seek
	 * @return <b>true</b> if it is possible for the user agent to seek. <br/>
	 *         <b>false</b> otherwise
	 */
	public boolean isSeekable(double time);

	/**
	 * @param currentTime
	 *            the current playback position, expressed in seconds
	 */
	public void setCurrentTime(double currentTime);

	/**
	 * @return the current playback position, expressed in seconds
	 */
	public double getCurrentTime();

	/**
	 * @return <b>the initial playback position</b>, that is, time to which the media resource was automatically seeked
	 *         when it was loaded. <br/>
	 *         <b>0</b> if the initial playback position is still unknown.
	 */
	public double getInitialTime();

	/**
	 * @return <li><b>the length of the media resource, in seconds, </b>assuming that the start of the media resource is
	 *         at time zero.</li> <br/>
	 *         <li><b>-1</b> for unbounded streams.</li>
	 * @throws NumberFormatException
	 *             if duration is NaN
	 */
	public double getDuration();

	/**
	 * @return <b>true</b> if playback has reached the end of the media resource. <br/>
	 *         <b>false</b> otherwise
	 */
	public boolean isEnded();

	/**
	 * The default rate has no direct effect on playback, but if the user switches to a fast-forward mode, when they
	 * return to the normal playback mode, it is expected that the rate of playback will be returned to the default rate
	 * of playback.
	 * 
	 * @param defaultPlaybackRate
	 *            the desired speed at which the media resource is to play. <br/>
	 *            if value < 1.0 the playback is slower <br/>
	 *            if value > 1.0 the playback is faster
	 */
	public void setDefaultPlaybackRate(double defaultPlaybackRate);

	/**
	 * The default rate has no direct effect on playback, but if the user switches to a fast-forward mode, when they
	 * return to the normal playback mode, it is expected that the rate of playback will be returned to the default rate
	 * of playback.
	 * 
	 * @return the default rate of playback, for when the user is not fast-forwarding or reversing through the media
	 *         resource.
	 */
	public double getDefaultPlaybackRate();

	/**
	 * @param playbackRate
	 *            the current rate playback, where 1.0 is normal speed.
	 */
	public void setPlaybackRate(double playbackRate);

	/**
	 * @return the current rate playback, where 1.0 is normal speed.
	 */
	public double getPlaybackRate();

	/**
	 * @return the current buffer position end time, in second
	 */
	public double getBufferedEndTime();

	/**
	 * @return <b>the address</b> of the current media resource. <br/>
	 *         <b>""</b> when there is no media resource.
	 */
	public String getCurrentSrc();

	/**
	 * Use this function to test if the media could be play by the video tag
	 * 
	 * @param videoType
	 *            the videoType to check
	 * @return <b>TypeSupport.NO</b> if videoType is a type that the user agent knows it cannot render <br/>
	 *         <b>TypeSupport.PROBABLY</b> if if the user agent is confident that the type represents a media resource that it
	 *         can render if used in with this audio or video element <br/>
	 *         <b>TypeSupport.MAYBE</b> otherwise
	 */
	public TypeSupport canPlayType(String videoType);

	/**
	 * This function is call in JNI code to dispatch {@link GwtEvent}
	 * 
	 * @param event
	 */
	public void fireEvent(Object event);

	public void load();

	public void setSrc(String src);
	
	public void mute();
	
	public void unmute();
	
	public boolean isMuted();
	
	public void fullScreen();
	
	public double getVolume();
	
	public void setVolume(double value);

}