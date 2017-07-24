package br.com.api.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lembrete implements EntidadeBase{
	
	private Long id;
	
	
	private String titulo;
	
	
	private String descricao;
	
	public Lembrete() {}
	
	public Lembrete(long id, String titulo, String descricao) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = titulo;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	public Long getId() {
		return id;
	}

	@Column(name="titulo", length=30, nullable=false)
	public String getTitulo() {
		return titulo;
	}
	
	@Column(name="descricao", columnDefinition="text", nullable=false)
	public String getDescricao() {
		return descricao;
	}
	
	@Override
	public String toString() {
		return "Lembrete {id=" + id + ", titulo=" + titulo + ", descricao=" + descricao + "}";
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
