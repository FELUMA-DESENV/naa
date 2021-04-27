package com.feluma.naa.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the atendimento database table.
 * 
 */
@Entity
@Table(name="atendimento")
@NamedQuery(name="Atendimento.findAll", query="SELECT a FROM Atendimento a")
public class Atendimento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cod_ate")
	private Long codigo;

	@Column(name="con_ate")
	private String conduta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="dat_ate")
	private Date dataAtendimento;

	@Column(name="que_pri_ate")
	private String queixaPrincipal;

	//bi-directional many-to-one association to Estudante
	@ManyToOne
	@JoinColumn(name="cod_est")
	private Estudante estudante;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="cod_usu")
	private Usuario usuario;

	public Atendimento() {
	}

	public Long getCodigo() {
		return this.codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getConduta() {
		return this.conduta;
	}

	public void setConduta(String conduta) {
		this.conduta = conduta;
	}

	public Date getDataAtendimento() {
		return this.dataAtendimento;
	}

	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	public String getQueixaPrincipal() {
		return this.queixaPrincipal;
	}

	public void setQueixaPrincipal(String queixaPrincipal) {
		this.queixaPrincipal = queixaPrincipal;
	}

	public Estudante getEstudante() {
		return this.estudante;
	}

	public void setEstudante(Estudante estudante) {
		this.estudante = estudante;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Atendimento other = (Atendimento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
}