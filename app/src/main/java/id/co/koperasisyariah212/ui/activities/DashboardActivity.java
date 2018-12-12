package id.co.koperasisyariah212.ui.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.co.koperasisyariah212.ui.fragments.ArsipTransaksiFragment;
import id.co.koperasisyariah212.ui.fragments.FavoriteFragment;
import id.co.koperasisyariah212.ui.fragments.GantiPinFragment;
import id.co.koperasisyariah212.ui.fragments.HomeFragment;
import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.ui.fragments.HubungiKamiFragment;
import id.co.koperasisyariah212.ui.fragments.InformasiTabunganFragment;
import id.co.koperasisyariah212.ui.fragments.MutasiTabunganFragment;
import id.co.koperasisyariah212.ui.fragments.PembayaranFragment;
import id.co.koperasisyariah212.ui.fragments.PembelianFragment;
import id.co.koperasisyariah212.ui.fragments.ProfileFragment;
import id.co.koperasisyariah212.ui.fragments.RegisterFragment;
import id.co.koperasisyariah212.ui.fragments.TransferFragment;

public class DashboardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private long lastPress;

    HomeFragment homeFragment = new HomeFragment();
    RegisterFragment registerFragment = new RegisterFragment();
    FavoriteFragment favoriteFragment = new FavoriteFragment();
    InformasiTabunganFragment informasiTabunganFragment = new InformasiTabunganFragment();
    MutasiTabunganFragment mutasiTabunganFragment = new MutasiTabunganFragment();
    PembelianFragment pembelianFragment = new PembelianFragment();
    ArsipTransaksiFragment arsipTransaksiFragment = new ArsipTransaksiFragment();
    HubungiKamiFragment hubungiKamiFragment = new HubungiKamiFragment();
    TransferFragment transferFragment = new TransferFragment();
    GantiPinFragment gantiPinFragment = new GantiPinFragment();
    PembayaranFragment pembayaranFragment = new PembayaranFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);
        navigationView.getMenu().performIdentifierAction(R.id.nav_home, 0);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastPress > 5000) {
                Snackbar.make(drawer.getRootView(), "Press back again to exit", Snackbar.LENGTH_SHORT).show();
                lastPress = currentTime;
            } else {
                moveTaskToBack(true);
            }

        } else {
            drawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_home :
                changeFragment(homeFragment);
                break;
            case R.id.nav_favorite :
                changeFragment(favoriteFragment);
                break;
            case R.id.nav_informasi_tabungan :
                changeFragment(informasiTabunganFragment);
                break;
            case R.id.nav_mutasi_tabungan :
                changeFragment(mutasiTabunganFragment);
                break;
            case R.id.nav_pembelian :
                changeFragment(pembelianFragment);
                break;
            case R.id.nav_transfer :
                changeFragment(transferFragment);
                break;
            case R.id.nav_ganti_pin :
                changeFragment(gantiPinFragment);
                break;
            case R.id.nav_pembayaran :
                changeFragment(pembayaranFragment);
                break;
            case R.id.nav_arsip_transaksi:
                changeFragment(arsipTransaksiFragment);
                break;
            case R.id.nav_hubungi_kami:
                changeFragment(hubungiKamiFragment);
                break;
            case R.id.nav_profile:
                changeFragment(profileFragment);
                break;
            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.dashboard_container, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .disallowAddToBackStack()
                .commit();
    }
}
