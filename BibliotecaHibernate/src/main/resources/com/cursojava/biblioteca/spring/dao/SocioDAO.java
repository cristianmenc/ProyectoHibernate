package com.cursojava.biblioteca.spring.dao;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.cursojava.biblioteca.spring.beans.Socio;

 

@Transactional
@Repository("SocioDAO")
public class SocioDAO extends AbstractDAO {
 
    public void saveSocio(Socio socio) {
        	persist(socio);
    }
    
    public Socio findSocios(Long id) {
		Criteria criteria = getSession().createCriteria(Socio.class);
		criteria.add(Restrictions.eq("id", id));
		List mySocio = (List<Socio>) criteria.list();
        return (Socio) criteria.list().get(0);
    }
 
    @SuppressWarnings("unchecked")
    public List<Socio> findAllSocios() {
        Criteria criteria = getSession().createCriteria(Socio.class).add(Restrictions.isNull("f_baja"));
        List mySocio = (List<Socio>) criteria.list();
        return (List<Socio>) criteria.list();
    }
 
    public void deleteSocio(Long id) {
//        Query query = getSession().createSQLQuery("UPDATE BIBLIOTECA_SOCIO "
//        		+ "SET f_baja = SYSDATE WHERE id_socio = :id");
//        query.setLong("id", id);
//        query.executeUpdate();
    	Socio s = findSocios(id);
    	s.setF_baja(new Date());
    	getSession().update(s);
    	
    }
    
    public void updateSocio(Socio libro){
        getSession().update(libro);
    }
    
    public List<Socio> sociosNoSancionados() throws ParseException {
//    	SELECT * FROM BIBLIOTECA_LIBRO WHERE ID_LIBRO NOT IN
//    	(SELECT ID_LIBRO FROM BIBLIOTECA_PRESTAMO WHERE F_DEVOLUCION IS NULL)
    	
    	String consulta = ("FROM Socio "
    			+ "WHERE id_socio NOT IN " + 
    			"(SELECT socio.id_socio FROM Prestamo "
    			+ "WHERE (f_devolucion is not null and f_devolucion > f_estimada_devolucion)"
    			+ "or (f_devolucion is null and f_devolucion > current_date))");
    	
    	//System.out.println(consulta);
//    	String consulta = "FROM Socio";
    	
    	//SQLQuery query = getSession().createSQLQuery(consulta).addEntity("Socio", Socio.class);
    	
    	Query query = getSession().createQuery(consulta);
    	
    	return (List<Socio>) query.list();

	}
}
