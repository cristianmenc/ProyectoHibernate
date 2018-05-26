package com.cursojava.biblioteca.spring.dao;

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

 

@Transactional
@Repository("LibroDAO")

public class LibroDAO extends AbstractDAO {
 
    public void saveLibro(Libro libro) {
        	persist(libro);
    }
    
    public Libro findLibros(Long id) {
		Criteria criteria = getSession().createCriteria(Libro.class);
		criteria.add(Restrictions.eq("id", id));
		List myLibro = (List<Libro>) criteria.list();
        return (Libro) criteria.list().get(0);
    }
 
    @SuppressWarnings("unchecked")
    public List<Libro> findAllLibros() {
        Criteria criteria = getSession().createCriteria(Libro.class).add(Restrictions.isNull("f_baja"));
        List mySocio = (List<Libro>) criteria.list();
        return (List<Libro>) criteria.list();
    }
 
    public void deleteLibro(Long id) {
//        Query query = getSession().createSQLQuery("UPDATE BIBLIOTECA_SOCIO "
//        		+ "SET f_baja = SYSDATE WHERE id_socio = :id");
//        query.setLong("id", id);
//        query.executeUpdate();
    	Libro l = findLibros(id);
    	l.setF_baja(new Date());
    	getSession().update(l);
    	
    }
    
    public void updateLibro(Libro libro){
        getSession().update(libro);
    }
    
    public List<Libro> librosNoPrestados() {
    	Criteria criteriaPrestamo = getSession().createCriteria(Prestamo.class)
                .add(Restrictions.isNull("f_devolucion"))
                .setProjection(Projections.property("libro.id_libro"));
        Criteria criteriaLibro = getSession().createCriteria(Libro.class)
                .add(Restrictions.not(Restrictions.in("id_libro", criteriaPrestamo.list())));
        return (List<Libro>) criteriaLibro.list();

	}
  
}
