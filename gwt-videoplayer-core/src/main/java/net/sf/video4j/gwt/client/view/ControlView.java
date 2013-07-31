package net.sf.video4j.gwt.client.view;


import net.sf.video4j.gwt.client.handler.ControlUiHandlers;
import net.sf.video4j.gwt.client.presenter.ControlPresenter;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.kiouri.sliderbar.client.event.BarValueChangedEvent;
import com.kiouri.sliderbar.client.event.BarValueChangedHandler;
import com.kiouri.sliderbar.client.solution.adv.AdvancedSliderBar;
import com.kiouri.sliderbar.client.solution.adv.BasicSliderBar;

/**
 * @author gumatias
 */
public class ControlView extends ViewWithUiHandlers<ControlUiHandlers> implements ControlPresenter.CView {
    
    public interface Binder extends UiBinder<HTMLPanel, ControlView> {
    }
    
    @UiField
    Button mPlayButton;
    @UiField
    Button mPauseButton;
    @UiField
    Button mMuteButton;
    @UiField
    Button mUnmuteButton;
    @UiField
    Button mFullScreenButton;
    @UiField
    AdvancedSliderBar mVolumeSlider;
    @UiField
    BasicSliderBar mTimelineSlider;
    
    @Inject
    public ControlView(Binder pBinder) {
        initWidget(pBinder.createAndBindUi(this));
        setUpTimelineSlider();
        setUpVolumeSlider();
    }
    
    private void setUpTimelineSlider() {
        mTimelineSlider.setMaxValue(100);
        mTimelineSlider.setValue(0);
    }
    
    private void setUpVolumeSlider() {
        mVolumeSlider.drawMarks("black", 2);
        mVolumeSlider.setMaxValue(10);
        mVolumeSlider.setValue(10); 
        mVolumeSlider.addBarValueChangedHandler(new BarValueChangedHandler() {
            @Override
            public void onBarValueChanged(BarValueChangedEvent event) {
                getUiHandlers().onVolumeChange((double)event.getValue() / 10);
            }
        });
    }
    
    @UiHandler("mPlayButton")
    public void onPlayClickEvent(ClickEvent pEvent) {
        mPlayButton.setVisible(false);
        mPauseButton.setVisible(true);
        getUiHandlers().onPlay();
    }
    
    @UiHandler("mPauseButton")
    public void onPauseClickEvent(ClickEvent pEvent) {
        mPlayButton.setVisible(true);
        mPauseButton.setVisible(false);
        getUiHandlers().onPause();
    }
    
    @UiHandler("mMuteButton")
    public void onMuteClickEvent(ClickEvent pEvent) {
        mMuteButton.setVisible(false);
        mUnmuteButton.setVisible(true);
        getUiHandlers().onMute();
    }
    
    @UiHandler("mUnmuteButton")
    public void onUnmuteClickEvent(ClickEvent pEvent) {
        mMuteButton.setVisible(true);
        mUnmuteButton.setVisible(false);
        getUiHandlers().onUnmute();
    }
    
    @UiHandler("mFullScreenButton")
    public void onFullScreenClickEvent(ClickEvent pEvent) {
        getUiHandlers().onFullScreen();
    }

}