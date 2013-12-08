package net.sf.video4j.gwt.client.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.player.AdPlayerWidgetInitializer;
import net.sf.video4j.gwt.client.presenter.PlayerPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;

import fr.hd3d.html5.video.client.VideoWidget;

/**
 * @author gumatias
 */
public class PlayerView extends BasePlayerView implements PlayerPresenter.PView {

	protected Logger	mLogger	= Logger.getLogger(this.getClass().getName());

	public interface Binder extends UiBinder<HTMLPanel, PlayerView> {
	}

	@UiField
	VideoWidget	mPlayerWidget;

	@Inject
	public PlayerView(Binder pBinder) {
		initWidget(pBinder.createAndBindUi(this));
		setUpVideoWidgetHandlers();
	}

	@Override
	public void startPlayer(PlayerParameters pParams) {
		mLogger.log(Level.INFO, "Starting player");
		new AdPlayerWidgetInitializer(mPlayerWidget, pParams).start();
		mLogger.log(Level.INFO, "Player started =" + mPlayerWidget);
	}

	@Override
	public void play() {
		mPlayerWidget.playPause();
	}

	@Override
	public void pause() {
		mPlayerWidget.playPause();
	}

	@Override
	public void mute() {
		mPlayerWidget.mute();
	}

	@Override
	public void unmute() {
		mPlayerWidget.unmute();
	}

	@Override
	public void fullScreen() {
		mPlayerWidget.fullScreen();
	}

	@Override
	public void volume(double pValue) {
		mPlayerWidget.setVolume(pValue);
	}

	@Override
	public void seek(double pValue) {
		mPlayerWidget.setCurrentTime(pValue);
	}

	@Override
	public void hide() {
		mPlayerWidget.setVisible(false);
	}

	@Override
	public void show() {
		mPlayerWidget.setVisible(true);
	}

	@Override
	protected VideoWidget getVideoWidget() {
		return mPlayerWidget;
	}

}