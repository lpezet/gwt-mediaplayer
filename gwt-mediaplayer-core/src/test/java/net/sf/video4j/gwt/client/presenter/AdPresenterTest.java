package net.sf.video4j.gwt.client.presenter;

import net.sf.video4j.gwt.plugin.client.vast.service.IAdService;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gwt.event.shared.EventBus;
import com.gwtplatform.dispatch.shared.DispatchAsync;

/**
 * @author gumatias
 */
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class AdPresenterTest {

    private AdPresenter mPresenter;
    
    @Mock
    private AdPresenter.AView mView;
    @Mock
    private EventBus mEventBus;
    @Mock
    private IAdService mAdService;
    @Mock
    private DispatchAsync mDispatcher;
    
    @Before
    public void setUp() throws Exception {
        mPresenter = new AdPresenter(mEventBus, mView, mAdService, mDispatcher);
    }
    
}