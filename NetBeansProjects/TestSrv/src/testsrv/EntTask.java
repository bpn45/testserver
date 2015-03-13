/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsrv;

import db.SrvConfig;
import db.Entities;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveTask;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.xml.sax.SAXException;
import xml.EntHandler;

/**
 *
 * @author pasha
 */
public class EntTask extends RecursiveTask<ConcurrentLinkedDeque<Entities>>{
    private static final long serialVersionUID = 1L;
    private static  CopyOnWriteArrayList<Path> listPath;
    SrvConfig srvConf;
    SAXParserFactory factory;
    SessionFactory sFactory;
    protected static final Logger logger = Logger.getLogger(EntTask.class);
    ConcurrentLinkedDeque<Entities> entL;
    
    
    EntTask(CopyOnWriteArrayList<Path> listPath, SAXParserFactory factory, SrvConfig srvConf){
        this.listPath=new CopyOnWriteArrayList(listPath);
        this.factory=factory;
        this.srvConf=srvConf;
        this.entL=new ConcurrentLinkedDeque<>();
        
        //logger.info("Длина списка" + Integer.toString(listPath.size()));
        
    }
    
    @Override
    protected ConcurrentLinkedDeque<Entities> compute() {
        if(listPath.size()<= 20) {
            int count=listPath.size();
            String wrongP=srvConf.getWrong();
            String destP=srvConf.getDestination();
            Entities ent;
            EntHandler handler = new EntHandler();
            SAXParser parser1=null;
        try {
                parser1 = factory.newSAXParser();
            } catch (ParserConfigurationException ex) {
                logger.error("Ошибка конфигурации  ", ex);
            } catch (SAXException ex) {
                logger.error("Исключения парсера", ex);
            }
    
    
 try {     for (int i=0;i<count & listPath.size()>0; i++) {
            File f=listPath.remove(0).toFile();
            parser1.parse(f, handler);
            ent=handler.getResult();
            //logger.info(ent.getContent()+ ent.getCreated());
            if (ent==null || ent.isEmpty()){
               logger.info("Пустой класс!!!!");
               
               f.renameTo(new File(wrongP,f.getName()));
            } else {
                logger.info("ResultList" + Integer.toString(entL.size()));
                entL.add(ent);
                f.renameTo(new File(destP,f.getName()));
                
            }
        }
    }
catch (SAXException e) {
    logger.error("parsing error");
} 
catch (IOException e) {
    logger.error("i/o error");
}        
            
           
        } else {
           
          CopyOnWriteArrayList<Path> tempList= tempList = new CopyOnWriteArrayList<>();
          for(int i=0;i<20;i++) tempList.add(listPath.get(i));
           listPath.removeAll(tempList); 
           EntTask left;
           left = new EntTask(tempList  ,factory, srvConf);
           left.fork();
           EntTask right;
           right = new EntTask(listPath,factory,   srvConf);
           entL.addAll(left.join());
           entL.addAll(right.compute());
           return entL;
        }
        
        return entL;
    }

    

    
   

    

    
    
}
