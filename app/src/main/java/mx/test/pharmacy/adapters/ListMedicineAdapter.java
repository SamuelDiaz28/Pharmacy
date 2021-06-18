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
import mx.test.pharmacy.roomData.AppDatabase;
import mx.test.pharmacy.roomData.entities.Medicines;
import mx.test.pharmacy.util.ComunMethod;

public class ListMedicineAdapter extends RecyclerView.Adapter<ListMedicineAdapter.ViewHolder> {

    private List<ListElementMedicine> mData;
    private List<ListElementMedicine> mDataOriginal;
    private LayoutInflater mInflater;
    private Context context;
    private ComunMethod comunMethod = new ComunMethod();

    public ListMedicineAdapter(List<ListElementMedicine> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

        mDataOriginal = new ArrayList<>();
        mDataOriginal.addAll(mData);
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

    public void setItems(List<ListElementMedicine> items) {
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

        void bindData(final ListElementMedicine item) {
            iconImage.setImageBitmap(comunMethod.getDecodedB64(item.getResourceImg()));
            name.setText(item.getName());
            cost.setText(item.getCost());

            iconCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(">>Adapter<<", "Presiono el boton " + item.getName());
                    new SaveData().execute(item);

                }
            });
        }
    }


    public class SaveData extends AsyncTask<ListElementMedicine, Void, Boolean> {

        @Override
        protected Boolean doInBackground(ListElementMedicine... listElementMedicines) {

            try {
                Medicines medicine = new Medicines();

                medicine.setName(listElementMedicines[0].getName());
                medicine.setCost(listElementMedicines[0].getCost());
                medicine.setGrammage(listElementMedicines[0].getGrammage());

                medicine.setImgMedicine(listElementMedicines[0].getResourceImg());

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
