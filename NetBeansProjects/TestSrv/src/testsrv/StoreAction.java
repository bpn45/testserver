/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testsrv;

import db.Entities;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author pasha
 */
public class StoreAction extends RecursiveAction {
    private static final long serialVersionUID = 1L;
    SessionFactory sf;
    ArrayDeque<Entities> lp;
    private static final int dbPoolSize=20;//Размер пула JDBC
    protected static final Logger logger = Logger.getLogger(StoreAction.class);
    
    StoreAction(SessionFactory sf, ArrayDeque<Entities> listEnt){
        this.sf=sf;
        lp=listEnt.clone();
        //while (listEnt.size()>0) lp.add(listEnt.poll());
        logger.info("Пришло на сохранение элементов"+ Integer.toString(lp.size()));
    }

    private  ArrayDeque<Entities> takeEnt(int count){
        ArrayDeque<Entities> lp2=new ArrayDeque<>();
        int min;
        if (count>0) {
            min = Math.min(count, lp.size());
        } else {
            min = lp.size();
        }
        for(int i=0; i<min; i++){
            lp2.add(lp.poll());
        }
        return lp2;
    }
    @Override
    protected void compute() {
        Session sess=sf.openSession();
        if (lp.size()<=20){
            Transaction tk=sess.beginTransaction();
            while(lp.size()>0) {
                Entities ent=lp.poll();
                int executeUpdate = sess.createSQLQuery(ent.getSQLstring()).executeUpdate();
                //logger.info("После сохранения" + Integer.toString(executeUpdate));
            }
            tk.commit();
            sess.close();
            
        } else {
           // ArrayDeque<Entities> temp=takeEnt(20);
            invokeAll(new StoreAction(sf, takeEnt(20)),
                      new StoreAction(sf, takeEnt(-1)));
        }
    }
    
}
