package mx.test.pharmacy.fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mx.test.pharmacy.R;
import mx.test.pharmacy.adapters.ListMedicineAdapter;
import mx.test.pharmacy.adapters.ListShoppingCartAdapter;
import mx.test.pharmacy.models.ListElementMedicine;
import mx.test.pharmacy.models.Medicament;
import mx.test.pharmacy.roomData.AppDatabase;
import mx.test.pharmacy.roomData.entities.Medicines;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ShoppingCartFragment extends Fragment {

    private List<ListElementMedicine> elementMedicines;
    private SearchView searchView = null;
    private ListShoppingCartAdapter listShoppingCartAdapter;
    private RecyclerView recyclerView;


    public ShoppingCartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_shopping_cart, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        new GetData().execute();

        return root;
    }

    private void showList() {
        listShoppingCartAdapter = new ListShoppingCartAdapter(elementMedicines, getContext());
        LinearLayoutManager li = new LinearLayoutManager(getActivity());
        li.setOrientation(LinearLayoutManager.VERTICAL);


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(li);
        recyclerView.setAdapter(listShoppingCartAdapter);
    }

    public class GetData extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                List<Medicines> medicinesList = AppDatabase.getInstance(getActivity().getApplicationContext()).medicinesDao().get();

                if (medicinesList.size() != 0){
                    for (Medicines medicine : medicinesList) {
                        elementMedicines = new ArrayList<>();
                        elementMedicines.add(new ListElementMedicine(medicine.getName(), medicine.getCost(), medicine.getGrammage(), medicine.getImgMedicine()));
                    }
                    return true;
                } else
                    return false;

            } catch (Exception e) {

                Log.e("ObverseIdentifyFragment", "Error al almacenar reporte", e);
                return false;

            }
        }

        @Override
        protected void onPostExecute(Boolean ok) {
            //Toast.makeText(getActivity(), ok ? "Datos almacenados" : "Ocurrio un error al intentar almacenar los datos", Toast.LENGTH_LONG).show();
            if (ok) {
                showList();
            }

        }
    }
}