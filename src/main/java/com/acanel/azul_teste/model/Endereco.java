package com.acanel.azul_teste.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "enderecos")
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@JsonIgnore
	private long id;

	@NotNull
	@Column()
	// @JsonIgnore
	private String logradouro;

	@NotNull
	@Column()
	private String cep;

	@Column()
	// @JsonIgnore
	private String complemento;

	@Column()
	// @JsonIgnore
	private String bairro;

	@Column()
	// @JsonIgnore
	private String localidade;

	@Column()
	// @JsonIgnore
	private String uf;

	@Column()
	// @JsonIgnore
	private String ibge;

	public Endereco() {
		super();
	}

	public Endereco(@NotNull String logradouro, @NotNull String cep, String complemento, String bairro,
			String localidade, String uf, String ibge) {
		super();
		this.logradouro = logradouro;
		this.cep = cep;
		this.complemento = complemento;
		this.bairro = bairro;
		this.localidade = localidade;
		this.uf = uf;
		this.ibge = ibge;
	}

	public Long getId() {
		return id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", logradouro=" + logradouro + ", cep=" + cep + ", complemento=" + complemento
				+ ", bairro=" + bairro + ", localidade=" + localidade + ", uf=" + uf + ", ibge=" + ibge + "]";
	}

}
