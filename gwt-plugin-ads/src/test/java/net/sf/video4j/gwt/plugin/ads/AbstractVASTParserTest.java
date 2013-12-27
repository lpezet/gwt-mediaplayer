/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.sf.video4j.gwt.plugin.ads.shared.vast.Ad;
import net.sf.video4j.gwt.plugin.ads.shared.vast.CompanionAd;
import net.sf.video4j.gwt.plugin.ads.shared.vast.CompanionAds;
import net.sf.video4j.gwt.plugin.ads.shared.vast.CompanionResource;
import net.sf.video4j.gwt.plugin.ads.shared.vast.CompanionResourceType;
import net.sf.video4j.gwt.plugin.ads.shared.vast.Creative;
import net.sf.video4j.gwt.plugin.ads.shared.vast.InLine;
import net.sf.video4j.gwt.plugin.ads.shared.vast.Linear;
import net.sf.video4j.gwt.plugin.ads.shared.vast.MediaFile;
import net.sf.video4j.gwt.plugin.ads.shared.vast.NonLinearAd;
import net.sf.video4j.gwt.plugin.ads.shared.vast.NonLinearAds;
import net.sf.video4j.gwt.plugin.ads.shared.vast.VAST;
import net.sf.video4j.gwt.plugin.ads.shared.vast.Wrapper;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * @author luc
 *
 */
public abstract class AbstractVASTParserTest implements IVASTParserTest {
	
	@Override
	@Test
	public void wrapperAd() throws Exception {
		String oXML = getXMLAsString("/vast2_wrapper.xml");
		VAST oVAST = parse(oXML);

        assertNotNull(oVAST);
        assertNotNull(oVAST.getAds());
        assertEquals(1, oVAST.getAds().size());
        Ad oAd = oVAST.getAds().get(0);
        assertTrue(oAd instanceof Wrapper);
        Wrapper oWrapper = (Wrapper) oAd;
        assertEquals("Acudeo Compatible", oWrapper.getAdSystem().getName());
        assertEquals("http://demo.tremormedia.com/proddev/vast/vast_inline_linear.xml", oWrapper.getVASTAdTagURI());
        assertEquals(1, oWrapper.getImpressions().size());
        assertEquals("http://myTrackingURL/wrapper/impression", oWrapper.getImpressions().get(0).getURI());
        assertEquals(3, oWrapper.getCreatives().size());
		assertEquals(11, ((Linear) oWrapper.getCreatives().get(0)).getTrackingEvents().size());
	}
	
	/* (non-Javadoc)
	 * @see net.sf.video4j.gwt.plugin.IVASTParserTest#companionAds()
	 */
	@Override
	@Test
	public void companionAds() throws Exception {
        String oXML = getXMLAsString("/vast2_companion.xml");

        VAST oVAST = parse(oXML);

        assertNotNull(oVAST);
        assertNotNull(oVAST.getAds());
        assertEquals(1, oVAST.getAds().size());
        Ad oAd = oVAST.getAds().get(0);
        assertTrue(oAd instanceof InLine);
        InLine oInLine = (InLine) oAd;
        assertNotNull(oInLine.getAdSystem());
        assertEquals("2.0", oInLine.getAdSystem().getName());
        assertEquals("5750101", oInLine.getAdTitle());
        assertNotNull(oInLine.getCreatives());
        assertEquals(1, oInLine.getCreatives().size());
        Creative oCreative = oInLine.getCreatives().get(0);
        assertNotNull(oCreative);
        assertTrue(oCreative instanceof CompanionAds);
        CompanionAds oCompanionAds = (CompanionAds) oCreative;
        assertNotNull(oCompanionAds.getCompanions());
        assertEquals(1, oCompanionAds.getCompanions().size());
        CompanionAd oCompanionAd = oCompanionAds.getCompanions().get(0);
        assertNotNull(oCompanionAd.getTrackingEvents());
        assertEquals(5, oCompanionAd.getTrackingEvents().size());
        
        CompanionResource oResource = oCompanionAd.getResource();
        assertNotNull(oResource);        
        assertEquals(CompanionResourceType.Static, oResource.getType());
        assertEquals("http://static.scanscout.com/ads/vpaidad3.swf?adData=http%3A//app.scanscout.com/ssframework/adStreamJSController.xml%3Fa%3Dgetadscheduleforcontent%26PI%3D567%26scheduleVersion%3D3%26HI%3D567|overlay|372934318%26AI%3D0", oResource.getURI());        
	}
	
	/* (non-Javadoc)
	 * @see net.sf.video4j.gwt.plugin.IVASTParserTest#nonLinearAds()
	 */
	@Override
	@Test
    public void nonLinearAds() throws Exception {
        String oXML = getXMLAsString("/vast2_nonlinear.xml");

        VAST oVAST = parse(oXML);

        assertNotNull(oVAST);
        assertNotNull(oVAST.getAds());
        assertEquals(1, oVAST.getAds().size());
        Ad oAd = oVAST.getAds().get(0);
        assertTrue(oAd instanceof InLine);
        InLine oInLine = (InLine) oAd;
        assertNotNull(oInLine.getAdSystem());
        assertEquals("2.0", oInLine.getAdSystem().getName());
        assertEquals("5750100", oInLine.getAdTitle());
        assertNotNull(oInLine.getCreatives());
        assertEquals(1, oInLine.getCreatives().size());
        Creative oCreative = oInLine.getCreatives().get(0);
        assertNotNull(oCreative);
        assertTrue(oCreative instanceof NonLinearAds);
        NonLinearAds oNonLinearAds = (NonLinearAds) oCreative;
        assertNotNull(oNonLinearAds.getList());
        assertEquals(1, oNonLinearAds.getList().size());
        NonLinearAd oNonLinearAd = oNonLinearAds.getList().get(0);
        
        assertEquals("overlay-1", oNonLinearAd.getId());
        assertEquals(380, oNonLinearAd.getWidth());
        assertEquals(60, oNonLinearAd.getHeight());
        //NOT WORKING: XMLParserImpl problem??
        //assertEquals("VPAID", oNonLinearAd.getApiFramework());
        
        CompanionResource oCompanion = oNonLinearAd.getResource();
        assertNotNull(oCompanion);        
        assertEquals(CompanionResourceType.Static, oCompanion.getType());
        assertEquals("http://static.scanscout.com/ads/vpaidad3.swf?adData=http%3A//app.scanscout.com/ssframework/adStreamJSController.xml%3Fa%3Dgetadscheduleforcontent%26PI%3D567%26scheduleVersion%3D3%26HI%3D567|overlay|372934318%26AI%3D0", oCompanion.getURI());
        //NOT WORKING: XMLParserImpl problem??
        //assertEquals("application/x-shockwave-flash", oCompanion.getCreativeType()); //TODO: fix this: somehow the attribute value from GWT is null but in the document it's not.
        
    }
	
    /* (non-Javadoc)
	 * @see net.sf.video4j.gwt.plugin.IVASTParserTest#overlays()
	 */
    @Override
	@Test
    public void overlays() throws Exception {
        String oXML = getXMLAsString("/vast2_overlays.xml");

        VAST oVAST = parse(oXML);

        assertNotNull(oVAST);
        assertNotNull(oVAST.getAds());
        assertEquals(10, oVAST.getAds().size());
        for (Ad oAd : oVAST.getAds()) {
        	assertTrue(oAd instanceof net.sf.video4j.gwt.plugin.ads.shared.vast.InLine);
        }
    }

    /* (non-Javadoc)
	 * @see net.sf.video4j.gwt.plugin.IVASTParserTest#regularLinear()
	 */
    @Override
	@Test
    public void regularLinear() throws Exception {
        String oXML = getXMLAsString("/vast2_regular_linear.xml");

        VAST oVAST = parse(oXML);
        assertNotNull(oVAST);
        assertNotNull(oVAST.getAds());
        assertEquals(1, oVAST.getAds().size());

        Ad oAd = oVAST.getAds().get(0);
        assertEquals("preroll-1", oAd.getId());
        assertTrue(oAd instanceof InLine);

        InLine oInLine = (InLine) oAd;
        assertEquals("2.0", oInLine.getAdSystem().getName());
        assertEquals("5748406", oInLine.getAdTitle());
        assertEquals(2, oInLine.getCreatives().size());
        
        Linear oLinear = (Linear) oInLine.getCreatives().get(0);
        assertNotNull(oLinear.getMediaFiles());
        assertEquals(1, oLinear.getMediaFiles().size());
        MediaFile oMF = oLinear.getMediaFiles().get(0);
        assertNotNull(oMF);
        assertEquals("http://media.scanscout.com/ads/partner1_a1d1fbbc-c4d4-419f-b6c8-e9db63fd4491.flv", oMF.getURI());
    }
    
    protected String getXMLAsString(String pResourcePath) throws IOException {
        InputStream oIS = this.getClass().getResourceAsStream(pResourcePath);
        StringWriter writer = new StringWriter();
        IOUtils.copy(oIS, writer, "UTF8");
        String oXML = writer.toString();
        return oXML;
    }
    
    protected abstract VAST parse(String pXML);
    
}
