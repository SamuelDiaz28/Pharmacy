package mx.test.pharmacy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import mx.test.pharmacy.R;
import mx.test.pharmacy.models.ListFarmaciaPrecDist;

public class ListFarmaciaPrecDistAdapter extends RecyclerView.Adapter<ListFarmaciaPrecDistAdapter.ViewHolder> {

    private List<ListFarmaciaPrecDist> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListFarmaciaPrecDistAdapter(List<ListFarmaciaPrecDist> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListFarmaciaPrecDistAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_medicines_prev,null);
        return new ListFarmaciaPrecDistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListFarmaciaPrecDistAdapter.ViewHolder holder, final int position) {
        holder.binData(mData.get(position));
    }



    public void setItems(List<ListFarmaciaPrecDist> items){mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView farmacia, total, distancia;

        ViewHolder(View itemView) {
            super(itemView);

            farmacia = itemView.findViewById(R.id.txtName1);
            total = itemView.findViewById(R.id.txtCost);
            distancia = itemView.findViewById(R.id.txtKlm);
        }

        void binData(final ListFarmaciaPrecDist item){
            farmacia.setText(item.getFarmacia());
            total.setText("$"+item.getTotal());
            distancia.setText(item.getDistancia());
        }
    }

}
