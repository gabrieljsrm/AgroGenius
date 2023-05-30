package br.agrogenius.app.models;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

public class HistoricoVoo {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // auto increment
	private Long id;
	
	@NotNull(message = "O Drone é obrigatório!")
	private Long droneId;
	
	@NotNull(message = "A Latitude Inicial é obrigatório!")
	private Long latitudeInicial;
	
	@NotNull(message = "A Longitude Inicial é obrigatório!")
	private Long longitudeInicial;
	
	@NotNull(message = "A Latitude Final é obrigatório!")
	private Long latitudeFinal;
	
	@NotNull(message = "A Longitude Final é obrigatório!")
	private Long longitudeFinal;
	
	@NotNull(message = "A Altitude Média é obrigatório!")
	private Long altitudeMedia;
	
	@NotNull(message = "A Velocidade Média é obrigatório!")
	private Long velocidadeMedia;
		
	@NotNull(message = "A Data de Decolagem é obrigatório!")
	private LocalDate dataDecolagem;
	
	@NotNull(message = "A Data de Aterrisagem é obrigatório!")
	private LocalDate dataAterrisagem;

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

	public Long getLatitudeInicial() {
		return latitudeInicial;
	}

	public void setLatitudeInicial(Long latitudeInicial) {
		this.latitudeInicial = latitudeInicial;
	}

	public Long getLongitudeInicial() {
		return longitudeInicial;
	}

	public void setLongitudeInicial(Long longitudeInicial) {
		this.longitudeInicial = longitudeInicial;
	}

	public Long getLatitudeFinal() {
		return latitudeFinal;
	}

	public void setLatitudeFinal(Long latitudeFinal) {
		this.latitudeFinal = latitudeFinal;
	}

	public Long getLongitudeFinal() {
		return longitudeFinal;
	}

	public void setLongitudeFinal(Long longitudeFinal) {
		this.longitudeFinal = longitudeFinal;
	}

	public Long getAltitudeMedia() {
		return altitudeMedia;
	}

	public void setAltitudeMedia(Long altitudeMedia) {
		this.altitudeMedia = altitudeMedia;
	}

	public Long getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public void setVelocidadeMedia(Long velocidadeMedia) {
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
