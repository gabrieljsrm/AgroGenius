package br.agrogenius.app.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class HistoricoVoo {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // auto increment
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "drone_id")
	private Drone drone;
	
	@NotNull(message = "A Latitude Inicial é obrigatório!")
	private Double latitudeInicial;
	
	@NotNull(message = "A Longitude Inicial é obrigatório!")
	private Double longitudeInicial;
	
	@NotNull(message = "A Latitude Final é obrigatório!")
	private Double latitudeFinal;
	
	@NotNull(message = "A Longitude Final é obrigatório!")
	private Double longitudeFinal;
	
	@NotNull(message = "A Altitude Média é obrigatório!")
	private Double altitudeMedia;
	
	@NotNull(message = "A Velocidade Média é obrigatório!")
	private Double velocidadeMedia;
		
	@NotNull(message = "A Data de Decolagem é obrigatório!")
	private LocalDate dataDecolagem;
	
	@NotNull(message = "A Data de Aterrisagem é obrigatório!")
	private LocalDate dataAterrisagem;

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

	public Double getLatitudeInicial() {
		return latitudeInicial;
	}

	public void setLatitudeInicial(Double latitudeInicial) {
		this.latitudeInicial = latitudeInicial;
	}

	public Double getLongitudeInicial() {
		return longitudeInicial;
	}

	public void setLongitudeInicial(Double longitudeInicial) {
		this.longitudeInicial = longitudeInicial;
	}

	public Double getLatitudeFinal() {
		return latitudeFinal;
	}

	public void setLatitudeFinal(Double latitudeFinal) {
		this.latitudeFinal = latitudeFinal;
	}

	public Double getLongitudeFinal() {
		return longitudeFinal;
	}

	public void setLongitudeFinal(Double longitudeFinal) {
		this.longitudeFinal = longitudeFinal;
	}

	public Double getAltitudeMedia() {
		return altitudeMedia;
	}

	public void setAltitudeMedia(Double altitudeMedia) {
		this.altitudeMedia = altitudeMedia;
	}

	public Double getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public void setVelocidadeMedia(Double velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}

	public LocalDate getDataDecolagem() {
		return dataDecolagem;
	}

	public void setDataDecolagem(LocalDate dataDecolagem) {
		this.dataDecolagem = dataDecolagem;
	}

	public LocalDate getDataAterrisagem() {
		return dataAterrisagem;
	}

	public void setDataAterrisagem(LocalDate dataAterrisagem) {
		this.dataAterrisagem = dataAterrisagem;
	}
	
	
}
