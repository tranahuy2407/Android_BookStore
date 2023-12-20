package stu.edu.vn.tahbookstore.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.retrofix.APIBookStore;
import stu.edu.vn.tahbookstore.retrofix.RetrofixClient;
import stu.edu.vn.tahbookstore.utils.Utils;

public class DangKyActivity extends AppCompatActivity {
    EditText email,pass,repass,mobile,username;
    AppCompatButton button;
    TextView txtdangky;
    APIBookStore apiBookStore;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        addControls();
        addEvents();

    }

    private void addEvents() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dangKy();
            }
        });
    }

    private void dangKy() {
        String str_email = email.getText().toString().trim();
        String str_pass = pass.getText().toString().trim();
        String str_username = username.getText().toString().trim();
        String str_repass = repass.getText().toString().trim();
        String str_mobile = mobile.getText().toString().trim();
        if(TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(),"Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_pass)){
            Toast.makeText(getApplicationContext(),"Bạn chưa nhập password", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_repass)) {
            Toast.makeText(getApplicationContext(),"Bạn cần nhập lại mật khẩu",Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(str_username)) {
            Toast.makeText(getApplicationContext(),"Bạn chưa nhập tên",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_mobile)){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập số điện thoại",Toast.LENGTH_SHORT).show();
        }else{
            if(str_pass.equals(str_repass)){
                compositeDisposable.add(apiBookStore.dangKy(str_email,str_pass,str_username,str_mobile)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if(userModel.isSuccess()){
                                        Utils.user_current.setEmail(str_email);
                                        Utils.user_current.setPass(str_pass);
                                          Toast.makeText(getApplicationContext(),"Đăng ký thành công!",Toast.LENGTH_SHORT).show();
                                          Intent intent =  new Intent(getApplicationContext(), DangNhapActivity.class);
                                          startActivity(intent);
                                          finish();;
                                    }else{
                                        Toast.makeText(getApplicationContext(),userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(getApplicationContext(),throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));

            }else{
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void addControls() {
        apiBookStore = RetrofixClient.getInstane(Utils.BASE_URL).create(APIBookStore.class);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        repass = findViewById(R.id.repass);
        button = findViewById(R.id.btndangky);
        txtdangky = findViewById(R.id.txtdangky);
        mobile=findViewById(R.id.mobile);
        username = findViewById(R.id.username);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}