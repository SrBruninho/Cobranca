package com.gora.cobranca.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Titulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codTitulo;
	
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;	
	
	public Long getCodTitulo() {
		return codTitulo;
	}
	
	public void setCodTitulo(Long codTitulo) {
		this.codTitulo = codTitulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Date getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	public StatusTitulo getStatus() {
		return status;
	}
	public void setStatus(StatusTitulo status) {
		this.status = status;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(codTitulo, dataVencimento, descricao, status, valor);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Titulo other = (Titulo) obj;
		return Objects.equals(codTitulo, other.codTitulo) && Objects.equals(dataVencimento, other.dataVencimento)
				&& Objects.equals(descricao, other.descricao) && status == other.status
				&& Objects.equals(valor, other.valor);
	}
	@Override
	public String toString() {
		return "Titulo [codTitulo=" + codTitulo + ", descricao=" + descricao + ", dataVencimento=" + dataVencimento
				+ ", valor=" + valor + ", status=" + status + "]";
	}
	
}