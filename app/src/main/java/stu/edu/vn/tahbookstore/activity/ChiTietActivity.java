package stu.edu.vn.tahbookstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;

import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.model.SachBanChay;

public class ChiTietActivity extends AppCompatActivity {
    TextView tensp, tacgia, giagocsp, giagiamsp, mota;
    Button btnThem;
    ImageView imghinhanh;
    Spinner spinner;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        addControls();
        actionToolBar();
        initData();
    }

    private void initData() {
        SachBanChay sachBanChay = (SachBanChay) getIntent().getSerializableExtra("chitiet");
        tensp.setText(sachBanChay.getTENSACH());
        tacgia.setText(sachBanChay.getTACGIA());
        mota.setText(sachBanChay.getMOTA());
        Glide.with(getApplicationContext()).load(sachBanChay.getHINHANH()).into(imghinhanh);
        DecimalFormat decimalFormat = new DecimalFormat("##,###");
        giagocsp.setText("Giá gốc"+ decimalFormat.format(Double.parseDouble(sachBanChay.getGIAGOC()))+ "Đ");
        giagiamsp.setText("Giá giảm"+ decimalFormat.format(Double.parseDouble(sachBanChay.getGIAGIAM()))+ "Đ");
        Integer[] so = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapterspin = new ArrayAdapter<>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,so);
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
}