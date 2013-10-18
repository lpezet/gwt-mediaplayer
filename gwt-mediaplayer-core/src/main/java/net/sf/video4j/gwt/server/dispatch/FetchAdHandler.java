package net.sf.video4j.gwt.server.dispatch;

import java.util.logging.Logger;

import net.sf.video4j.gwt.plugin.server.vast.dao.AdService;
import net.sf.video4j.gwt.plugin.server.vast.dao.IAdService;
import net.sf.video4j.gwt.plugin.shared.vast.VAST;
import net.sf.video4j.gwt.shared.FetchAdAction;
import net.sf.video4j.gwt.shared.model.FetchAdResult;

import com.gwtplatform.dispatch.server.ExecutionContext;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * @author gumatias
 */
public class FetchAdHandler extends AbstractAction<FetchAdAction, FetchAdResult> {
    
    protected Logger mLogger = Logger.getLogger(this.getClass().getName());
    
    private final IAdService mAdService;

    public FetchAdHandler() {
        super(FetchAdAction.class);
        mAdService = new AdService(); // How to use guice's DI on server side? @Inject didn't work
    }

    @Override
    public FetchAdResult execute(FetchAdAction pAction, ExecutionContext pContext) throws ActionException {
        mLogger.info("Executing fetchAds");
        VAST oVAST = mAdService.fetchAds(); // where will the list of Ads URL's come from? can come from frontend OR from servlet itself...
        mLogger.info("Got VAST=" + oVAST);
        return new FetchAdResult(oVAST);
    }

}