package mx.test.pharmacy.models;

public class ListFarmaciaPrecDist {

    public String farmacia;
    public String total;
    public String distancia;


    public ListFarmaciaPrecDist(String farmacia, String total, String distancia) {
        this.farmacia = farmacia;
        this.total = total;
        this.distancia = distancia;
    }

    public String getFarmacia() {
        return farmacia;
    }

    public void setFarmacia(String farmacia) {
        this.farmacia = farmacia;
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
}
