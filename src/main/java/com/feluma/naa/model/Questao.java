package com.feluma.naa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the questao database table.
 * 
 */
@Entity
@Table(name = "questao")
@NamedQuery(name = "Questao.findAll", query = "SELECT q FROM Questao q")
public class Questao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_que")
	private Long codigo;

	private String descricao;

	@Column(name = "flg_txt_que")
	private boolean flagTexto;

	@Column(name = "flg_vis_sn_que")
	private boolean flagAlternativa;

	// bi-directional many-to-one association to EstudanteInformacao
	@OneToMany(mappedBy = "questao", cascade = CascadeType.ALL)
	private List<EstudanteInformacao> estudanteInformacoes = new ArrayList<EstudanteInformacao>();

	public Questao() {
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isFlagTexto() {
		return flagTexto;
	}

	public void setFlagTexto(boolean flagTexto) {
		this.flagTexto = flagTexto;
	}

	public boolean isFlagAlternativa() {
		return flagAlternativa;
	}

	public void setFlagAlternativa(boolean flagAlternativa) {
		this.flagAlternativa = flagAlternativa;
	}

	public List<EstudanteInformacao> getEstudanteInformacoes() {
		return estudanteInformacoes;
	}

	public void setEstudanteInformacoes(List<EstudanteInformacao> estudanteInformacoes) {
		this.estudanteInformacoes = estudanteInformacoes;
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
		Questao other = (Questao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}



}