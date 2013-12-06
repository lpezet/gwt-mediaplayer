package net.sf.video4j.gwt.client.presenter;

import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.player.PlayItem;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.View;

/**
 * @author Gustavo Matias
 */
public class DummyBasePlayerPresenter extends BasePlayerPresenterWidget<DummyBasePlayerPresenter.DView> {

	public interface DView extends View, HasUiHandlers<PlayerUiHandlers> {
	}

	public DummyBasePlayerPresenter(EventBus pEventBus, DView pView) {
		super(pEventBus, pView);
	}

	@Override
	public String getPluginId() {
		return this.getClass().getName();
	}

	@Override
	protected PlayItem getPlayingItem() {
		return null;
	}

}