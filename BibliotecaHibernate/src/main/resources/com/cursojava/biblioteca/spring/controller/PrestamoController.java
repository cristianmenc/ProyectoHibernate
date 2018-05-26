package com.cursojava.biblioteca.spring.controller;


import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cursojava.biblioteca.spring.dao.PrestamoDAO;
import com.cursojava.biblioteca.spring.beans.Prestamo;

@RestController
public class PrestamoController {

	@Autowired
	private PrestamoDAO prestamoDAO;

	
	@GetMapping("/listaPrestamos")
	public List getPrestamos() throws ParseException {
		
		return prestamoDAO.findAllPrestamos();
	}
	
//	@PostMapping(value = "/listaSocio")
//	public ResponseEntity getCustomer(@RequestBody Socio socio) throws ClassNotFoundException, SQLException {
//		System.out.println(socio.getId_socio());
//
//		return new ResponseEntity(socio, HttpStatus.OK);
//	}

	@GetMapping("/listaPrestamos/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Prestamo prestamo = prestamoDAO.findPrestamos(id);
		if (prestamo == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(prestamo, HttpStatus.OK);
	}
	
//	El metodo de recoger los prestamos por id pero con get
//	@GetMapping("/listaPrestamosSocio/{id}")
//	public ResponseEntity getPrestamosSocio(@PathVariable("id") Long id) {
//
//		List<Prestamo> prestamo = prestamoDAO.findPrestamosSocio(id);
//		if (prestamo == null) {
//			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//		}
//
//		return new ResponseEntity(prestamo, HttpStatus.OK);
//	}
	
	@PostMapping(value = "/listaPrestamosSocio")
	public List<Prestamo> getPrestamosSocio(@RequestBody Prestamo prestamo) throws ClassNotFoundException, SQLException {

		prestamoDAO.findPrestamosSocio(prestamo);

		return prestamoDAO.findPrestamosSocio(prestamo);
	}
	
	@PostMapping(value = "/listaPrestamosLibro")
	public List<Prestamo> getPrestamosLibro(@RequestBody Prestamo prestamo) throws ClassNotFoundException, SQLException {

		prestamoDAO.findPrestamosLibro(prestamo);

		return prestamoDAO.findPrestamosLibro(prestamo);
	}


	@PostMapping(value = "/crearPrestamo")
	public ResponseEntity createCustomer(@RequestBody Prestamo prestamo) throws ClassNotFoundException, SQLException {

		prestamoDAO.savePrestamo(prestamo);

		return new ResponseEntity(prestamo, HttpStatus.OK);
	}
	
	@PostMapping(value = "/modificarPrestamo")
	public ResponseEntity modifCustomer(@RequestBody Prestamo prestamo) throws ClassNotFoundException, SQLException {

		prestamoDAO.updatePrestamo(prestamo);

		return new ResponseEntity(prestamo, HttpStatus.OK);
	}
	
	@PostMapping(value = "/borrarPrestamo")
	public ResponseEntity deleteCustomer(@RequestBody Prestamo prestamo) throws ClassNotFoundException, SQLException {

		prestamoDAO.deletePrestamo(prestamo.getId_prestamo());

		return new ResponseEntity(prestamo, HttpStatus.OK);
	}
	
	
//
//	@PutMapping("/customers/{id}")
//	public ResponseEntity updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
//
//		customer = customerDAO.update(id, customer);
//
//		if (null == customer) {
//			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
//		}
//
//		return new ResponseEntity(customer, HttpStatus.OK);
//	}
}