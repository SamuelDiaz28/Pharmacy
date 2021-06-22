package mx.test.pharmacy.models;

import java.util.List;

public class ListCompraUsuario {

    public String farmacia;
    public String telefono;
    public String direccion;
    public String subtotal;
    public String total;
    public String distancia;
    public List<ListMedicamentoShow>listMedicamentoShows;

    public ListCompraUsuario(String farmacia, String telefono, String direccion, String subtotal, String total, String distancia, List<ListMedicamentoShow> listMedicamentoShows) {
        this.farmacia = farmacia;
        this.telefono = telefono;
        this.direccion = direccion;
        this.subtotal = subtotal;
        this.total = total;
        this.distancia = distancia;
        this.listMedicamentoShows = listMedicamentoShows;
    }

    public String getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(String farmacia) {
        this.farmacia = farmacia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public List<ListMedicamentoShow> getListMedicamentoShows() {
        return listMedicamentoShows;
    }

    public void setListMedicamentoShows(List<ListMedicamentoShow> listMedicamentoShows) {
        this.listMedicamentoShows = listMedicamentoShows;
    }
}
