package stu.edu.vn.tahbookstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;

import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.model.GioHang;
import stu.edu.vn.tahbookstore.model.SachBanChay;
import stu.edu.vn.tahbookstore.utils.Utils;

public class ChiTietActivity extends AppCompatActivity {
    TextView tensp, tacgia, giagocsp, giagiamsp, mota;
    Button btnThem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    SachBanChay sachBanChay;
    NotificationBadge badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        addControls();
        actionToolBar();
        initData();
        addEvents();
    }

    private void addEvents() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themGioHang();
            }
        });
    }

    private void themGioHang() {
        if (Utils.manggiohang.size() >0) {
            boolean flag = false;
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            for(int i =0;i<Utils.manggiohang.size();i++){
                if(Utils.manggiohang.get(i).getIdsp()==sachBanChay.getID()){
                    Utils.manggiohang.get(i).setSoluong(soluong+Utils.manggiohang.get(i).getSoluong());
                    long gia = Long.parseLong(sachBanChay.getGIAGIAM())*Utils.manggiohang.get(i).getSoluong();
                    Utils.manggiohang.get(i).setGiasp(gia);
                    flag=true;
                }
            }
            if(flag==false){
                long gia =  Long.parseLong(sachBanChay.getGIAGIAM()) * soluong;
                GioHang gioHang = new GioHang();
                gioHang.setGiasp(gia);
                gioHang.setSoluong(soluong);
                gioHang.setIdsp(sachBanChay.getID());
                gioHang.setTensp(sachBanChay.getTENSACH());
                gioHang.setHinhsp(sachBanChay.getHINHANH());
                Utils.manggiohang.add(gioHang);

            }
        } else{
            int soluong = Integer.parseInt(spinner.getSelectedItem().toString());
            long gia =  Long.parseLong(sachBanChay.getGIAGIAM()) * soluong;
            GioHang gioHang = new GioHang();
            gioHang.setGiasp(gia);
            gioHang.setSoluong(soluong);
            gioHang.setIdsp(sachBanChay.getID());
            gioHang.setTensp(sachBanChay.getTENSACH());
            gioHang.setHinhsp(sachBanChay.getHINHANH());
            Utils.manggiohang.add(gioHang);

        }
        int totalItem = 0;
        for (int i = 0; i<Utils.manggiohang.size(); i++){
            totalItem = totalItem+ Utils.manggiohang.get(i).getSoluong();
        }
            badge.setText(String.valueOf(totalItem));
        }



    private void initData() {
        Intent intent = getIntent();

        sachBanChay = (SachBanChay) intent.getSerializableExtra("chitiet");
        tensp.setText(sachBanChay.getTENSACH());
        tacgia.setText(sachBanChay.getTACGIA());
        mota.setText(sachBanChay.getMOTA());
        Glide.with(getApplicationContext()).load(sachBanChay.getHINHANH()).into(imghinhanh);
        DecimalFormat decimalFormat = new DecimalFormat("##,###");
        giagocsp.setText("Giá gốc" + decimalFormat.format(Double.parseDouble(sachBanChay.getGIAGOC())) + "Đ");
        giagiamsp.setText("Giá giảm" + decimalFormat.format(Double.parseDouble(sachBanChay.getGIAGIAM())) + "Đ");
        Integer[] so = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, com.nex3z.notificationbadge.R.layout.support_simple_spinner_dropdown_item, so);
        spinner.setAdapter(adapterspin);
    }
    private void addControls() {
        tensp = findViewById(R.id.txttensp);
        tacgia = findViewById(R.id.txttacgia);
        giagocsp = findViewById(R.id.txtgiagocsp);
        giagiamsp = findViewById(R.id.txtgiagiamsp);
        mota = findViewById(R.id.txtmotachitiet);
        btnThem= findViewById(R.id.btnthemvaogiohang);
        imghinhanh = findViewById(R.id.imgchitiet);
        toolbar =findViewById(R.id.toobar);
        spinner = findViewById(R.id.spinner);
        badge = findViewById(R.id.menu_sl);
        FrameLayout frameLayoutgiohang = findViewById(R.id.framegiohang);
        frameLayoutgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent giohang = new Intent(getApplicationContext(), GioHangActivity.class);
                startActivity(giohang);
            }
        });
        if(Utils.manggiohang != null){
            int totalItem = 0;
            for (int i = 0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem+ Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }

    }
    private void actionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.manggiohang != null){
            int totalItem = 0;
            for (int i = 0; i<Utils.manggiohang.size(); i++){
                totalItem = totalItem+ Utils.manggiohang.get(i).getSoluong();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }
}