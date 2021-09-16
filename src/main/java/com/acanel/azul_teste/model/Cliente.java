package com.acanel.azul_teste.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@JsonIgnore
	private Long id;

	@NotNull
	@Size(min = 11, max = 11)
	@Column(length = 11, unique = true, nullable = false)
	private String cpf;

	@Size(max = 100)
	@Column(name = "email", unique = true, nullable = false)
	private String email;

	@Size(max = 150)
	@Column(name = "nome")
	private String nome;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "enderecos_id", referencedColumnName = "id")
	private Endereco endereco;

	public Cliente() {
		super();
	}

	public Cliente(@NotNull @Size(min = 11, max = 11) String cpf, @Size(max = 100) String email,
			@Size(max = 150) String nome, Endereco endereco) {
		super();
		this.cpf = cpf;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

//	@Override
//	public String toString() {
//		return "Cliente [id=" + id + ", cpf=" + cpf + ", email=" + email + ", nome=" + nome + ", endereco=" + endereco
//				+ "]";
//	}
}
