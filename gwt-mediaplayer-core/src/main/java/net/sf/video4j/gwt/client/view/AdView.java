package net.sf.video4j.gwt.client.view;


import net.sf.video4j.gwt.client.model.PlayerParameters;
import net.sf.video4j.gwt.client.presenter.AdPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

import fr.hd3d.html5.video.client.VideoWidget;

/**
 * @author gumatias
 */
public class AdView extends ViewImpl implements AdPresenter.AView {
    
    public interface Binder extends UiBinder<HTMLPanel, AdView> {
    }

    @UiField
    VideoWidget mPlayerWidget;
	
    @Inject
    public AdView(Binder pBinder) {
        initWidget(pBinder.createAndBindUi(this));
    }
    
    @Override
    public void startPlayer(PlayerParameters pParams) {
        mPlayerWidget.setControls(pParams.hasControls());
        mPlayerWidget.setAutoPlay(pParams.isAutoPlay());
        mPlayerWidget.setSrc(pParams.getFileSource());
        mPlayerWidget.setPixelSize(pParams.getWidthInPixels(), pParams.getHeightInPixels());
    }

	@Override
	public String canPlayType(String pMediaType) {
		return mPlayerWidget.canPlayType(pMediaType).toString();
	}
    
}