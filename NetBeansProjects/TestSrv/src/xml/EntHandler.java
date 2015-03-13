package xml;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pasha
 */
import db.Entities;
import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class EntHandler extends DefaultHandler {

   String thisElement="";
   Entities ent;
   protected static final Logger logger = Logger.getLogger(EntHandler.class);
   @Override 
public void startDocument() throws SAXException { 
    ent = new Entities();
}

   @Override
   public void startElement(String uri, 
   String localName, String qName, Attributes attributes)
      {
      thisElement = qName;
   }

   @Override
   public void endElement(String uri, 
   String localName, String qName)  {
      thisElement="";
   }
   
   @Override 
public  void characters(char[] ch, int start, int length) { 
  if (thisElement.equals("content")) { 
     ent.setContent(new String(ch, start, length)); 
  } 
  if (thisElement.equals("creationDate")) { 
      ent.setCreated(new String(ch, start, length));
  } 
  
  
}
    @Override 
public void endDocument() { 
  //logger.info("Конец документа");
} 
public Entities getResult(){
    if (ent.isEmpty()) return null;
    else return ent;
}

}
