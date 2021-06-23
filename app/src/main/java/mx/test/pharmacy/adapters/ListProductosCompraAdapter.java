package mx.test.pharmacy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.test.pharmacy.R;
import mx.test.pharmacy.models.ListFarmaciaPrecDist;
import mx.test.pharmacy.models.ListMedicamentoShow;

import mx.test.pharmacy.util.ComunMethod;

public class ListProductosCompraAdapter extends RecyclerView.Adapter<ListProductosCompraAdapter.ViewHolder> {

    private List<ListMedicamentoShow> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListProductosCompraAdapter(List<ListMedicamentoShow> itemList, Context context) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() { return mData.size(); }

    @Override
    public ListProductosCompraAdapter.ViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_medicines_buy,null);
        return new ListProductosCompraAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListProductosCompraAdapter.ViewHolder holder,final int position) {
        holder.binData(mData.get(position));
    }

    public void setItems(List<ListMedicamentoShow> items){mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nomMed, ingrdntActv, composicion, formaFarma, categoria,presentacion,precio;
        ImageView img;

        ViewHolder(View itemView) {
            super(itemView);

            nomMed = itemView.findViewById(R.id.txtNomMed);
            ingrdntActv = itemView.findViewById(R.id.txtingrdntActv);
            composicion = itemView.findViewById(R.id.txtComposicion);
            formaFarma = itemView.findViewById(R.id.txtformaFarmaceutica);
            categoria = itemView.findViewById(R.id.txtCategoria);
            presentacion = itemView.findViewById(R.id.txtPresentacion);
            precio = itemView.findViewById(R.id.txtPrecio);
            img = itemView.findViewById(R.id.icImageView_buy);

        }

        void binData(final ListMedicamentoShow item){
            nomMed.setText(item.getNombreMedicamento());
            ingrdntActv.setText(item.getIngredienteActivo());
            composicion.setText(item.getComposicion());
            formaFarma.setText(item.getFormaFarmaceutica());
            categoria.setText(item.getCategoria());
            presentacion.setText(item.getPresentacion());
            precio.setText("$"+item.getPrecio());
            img.setImageBitmap(ComunMethod.getDecodedB642(item.img));

        }
    }
}
