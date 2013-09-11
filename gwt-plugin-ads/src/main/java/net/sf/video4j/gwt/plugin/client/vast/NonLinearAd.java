/**
 * 
 */
package net.sf.video4j.gwt.plugin.client.vast;

import com.google.gwt.safehtml.shared.SafeUri;

/**
 * @author luc
 * 
 */
public class NonLinearAd {

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
    private int               mSuggestedDuration;

    // The apiFramework defines the method to use for communication with the
    // companion
    private String            mApiFramework;

    private CompanionResource mResource;

    // URL to open as destination page when user clicks on the the companion
    // banner ad.
    private SafeUri           mClickThrough;

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

    public int getSuggestedDuration() {
        return mSuggestedDuration;
    }

    public void setSuggestedDuration(int pSuggestedDuration) {
        mSuggestedDuration = pSuggestedDuration;
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

    public SafeUri getClickThrough() {
        return mClickThrough;
    }

    public void setClickThrough(SafeUri pClickThrough) {
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
        return "NonLinearAd [mId=" + mId + ", mWidth=" + mWidth + ", mHeight="
                + mHeight + ", mExpandedWidth=" + mExpandedWidth
                + ", mExpandedHeight=" + mExpandedHeight + ", mScalable="
                + mScalable + ", mMaintainAspectRatio=" + mMaintainAspectRatio
                + ", mSuggestedDuration=" + mSuggestedDuration
                + ", mApiFramework=" + mApiFramework + ", mResource="
                + mResource + ", mClickThrough=" + mClickThrough
                + ", mAdParameters=" + mAdParameters + "]";
    }
}
