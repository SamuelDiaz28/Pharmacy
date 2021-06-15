

package mx.test.pharmacy;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import mx.test.pharmacy.fragments.MapFragment;
import mx.test.pharmacy.fragments.MedicineFragment;
import mx.test.pharmacy.fragments.ShoppingCartFragment;

public class MainActivity extends AppCompatActivity {

    private int currentFragment;

    private MedicineFragment medicineFragment = null;
    private ShoppingCartFragment shoppingCartFragment = null;
    private MapFragment mapFragment = null;

    private BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (currentFragment == item.getItemId())
                return true;
            currentFragment = item.getItemId();
            switch (currentFragment) {
                case R.id.menuHome:
                    if (medicineFragment == null)
                        medicineFragment = new MedicineFragment();
                    FragmentManager fm = getSupportFragmentManager();
                    if (fm.getBackStackEntryCount() > 0)
                        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    addFragment(medicineFragment, false);
                    return true;
                case R.id.menuCart:
                    if (shoppingCartFragment == null)
                        shoppingCartFragment = new ShoppingCartFragment();
                    addFragment(shoppingCartFragment, false);
                    return true;
                case R.id.menuMap:
                    MainActivity.this.setTitle("Mapa");
                    if (mapFragment == null)
                        mapFragment = new MapFragment();
                    addFragment(mapFragment, false);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener);

        if (medicineFragment == null)
            medicineFragment = new MedicineFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, medicineFragment).commit();

    }

    public void addFragment(@NonNull Fragment fragment, boolean push) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setTransition( FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        if(push)
            fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}