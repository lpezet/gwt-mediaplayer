package net.sf.video4j.gwt.shared.model;

import net.sf.video4j.gwt.plugin.shared.vast.VAST;

import com.gwtplatform.dispatch.shared.Result;

/**
 * @author gumatias
 */
public class FetchAdResult implements Result {

    private static final long serialVersionUID = 1L;
    
    private VAST mVAST;
    
    public FetchAdResult() {}
    
    public FetchAdResult(VAST pVAST) {
        mVAST = pVAST;
    }

    public VAST getVAST() {
        return mVAST;
    }

    @Override
    public String toString() {
        return "FetchAdsResult [mVAST=" + mVAST + "]";
    }

}