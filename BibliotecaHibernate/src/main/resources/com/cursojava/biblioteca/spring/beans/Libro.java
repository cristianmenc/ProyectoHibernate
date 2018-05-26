package com.cursojava.biblioteca.spring.beans;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "BIBLIOTECA_LIBRO")
public class Libro {
	
	@Id
	@Column(name = "ID_LIBRO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
	@SequenceGenerator(name = "id_Sequence", allocationSize=1, sequenceName = "SEQ_ID_LIBRO")
	private long id_libro;
	private String titulo;
	private String autor;
	private String isbn;
	private String genero;
	@Type(type = "date")
	@CreationTimestamp
	private Date f_alta;
	@Type(type = "date")
	private Date f_baja;
	
	
	public Libro(){
		
	}

	public Libro(long id_libro) {
		this.id_libro = id_libro;
	}

	public long getId_libro() {
		return id_libro;
	}

	public void setId_libro(long id_libro) {
		this.id_libro = id_libro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
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
	

}

