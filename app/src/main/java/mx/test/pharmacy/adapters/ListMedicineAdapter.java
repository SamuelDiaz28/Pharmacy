package mx.test.pharmacy.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import mx.test.pharmacy.R;
import mx.test.pharmacy.models.ListElementMedicine;

public class ListMedicineAdapter extends RecyclerView.Adapter<ListMedicineAdapter.ViewHolder> {

    private List<ListElementMedicine> mData;
    private List<ListElementMedicine> mDataOriginal;
    private LayoutInflater mInflater;
    private Context context;

    public ListMedicineAdapter(List<ListElementMedicine> itemList, Context context){
         this.mInflater = LayoutInflater.from(context);
         this.context = context;
         this.mData = itemList;

         mDataOriginal = new ArrayList<>();
         mDataOriginal.addAll(mData);
    }

    @Override
    public int getItemCount() {return mData.size();}

    @Override
    public ListMedicineAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.list_medicines, null);
        return new ListMedicineAdapter.ViewHolder(view);
    }

    /*public void filter(String txtSearch){
        int longitud = txtSearch.length();
        if (longitud == 0){
            mData.clear();
            mData.addAll(mDataOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<ListElementMedicine> collection = mData.stream()
                        .filter(listElementMedicine -> listElementMedicine.getName().toLowerCase().contains(txtSearch.toLowerCase()))
                        .collect(Collectors.toList());
                mData.clear();
                mData.addAll(collection);
            } else {
                for (ListElementMedicine m : mDataOriginal){
                    if (m.getName().toLowerCase().contains(txtSearch.toLowerCase())){
                        mData.add(m);

                    }
                }
            }
        }
        notifyDataSetChanged();
    }*/

    @Override
    public void onBindViewHolder(final ListMedicineAdapter.ViewHolder holder, final int position){
        holder.bindData(mData.get(position));
    }

    public void setItems(List<ListElementMedicine> items){
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iconImage;
        TextView name, cost;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.icImageView);
            name = itemView.findViewById(R.id.txtName);
            cost = itemView.findViewById(R.id.txtCost);
        }

        void bindData(final ListElementMedicine item){
            iconImage.setColorFilter(Color.parseColor(item.getResourceImg()), PorterDuff.Mode.SRC_IN);
            name.setText(item.getName());
            cost.setText(item.getCost());
        }
    }
}
