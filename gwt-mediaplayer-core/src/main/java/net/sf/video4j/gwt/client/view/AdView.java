package net.sf.video4j.gwt.client.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.player.PlayerWidgetInitializer;
import net.sf.video4j.gwt.client.presenter.AdPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;

import fr.hd3d.html5.video.client.VideoWidget;

/**
 * @author gumatias
 */
public class AdView extends BasePlayerView implements AdPresenter.AView {

	protected Logger	mLogger	= Logger.getLogger(this.getClass().getName());

	public interface Binder extends UiBinder<HTMLPanel, AdView> {
	}

	@UiField
	VideoWidget	mPlayerWidget;

	@Inject
	public AdView(Binder pBinder) {
		initWidget(pBinder.createAndBindUi(this));
		setUpVideoWidgetHandlers();
	}

	@Override
	public void startPlayer(PlayerParameters pParams) {
		mLogger.log(Level.INFO, "Starting ad player");
		new PlayerWidgetInitializer(mPlayerWidget, pParams).start();
		mLogger.log(Level.INFO, "Ad Player started =" + mPlayerWidget);
	}

	@Override
	public void hidePlayer() {
		mPlayerWidget.setVisible(false);
	}

	@Override
	protected VideoWidget getVideoWidget() {
		return mPlayerWidget;
	}

}