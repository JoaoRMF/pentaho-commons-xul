/**
 * 
 */
package org.pentaho.ui.xul;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.pentaho.core.util.CleanXmlHelper;
import org.pentaho.ui.xul.components.XulMessageBox;
import org.pentaho.ui.xul.containers.XulWindow;
import org.pentaho.ui.xul.dom.Document;
import org.pentaho.ui.xul.swing.SwingXulLoader;
import org.pentaho.ui.xul.swing.SwingXulRunner;

/**
 * @author OEM
 *
 */
public class XulWindowContainer extends XulDomContainer{
  private List<Document> windows;
  private XulLoader xulLoader;
  
  public XulWindowContainer() throws XulException{
    super();
    windows = new ArrayList<Document>();
  }
  
  public XulWindowContainer(XulLoader xulLoader) throws XulException{
    this();
    this.xulLoader = xulLoader;
  }

  public Document getDocumentRoot(){
    return this.windows.get(0);
  }

  public void addDocument(Document document){
    this.windows.add(document);
  }

  public XulMessageBox createMessageBox(String message) {
    Object handler = XulParser.handlers.get("MESSAGEBOX");

    if (handler == null) {
      //TODO: add logging, discuss Exception handling
      System.out.println("Dialog handler not found: ");
      
    }

    XulElement rootElement = this.getDocumentRoot().getRootElement().getXulElement();
    
    Class<?> c;
    try {
      c = Class.forName((String) handler);
      Constructor<?> constructor = c
          .getConstructor(new Class[] { XulElement.class, String.class });
      XulMessageBox ele = (XulMessageBox) constructor.newInstance(rootElement, message);
      
      return ele;
    } catch (Exception e) {
      //TODO: add logging, discuss Exception handling
      System.out.println(String.format("Error Creating Message Dialog: %s",e.getMessage()));
      e.printStackTrace(System.out);
      return null;
    }
  }

  @Override
  public void close() {
    ((XulWindow) this.windows.get(0).getXulElement()).close();
  }

  @Override
  public XulFragmentContainer loadFragment(String xulLocation) throws XulException{
    try{
          
      InputStream in = SwingXulRunner.class.getClassLoader().getResourceAsStream(xulLocation);
      
      if(in == null){
        throw new XulException("loadFragment: input document is null");
      }
      
      org.dom4j.Document doc = CleanXmlHelper.getDocFromStream(in);
      
      XulFragmentContainer container = this.xulLoader.loadXulFragment(doc);
      return container;
    } catch(Exception e){
      System.out.println(e.getMessage());
      e.printStackTrace(System.out);
      throw new XulException(e);
    }
  }
  
}
