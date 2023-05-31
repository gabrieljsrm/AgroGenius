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
public class Drone {

    @Id // primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // auto increment
    private Long id;

    @NotNull(message = "O Modelo é obrigatório!")
    private String modelo;

    @NotNull(message = "A Data de Compra é obrigatória!")
    private LocalDate dataCompra;

    @NotNull(message = "A Capacidade da Bateria é obrigatória!")
    private Long capacidadeBateria;

    @NotNull(message = "O Número de Série é obrigatório!")
    private String numeroSerie;

    @ManyToOne
    @JoinColumn(name = "licenca_voo_id")
    private LicencaVoo licencaVoo;

    @NotNull(message = "A Capacidade de Carga é obrigatória!")
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

    public LicencaVoo getLicencaVoo() {
        return licencaVoo;
    }

    public void setLicencaVoo(LicencaVoo licencaVoo) {
        this.licencaVoo = licencaVoo;
    }

    public Long getCapacidadeCarga() {
        return capacidadeCarga;
    }

    public void setCapacidadeCarga(Long capacidadeCarga) {
        this.capacidadeCarga = capacidadeCarga;
    }

    // Getter e Setter para licencaVooId
    public Long getLicencaVooId() {
        if (licencaVoo != null) {
            return licencaVoo.getId();
        }
        return null;
    }

    public void setLicencaVooId(Long licencaVooId) {
        if (licencaVooId != null) {
            LicencaVoo licencaVoo = new LicencaVoo();
            licencaVoo.setId(licencaVooId);
            this.licencaVoo = licencaVoo;
        }
    }
}
