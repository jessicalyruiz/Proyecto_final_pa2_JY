package ec.edu.uce.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reserva")
public class Reserva {

	@Id
	@Column(name = "rese_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_reserva")
	@SequenceGenerator(name = "seq_reserva", sequenceName = "seq_reserva", allocationSize = 1)
	private Integer id;
	
	@Column(name = "rese_fecha_inicio", columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime fechaInicio;
	
	@Column(name = "rese_fecha_fin", columnDefinition = "TIMESTAMP")
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	private LocalDateTime fechaFin;
	
	@Column(name = "rese_valor_subtotal")
	private BigDecimal valorSubtotal;
	
	@Column(name = "rese_valor_iva")
	private BigDecimal valorIVA;
	
	@Column(name = "rese_valor_total_pagar")
	private BigDecimal valorTotalPagar;
	
	@Column(name = "rese_numero")
	private String numero;
	
	@Column(name = "rese_estado")
	private String estado;
	
	@ManyToOne
	@JoinColumn(name="rese_fk_cliente")
	private Cliente cliente;
	
	
	@ManyToOne
	@JoinColumn(name="rese_fk_vehiculo")
	private Vehiculo vehiculo;
	
	@OneToOne
	@JoinColumn(name="rese_fk_cobro")
	private Cobro cobro;

	//GET Y SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDateTime fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDateTime getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDateTime fechaFin) {
		this.fechaFin = fechaFin;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Cobro getCobro() {
		return cobro;
	}

	public void setCobro(Cobro cobro) {
		this.cobro = cobro;
	}

	@Override
	public String toString() {
		return "Reserva [id=" + id + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", valorSubtotal="
				+ valorSubtotal + ", valorIVA=" + valorIVA + ", valorTotalPagar=" + valorTotalPagar + ", numero="
				+ numero + ", estado=" + estado + "]";
	}
	
	
}
