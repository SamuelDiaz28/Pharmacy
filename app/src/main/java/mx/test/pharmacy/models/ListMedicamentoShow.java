package mx.test.pharmacy.models;

public class ListMedicamentoShow {
    public String nombreMedicamento;
    public String categoria;
    public String ingredienteActivo;
    public String formaFarmaceutica;
    public String composicion;
    public String presentacion;
    public String precio;
    public String img;
    public String estatus;

    public ListMedicamentoShow(String nombreMedicamento, String categoria, String ingredienteActivo, String formaFarmaceutica, String composicion, String presentacion, String precio, String img, String estatus) {
        this.nombreMedicamento = nombreMedicamento;
        this.categoria = categoria;
        this.ingredienteActivo = ingredienteActivo;
        this.formaFarmaceutica = formaFarmaceutica;
        this.composicion = composicion;
        this.presentacion = presentacion;
        this.precio = precio;
        this.img = img;
        this.estatus = estatus;
    }

    public String getNombreMedicamento() {
        return nombreMedicamento;
    }

    public void setNombreMedicamento(String nombreMedicamento) {
        this.nombreMedicamento = nombreMedicamento;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIngredienteActivo() {
        return ingredienteActivo;
    }

    public void setIngredienteActivo(String ingredienteActivo) {
        this.ingredienteActivo = ingredienteActivo;
    }

    public String getFormaFarmaceutica() {
        return formaFarmaceutica;
    }

    public void setFormaFarmaceutica(String formaFarmaceutica) {
        this.formaFarmaceutica = formaFarmaceutica;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }
}
