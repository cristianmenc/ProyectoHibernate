package com.cursojava.biblioteca.spring.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cursojava.biblioteca.spring.beans.Libro;
import com.cursojava.biblioteca.spring.beans.Prestamo;
import com.cursojava.biblioteca.spring.beans.Socio;

 

@Transactional
@Repository("PrestamoDAO")

public class PrestamoDAO extends AbstractDAO {
 
    public void savePrestamo(Prestamo prestamo) {
        	persist(prestamo);
    }
    
    public Prestamo findPrestamos(Long id) {
		Criteria criteria = getSession().createCriteria(Prestamo.class);
		criteria.add(Restrictions.eq("id", id));
		List myPrestamo = (List<Prestamo>) criteria.list();
        return (Prestamo) criteria.list().get(0);
    }
 
    @SuppressWarnings("unchecked")
    public List<Prestamo> findAllPrestamos() {
        Criteria criteria = getSession().createCriteria(Prestamo.class).add(Restrictions.isNull("f_devolucion"));
        List mySocio = (List<Prestamo>) criteria.list();
        return (List<Prestamo>) criteria.list();
    }
 
    public void deletePrestamo(Long id) {
//        Query query = getSession().createSQLQuery("UPDATE BIBLIOTECA_SOCIO "
//        		+ "SET f_baja = SYSDATE WHERE id_socio = :id");
//        query.setLong("id", id);
//        query.executeUpdate();
    	Prestamo l = findPrestamos(id);
    	l.setF_devolucion(new Date());
    	getSession().update(l);
    	
    }
    
    public void updatePrestamo(Prestamo prestamo){
        getSession().update(prestamo);
    }
    
    public List<Prestamo> findPrestamosSocio(Prestamo prestamo) {
    	String consulta = ("FROM Prestamo " + 
    			"WHERE id_socio = "+prestamo.getSocio().getId_socio());
    	
//    	String consulta = "FROM Socio";
    	
    	//SQLQuery query = getSession().createSQLQuery(consulta).addEntity("Socio", Socio.class);
    	
    	Query query = getSession().createQuery(consulta);
    	
    	return (List<Prestamo>) query.list();
    }
    
    public List<Prestamo> findPrestamosLibro(Prestamo prestamo) {
    	String consulta = ("FROM Prestamo " + 
    			"WHERE id_libro = "+prestamo.getLibro().getId_libro());
    	
//    	String consulta = "FROM Socio";
    	
    	//SQLQuery query = getSession().createSQLQuery(consulta).addEntity("Socio", Socio.class);
    	
    	Query query = getSession().createQuery(consulta);
    	
    	return (List<Prestamo>) query.list();
    }
    
    
}
