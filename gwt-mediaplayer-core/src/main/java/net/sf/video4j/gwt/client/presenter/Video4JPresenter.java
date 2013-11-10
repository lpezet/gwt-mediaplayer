package net.sf.video4j.gwt.client.presenter;

import java.util.logging.Logger;

import net.sf.video4j.gwt.client.controller.ApplicationController;
import net.sf.video4j.gwt.client.controller.BandwidthController;
import net.sf.video4j.gwt.client.controller.PlaylistController;
import net.sf.video4j.gwt.client.place.NameTokens;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;

/**
 * @author gumatias
 */
public class Video4JPresenter extends Presenter<Video4JPresenter.V4JView, Video4JPresenter.V4JProxy> {

	private Logger	mLogger	= Logger.getLogger(this.getClass().getName());

	@ProxyStandard
	@NameToken(NameTokens.video4JModule)
	public interface V4JProxy extends ProxyPlace<Video4JPresenter> {
	}

	public interface V4JView extends View {
	}

	public static final Object	SLOT_VIDEO_PLAYER	= new Object();
	public static final Object	SLOT_CONTROL		= new Object();
	public static final Object	SLOT_AD				= new Object();

	PlayerPresenter				mPlayerPresenter;
	ControlPresenter			mControlPresenter;
	AdPresenter					mAdPresenter;
	ApplicationController		mApplicationController;
	PlaylistController			mPlaylistController;
	BandwidthController			mBandwidthController;

	@Inject
	Video4JPresenter(
			EventBus pEventBus,
			V4JView pView,
			V4JProxy pProxy,
			PlayerPresenter pPlayerPresenter,
			ControlPresenter pControlPresenter,
			AdPresenter pAdPresenter,
			ApplicationController pApplicationController,
			PlaylistController pPlaylistController,
			BandwidthController pBandwidthController) {
		super(pEventBus, pView, pProxy, RevealType.Root);
		mPlayerPresenter = pPlayerPresenter;
		mControlPresenter = pControlPresenter;
		mAdPresenter = pAdPresenter;
		mApplicationController = pApplicationController;
		mPlaylistController = pPlaylistController;
		mBandwidthController = pBandwidthController;
	}

	@Override
	protected void onBind() {
		super.onBind();
		setInSlot(SLOT_VIDEO_PLAYER, mPlayerPresenter);
		setInSlot(SLOT_CONTROL, mControlPresenter);
		setInSlot(SLOT_AD, mAdPresenter);
		mApplicationController.begin();
	}

}