package com.feluma.naa.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the vacina database table.
 * 
 */
@Entity
@Table(name="vacina")
@NamedQuery(name="Vacina.findAll", query="SELECT v FROM Vacina v")
public class Vacina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_vac")
	private Long codigo;

	@Column(name="car_vac")
	private int carencia;

	@Column(name="des_vac")
	private String descricao;

	@Column(name="nom_vac")
	private String nome;
	
	@Column(name="obr_vac")
	private boolean obrigatoria;

	//bi-directional many-to-one association to VacinaEstudante
	@OneToMany(mappedBy="vacina")
	private List<VacinaEstudante> vacinaEstudantes = new ArrayList<VacinaEstudante>();

	public Vacina() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public int getCarencia() {
		return this.carencia;
	}

	public void setCarencia(int carencia) {
		this.carencia = carencia;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean isObrigatoria() {
		return obrigatoria;
	}

	public void setObrigatoria(boolean obrigatoria) {
		this.obrigatoria = obrigatoria;
	}


	public List<VacinaEstudante> getVacinaEstudantes() {
		return this.vacinaEstudantes;
	}

	public void setVacinaEstudantes(List<VacinaEstudante> vacinaEstudantes) {
		this.vacinaEstudantes = vacinaEstudantes;
	}

	public VacinaEstudante addVacinaEstudante(VacinaEstudante vacinaEstudante) {
		getVacinaEstudantes().add(vacinaEstudante);
		vacinaEstudante.setVacina(this);

		return vacinaEstudante;
	}

	public VacinaEstudante removeVacinaEstudante(VacinaEstudante vacinaEstudante) {
		getVacinaEstudantes().remove(vacinaEstudante);
		vacinaEstudante.setVacina(null);

		return vacinaEstudante;
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
		Vacina other = (Vacina) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
}