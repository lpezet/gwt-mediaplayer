package net.sf.video4j.gwt.client.view;


import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.presenter.PlayerPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

/**
 * @author gumatias
 */
public class PlayerView extends ViewWithUiHandlers<PlayerUiHandlers> implements PlayerPresenter.PView {
    
    public interface Binder extends UiBinder<HTMLPanel, PlayerView> {
    }

    @UiField SimplePanel mVideoPlayer;
	
    @Inject
    public PlayerView(Binder pBinder) {
        initWidget(pBinder.createAndBindUi(this));
    }

}