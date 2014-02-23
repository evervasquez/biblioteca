package pe.disenio.biblioteca.model;

public class Libros {
	private String idlibro;
	private String idtipolibro;
	private String resumen;
	private String autor;
	private String editorial;
	private String pais;
	private String ciudad;
	private String Titulo;

	public String getTitulo() {
		return Titulo;
	}

	public void setTitulo(String titulo) {
		Titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getIdlibro() {
		return idlibro;
	}

	public void setIdlibro(String idlibro) {
		this.idlibro = idlibro;
	}

	public String getIdtipolibro() {
		return idtipolibro;
	}

	public void setIdtipolibro(String idtipolibro) {
		this.idtipolibro = idtipolibro;
	}

	public String getResumen() {
		return resumen;
	}

	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
}