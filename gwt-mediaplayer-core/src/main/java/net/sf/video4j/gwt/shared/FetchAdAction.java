package net.sf.video4j.gwt.shared;

import net.sf.video4j.gwt.shared.model.FetchAdResult;

import com.gwtplatform.dispatch.shared.UnsecuredActionImpl;

/**
 * @author gumatias
 */
public class FetchAdAction extends UnsecuredActionImpl<FetchAdResult> {

  private String mURL;

  public FetchAdAction() {}

  public String getURL() {
    return mURL;
  }

  public void setURL(String pURL) {
    mURL = pURL;
  }

  @Override
  public String toString() {
    return "FetchAdAction [mURL=" + mURL + "]";
  }

}