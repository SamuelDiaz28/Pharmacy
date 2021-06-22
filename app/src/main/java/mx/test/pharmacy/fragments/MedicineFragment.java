package mx.test.pharmacy.fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import mx.test.pharmacy.R;
import mx.test.pharmacy.adapters.ListMedicineAdapter;
import mx.test.pharmacy.models.ListElementMedicine;
import mx.test.pharmacy.models.Medicament;
import mx.test.pharmacy.request.RetrofitRequest;
import mx.test.pharmacy.request.service.MedicineService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MedicineFragment extends Fragment implements View.OnClickListener {

    private List<ListElementMedicine> elementMedicines;
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;
    private ListMedicineAdapter listMedicineAdapter;
    private RecyclerView recyclerView;

    private ImageView iconSearch;
    private ImageView iconScan;
    private EditText edtSearch;

    private Dialog dialog;

    Fragment currentFragment = new MapOffer1();
    Fragment currentFragment2 = new MapOffer2();


    public MedicineFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_medicine, container, false);

        iconSearch = root.findViewById(R.id.imgSearch);
        iconScan = root.findViewById(R.id.imgScan);
        edtSearch = root.findViewById(R.id.edtSearch);

        iconSearch.setOnClickListener(this);
        iconScan.setOnClickListener(this);

        elementMedicines = new ArrayList<>();
        elementMedicines.add(new ListElementMedicine("PARACETAMOL", "$200.00", "500 mg", "#0a9396"));
        elementMedicines.add(new ListElementMedicine("ASPIRINA", "$100.00", "500 mg", "#bb3e03"));
        elementMedicines.add(new ListElementMedicine("AMBROXOL", "$50.00", "250 mg", "#e9d8a6"));

        listMedicineAdapter = new ListMedicineAdapter(elementMedicines, getContext());
        LinearLayoutManager li = new LinearLayoutManager(getActivity());
        li.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(li);

        recyclerView.setAdapter(listMedicineAdapter);

        // Inflate the layout for this fragment
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //FloatingActionButton floatingActionButton = view.findViewById(R.id.fabAgregarGasto);
        ConstraintLayout offer = (ConstraintLayout)view.findViewById(R.id.ofertas);
        ConstraintLayout offer2 = (ConstraintLayout)view.findViewById(R.id.ofertas2);

        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí el código cuando se hace click
                changeFragment(currentFragment);
            }
        });

        offer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(currentFragment2);
            }
        });

    }
    private void changeFragment(Fragment fragment){
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgSearch:

                if (!edtSearch.getText().toString().trim().isEmpty()){
                    iconSearch.setEnabled(false);
                    edtSearch.setEnabled(false);
                    getListMedicines();
                } else
                    Toast.makeText(getActivity().getApplicationContext(), "¡Vacio!", Toast.LENGTH_SHORT).show();

                break;

            case R.id.imgScan:
                IntentIntegrator integrator = new IntentIntegrator(getActivity());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                integrator.setPrompt("Lector - CDP");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(true);
                integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(getActivity().getApplicationContext(), "Escaneo cancelado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity().getApplicationContext(), result.getContents(), Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void getListMedicines(){

        MedicineService service = RetrofitRequest.create(MedicineService.class);

        Call<String> response = service.getMedicament(edtSearch.getText().toString().trim());

        response.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code() != 200) {
                    Toast.makeText( getActivity().getApplicationContext() , "Ocurrió un error\n " + response.code() + " " + response.message(), Toast.LENGTH_LONG ).show();
                    return;
                }
                try {

                    JSONArray jresponse = new JSONArray(response.body());


                    final List<Medicament> lista = getListMedicines(jresponse);
                    Log.i("List", "Break Point Here");

                    if (lista.size() <= 0){
                        Toast.makeText(getContext(), "No hay coincidencias", Toast.LENGTH_SHORT ).show();
                        iconSearch.setEnabled(true);
                        edtSearch.setEnabled(true);
                        return;
                    }

                    elementMedicines.clear();

                    for (Medicament m : lista) {
                        elementMedicines.add(new ListElementMedicine(m.getNombre(),  m.getPrecio(), m.getComposicion(), m.getImagen()));
                    }


                    listMedicineAdapter = new ListMedicineAdapter(elementMedicines, getContext());
                    recyclerView.setAdapter(listMedicineAdapter);

                    iconSearch.setEnabled(true);
                    edtSearch.setEnabled(true);

                }catch (JSONException e){
                    Log.i("TAG", e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                iconSearch.setEnabled(true);
                edtSearch.setEnabled(true);
            }
        });
    }

    private List<Medicament> getListMedicines(JSONArray data) throws JSONException {

        List<Medicament> dependientes = new ArrayList<>();

        JSONObject object;
        for(int i = 0, length = data.length(); i < length; i++) {
            object = data.getJSONObject(i);
            Medicament medicament = new Medicament();

            medicament.setIdmedicamento(object.getInt("idmedicamento"));
            medicament.setNombre(object.getString( "nombre" ));
            medicament.setCategoria(object.getString( "categoria" ));
            medicament.setIngredienteactivo(object.getString("ingredienteactivo"));
            medicament.setFormafarmaceutica(object.getString("formafarmaceutica"));
            medicament.setComposicion(object.getString("composicion"));
            medicament.setPresentacion(object.getString("presentacion"));
            medicament.setPrecio(object.getString("precio"));
            medicament.setIdfarmacia(object.getInt("idfarmacia"));
            medicament.setImagen(object.getString("imagen"));

            dependientes.add( medicament );
        }

        return dependientes;
    }

}