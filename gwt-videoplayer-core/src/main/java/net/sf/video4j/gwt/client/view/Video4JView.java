package net.sf.video4j.gwt.client.view;

import net.sf.video4j.gwt.client.presenter.Video4JPresenter;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

/**
 * 
 * @author gumatias
 */
public class Video4JView extends ViewImpl implements Video4JPresenter.V4JView {
    
    interface Binder extends UiBinder<Widget, Video4JView> {
    }

    @UiField SimplePanel mVideoPlayerPanel;
    @UiField SimplePanel mControlPanel;

    @Inject
    Video4JView(Binder pBinder) {
        initWidget(pBinder.createAndBindUi(this));
    }

    @Override
    public void setInSlot(Object pSlot, IsWidget pContent) {
        if (pSlot == Video4JPresenter.SLOT_VIDEO_PLAYER) 
            mVideoPlayerPanel.setWidget(pContent);
        else if (pSlot == Video4JPresenter.SLOT_CONTROL) 
            mControlPanel.setWidget(pContent);        	
        else 
            super.setInSlot(pSlot, pContent);
    }

}