package com.hepta.funcionarios.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Setor implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_SETOR")
	private Integer id;
	
	@OneToMany(mappedBy="setor")
	@JsonIgnore
	private List<Funcionario> funcionarios = new ArrayList<>();

	@Column(name = "NOME")
	private String nome;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public Setor() {
		
	}
	public Setor(Integer Id, List<Funcionario> funcionarios, String nome) {
		this.id = id;
		this.funcionarios = funcionarios;
		this.nome = nome;
	}
	
}
