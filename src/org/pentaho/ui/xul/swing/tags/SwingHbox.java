/**
 * 
 */
package org.pentaho.ui.xul.swing.tags;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.pentaho.ui.xul.XulComponent;
import org.pentaho.ui.xul.XulDomContainer;
import org.pentaho.ui.xul.XulElement;
import org.pentaho.ui.xul.containers.XulHbox;
import org.pentaho.ui.xul.dom.Element;
import org.pentaho.ui.xul.swing.SwingElement;
import org.pentaho.ui.xul.util.Orient;

/**
 * @author OEM
 *
 */
public class SwingHbox extends SwingElement implements XulHbox{
 
  
  public SwingHbox(XulElement parent, XulDomContainer domContainer, String tagName) {
    super("Hbox");
    
    children = new ArrayList<XulComponent>();
    
    container = new JPanel(new GridBagLayout());
    //container.setBorder(BorderFactory.createLineBorder(Color.green));
    managedObject = container;
    
    resetContainer();
    
  }
  
  public void resetContainer(){
    
    container.removeAll();
    
    gc = new GridBagConstraints();
    gc.gridx = GridBagConstraints.RELATIVE;
    gc.gridy = 0;
    gc.gridheight = GridBagConstraints.REMAINDER;
    gc.gridwidth = 1;
    gc.insets = new Insets(2,2,2,2);
    gc.fill = GridBagConstraints.HORIZONTAL;
    gc.anchor = GridBagConstraints.NORTHWEST;
    gc.weighty = 1;
    
  }
  
  public Orient getOrientation() {
    return Orient.HORIZONTAL;
  }


  @Override
  public void replaceChild(Element oldElement, Element newElement) {
    this.resetContainer();
    super.replaceChild(oldElement, newElement);
  }

}

