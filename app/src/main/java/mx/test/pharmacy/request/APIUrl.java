package mx.test.pharmacy.request;

public class APIUrl {
    public static final String BASE = "http://172.241.131.200:9999/farmacia/";


    public static final String GET_MEDICINES = "medicamento/findByNameDistinct/{SearchWord}";

    public static final String GET_MEDICINES_BARCODE = "medicamento/findByBarCode/{BarCode}";


}
