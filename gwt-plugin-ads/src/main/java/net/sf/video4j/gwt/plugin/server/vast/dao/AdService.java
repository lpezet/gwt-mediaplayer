package net.sf.video4j.gwt.plugin.server.vast.dao;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.video4j.gwt.plugin.shared.vast.Ad;
import net.sf.video4j.gwt.plugin.shared.vast.VAST;
import net.sf.video4j.gwt.plugin.shared.vast.Wrapper;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gumatias
 */
public class AdService implements IAdService {
	
	private final Logger	mLogger	= LoggerFactory.getLogger(this.getClass());
	private static final int MAX_DEPTH_WRAPPER = 3;
	private int mMaxDepthWrapper = MAX_DEPTH_WRAPPER;
	private VASTParser mVASTParser = new VASTParser();
	private IUrlFetcher mContentFetcher = new UrlFetcher();

	@Override
	public VAST fetchAds(String pURL) {
		return fetchAds(pURL, 0);
	}
	
	protected VAST fetchAds(String pURL, int pDepth) {
		VAST oVAST = new VAST();
		try {
			String oXML = getContent(pURL);			
			oVAST = mVASTParser.parse(oXML);
			processWrapper(oVAST, pDepth);
		}  catch (Throwable t) {
			mLogger.error("Error getting VAST.", t);
		}
		return oVAST;
	}

	private void processWrapper(VAST pVAST, int pDepth) {
		if (pDepth >= mMaxDepthWrapper) return;
		for (Ad oAd : pVAST.getAds()) {
			if (oAd instanceof Wrapper) {
				Wrapper oWrapper = (Wrapper) oAd;
				if (oWrapper.getVASTAdTagURI() != null) {
					VAST oWrapped = fetchAds(oWrapper.getVASTAdTagURI(), pDepth+1);
					oWrapper.setVAST(oWrapped);
				}
			}
		}
	}

	private String getContent(String pUrl) {
		return mContentFetcher.getContent(pUrl);
	}

	public int getMaxDepthWrapper() {
		return mMaxDepthWrapper;
	}

	public void setMaxDepthWrapper(int pMaxDepthWrapper) {
		mMaxDepthWrapper = pMaxDepthWrapper;
	}

	public IUrlFetcher getContentFetcher() {
		return mContentFetcher;
	}

	public void setContentFetcher(IUrlFetcher pContentFetcher) {
		mContentFetcher = pContentFetcher;
	}

}
