/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import modelo.GestionDevolucion;
import mx.com.gm.sga.domain.Devolucion;
import mx.com.gm.sga.domain.Producto;
/**
 *
 * @author antonio
 */
public class GestionDevolucion {
    
    
    public List<Devolucion> recuperardevolucion(){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("WEBCOM");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        //iniciamos la trasaccion
        tx.begin();
        String select = "Select c from Devolucion c";
        Query qr = em.createQuery(select);
        List<Devolucion> listadevolucion = (List<Devolucion>) qr.getResultList();
        //terminamos la trasaccion
        tx.commit();
      
        em.close();
        return listadevolucion;
        
    }
    
    public void Realizardevolucion(java.sql.Date Fechadevolucion, int idwallet , int idproducto){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("WEBCOM");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Devolucion sw = new Devolucion(Fechadevolucion, idwallet, idproducto);
        tx.begin();
        em.persist(sw);
        tx.commit();
        em.clear();
       
        
    }
    
    public void borrarDevolucion(int iddevolucion){
         EntityManagerFactory emf = Persistence.createEntityManagerFactory("WEBCOM");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        Devolucion devo =new Devolucion();
        tx.begin();
        devo=em.find(Devolucion.class, iddevolucion);
        em.remove(devo);
        tx.commit();
        em.clear();
    }
     
}
