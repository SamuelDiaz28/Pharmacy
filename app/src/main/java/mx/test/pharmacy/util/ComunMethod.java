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
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import java.io.ByteArrayOutputStream;

import mx.test.pharmacy.R;

public class ComunMethod{

    //RadioButton
    private static RadioButton r1,r2;
    String slectedRadio="Envio a domicilio";
    View vista;
    FragmentManager fragment;
    private View.OnClickListener listener;
    public ComunMethod (){

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

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

}
