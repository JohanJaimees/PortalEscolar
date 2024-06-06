package com.school.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.school.security.models.Usuario;

import org.springframework.data.mongodb.core.mapping.DBRef;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Document(collection = "empleados")
public class Empleado implements Serializable {

    @Id
    private String id; // Clave primaria de tipo String
    
    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 2, max = 40, message = "debe ser entre 2 y 40 caracteres.")
    private String nombres;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 2, max = 20, message = "debe ser entre 2 y 20 caracteres.")
    private String apellidoPaterno;

    @NotEmpty(message = "no puede estar vacío")
    @Size(min = 2, max = 20, message = "debe ser entre 2 y 20 caracteres.")
    private String apellidoMaterno;

    @NotNull(message = "no puede estar vacío.")
    private String fechaNacimiento;

    @NotEmpty(message = "no puede estar vacío.")
    @Size(min = 8, max = 20, message = "debe ser de 8 caracteres.")
    private String dni;

    private String celular;

    private String domicilio;

    private String sexo;

    private String correo;

    @DBRef
    private Usuario usuario; // Referencia a otro documento
    
    @DBRef
    private List<Clase> clases; // Añadir la lista de clases

    // Getters y Setters...

    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Especialidad> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(Set<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@DBRef
    private Set<Especialidad> especialidades = new HashSet<>(); // Referencias múltiples

    private static final long serialVersionUID = 1L; // Para Serializable
    
    // Getters y Setters...
}
