package net.sf.video4j.gwt.client.handler;

import net.sf.video4j.gwt.client.player.PlayItem;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author gumatias
 */
public interface PlayerUiHandlers extends UiHandlers {

	void onTimeUpdate(double pCurrentTime);
	void onError();
	void onPlaying();
	void onPause();
	void onEnded();
	void onDurationChanged(double pNewDuration);
}
