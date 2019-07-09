package com.santg.disproclientes.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;

@Entity
public class Comentario implements Comparable<Comentario>{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="detalle")
	@Size(max = 240)
	private String detalle;
	
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	@Column(name="timestamp")
	private Date timestamp;
	
	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Vendedor vendedor;
	
	// constructors
	public Comentario() {}

	public Comentario(@Size(max = 240) String detalle, Date timestamp, Vendedor vendedor) {
		this.detalle = detalle;
		this.timestamp = timestamp;
		this.vendedor = vendedor;
	}
	
	// comparable to sort by date descending (newer first)
	@Override
	public int compareTo(Comentario o) {
		
		return o.getTimestamp().compareTo(this.timestamp);
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}



}
