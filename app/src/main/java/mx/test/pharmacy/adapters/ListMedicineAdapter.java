package mx.test.pharmacy.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import mx.test.pharmacy.R;
import mx.test.pharmacy.models.ListElementMedicine;
import mx.test.pharmacy.models.Medicament;
import mx.test.pharmacy.roomData.AppDatabase;
import mx.test.pharmacy.roomData.entities.Medicines;
import mx.test.pharmacy.util.ComunMethod;

public class ListMedicineAdapter extends RecyclerView.Adapter<ListMedicineAdapter.ViewHolder> {

    private List<Medicament> mData;
    private List<ListElementMedicine> mDataOriginal;
    private LayoutInflater mInflater;
    private Context context;
    private ComunMethod comunMethod = new ComunMethod();

    public ListMedicineAdapter(List<Medicament> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListMedicineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_medicines, null);
        return new ListMedicineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListMedicineAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Medicament> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iconImage, iconCart;
        TextView name, cost;

        ViewHolder(View itemView) {
            super(itemView);
            iconImage = itemView.findViewById(R.id.icImageView);
            name = itemView.findViewById(R.id.txtName);
            cost = itemView.findViewById(R.id.txtCost);
            iconCart = itemView.findViewById(R.id.imgCart);

        }

        void bindData(final Medicament item) {
            iconImage.setImageBitmap(comunMethod.getDecodedB64(item.getImagen()));
            name.setText(item.getNombre());
            cost.setText(item.getComposicion());

            iconCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new SaveData().execute(item);

                }
            });
        }
    }


    public class SaveData extends AsyncTask<Medicament, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Medicament... medicaments) {

            try {
                Medicines medicine = new Medicines();

                medicine.setIdMedicament(medicaments[0].getIdmedicamento());
                medicine.setName(medicaments[0].getNombre());
                medicine.setCategory(medicaments[0].getCategoria());
                medicine.setActiveIngredient(medicaments[0].getIngredienteactivo());
                medicine.setPharmaceuticalForm(medicaments[0].getFormafarmaceutica());
                medicine.setCost(medicaments[0].getPrecio());
                medicine.setGrammage(medicaments[0].getComposicion());
                medicine.setPresentation(medicaments[0].getPresentacion());
                medicine.setIdPharmacy(medicaments[0].getIdfarmacia());

                medicine.setImgMedicine(medicaments[0].getImagen());

                AppDatabase.getInstance(context).medicinesDao().insert(medicine);

                return true;
            } catch (Exception e) {
                Log.e("ObverseIdentifyFragment", "Error al almacenar reporte", e);
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean ok) {
            Toast.makeText(context, ok ? "Producto Agregado" : "Ocurrio un error al intentar almacenar los datos", Toast.LENGTH_LONG).show();
        }

    }
}
