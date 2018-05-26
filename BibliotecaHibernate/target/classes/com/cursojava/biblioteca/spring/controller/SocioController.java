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

import com.cursojava.biblioteca.spring.dao.SocioDAO;
import com.cursojava.biblioteca.spring.beans.Socio;

@RestController
public class SocioController {

	@Autowired
	private SocioDAO socioDAO;

	
	@GetMapping("/listaSocios")
	public List getSocios() throws ParseException {
		
		return socioDAO.findAllSocios();
	}
	
//	@PostMapping(value = "/listaSocio")
//	public ResponseEntity getCustomer(@RequestBody Socio socio) throws ClassNotFoundException, SQLException {
//		System.out.println(socio.getId_socio());
//
//		return new ResponseEntity(socio, HttpStatus.OK);
//	}

	@GetMapping("/listaSocios/{id}")
	public ResponseEntity getCustomer(@PathVariable("id") Long id) {

		Socio socio = socioDAO.findSocios(id);
		if (socio == null) {
			return new ResponseEntity("No Customer found for ID " + id, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity(socio, HttpStatus.OK);
	}

	@PostMapping(value = "/crearSocio")
	public ResponseEntity createCustomer(@RequestBody Socio socio) throws ClassNotFoundException, SQLException {

		socioDAO.saveSocio(socio);

		return new ResponseEntity(socio, HttpStatus.OK);
	}
	
	@PostMapping(value = "/borrarSocio")
	public ResponseEntity deleteCustomer(@RequestBody Socio socio) throws ClassNotFoundException, SQLException {

		socioDAO.deleteSocio(socio.getId_socio());

		return new ResponseEntity(socio, HttpStatus.OK);
	}
	
	@GetMapping("/listaSociosNoSancionados")
	public List getSociosNoSancionados() throws ParseException {
		
		return socioDAO.sociosNoSancionados();
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