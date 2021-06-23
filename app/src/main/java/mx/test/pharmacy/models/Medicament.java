package mx.test.pharmacy.models;

public class Medicament {
    public int idmedicamento;
    public String nombre;
    public String categoria;
    public String ingredienteactivo;
    public String formafarmaceutica;
    public String composicion;
    public String presentacion;
    public String precio;
    public int idfarmacia;
    public String imagen;

    public Medicament(){};

    public Medicament(int idmedicamento, String nombre, String categoria, String ingredienteactivo, String formafarmaceutica, String composicion, String presentacion, String precio, int idfarmacia, String imagen) {
        this.idmedicamento = idmedicamento;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ingredienteactivo = ingredienteactivo;
        this.formafarmaceutica = formafarmaceutica;
        this.composicion = composicion;
        this.presentacion = presentacion;
        this.precio = precio;
        this.idfarmacia = idfarmacia;
        this.imagen = imagen;
    }

    public int getIdmedicamento() {
        return idmedicamento;
    }

    public void setIdmedicamento(int idmedicamento) {
        this.idmedicamento = idmedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIngredienteactivo() {
        return ingredienteactivo;
    }

    public void setIngredienteactivo(String ingredienteactivo) {
        this.ingredienteactivo = ingredienteactivo;
    }

    public String getFormafarmaceutica() {
        return formafarmaceutica;
    }

    public void setFormafarmaceutica(String formafarmaceutica) {
        this.formafarmaceutica = formafarmaceutica;
    }

    public String getComposicion() {
        return composicion;
    }

    public void setComposicion(String composicion) {
        this.composicion = composicion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getIdfarmacia() {
        return idfarmacia;
    }

    public void setIdfarmacia(int idfarmacia) {
        this.idfarmacia = idfarmacia;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
