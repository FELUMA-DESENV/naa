package com.feluma.naa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


/**
 * The persistent class for the estudante_informacao database table.
 * 
 */
@Entity
@Table(name="estudante_informacao")
@NamedQuery(name="EstudanteInformacao.findAll", query="SELECT e FROM EstudanteInformacao e")
public class EstudanteInformacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_est_inf")
	private Long codigo;

	@Column(name="res_que_est_inf")
	private boolean respostaQuestao;

	@Column(name="res_que_txt_est_inf")
	private String respostaAbertaQuestao;

	//bi-directional many-to-one association to Estudante
	@ManyToOne
	@JoinColumn(name="cod_est")
	private Estudante estudante;

	//bi-directional many-to-one association to Questao
	@ManyToOne
	@JoinColumn(name="cod_que")
	private Questao questao;



	public EstudanteInformacao() {

	}
	
	public EstudanteInformacao(Estudante estudante, Questao questao) {
		this.estudante = estudante;
		this.questao = questao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public boolean getRespostaQuestao() {
		return respostaQuestao;
	}

	public void setRespostaQuestao(boolean respostaQuestao) {
		this.respostaQuestao = respostaQuestao;
	}
	
	public String getRespostaAbertaQuestao() {
		return respostaAbertaQuestao;
	}

	public void setRespostaAbertaQuestao(String respostaAbertaQuestao) {
		this.respostaAbertaQuestao = respostaAbertaQuestao;
	}

	public Estudante getEstudante() {
		return estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public Questao getQuestao() {
		return questao;
	}

	public void setQuestao(Questao questao) {
		this.questao = questao;
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
		EstudanteInformacao other = (EstudanteInformacao) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}