package com.init.APIRestIsilFitnessSAC.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Entrenamientos")
public class Entrenamiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_entrenamiento")
    private int idEntrenamiento;

    private String tipo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fecha;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora_inicio;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime hora_fin;
    private Integer max_participantes;

    @ManyToOne
	@JoinColumn(name="id_modalidad")
	private Modalidad modalidad;

    @ManyToOne
    @JoinColumn(name = "id_trainer")
    private Trainer trainer;

	public int getIdEntrenamiento() {
		return idEntrenamiento;
	}

	public void setIdEntrenamiento(int idEntrenamiento) {
		this.idEntrenamiento = idEntrenamiento;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(LocalTime hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public LocalTime getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(LocalTime hora_fin) {
		this.hora_fin = hora_fin;
	}

	public Integer getMax_participantes() {
		return max_participantes;
	}

	public void setMax_participantes(Integer max_participantes) {
		this.max_participantes = max_participantes;
	}

	public Modalidad getModalidad() {
		return modalidad;
	}

	public void setModalidad(Modalidad modalidad) {
		this.modalidad = modalidad;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
}
