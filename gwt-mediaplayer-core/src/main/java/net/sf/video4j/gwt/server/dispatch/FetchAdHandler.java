package net.sf.video4j.gwt.server.dispatch;

import net.sf.video4j.gwt.plugin.client.vast.VAST;
import net.sf.video4j.gwt.server.dao.IAdService;
import net.sf.video4j.gwt.shared.FetchAdAction;
import net.sf.video4j.gwt.shared.model.FetchAdResult;

import com.google.inject.Inject;
import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author gumatias
 */
public class FetchAdHandler extends AbstractAction<FetchAdAction, FetchAdResult> {
    
    private final IAdService mAdService;

    @Inject
    public FetchAdHandler(IAdService pAdService) {
        super(FetchAdAction.class);
        mAdService = pAdService;
    }

    @Override
    public FetchAdResult execute(FetchAdAction pAction, ExecutionContext pContext) throws ActionException {
        VAST oVAST = mAdService.fetchAds(); // where will the list of Ads URL's come from? 
        return new FetchAdResult(oVAST);
    }

}