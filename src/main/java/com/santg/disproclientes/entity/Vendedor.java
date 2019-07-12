package com.santg.disproclientes.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="vendedor")
public class Vendedor {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="nombre", nullable=false)
	private String nombre;
	
	@Column(name="password", nullable=false)
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="vendedor_id")
	private List<Comentario> comentarios;
	
	private String roles;

	// constructors
	public Vendedor() {}

	public Vendedor(String nombre) {
		this.nombre = nombre;
	}

	public Vendedor(String nombre, String password, String roles) {
		this.nombre = nombre;
		this.password = password;
		this.roles = roles;
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void addRole(String rol) {
		
		if(this.roles == null) {
			this.roles = "";
		}
		this.roles = this.roles + "," + rol;
	}
	
	public List<String> getRolesList() {
		
		if(this.roles.length() > 0) {
			return Arrays.asList(this.roles.split(","));
		}
		
		return new ArrayList<>();
	}
	
	// toString
	public String toString() {
		return this.nombre + ", " + this.id;
	}
	
}
