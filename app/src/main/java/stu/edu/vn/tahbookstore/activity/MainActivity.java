package stu.edu.vn.tahbookstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.adapter.LoaiSachAdapter;
import stu.edu.vn.tahbookstore.model.LoaiSach;
import stu.edu.vn.tahbookstore.model.LoaiSachModel;
import stu.edu.vn.tahbookstore.retrofix.APIBookStore;
import stu.edu.vn.tahbookstore.retrofix.RetrofixClient;
import stu.edu.vn.tahbookstore.utils.Utils;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar ;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    LoaiSachAdapter loaiSachAdapter;
    List<LoaiSach> listLoaiSach;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBookStore apiBookStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiBookStore= RetrofixClient.getInstane(Utils.BASE_URL).create(APIBookStore.class);
        addControls();
        ActionBar();

        if(isConnected(this)){

            ActionViewFlipper();
            getLoaiSach();
        }
        else{
            Toast.makeText(getApplicationContext(),"Không có kết nối internet, vui lòng thử kết nối lại !",Toast.LENGTH_LONG).show();
        }
    }

    private void getLoaiSach() {
        compositeDisposable.add(apiBookStore.getLoaiSach()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        loaiSachModel->{
                            if(loaiSachModel.isSuccess()){
                                listLoaiSach = loaiSachModel.getResult();
                                loaiSachAdapter = new LoaiSachAdapter(getApplicationContext(),listLoaiSach);
                                listView.setAdapter(loaiSachAdapter);
                            }
                        }
                ));
    }

    private void ActionViewFlipper() {
        List<String> mangQuangCao= new ArrayList<>();
        mangQuangCao.add("https://nguonluc.com.vn/uploads/images/2023/04/20/sachhay-dac-nhan-tam-1681986383.jpg");
        mangQuangCao.add("https://cdn.chanhtuoi.com/uploads/2018/03/sach-tiki-giam-gia-1.jpg");
        mangQuangCao.add("https://novai.vn/upload/images/58e95ec76e1dab596c4e7ec0da58e7eb.jpg");
        for(int i=0;i<mangQuangCao.size();i++){
            ImageView imageView= new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(mangQuangCao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    private void addControls() {
        toolbar = findViewById(R.id.toolbarmanhinhchinh);
        viewFlipper=findViewById(R.id.viewFlipper);
        navigationView=findViewById(R.id.navigationview);
        listView = findViewById(R.id.lvDSSPManHinhChinh);
        recyclerView = findViewById(R.id.recyleview);
        drawerLayout = findViewById(R.id.drawerLayout);
        //adapter
        listLoaiSach = new ArrayList<>();

    }
    private boolean isConnected(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if(wifi != null && wifi.isConnected()||mobile!=null && mobile.isConnected()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();

    }
}