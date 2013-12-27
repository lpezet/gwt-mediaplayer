/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.sf.video4j.gwt.plugin.ads.shared.vast.VAST;
import net.sf.video4j.gwt.plugin.ads.shared.vast.VASTParser;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author luc
 *
 */
@GwtModule("net.sf.video4j.gwt.plugin.ads.VAST")
public class IABTest extends GwtTest {

	@Test
	public void doubleClick() throws Exception {
		VASTParser oParser = new VASTParser();
		
        parse(oParser, "doubleclick_prefetch_tag_1.xml");
        parse(oParser, "doubleclick_prefetch_tag_2.xml");
	}

	
	
	@Test
	public void liverail() throws Exception {
		VASTParser oParser = new VASTParser();
		parse(oParser, "liverail_companion.xml");
		parse(oParser, "liverail_nonlinear.xml");
	}
	
	@Test
	public void tremor() throws Exception {
		VASTParser oParser = new VASTParser();
		parse(oParser, "tremor_video_inline_linear.xml");
		parse(oParser, "tremor_video_inline_nonlinear.xml");
		parse(oParser, "tremor_video_nonlinear.xml");
		parse(oParser, "tremor_video_regular_linear.xml");
		parse(oParser, "tremor_video_vpaid_linear.xml");
		parse(oParser, "tremor_video_wrapper_linear_1.xml");
		parse(oParser, "tremor_video_wrapper_linear_2.xml");
		parse(oParser, "tremor_video_wrapper_nonlinear_1.xml");
		parse(oParser, "tremor_video_wrapper_nonlinear_2.xml");
	}
	
	private VAST parse(VASTParser oParser, String pFilename) throws IOException {
		String oXML = getXMLAsString("/iab/vast2/" + pFilename);
        VAST oVAST = oParser.parse(oXML);
        assertNotNull(oVAST);
        assertNotNull(oVAST.getAds());
        return oVAST;
	}
	
	private String getXMLAsString(String pResourcePath) throws IOException {
        InputStream oIS = this.getClass().getResourceAsStream(pResourcePath);
        StringWriter writer = new StringWriter();
        IOUtils.copy(oIS, writer, "UTF8");
        String oXML = writer.toString();
        return oXML;
    }

}
