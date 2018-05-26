package com.cursojava.biblioteca.spring.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BIBLIOTECA_SOCIO")
public class Socio {

	/* configuration for Oracle with autonumeric */

	@Id
	@Column(name = "ID_SOCIO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", sequenceName = "SEQ_ID_SOCIO")
	private long id_socio;
	// @Column(name = "NOMBRE")
	private String nombre;
	private String apellidos;
	private String dni;
	@Column(name = "f_naciminento")
	private Date f_nacimiento;
	private Date f_alta;
	private Date f_baja;

	public Socio() {

	}

	public Socio(long id_socio, String nombre, String apellidos, String dni, Date fecha_nacimiento) {
		super();
		this.id_socio = id_socio;
		this.nombre = nombre;
		this.dni = dni;
		this.f_nacimiento = fecha_nacimiento;
	}

	public long getId_socio() {
		return id_socio;
	}

	public void setId_socio(long id_socio) {
		this.id_socio = id_socio;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public Date getF_alta() {
		return f_alta;
	}

	public void setF_alta(Date f_alta) {
		this.f_alta = f_alta;
	}

	public Date getF_baja() {
		return f_baja;
	}

	public void setF_baja(Date f_baja) {
		this.f_baja = f_baja;
	}

	public Date getF_nacimiento() {
		return f_nacimiento;
	}

	public void setF_nacimiento(Date f_nacimiento) {
		this.f_nacimiento = f_nacimiento;
	}
	
	
	

}
