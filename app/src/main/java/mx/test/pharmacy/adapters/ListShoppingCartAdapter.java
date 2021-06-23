package mx.test.pharmacy.adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import mx.test.pharmacy.R;
import mx.test.pharmacy.models.ListElementMedicine;
import mx.test.pharmacy.roomData.AppDatabase;
import mx.test.pharmacy.roomData.entities.Medicines;
import mx.test.pharmacy.util.ComunMethod;

public class ListShoppingCartAdapter extends RecyclerView.Adapter<ListShoppingCartAdapter.ViewHolder> {

    private List<Medicines> mData;
    private LayoutInflater mInflater;
    private Context context;
    private ComunMethod comunMethod = new ComunMethod();

    public ListShoppingCartAdapter(List<Medicines> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ListShoppingCartAdapter.ViewHolder onCreateViewHolder(ViewGroup paren, int viewType){
        View view = mInflater.inflate(R.layout.list_medicines_cart, null);
        return new ListShoppingCartAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ListShoppingCartAdapter.ViewHolder holder, final int position) {
        holder.bindData(mData.get(position));
    }

    public void setItems(List<Medicines> items) {
        mData = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView iconImage, iconCart;
        TextView name, cost, ingredient;

        ViewHolder(View itemView){
            super(itemView);
            iconImage = itemView.findViewById(R.id.icImageView);
            name = itemView.findViewById(R.id.txtName);
            ingredient = itemView.findViewById(R.id.txtActiveIngredient);
            cost = itemView.findViewById(R.id.txtCost);
            iconCart = itemView.findViewById(R.id.imgCart);
        }

        void bindData(final Medicines item){
            iconImage.setImageBitmap(comunMethod.getDecodedB64(item.getImgMedicine()));
            name.setText(item.getName());
            ingredient.setText(item.getActiveIngredient());
            cost.setText(item.getGrammage());

            iconCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new DeleteItem().execute(item);
                }
            });
        }
    }

    public class DeleteItem extends AsyncTask<Medicines, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Medicines... medicines) {

            AppDatabase.getInstance(context).medicinesDao().delete(medicines[0]);
            mData.remove(medicines[0]);

            return true;

        }

        @Override
        protected void onPostExecute(Boolean ok) {
            if (ok) {
                notifyDataSetChanged();
            }
        }
    }

}
