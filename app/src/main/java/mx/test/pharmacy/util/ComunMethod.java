package mx.test.pharmacy.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import mx.test.pharmacy.R;
import mx.test.pharmacy.adapters.ListFarmaciaPrecDistAdapter;
import mx.test.pharmacy.adapters.ListProductosCompraAdapter;
import mx.test.pharmacy.models.ListCompraUsuario;
import mx.test.pharmacy.models.ListFarmaciaPrecDist;
import mx.test.pharmacy.models.ListMedicamentoShow;

public class ComunMethod{

    //RadioButton
    private static RadioButton r1,r2;
    String slectedRadio="Envio a domicilio";
    View vista;
    FragmentManager fragment;
    private View.OnClickListener listener;
    public ComunMethod (){

    }

    public static void showSuccessDialogShow(String id, List<ListCompraUsuario> listCompraUsuarios, Context activity){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(activity).inflate(R.layout.info_layout_precompra,null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_dialog);

        List<ListMedicamentoShow> listMedicamentoShows = new ArrayList();

        switch (id){
            case "Farmacia Ahora":

                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia Ahora"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.titulo)).setText(comUsuario.farmacia);
                        ((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);

                        double subtotal = 0;
                        double total = 0;

                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);
                        }
                        ((TextView) view.findViewById(R.id.total)).setText(String.valueOf(total));


                        //total = subtotal;
                        listMedicamentoShows = comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);




                        //((TextView) view.findViewById(R.id.subtotal)).setText(String.valueOf(subtotal));

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

            case "Farmacia Esp":
                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia Esp"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.titulo)).setText(comUsuario.farmacia);
                        ((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);
                        double total = 0;

                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);

                        }
                        ((TextView) view.findViewById(R.id.total)).setText(String.valueOf(total));

                        listMedicamentoShows = comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

            case "Farmacia San-Pa":
                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia San-Pa"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.titulo)).setText(comUsuario.farmacia);
                        ((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);
                        //((TextView) view.findViewById(R.id.subtotal)).setText(comUsuario.subtotal);
                        double total = 0;

                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);

                        }
                        ((TextView) view.findViewById(R.id.total)).setText(String.valueOf(total));

                        listMedicamentoShows= comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

            case "Farmacia Walwart":
                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia Walwart"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.titulo)).setText(comUsuario.farmacia);
                        ((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);
                        //((TextView) view.findViewById(R.id.subtotal)).setText(comUsuario.subtotal);
                        double total = 0;

                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);

                        }
                        ((TextView) view.findViewById(R.id.total)).setText(String.valueOf(total));

                        listMedicamentoShows = comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

            case "Farmacia Gua":
                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia Gua"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.titulo)).setText(comUsuario.farmacia);
                        ((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);
                        //((TextView) view.findViewById(R.id.subtotal)).setText(comUsuario.subtotal);
                        double total = 0;

                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);

                        }
                        ((TextView) view.findViewById(R.id.total)).setText(String.valueOf(total));

                        listMedicamentoShows = comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

        }

        AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.botonComprar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                showEnvioDialog((Activity) activity);
            }
        });

        view.findViewById(R.id.imgIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }


        alertDialog.show();

    }

    public static void showDialogPharmacy(String id, List<ListCompraUsuario> listCompraUsuarios, Context activity){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(activity).inflate(R.layout.layout_dialog_pharmacy,null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewDialog);

        List<ListMedicamentoShow> listMedicamentoShows = new ArrayList();

        switch (id){
            case "Farmacia Ahora":

                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia Ahora"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.txtTitle)).setText(comUsuario.farmacia);
                        /*((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);*/

                        DecimalFormat dc = new DecimalFormat(".00");
                        double subtotal = 0;
                        double total = 0;




                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);
                        }
                        ((TextView) view.findViewById(R.id.txtTotal)).setText("$ " + dc.format(total));


                        //total = subtotal;

                        listMedicamentoShows = comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);


                        //((TextView) view.findViewById(R.id.subtotal)).setText(String.valueOf(subtotal));

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

            case "Farmacia Esp":
                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia Esp"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.txtTitle)).setText(comUsuario.farmacia);
                        /*((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);*/
                        DecimalFormat dc = new DecimalFormat(".00");
                        double total = 0;

                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);

                        }
                        ((TextView) view.findViewById(R.id.txtTotal)).setText("$ " + dc.format(total));

                        listMedicamentoShows = comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);


                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

            case "Farmacia San-Pa":
                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia San-Pa"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.txtTitle)).setText(comUsuario.farmacia);
                        /*((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);*/
                        //((TextView) view.findViewById(R.id.subtotal)).setText(comUsuario.subtotal);
                        DecimalFormat dc = new DecimalFormat(".00");

                        double total = 0;

                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);

                        }
                        ((TextView) view.findViewById(R.id.txtTotal)).setText("$ " + dc.format(total));

                        listMedicamentoShows= comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

            case "Farmacia Walwart":
                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia Walwart"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.txtTitle)).setText(comUsuario.farmacia);
                        /*((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);*/
                        //((TextView) view.findViewById(R.id.subtotal)).setText(comUsuario.subtotal);
                        DecimalFormat dc = new DecimalFormat(".00");
                        double total = 0;

                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);

                        }
                        ((TextView) view.findViewById(R.id.txtTotal)).setText("$ " +  dc.format(total));

                        listMedicamentoShows = comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

            case "Farmacia Gua":
                for (ListCompraUsuario comUsuario : listCompraUsuarios){

                    if (comUsuario.farmacia=="Farmacia Gua"){

                        builder.setView(view);
                        ((TextView) view.findViewById(R.id.txtTitle)).setText(comUsuario.farmacia);
                        /*((TextView) view.findViewById(R.id.telefono)).setText(comUsuario.telefono);
                        ((TextView) view.findViewById(R.id.direccion)).setText(comUsuario.direccion);*/
                        //((TextView) view.findViewById(R.id.subtotal)).setText(comUsuario.subtotal);
                        DecimalFormat dc = new DecimalFormat(".00");
                        double total = 0;

                        for (ListMedicamentoShow listMedicamentoShow : comUsuario.listMedicamentoShows){
                            total += Double.parseDouble(listMedicamentoShow.precio);

                        }
                        ((TextView) view.findViewById(R.id.txtTotal)).setText("$ " + dc.format(total));

                        listMedicamentoShows = comUsuario.listMedicamentoShows;
                        ListProductosCompraAdapter listProductosCompraAdapter = new ListProductosCompraAdapter(listMedicamentoShows,activity);
                        LinearLayoutManager li = new LinearLayoutManager(activity.getApplicationContext());
                        li.setOrientation(LinearLayoutManager.VERTICAL);

                        recyclerView.setHasFixedSize(true);
                        recyclerView.setLayoutManager(li);
                        recyclerView.setAdapter(listProductosCompraAdapter);


                    }
                }
                break;

        }

        AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.btnAction).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                showEnvioDialog((Activity) activity);
            }
        });

        view.findViewById(R.id.imgIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }


        alertDialog.show();

    }

    public static void showSuccessDialog(String titulo, String telefono, String precio, String descripcion, Context activity){

        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(activity).inflate(
                R.layout.info_layout_precompra,null
        );
        //Button positiveDialog = view.findViewById(R.id.botonComprar);

        builder.setView(view);
        ((TextView) view.findViewById(R.id.titulo)).setText(titulo);
        ((TextView) view.findViewById(R.id.telefono)).setText(telefono);
        ((TextView) view.findViewById(R.id.precio)).setText(precio);
        ((TextView) view.findViewById(R.id.descripcion)).setText(descripcion);

        AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.botonComprar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                showEnvioDialog((Activity) activity);
            }
        });

        view.findViewById(R.id.imgIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }


        alertDialog.show();

    }

    public static void showEnvioDialog(Activity activity){
        //final String[] opciones={"Envio a domicilio","Recoger en tienda"};
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(activity).inflate(
                R.layout.info_envio,null
        );
        builder.setView(view);

        r1= view.findViewById(R.id.radio1);
        r2= view.findViewById(R.id.radio2);
        ((TextView) view.findViewById(R.id.titulo)).setText("DELIVERY");

        AlertDialog alertDialog = builder.create();


        view.findViewById(R.id.botonContinuar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                showSuccessDialogPaypal((Activity) activity);
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }
    private void validar(){
        String cad= "Seleccionado: \n";
        if(r1.isChecked() == true){
            cad+="Opcion\n";
        }
        if(r2.isChecked()){
            cad+="Opcion\n";
        }
    }
    public static void showSuccessDialogPaypal(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(activity).inflate(
                R.layout.paypal_dialog,null
        );
        builder.setView(view);
        //((TextView) view.findViewById(R.id.preciopagar)).setText("100");
        //((TextView) view.findViewById(R.id.telefono)).setText(telefono);
        //((TextView) view.findViewById(R.id.precio)).setText(precio);
        //((TextView) view.findViewById(R.id.descripcion)).setText(descripcion);
        AlertDialog alertDialog = builder.create();
        /*view.findViewById(R.id.btnAcceptDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });*/

        view.findViewById(R.id.botonComprar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                showSuccessDialogPaypalPago((Activity) activity);
            }
        });
        view.findViewById(R.id.botoncCancelar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        view.findViewById(R.id.imgIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }
    public static void showSuccessDialogPaypalPago(Activity activity){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity, R.style.AlertDialogTheme);
        View view = LayoutInflater.from(activity).inflate(
                R.layout.paypal_compra_confirmada,null
        );
        builder.setView(view);
        //((TextView) view.findViewById(R.id.preciopagar)).setText("100");
        //((TextView) view.findViewById(R.id.telefono)).setText(telefono);
        //((TextView) view.findViewById(R.id.precio)).setText(precio);
        //((TextView) view.findViewById(R.id.descripcion)).setText(descripcion);
        AlertDialog alertDialog = builder.create();
        /*view.findViewById(R.id.btnAcceptDialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });*/

        view.findViewById(R.id.terminar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow() != null) {
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        alertDialog.show();

    }

    public String getEncoded64(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteFormat = stream.toByteArray();
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }

    public Bitmap getDecodedB64(String encodedImage){
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public static Bitmap getDecodedB642(String encodedImage){
        byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        return decodedByte;
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

}
