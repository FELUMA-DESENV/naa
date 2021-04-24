package com.feluma.naa.model;

import java.io.Serializable;
import java.util.Date;

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
 * The persistent class for the vacina_estudante database table.
 * 
 */
@Entity
@Table(name="vacina_estudante")
@NamedQuery(name="VacinaEstudante.findAll", query="SELECT v FROM VacinaEstudante v")
public class VacinaEstudante implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_vac_est")
	private Long codigo;

	@Column(name="dat_dos_vac_est")
	private Date dataDose;

	@Column(name="dat_val_vac_est")
	private Date dataValidade;

	@Column(name="exa_sor_con_vac_est")
	private boolean exameSoroConvercao;

	@Column(name="obs_vac_est")
	private String observacao;

	@Column(name="res_exa_sor_con_vac_est")
	private String resultadoSoroConvercao;

	//bi-directional many-to-one association to Estudante
	@ManyToOne
	@JoinColumn(name="cod_est")
	private Estudante estudante;

	//bi-directional many-to-one association to Vacina
	@ManyToOne
	@JoinColumn(name="cod_vac")
	private Vacina vacina;

	public VacinaEstudante() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Date getDataDose() {
		return this.dataDose;
	}

	public void setDataDose(Date dataDose) {
		this.dataDose = dataDose;
	}

	public Date getDataValidade() {
		return this.dataValidade;
	}

	public void setDataValidade(Date dataValidade) {
		this.dataValidade = dataValidade;
	}

	public boolean getExameSoroConvercao() {
		return this.exameSoroConvercao;
	}

	public void setExameSoroConvercao(boolean exameSoroConvercao) {
		this.exameSoroConvercao = exameSoroConvercao;
	}

	public String getObservacao() {
		return this.observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getResultadoSoroConvercao() {
		return this.resultadoSoroConvercao;
	}

	public void setResultadoSoroConvercao(String resultadoSoroConvercao) {
		this.resultadoSoroConvercao = resultadoSoroConvercao;
	}

	public Estudante getEstudante() {
		return this.estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public Vacina getVacina() {
		return this.vacina;
	}

	public void setVacina(Vacina vacina) {
		this.vacina = vacina;
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
		VacinaEstudante other = (VacinaEstudante) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}