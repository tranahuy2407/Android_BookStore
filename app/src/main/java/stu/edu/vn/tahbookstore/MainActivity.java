package stu.edu.vn.tahbookstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar ;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    ListView listView;
    RecyclerView recyclerView;
    DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        ActionBar();
        ActionViewFlipper();
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
    }
}