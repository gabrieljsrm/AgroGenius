package br.agrogenius.app.models;

import java.time.LocalDateTime;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class Telemetria {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // auto increment
	private Long id;
	
	@NotNull(message = "O Drone é obrigatório!")
	private Long droneId;
	
	@NotNull(message = "A Latitude é obrigatório!")
	private Long latitude;
	
	@NotNull(message = "A Longitude é obrigatório!")
	private Long longitude;
	
	@NotNull(message = "A velocidade é obrigatório!")
	private Long velocidade;
	
	@NotNull(message = "A direcao é obrigatório!")
	private Long direcao;
	
	private LocalDateTime dataHora;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getDroneId() {
		return droneId;
	}

	public void setDroneId(Long droneId) {
		this.droneId = droneId;
	}

	public Long getLatitude() {
		return latitude;
	}

	public void setLatitude(Long latitude) {
		this.latitude = latitude;
	}

	public Long getLongitude() {
		return longitude;
	}

	public void setLongitude(Long longitude) {
		this.longitude = longitude;
	}

	public Long getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Long velocidade) {
		this.velocidade = velocidade;
	}

	public Long getDirecao() {
		return direcao;
	}

	public void setDirecao(Long direcao) {
		this.direcao = direcao;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}
	
	

}
