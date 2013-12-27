/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads.shared.vast;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author luc
 * 
 */
public class NonLinearAd implements IsSerializable {

    // Optional identifier
    private String            mId;

    // Pixel dimensions of companion
    private int               mWidth;

    // Pixel dimensions of companion
    private int               mHeight;

    // Pixel dimensions of expanding companion ad when in expanded state
    private int               mExpandedWidth;

    // Pixel dimensions of expanding companion ad when in expanded state
    private int               mExpandedHeight;

    // Whether it is acceptable to scale the image.
    private boolean           mScalable;

    // Whether the ad must have its aspect ratio maintained when scales
    private boolean           mMaintainAspectRatio;

    // Suggested duration to display non-linear ad, typically for animation to
    // complete. Expressed in seconds
    private int               mMinSuggestedDuration;

    // The apiFramework defines the method to use for communication with the
    // companion
    private String            mApiFramework;

    private CompanionResource mResource;

    // URL to open as destination page when user clicks on the the companion
    // banner ad.
    private String           mClickThrough;

    // Data to be passed into the companion ads. The apiFramework defines the
    // method to use for communication (e.g. "FlashVar")
    private String            mAdParameters;

    public String getId() {
        return mId;
    }

    public void setId(String pId) {
        mId = pId;
    }

    public int getWidth() {
        return mWidth;
    }

    public void setWidth(int pWidth) {
        mWidth = pWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public void setHeight(int pHeight) {
        mHeight = pHeight;
    }

    public int getExpandedWidth() {
        return mExpandedWidth;
    }

    public void setExpandedWidth(int pExpandedWidth) {
        mExpandedWidth = pExpandedWidth;
    }

    public int getExpandedHeight() {
        return mExpandedHeight;
    }

    public void setExpandedHeight(int pExpandedHeight) {
        mExpandedHeight = pExpandedHeight;
    }

    public boolean isScalable() {
        return mScalable;
    }

    public void setScalable(boolean pScalable) {
        mScalable = pScalable;
    }

    public boolean isMaintainAspectRatio() {
        return mMaintainAspectRatio;
    }

    public void setMaintainAspectRatio(boolean pMaintainAspectRatio) {
        mMaintainAspectRatio = pMaintainAspectRatio;
    }

    public int getMinSuggestedDuration() {
        return mMinSuggestedDuration;
    }

    public void setMinSuggestedDuration(int pSuggestedDuration) {
        mMinSuggestedDuration = pSuggestedDuration;
    }

    public String getApiFramework() {
        return mApiFramework;
    }

    public void setApiFramework(String pApiFramework) {
        mApiFramework = pApiFramework;
    }

    public CompanionResource getResource() {
        return mResource;
    }

    public void setResource(CompanionResource pResource) {
        mResource = pResource;
    }

    public String getClickThrough() {
        return mClickThrough;
    }

    public void setClickThrough(String pClickThrough) {
        mClickThrough = pClickThrough;
    }

    public String getAdParameters() {
        return mAdParameters;
    }

    public void setAdParameters(String pAdParameters) {
        mAdParameters = pAdParameters;
    }

    @Override
    public String toString() {
        return "NonLinearAd [id=" + mId + ", width=" + mWidth + ", height="
                + mHeight + ", expandedWidth=" + mExpandedWidth
                + ", expandedHeight=" + mExpandedHeight + ", scalable="
                + mScalable + ", maintainAspectRatio=" + mMaintainAspectRatio
                + ", suggestedDuration=" + mMinSuggestedDuration
                + ", apiFramework=" + mApiFramework + ", resource="
                + mResource + ", clickThrough=" + mClickThrough
                + ", adParameters=" + mAdParameters + "]";
    }
}
