/**
 * 
 */
package net.sf.video4j.gwt.plugin.server.vast.dao;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import net.sf.video4j.gwt.plugin.shared.vast.Ad;
import net.sf.video4j.gwt.plugin.shared.vast.VAST;
import net.sf.video4j.gwt.plugin.shared.vast.Wrapper;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

/**
 * @author luc
 *
 */
public class AdServiceTest {
	
	private static String mVASTWrapper = null;
	
	static {
		try {
			InputStream oIS = AdServiceTest.class.getResourceAsStream("/vast2_wrapper.xml");
	        StringWriter writer = new StringWriter();
	        IOUtils.copy(oIS, writer, "UTF8");
	        mVASTWrapper = writer.toString();
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}
	}

	@Test
	public void fetchVASTForWrapper() throws Exception {
		AdService oAdService = new AdService();
		IUrlFetcher oFetcher = mock(IUrlFetcher.class);
		oAdService.setContentFetcher(oFetcher);
		when(oFetcher.getContent(anyString())).thenReturn(mVASTWrapper);
		
		VAST oVAST = oAdService.fetchAds("");
		assertNotNull(oVAST);
		assertFalse(oVAST.getAds().isEmpty());
		Ad oAd = oVAST.getAds().get(0);
		assertNotNull(oAd);
		assertTrue(oAd instanceof Wrapper);
		
	}

}
