package com.feluma.naa.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the estudante_contato database table.
 * 
 */
@Entity
@Table(name="estudante_contato")
@NamedQuery(name="EstudanteContato.findAll", query="SELECT e FROM EstudanteContato e")
public class EstudanteContato implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_est_con")
	private Long codigo;

	@Column(name="cel_est_con")
	private String celular;

	@Column(name="nom_est_con")
	private String nome;

	@Column(name="par_est_con")
	private String parentensco;

	@Column(name="tel_est_con")
	private String telefone;

	//bi-directional many-to-one association to Estudante
	@ManyToOne
	@JoinColumn(name="cod_est")
	private Estudante estudante;

	public EstudanteContato() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getParentensco() {
		return this.parentensco;
	}

	public void setParentensco(String parentensco) {
		this.parentensco = parentensco;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Estudante getEstudante() {
		return this.estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstudanteContato other = (EstudanteContato) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}