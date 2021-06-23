package mx.test.pharmacy.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mx.test.pharmacy.R;
import mx.test.pharmacy.adapters.ListFarmaciaPrecDistAdapter;
import mx.test.pharmacy.adapters.ListMedicineAdapter;
import mx.test.pharmacy.models.ListCompraUsuario;
import mx.test.pharmacy.models.ListElementMedicine;
import mx.test.pharmacy.models.ListFarmaciaPrecDist;
import mx.test.pharmacy.models.ListMedicamentoShow;
import mx.test.pharmacy.models.Medicament;
import mx.test.pharmacy.roomData.AppDatabase;
import mx.test.pharmacy.roomData.entities.Medicines;
import mx.test.pharmacy.util.ComunMethod;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener , LocationListener {

    //LISTA MEDICAMENTOS Y FARMACIAS
    private List<ListMedicamentoShow> listMedicamentoShowsPrecargados;
    private List<ListMedicamentoShow> listMedicamentoShows;
    private List<ListCompraUsuario> listCompraUsuarios;
    private RecyclerView recyclerView;
    private List<ListFarmaciaPrecDist> elementMedicines;
    private ListFarmaciaPrecDistAdapter listFarmaciaPrecDistAdapter;

    //MAPAS
    private Marker marker1,marker2,marker3;
    private View rootView;
    private MapView mapView;
    private GoogleMap mMap;
    private FloatingActionButton fab;
    Map<String, Integer> mMarkers = new HashMap<String, Integer>();
    boolean doubleClick = false;

    private LocationManager locationManager;
    private Location currentLocation;

    private Marker marker;
    private CameraPosition camara;
    Fragment currentFragment;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ComunMethod commonMethods = new ComunMethod();

    public MapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MapFragment newInstance(String param1, String param2) {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        rootView = inflater.inflate(R.layout.fragment_map, container, false);

        fab = rootView.findViewById(R.id.fload);

        elementMedicines = new ArrayList<>();
        listCompraUsuarios = new ArrayList<>();
        listMedicamentoShows = new ArrayList<>();
        listMedicamentoShowsPrecargados = new ArrayList<>();
        cargarFarmacias();
        cargarListaUsuario();
        new GetData().execute();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Atras " , Toast.LENGTH_SHORT).show();
                toggleBottomSheet();

            }
        });

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = (MapView) rootView.findViewById(R.id.mapf);
        if (mapView != null) {
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync(this);
        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0,  this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,0,this);

        Location location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        if (location == null){
            location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        }
        currentLocation = location;

        while (currentLocation == null){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1, 0,  this);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,0,this);

            location = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
            if (location == null){
                location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
            }
            currentLocation = location;
        }
        zoomToLocation(currentLocation);

        int height = 100;
        int width = 100;

        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.icfarmacia);
        Bitmap b = bitmapdraw.getBitmap();
        Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);

        BitmapDrawable bitmapdraw1 = (BitmapDrawable)getResources().getDrawable(R.drawable.icfarmacia);
        b = bitmapdraw1.getBitmap();
        Bitmap smallMarker1 = Bitmap.createScaledBitmap(b, width, height, false);

        BitmapDrawable bitmapdraw2 = (BitmapDrawable)getResources().getDrawable(R.drawable.icfarmacia);
        b = bitmapdraw2.getBitmap();
        Bitmap smallMarker2 = Bitmap.createScaledBitmap(b, width, height, false);

        BitmapDrawable bitmapdraw3 = (BitmapDrawable)getResources().getDrawable(R.drawable.icfarmacia);
        b = bitmapdraw3.getBitmap();
        Bitmap smallMarker3 = Bitmap.createScaledBitmap(b, width, height, false);

        BitmapDrawable bitmapdraw4 = (BitmapDrawable)getResources().getDrawable(R.drawable.icfarmacia);
        b = bitmapdraw4.getBitmap();
        Bitmap smallMarker4 = Bitmap.createScaledBitmap(b, width, height, false);

        LatLng miUbicacion = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.addMarker(new MarkerOptions().position(miUbicacion).title("Ubicación actual"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(miUbicacion));
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(miUbicacion)
                .zoom(15)
                .bearing(90)
                .tilt(45)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        LatLng farma = new LatLng(location.getLatitude()-0.00192, location.getLongitude()+0.003);
        LatLng farma1 = new LatLng(location.getLatitude()+0.00291, location.getLongitude()+0.0028);
        LatLng farma2 = new LatLng(location.getLatitude()+0.00392, location.getLongitude()-0.00112);
        LatLng farma3 = new LatLng(location.getLatitude()+0.00234, location.getLongitude()-0.0049);
        LatLng farma4 = new LatLng(location.getLatitude()+0.00000003, location.getLongitude()-0.0043);

        MarkerOptions marcador1 = new MarkerOptions()
                .position(farma)
                .title("Farmacia Ahora")
                .snippet("Distancia: 1 km ")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker));

        mMap.addMarker(marcador1);
        MarkerOptions marcador2 = new MarkerOptions()
                .position(farma1)
                .title("Farmacia Esp")
                .snippet("Distancia: 2 km ")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker1));
        mMap.addMarker(marcador2);
        /*mMap.addMarker(new MarkerOptions()
                .position(farma1)
                .title("Farmacia Esp ")
                .snippet("Precio: 99.00 "+"\n"+"Tiempo de Entrega: 25 min")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker1)));*/
        mMap.addMarker(new MarkerOptions()
                .position(farma2)
                .title("Farmacia San-Pa")
                .snippet("Distancia: 1.8 km")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker2)));
                //.showInfoWindow();

        Marker farmaWalwart = mMap.addMarker(new MarkerOptions()
                .position(farma3)
                .title("Farmacia Walwart")
                .snippet("Distancia: 2.1 km")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker3))
        );
        /*mMap.addMarker(new MarkerOptions()
                .position(farma3)
                .title("Farmacia Walwart ")
                .snippet("Precio: $110.00 "+"\n"+"Tiempo de Entrega: 15 min")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker3)))
                .showInfoWindow();*/
        Marker farmaGua = mMap.addMarker(new MarkerOptions()
                .position(farma4)
                .title("Farmacia Gua")
                .snippet("Distancia: 3 km ")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker4))
                .infoWindowAnchor(0.5f, 0.5f));
        /*mMap.addMarker(new MarkerOptions()
                .position(farma4)
                .title("Farmacia Gua ")
                .snippet("Precio: $60.00 "+"\n"+"Tiempo de Entrega: 55 min")
                .icon(BitmapDescriptorFactory.fromBitmap(smallMarker4)));*/

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(@NonNull Marker marker) {

                //Mandamos a traer el boottomSheet
                //toggleBottomSheet();
                if (marker.isInfoWindowShown()) {
                    marker.hideInfoWindow();
                } else {
                    marker.showInfoWindow();
                }
                return true;
            }
        });


        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(getContext());
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(getContext());
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(getContext());
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);
                info.isClickable();
                return info;
            }
        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker arg0) {
                // TODO Auto-generated method stub
                String titulo = arg0.getTitle();
                String descripcion = "Av.Reforma #1090 , Col. Centro";
                String telefono ="555-555-5555";
                String precio ="$90";

                cargarListaUsuarioPrecargado();
                ComunMethod.showSuccessDialogShow(titulo,listCompraUsuarios, getActivity());

            }

        });
    }
    private void zoomToLocation(Location location){
        camara = new CameraPosition.Builder()
                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                .zoom(15)
                .bearing(0)
                .tilt(30)
                .build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(camara));
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onClick(View v) {

    }

    public void toggleBottomSheet(){

        View view = getLayoutInflater().inflate(R.layout.fragment_bottom_sheet, null);
        recyclerView = view.findViewById(R.id.recyclerView_bootomshet);

        BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
        //Button btnPago = view.findViewById(R.id.shetbotompagar);


       /* btnPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ComunMethod.showSuccessDialog(titulo,telefono,precio,descripcion, getActivity());
            }
        });*/

        showList();
        dialog.setContentView(view);
        dialog.show();
    }

    private void showList(){
        listFarmaciaPrecDistAdapter = new ListFarmaciaPrecDistAdapter(elementMedicines, getContext());
        LinearLayoutManager li = new LinearLayoutManager(getContext());
        li.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(li);
        recyclerView.setAdapter(listFarmaciaPrecDistAdapter);
    }

    private void cargarFarmacias(){
        elementMedicines.add(new ListFarmaciaPrecDist("Farmacia Ahora", "180.00", "1km"));
        elementMedicines.add(new ListFarmaciaPrecDist("Framacia Esp", "166.30", "2km"));
        elementMedicines.add(new ListFarmaciaPrecDist("Framacia San-Pa", "150.10", "1.8km"));
        elementMedicines.add(new ListFarmaciaPrecDist("Framacia Walwart", "132.50", "2.1km"));
        elementMedicines.add(new ListFarmaciaPrecDist("Framacia Gua", "100.99", "3km"));

    }
    private void cargarListaUsuario(){

        CargarMedicamentos();

        listCompraUsuarios.add(new ListCompraUsuario("Farmacia Ahora", "555-551-2453","Calle el Empleado #152 -A1, col.Vicente","180.00","180.00","1km",listMedicamentoShows));
        //listCompraUsuarios.add(new ListCompraUsuario("Farmacia Esp", "555-652-7845","Av. Insurgentes Sur #458 -1, Tlalpan","166.30","166.30","2km",listMedicamentoShows ));
        //listCompraUsuarios.add(new ListCompraUsuario("Farmacia San-Pa", "555-753-1594","Av. Ferrocarril #245, col.Centro","150.10","150.10","1.8km",listMedicamentoShows));
        //listCompraUsuarios.add(new ListCompraUsuario("Farmacia Walwart", "555-956-1236","Calle el Empleado #1, col.Vicente","132.50","132.50","2.1km",listMedicamentoShows));
        //listCompraUsuarios.add(new ListCompraUsuario("Farmacia Gua", "555-648-2789","Calle el Empleado #1, col.Vicente","100.99","100.99","3km",listMedicamentoShows));
    }
    private void cargarListaUsuarioPrecargado(){
        listCompraUsuarios.add(new ListCompraUsuario("Farmacia Esp", "555-652-7845","Av. Insurgentes Sur #458 -1, Tlalpan","166.30","166.30","2km",listMedicamentoShowsPrecargados ));
        listCompraUsuarios.add(new ListCompraUsuario("Farmacia San-Pa", "555-753-1594","Av. Ferrocarril #245, col.Centro","150.10","150.10","1.8km",listMedicamentoShowsPrecargados));
        listCompraUsuarios.add(new ListCompraUsuario("Farmacia Walwart", "555-956-1236","Calle el Empleado #1, col.Vicente","132.50","132.50","2.1km",listMedicamentoShowsPrecargados));
        listCompraUsuarios.add(new ListCompraUsuario("Farmacia Gua", "555-648-2789","Calle el Empleado #1, col.Vicente","100.99","100.99","3km",listMedicamentoShowsPrecargados));
    }

    private void CargarMedicamentos(){
        listMedicamentoShowsPrecargados.add(new ListMedicamentoShow("Pirquet","Adulto","Fexofenadina","Tabletas","180 mg","10 Tabletas(1 caja)","209.50"));
        listMedicamentoShowsPrecargados.add(new ListMedicamentoShow("Buscapina","Adulto","Hioscina/Metamizol","Tabletas","10 mg/250 mg","24 Tabletas","358.00"));
        listMedicamentoShowsPrecargados.add(new ListMedicamentoShow("Arcoxia","Adulto","Etoricoxib","Tabletas","90 mg","28 Tabletas","1829.00"));
        listMedicamentoShowsPrecargados.add(new ListMedicamentoShow("Colchicina Aurax","Adulto","Colchicina","Tabletas","1 mg","30 Tabletas","51.50"));
        listMedicamentoShowsPrecargados.add(new ListMedicamentoShow("Melatonina Aurax","Adulto","Melatonina","Tabletas","5 mg","20 Tabletas","144.00"));
    }

    public class GetData extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            try {
                List<Medicines> medicinesList = AppDatabase.getInstance(getActivity().getApplicationContext()).medicinesDao().get();

                for (Medicines medicine : medicinesList) {
                    listMedicamentoShows.add(new ListMedicamentoShow(medicine.getName(),"Adulto","","",medicine.getGrammage(),"",medicine.getCost()));
                }



                return true;
            } catch (Exception e) {

                Log.e("ObverseIdentifyFragment", "Error al almacenar reporte", e);
                return false;

            }
        }

        /*@Override
        protected void onPostExecute(Boolean ok) {
            Toast.makeText(getActivity(), ok ? "Datos almacenados" : "Ocurrio un error al intentar almacenar los datos", Toast.LENGTH_LONG).show();
            if (ok) {
                showList();
            }

        }*/
    }
}