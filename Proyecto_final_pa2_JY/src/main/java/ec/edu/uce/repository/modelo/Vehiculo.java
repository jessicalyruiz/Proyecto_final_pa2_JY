package ec.edu.uce.repository.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {

	@Id
	@Column(name = "vehi_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_vehiculo")
	@SequenceGenerator(name = "seq_vehiculo", sequenceName = "seq_vehiculo", allocationSize = 1)
	private Integer id;
	
	@Column(name = "vehi_placa")
	private String placa;
	
	@Column(name = "vehi_marca")
	private String marca;
	
	@Column(name = "vehi_modelo")
	private String modelo;
	
	@Column(name = "vehi_disponible")
	private String disponible;
	
	@Column(name = "vehi_anio_fabricacion")
	private String anioFabricacion;
	
	@Column(name = "vehi_valor_dia")
	private BigDecimal valorPorDia;
	
	@Column(name = "vehi_fecha_disponibilidad")
	private LocalDateTime fechaDisponibilidad;
	
	@Column(name = "vehi_paisfabricacion")
	private String paisFabricacion;
	
	@Column(name = "vehi_avaluo")
	private BigDecimal avaluo;
	
	@Column(name = "vehi_cilindraje")
	private Float cilindraje;
	
	@OneToOne(mappedBy = "vehiculo", cascade = CascadeType.ALL)
	private Reserva reserva;

	//GET Y SET
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getDisponible() {
		return disponible;
	}

	public void setDisponible(String disponible) {
		this.disponible = disponible;
	}

	public String getAnioFabricacion() {
		return anioFabricacion;
	}

	public void setAnioFabricacion(String anioFabricacion) {
		this.anioFabricacion = anioFabricacion;
	}

	public BigDecimal getValorPorDia() {
		return valorPorDia;
	}

	public void setValorPorDia(BigDecimal valorPorDia) {
		this.valorPorDia = valorPorDia;
	}

	public LocalDateTime getFechaDisponibilidad() {
		return fechaDisponibilidad;
	}

	public void setFechaDisponibilidad(LocalDateTime fechaDisponibilidad) {
		this.fechaDisponibilidad = fechaDisponibilidad;
	}

	public String getPaisFabricacion() {
		return paisFabricacion;
	}

	public void setPaisFabricacion(String paisFabricacion) {
		this.paisFabricacion = paisFabricacion;
	}

	public BigDecimal getAvaluo() {
		return avaluo;
	}

	public void setAvaluo(BigDecimal avaluo) {
		this.avaluo = avaluo;
	}

	public Float getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(Float cilindraje) {
		this.cilindraje = cilindraje;
	}

	public Reserva getReserva() {
		return reserva;
	}

	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
	}

	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", disponible="
				+ disponible + ", anioFabricacion=" + anioFabricacion + ", valorPorDia=" + valorPorDia
				+ ", fechaDisponibilidad=" + fechaDisponibilidad + ", paisFabricacion=" + paisFabricacion + ", avaluo="
				+ avaluo + ", cilindraje=" + cilindraje + "]";
	}
	
	
}