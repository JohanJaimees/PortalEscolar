package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.school.security.models.Usuario;

import org.springframework.data.mongodb.core.index.Indexed;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "estudiantes")
public class Estudiante implements Serializable {

	 @Id
	 private String id;

	 @Indexed(unique = true)
	 private String username; // Nueva propiedad 'username'

	
	 private String password; // Nueva propiedad 'password'

    @NotEmpty(message = "no puede estar vacío.")
    @Size(min = 2, max =  40, message = "tiene que ser entre 2 y 40 caracteres.")
    private String nombres;

    @NotEmpty(message = "no puede estar vacío.")
    @Size(min = 2, max = 20, message = "tiene que ser entre 2 y 20 caracteres.")
    private String apellidoPaterno;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 2, max = 20, message = "tiene que ser entre 2 y 20 caracteres.")
    private String apellidoMaterno;

    @NotNull(message = "no puede estar vacío.")
    private String fechaNacimiento;

    @NotEmpty(message = "no puede estar vacío.")
    @Size(min =  8, max =  8, message = "debe tener 8 caracteres.")
    private String dni;

    @NotEmpty(message = "no puede estar vacío.")
    @Size(min =  10, max  = 80, message = "debe tener entre 10 y 80 caracteres.")
    private String domicilio;

    @Size(min = 9, max =  9, message = "debe tener 9 caracteres.")
    @NotEmpty(message = "no puede estar vacío.")
    private String celular;

    private String sexo;

    private String correo;

    @DBRef(lazy = true) // Configuración correcta para MongoDB
    private Aula aulaEstudiante;

    @DBRef(lazy = true)
    private Apoderado apoderado;

    @DBRef(lazy = true)
    private Grado grado;

    @DBRef(lazy = true)
    private Usuario usuario;

    @DBRef(lazy = true)
    private Turno turno;

    @DBRef(lazy = true)
    private Nivel nivel;

    private List<Asistencia> asistencias = new ArrayList<>();
    private List<Nota> notas = new ArrayList<>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Aula getAulaEstudiante() {
		return aulaEstudiante;
	}
	public void setAulaEstudiante(Aula aulaEstudiante) {
		this.aulaEstudiante = aulaEstudiante;
	}
	public Apoderado getApoderado() {
		return apoderado;
	}
	public void setApoderado(Apoderado apoderado) {
		this.apoderado = apoderado;
	}
	public Grado getGrado() {
		return grado;
	}
	public void setGrado(Grado grado) {
		this.grado = grado;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public Nivel getNivel() {
		return nivel;
	}
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}
	public List<Asistencia> getAsistencias() {
		return asistencias;
	}
	public void setAsistencias(List<Asistencia> asistencias) {
		this.asistencias = asistencias;
	}
	public List<Nota> getNotas() {
		return notas;
	}
	public void setNotas(List<Nota> notas) {
		this.notas = notas;
	}
}

