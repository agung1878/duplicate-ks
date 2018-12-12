package id.co.koperasisyariah212.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import id.co.koperasisyariah212.R;
import id.co.koperasisyariah212.ui.fragments.ArsipTransaksiFragment;
import id.co.koperasisyariah212.ui.fragments.FavoriteFragment;
import id.co.koperasisyariah212.ui.fragments.GantiPinFragment;
import id.co.koperasisyariah212.ui.fragments.HomeFragment;
import id.co.koperasisyariah212.ui.fragments.HubungiKamiFragment;
import id.co.koperasisyariah212.ui.fragments.InformasiTabunganFragment;
import id.co.koperasisyariah212.ui.fragments.MutasiTabunganFragment;
import id.co.koperasisyariah212.ui.fragments.PembayaranFragment;
import id.co.koperasisyariah212.ui.fragments.PembelianFragment;
import id.co.koperasisyariah212.ui.fragments.ProfileFragment;
import id.co.koperasisyariah212.ui.fragments.RegisterFragment;
import id.co.koperasisyariah212.ui.fragments.TransferFragment;

public class HomeActivity extends AppCompatActivity {

    private CardView cvInformasi, cvMutasi,
            cvPembelian, cvPembayaran, cvTransfer, cvGantiPin, cvHubungiKami, cvProfile;

    //fragment
    InformasiTabunganFragment informasiTabunganFragment = new InformasiTabunganFragment();
    MutasiTabunganFragment mutasiTabunganFragment = new MutasiTabunganFragment();
    PembelianFragment pembelianFragment = new PembelianFragment();
    HubungiKamiFragment hubungiKamiFragment = new HubungiKamiFragment();
    TransferFragment transferFragment = new TransferFragment();
    GantiPinFragment gantiPinFragment = new GantiPinFragment();
    PembayaranFragment pembayaranFragment = new PembayaranFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        cvInformasi = findViewById(R.id.cv_informasi_tabungan);
        cvMutasi = findViewById(R.id.cv_mutasi_tabungan);
        cvPembelian = findViewById(R.id.cv_pembelian);
        cvPembayaran = findViewById(R.id.cv_pembayaran);
        cvTransfer = findViewById(R.id.cv_transfer);
        cvGantiPin = findViewById(R.id.cv_ganti_pin);
        cvHubungiKami = findViewById(R.id.cv_hubungi_kami);
        cvProfile = findViewById(R.id.cv_profile);

        cvInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(informasiTabunganFragment);
            }
        });

        cvMutasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(mutasiTabunganFragment);
            }
        });

        cvPembelian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(pembelianFragment);
            }
        });

        cvPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(pembayaranFragment);
            }
        });

        cvTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(transferFragment);
            }
        });

        cvGantiPin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(gantiPinFragment);
            }
        });

        cvHubungiKami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(hubungiKamiFragment);
            }
        });

        cvProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeFragment(profileFragment);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.activity_home, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
}
