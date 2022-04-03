package ec.edu.uce.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "cobro")
public class Cobro {

	
	@Id
	@Column(name = "cobr_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cobro")
	@SequenceGenerator(name = "seq_cobro", sequenceName = "seq_cobro", allocationSize = 1)
	private Integer id;
	
	
	@Column(name = "cobr_fecha", columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime fecha;
	
	@Column(name = "cobr_valor_subtotal")
	private BigDecimal valorSubtotal;
	
	@Column(name = "cobr_valor_iva")
	private BigDecimal valorIVA;
	
	@Column(name = "cobr_valor_total_pagar")
	private BigDecimal valorTotalPagar;
	
	
	@OneToOne(mappedBy = "cobro")
	private Reserva reserva;
	
	/*
	@OneToOne
	@JoinColumn(name="cobr_fk_tarjeta")
	private Tarjeta tarjeta;
	*/
	
	@Column(name = "cobr_tarjeta")
	private String tarjeta;
	
	//GET Y SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValorSubtotal() {
		return valorSubtotal;
	}

	public void setValorSubtotal(BigDecimal valorSubtotal) {
		this.valorSubtotal = valorSubtotal;
	}

	public BigDecimal getValorIVA() {
		return valorIVA;
	}

	public void setValorIVA(BigDecimal valorIVA) {
		this.valorIVA = valorIVA;
	}

	public BigDecimal getValorTotalPagar() {
		return valorTotalPagar;
	}

	public void setValorTotalPagar(BigDecimal valorTotalPagar) {
		this.valorTotalPagar = valorTotalPagar;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}
/*
	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	*/
	

	

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	/*
	@Override
	public String toString() {
		return "Cobro [id=" + id + ", fecha=" + fecha + "]";
	}*/

	@Override
	public String toString() {
		return "Cobro [id=" + id + ", fecha=" + fecha + ", valorSubtotal=" + valorSubtotal + ", valorIVA=" + valorIVA
				+ ", valorTotalPagar=" + valorTotalPagar + ", tarjeta=" + tarjeta + "]";
	}

	
	
	
}
