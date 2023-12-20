package stu.edu.vn.tahbookstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DecimalFormat;

import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.utils.Utils;

public class ThanhToanActivity extends AppCompatActivity {
Toolbar toolbar;
TextView txttongtien, txtsodt, txtemail;
EditText edtdiachi;
AppCompatButton btndathang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        addControls();
        addEvents();
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
        DecimalFormat decimalFormat = new DecimalFormat("##,###");
        long tongtien = getIntent().getLongExtra("tongtien",0);
        txttongtien.setText(decimalFormat.format(tongtien));
        txtemail.setText(Utils.user_current.getEmail());
        txtsodt.setText(Utils.user_current.getMobile());

        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String str_diachi =edtdiachi.getText().toString().trim();
               if(TextUtils.isEmpty(str_diachi)){
                   Toast.makeText(getApplicationContext(),"Bạn chưa nhập địa chỉ giao hàng",Toast.LENGTH_SHORT).show();
               }else{
                   Log.d("test", new Gson().toJson(Utils.manggiohang));
               }
            }
        });
    }

    private void addControls() {
        toolbar = findViewById(R.id.toobar);
        txttongtien=findViewById(R.id.txttongtien);
        txtsodt = findViewById(R.id.txtsodienthoai);
        txtemail = findViewById(R.id.txtemail);
        edtdiachi = findViewById(R.id.edtdiachi);
        btndathang = findViewById(R.id.btndathang);
    }
}