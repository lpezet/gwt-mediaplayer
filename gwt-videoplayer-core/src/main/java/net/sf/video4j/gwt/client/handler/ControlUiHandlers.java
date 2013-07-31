package net.sf.video4j.gwt.client.handler;

import com.gwtplatform.mvp.client.UiHandlers;

/**
 * @author gumatias
 */
public interface ControlUiHandlers extends UiHandlers {
    void onPlay();

    void onPause();

    void onMute();

    void onUnmute();

    void onFullScreen();

    void onSeeked();

    void onVolumeChange(double pValue);
}
