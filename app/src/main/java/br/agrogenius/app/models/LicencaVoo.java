package br.agrogenius.app.models;

import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;


public class LicencaVoo {
	
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.AUTO) // auto increment
	private Long id;
			
	@NotNull(message = "O Número da Licença é obrigatório!")
	private String licencavoo;
	
	@NotNull(message = "A Data de Emissão é obrigatório!")
	private LocalDate dataEmissao;
	
	@NotNull(message = "A Data de Validade é obrigatório!")
	private LocalDate dataValidade;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLicencavoo() {
		return licencavoo;
	}

	public void setLicencavoo(String licencavoo) {
		this.licencavoo = licencavoo;
	}

	public LocalDate getDataEmissao() {
		return dataEmissao;
	}

	public void setDataEmissao(LocalDate dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}




}
