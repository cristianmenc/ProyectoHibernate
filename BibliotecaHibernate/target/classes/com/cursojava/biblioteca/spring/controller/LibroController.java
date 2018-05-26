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

import com.cursojava.biblioteca.spring.dao.LibroDAO;
import com.cursojava.biblioteca.spring.beans.Libro;

@RestController
public class LibroController {

	@Autowired
	private LibroDAO libroDAO;

	
	@GetMapping("/listaLibros")
	public List getLibros() throws ParseException {
		
		return libroDAO.findAllLibros();
	}
	
//	@PostMapping(value = "/listaSocio")
//	public ResponseEntity getCustomer(@RequestBody Socio socio) throws ClassNotFoundException, SQLException {
//		System.out.println(socio.getId_socio());
//
//		return new ResponseEntity(socio, HttpStatus.OK);
//	}

	@GetMapping("/listaLibros/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Libro libro = libroDAO.findLibros(id);
		if (libro == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(libro, HttpStatus.OK);
	}

	@PostMapping(value = "/crearLibro")
	public ResponseEntity createCustomer(@RequestBody Libro libro) throws ClassNotFoundException, SQLException {

		libroDAO.saveLibro(libro);

		return new ResponseEntity(libro, HttpStatus.OK);
	}
	
	@PostMapping(value = "/modificarLibro")
	public ResponseEntity modifCustomer(@RequestBody Libro libro) throws ClassNotFoundException, SQLException {

		libroDAO.updateLibro(libro);

		return new ResponseEntity(libro, HttpStatus.OK);
	}
	
	@PostMapping(value = "/borrarLibro")
	public ResponseEntity deleteCustomer(@RequestBody Libro libro) throws ClassNotFoundException, SQLException {

		libroDAO.deleteLibro(libro.getId_libro());

		return new ResponseEntity(libro, HttpStatus.OK);
	}
	
	@GetMapping("/listaLibrosNoPrestados")
	public List getLibrosNoPrestados() throws ParseException {
		
		return libroDAO.librosNoPrestados();
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