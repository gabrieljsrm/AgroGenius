package br.agrogenius.app.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Drone {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // auto increment
	private Long id;
	
	@NotNull(message = "O Modelo é obrigatório!")
	private String modelo;
	
	@NotNull(message = "A Data de Compra é obrigatório!")
	private LocalDate dataCompra;
	
	@NotNull(message = "A Capacidade da Bateria é obrigatório!")
	private Long capacidadeBateria;

	@NotNull(message = "O Numero de Série é obrigatório!")
	private String numeroSerie;
	
	@NotNull(message = "A Licença de Voo é obrigatório!")
	private Long licencaVooId;
	
	@NotNull(message = "A Capacidade de Carga é obrigatório!")
	private Long capacidadeCarga;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public LocalDate getDataCompra() {
		return dataCompra;
	}

	public void setDataCompra(LocalDate dataCompra) {
		this.dataCompra = dataCompra;
	}

	public Long getCapacidadeBateria() {
		return capacidadeBateria;
	}

	public void setCapacidadeBateria(Long capacidadeBateria) {
		this.capacidadeBateria = capacidadeBateria;
	}

	public String getNumeroSerie() {
		return numeroSerie;
	}

	public void setNumeroSerie(String numeroSerie) {
		this.numeroSerie = numeroSerie;
	}

	public Long getLicencaVooId() {
		return licencaVooId;
	}

	public void setLicencaVooId(Long licencaVooId) {
		this.licencaVooId = licencaVooId;
	}

	public Long getCapacidadeCarga() {
		return capacidadeCarga;
	}

	public void setCapacidadeCarga(Long capacidadeCarga) {
		this.capacidadeCarga = capacidadeCarga;
	}
	
	
	
}
