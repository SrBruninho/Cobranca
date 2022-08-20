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
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;


@Entity
public class Titulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codTitulo;
	
	@NotEmpty(message = "Descrição obrigatória")
	@Size(max=60, message = "A descrição não ode conter mais de 60 caracteres")
	private String descricao;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message="Data de vencimento é obrigatória")
	private Date dataVencimento;
	
	@NumberFormat(pattern="#,##0.00")
	@NotNull( message = "Valor é obrigatória" )
	@DecimalMin( value = "0.01", message="Valor não pode ser menor que 0,01")
	private BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	private StatusTitulo status;	
	
	public Titulo(Long codTitulo, String descricao,Date dataVencimento,
			BigDecimal valor, StatusTitulo status) {
		
		this.codTitulo = codTitulo;
		this.descricao = descricao;
		this.dataVencimento = dataVencimento;
		this.valor = valor;
		this.status = status;
	}
	
	public Titulo() {		
	}
	
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
	
	public boolean isPendente() {
		return StatusTitulo.PENDENTE.equals( this.status );
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