package br.agrogenius.app.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class Telemetria {

	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // auto increment
	private Long id;

	@ManyToOne
	@JoinColumn(name = "drone_id")
	private Drone drone;

	@NotNull(message = "A Latitude é obrigatório!")
	private double latitude;

	@NotNull(message = "A Longitude é obrigatório!")
	private double longitude;

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

	public Drone getDrone() {
		return drone;
	}

	public void setDrone(Drone drone) {
		this.drone = drone;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double d) {
		this.latitude = d;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double d) {
		this.longitude = d;
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

	public Long getDroneId() {
		if (drone != null) {
			return drone.getId();
		}
		return null;
	}

	public void setDroneId(Long droneId) {
		if (droneId != null) {
			Drone drone = new Drone();
			drone.setId(droneId);
			this.drone = drone;
		}

	}
}