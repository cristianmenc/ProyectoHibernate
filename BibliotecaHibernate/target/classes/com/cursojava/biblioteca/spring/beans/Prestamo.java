package com.cursojava.biblioteca.spring.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BIBLIOTECA_PRESTAMO")

public class Prestamo {
	
	@Id
	@Column(name = "ID_PRESTAMO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", allocationSize=1, sequenceName = "SEQ_ID_PRESTAMO")
	private long id_prestamo;
	
//	@OneToOne
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="id_socio")
	private Socio socio;
	
	@OneToOne
	@JoinColumn(name="id_libro")
	private Libro libro;
	
	@Type(type = "date")
	@CreationTimestamp
	private Date f_prestamo;
	
	@Type(type = "date")
	private Date f_devolucion;
	@Type(type = "date")
	private Date f_estimada_devolucion;
	
	public Prestamo() {
		
	}
	
	public Prestamo(long id_prestamo) {
		this.id_prestamo = id_prestamo;
	}
	public long getId_prestamo() {
		return id_prestamo;
	}
	public void setId_prestamo(long id_prestamo) {
		this.id_prestamo = id_prestamo;
	}
	public Date getF_prestamo() {
		return f_prestamo;
	}
	public void setF_prestamo(Date f_prestamo) {
		this.f_prestamo = f_prestamo;
	}
	public Date getF_devolucion() {
		return f_devolucion;
	}
	public void setF_devolucion(Date f_devolucion) {
		this.f_devolucion = f_devolucion;
	}
	public Date getF_estimada_devolucion() {
		return f_estimada_devolucion;
	}
	public void setF_estimada_devolucion(Date f_estimada_devolucion) {
		this.f_estimada_devolucion = f_estimada_devolucion;
	}

	public Socio getSocio() {
		return socio;
	}

	public void setSocio(Socio socio) {
		this.socio = socio;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	
	
}

