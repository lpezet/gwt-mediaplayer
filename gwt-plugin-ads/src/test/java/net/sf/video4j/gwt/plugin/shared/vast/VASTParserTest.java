/**
 * 
 */
package net.sf.video4j.gwt.plugin.shared.vast;

import static org.junit.Assert.assertEquals;

import net.sf.video4j.gwt.plugin.AbstractVASTParserTest;
import net.sf.video4j.gwt.plugin.IVASTParserTest;

import org.junit.Ignore;
import org.junit.Test;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.XMLParser;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author luc
 */
@GwtModule("net.sf.video4j.gwt.plugin.VAST")
public class VASTParserTest extends GwtTest implements IVASTParserTest {
	
	private static class VASTParserTestImpl extends AbstractVASTParserTest {
		
		private VASTParser mParser = new VASTParser();
		
		@Override
		protected VAST parse(String pXML) {
			return mParser.parse(pXML);
		}
	}
	
	private IVASTParserTest mImpl = new VASTParserTestImpl();
	
	@Test
	@Ignore
	public void sanityCheckXmlAttributeParsingProblem() throws Exception {
		Document oDoc = XMLParser.parse("<toto><tata myAttribute=\"titi\" myAttribute2=\"tete\" test=\"test1\" testMe=\"hello\" test_me=\"world\"><tutu/></tata></toto>");
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
		assertEquals("test1", oTata.getAttributes().getNamedItem("test").getNodeValue()); //WORKS
		assertEquals("world", oTata.getAttributes().getNamedItem("test_me").getNodeValue()); //WORKS!
		assertEquals("hello", oTata.getAttributes().getNamedItem("testMe").getNodeValue()); // DOES NOT WORK
		assertEquals("tete", oTata.getAttributes().getNamedItem("myAttribute2").getNodeValue()); // DOES NOT WORK
		assertEquals("titi", oTata.getAttributes().getNamedItem("myAttribute").getNodeValue()); // DOES NOT WORK
	}

	@Test
	@Ignore
	public void sanityCheckXmlAttributeParsingProblem2() throws Exception {
		Document oDoc = XMLParser.parse("<toto><tata myAttribute=\"titi\" myAttribute2=\"tete\" test=\"test1\" testMe=\"hello\" test_me=\"world\"><tutu/></tata></toto>");
		Element oToto = oDoc.getDocumentElement();
		Element oTata = (Element) oToto.getFirstChild();
		System.out.println("########## attr test = " + oTata.getAttribute("test"));
		System.out.println("########## attr test_me = " + oTata.getAttribute("test_me"));
		System.out.println("########## attr testMe = " + oTata.getAttribute("testMe"));
		System.out.println("########## attr myAttribute2 = " + oTata.getAttribute("myAttribute2"));
		System.out.println("########## attr myAttribute = " + oTata.getAttribute("myAttribute"));
		
		//Node oToto = oDoc.getChildNodes().item(0);
		//Node oTata = oToto.getFirstChild();
		
		/*
		NamedNodeMap oNNMap = oTata.getAttributes();
		for (int i = 0; i < oNNMap.getLength(); i++) {
			Node n = oNNMap.item(i);
			System.out.println("###### Attribute #" + i + ": " + n.getNodeName() + ", class=" + n.getClass());
			com.google.gwt.xml.client.impl.AttrImpl oImpl = (com.google.gwt.xml.client.impl.AttrImpl) n;
			
		}
		*/
		/*
		assertEquals("test1", oTata.getAttributes().getNamedItem("test").getNodeValue()); //WORKS
		assertEquals("world", oTata.getAttributes().getNamedItem("test_me").getNodeValue()); //WORKS!
		assertEquals("hello", oTata.getAttributes().getNamedItem("testMe").getNodeValue()); // DOES NOT WORK
		assertEquals("tete", oTata.getAttributes().getNamedItem("myAttribute2").getNodeValue()); // DOES NOT WORK
		assertEquals("titi", oTata.getAttributes().getNamedItem("myAttribute").getNodeValue()); // DOES NOT WORK
		*/
	}
	
	@Override
	@Test
	public void companionAds() throws Exception {
		mImpl.companionAds();
	}
	
	@Override
	@Test
	public void nonLinearAds() throws Exception {
		mImpl.nonLinearAds();
	}
	
	@Override
	@Test
	public void overlays() throws Exception {
		mImpl.overlays();
	}
	
	@Override
	@Test
	public void regularLinear() throws Exception {
		mImpl.regularLinear();
	}

}