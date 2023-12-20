package stu.edu.vn.tahbookstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;

import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.adapter.LoaiSachAdapter;
import stu.edu.vn.tahbookstore.adapter.SachBanChayAdapter;
import stu.edu.vn.tahbookstore.model.LoaiSach;
import stu.edu.vn.tahbookstore.model.SachBanChay;
import stu.edu.vn.tahbookstore.retrofix.APIBookStore;
import stu.edu.vn.tahbookstore.retrofix.RetrofixClient;
import stu.edu.vn.tahbookstore.utils.Utils;


public class MainActivity extends AppCompatActivity {
    Toolbar toolbar ;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView;
    RecyclerView recyclerView,recyclerView1;
    DrawerLayout drawerLayout;
    LoaiSachAdapter loaiSachAdapter;
    List<LoaiSach> listLoaiSach;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    APIBookStore apiBookStore;
    List<SachBanChay> arraySachBanChay;
    SachBanChayAdapter sachBanChayAdapter;
    NotificationBadge badge;
    FrameLayout frameLayout;
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
            getSachBanChay();
            getSachMoi();
            getEventClick();
        }
        else{
            Toast.makeText(getApplicationContext(),"Không có kết nối internet, vui lòng thử kết nối lại !",Toast.LENGTH_LONG).show();
        }
    }

    private void getEventClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent trangChu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(trangChu);
                        break;
                    case 1:
                        Intent sachBanChay = new Intent(getApplicationContext(), SachBanChayActivity.class);
                        sachBanChay.putExtra("theloai",2);
                        startActivity(sachBanChay);
                        break;
                    case 2:
                        Intent sachMoi = new Intent(getApplicationContext(), SachBanChayActivity.class);
                        sachMoi.putExtra("theloai",3);
                        startActivity(sachMoi);
                        break;
                }
            }
        });
    }

    private void getSachMoi() {
        compositeDisposable.add(apiBookStore.getSachBanChayAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sachBanChayModel ->{
                            if(sachBanChayModel.isSuccess()){
                                arraySachBanChay = sachBanChayModel.getResult();
                                sachBanChayAdapter = new SachBanChayAdapter(getApplicationContext(), arraySachBanChay);
                                recyclerView1.setAdapter(sachBanChayAdapter);
                            }
                        },
                        throwable ->
                        {
                            Toast.makeText(getApplicationContext(),"Không có kết nối vào được sever"+ throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void getSachBanChay() {
        compositeDisposable.add(apiBookStore.getSachBanChayAPI()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sachBanChayModel -> {
                            if(sachBanChayModel.isSuccess()){
                            arraySachBanChay = sachBanChayModel.getResult();
                            sachBanChayAdapter = new SachBanChayAdapter(getApplicationContext(), arraySachBanChay);
                            recyclerView.setAdapter(sachBanChayAdapter);
                        }
                        },
                        throwable ->
                        {
                            Toast.makeText(getApplicationContext(),"Không có kết nối vào được sever"+ throwable.getMessage(),Toast.LENGTH_LONG).show();
                        }
                ));
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
        //Sach ban chay
        recyclerView = findViewById(R.id.recyleview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(sachBanChayAdapter);
        //SachMoi
        recyclerView1 = findViewById(R.id.recyleview1);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager1).setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView1.setLayoutManager(layoutManager1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setAdapter(sachBanChayAdapter);
        drawerLayout = findViewById(R.id.drawerLayout);
        badge = findViewById(R.id.menu_sl);
        frameLayout = findViewById(R.id.framegiohang);
        //adapter
        listLoaiSach = new ArrayList<>();
        arraySachBanChay = new ArrayList<>();
        if(Utils.manggiohang == null){
            Utils.manggiohang = new ArrayList<>();
        }else{
            int totalItem = 0;
            for (int i = 0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem+ Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
        frameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gioHang = new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(gioHang);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        int totalItem = 0;
        for (int i = 0; i<Utils.manggiohang.size(); i++){
            totalItem = totalItem+ Utils.manggiohang.get(i).getSoluong();
        }
        badge.setText(String.valueOf(totalItem));
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