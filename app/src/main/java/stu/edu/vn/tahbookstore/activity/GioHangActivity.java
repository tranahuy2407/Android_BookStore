package stu.edu.vn.tahbookstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;

import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.adapter.GioHangAdapter;
import stu.edu.vn.tahbookstore.model.EvenBus.TinhTongEvent;
import stu.edu.vn.tahbookstore.utils.Utils;

public class GioHangActivity extends AppCompatActivity {
TextView giohangtrong, tongtien;
Toolbar toolbar;
RecyclerView recyclerView;
Button btnMuaHang;
GioHangAdapter gioHangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gio_hang);
        addControls();
        addEvents();
        totalPrice();
    }

    private void totalPrice() {
        long tongTien =0;
        for(int i = 0; i<Utils.manggiohang.size(); i++){
            tongTien = tongTien + Utils.manggiohang.get(i).getGiasp()* Utils.manggiohang.get(i).getSoluong();
        }
        DecimalFormat decimalFormat = new DecimalFormat("##,###");
        tongtien.setText(decimalFormat.format(tongTien));
    }

    private void addEvents() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        if(Utils.manggiohang.size()==0){
            giohangtrong.setVisibility(View.VISIBLE);
        }
        else{
            gioHangAdapter = new GioHangAdapter(getApplicationContext(), Utils.manggiohang);
            recyclerView.setAdapter(gioHangAdapter);
        }

    }

    private void addControls() {
        giohangtrong = findViewById(R.id.txtgiohangtrong);
        tongtien = findViewById(R.id.txttongtien);
        toolbar = findViewById(R.id.toobar);
        recyclerView = findViewById(R.id.recyleviewgiohang);
        btnMuaHang = findViewById(R.id.btnmuahang);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();

    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void eventTinhTien(TinhTongEvent event){
        if(event!=null){
            totalPrice();
        }
    }
}