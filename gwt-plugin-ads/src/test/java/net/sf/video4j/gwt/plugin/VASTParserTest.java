/**
 * 
 */
package net.sf.video4j.gwt.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.sf.video4j.gwt.plugin.shared.vast.Ad;
import net.sf.video4j.gwt.plugin.shared.vast.CompanionResource;
import net.sf.video4j.gwt.plugin.shared.vast.CompanionResourceType;
import net.sf.video4j.gwt.plugin.shared.vast.Creative;
import net.sf.video4j.gwt.plugin.shared.vast.InLine;
import net.sf.video4j.gwt.plugin.shared.vast.NonLinearAd;
import net.sf.video4j.gwt.plugin.shared.vast.NonLinearAds;
import net.sf.video4j.gwt.plugin.shared.vast.VAST;
import net.sf.video4j.gwt.plugin.shared.vast.VASTParser;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author luc
 */
@GwtModule("net.sf.video4j.gwt.plugin.VAST")
public class VASTParserTest extends GwtTest {
	
	@Test
	public void xmlAttributeParsingProblem() throws Exception {
		Document oDoc = XMLParser.parse("<toto><tata myAttribute=\"titi\"><tutu/></tata></toto>");
		Node oToto = oDoc.getChildNodes().item(0);
		Node oTata = oToto.getFirstChild();
		/*
		NamedNodeMap oNNMap = oTata.getAttributes();
		for (int i = 0; i < oNNMap.getLength(); i++) {
			Node n = oNNMap.item(i);
			System.out.println("###### Attribute #" + i + ": " + n.getNodeName() + ", class=" + n.getClass());
			com.google.gwt.xml.client.impl.AttrImpl oImpl = (com.google.gwt.xml.client.impl.AttrImpl) n;
			
		}
		*/
		assertEquals("titi", oTata.getAttributes().getNamedItem("myAttribute").getNodeValue());
	}

	@Test
    public void nonLinearAds() throws Exception {
        VASTParser oParser = new VASTParser();
        String oXML = getXMLAsString("/vast2_nonlinear.xml");

        VAST oVAST = oParser.parse(oXML);

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
        CompanionResource oCompanion = oNonLinearAd.getResource();
        assertNotNull(oCompanion);        
        assertEquals(CompanionResourceType.Static, oCompanion.getType());
        //SafeUri oUri = UriUtils.fromString("http://static.scanscout.com/ads/vpaidad3.swf?adData=http%3A//app.scanscout.com/ssframework/adStreamJSController.xml%3Fa%3Dgetadscheduleforcontent%26PI%3D567%26scheduleVersion%3D3%26HI%3D567|overlay|372934318%26AI%3D0");
        //System.out.println("########### Safe Uri = " + oUri.asString());
        assertEquals("http://static.scanscout.com/ads/vpaidad3.swf?adData=http%3A//app.scanscout.com/ssframework/adStreamJSController.xml%3Fa%3Dgetadscheduleforcontent%26PI%3D567%26scheduleVersion%3D3%26HI%3D567|overlay|372934318%26AI%3D0", oCompanion.getURI());
        //assertEquals("application/x-shockwave-flash", oCompanion.getCreativeType()); //TODO: fix this: somehow the attribute value from GWT is null but in the document it's not.
    }
	
    @Test
    public void overlays() throws Exception {
        VASTParser oParser = new VASTParser();
        String oXML = getXMLAsString("/vast2_overlays.xml");

        VAST oVAST = oParser.parse(oXML);

        assertNotNull(oVAST);
        assertNotNull(oVAST.getAds());
        assertEquals(10, oVAST.getAds().size());
        for (Ad oAd : oVAST.getAds()) {
        	assertTrue(oAd instanceof net.sf.video4j.gwt.plugin.shared.vast.InLine);
        }
    }

    @Test
    public void regularLinear() throws Exception {
        VASTParser oParser = new VASTParser();
        String oXML = getXMLAsString("/vast2_regular_linear.xml");

        VAST oVAST = oParser.parse(oXML);
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
    }
    
    private String getXMLAsString(String pResourcePath) throws IOException {
        InputStream oIS = this.getClass().getResourceAsStream(pResourcePath);
        StringWriter writer = new StringWriter();
        IOUtils.copy(oIS, writer, "UTF8");
        String oXML = writer.toString();
        return oXML;
    }

}