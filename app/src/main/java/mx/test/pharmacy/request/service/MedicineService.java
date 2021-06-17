package mx.test.pharmacy.request.service;

import android.widget.Switch;

import java.util.List;

import mx.test.pharmacy.models.Medicament;
import mx.test.pharmacy.request.APIUrl;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface MedicineService {

    @GET(APIUrl.GET_MEDICINES)
    Call<List<Medicament>> getMedicines(@Path("SearchWord") String searchWord);

    @GET(APIUrl.GET_MEDICINES)
    Call<String> getMedicament(@Path("SearchWord") String searchWord);

}
