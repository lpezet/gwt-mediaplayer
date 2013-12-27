package net.sf.video4j.gwt.plugin.ads.shared.vast;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * 
 * @author gumatias
 * 
 */
public class VAST implements IsSerializable {

    private List<Ad> mAds = new ArrayList<Ad>();

    public List<Ad> getAds() {
        return mAds;
    }

    public void setAds(List<Ad> pAds) {
        mAds = pAds;
    }

    @Override
    public String toString() {
        return "VAST [mAds=" + mAds + "]";
    }

}