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

import org.w3c.dom.Text;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.retrofix.APIBookStore;
import stu.edu.vn.tahbookstore.retrofix.RetrofixClient;
import stu.edu.vn.tahbookstore.utils.Utils;

public class DangNhapActivity extends AppCompatActivity {
    TextView txtdangky;
    EditText email, pass;
    AppCompatButton btndangnhap;
    APIBookStore apiBookStore;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        addControls();
        addEvents();
    }

    private void addEvents() {
        txtdangky.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DangKyActivity.class);
                startActivity(intent);
            }
        });

        btndangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_email = email.getText().toString().trim();
                String str_pass = pass.getText().toString().trim();
                if(TextUtils.isEmpty(str_email)){
                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(str_pass)){
                    Toast.makeText(getApplicationContext(),"Bạn chưa nhập password", Toast.LENGTH_SHORT).show();
                }else{
                    Paper.book().write("email",str_email);
                    Paper.book().write("pass",str_pass);
                    compositeDisposable.add(apiBookStore.dangNhap(str_email,str_pass)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                  userModel -> {
                                      if(userModel.isSuccess()){
                                          Utils.user_current=userModel.getResult().get(0);
                                          Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                          startActivity(intent);
                                          finish();
                                      }
                                  },
                                    throwable -> {
                                      Toast.makeText(getApplicationContext(), throwable.getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });
    }
    private void addControls() {
        Paper.init(this);
        apiBookStore = RetrofixClient.getInstane(Utils.BASE_URL).create(APIBookStore.class);
        txtdangky = findViewById(R.id.txtdangky);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        btndangnhap = findViewById(R.id.btndangnhap);

        if(Paper.book().read("email")!= null && Paper.book().read("pass")!=null){
            email.setText(Paper.book().read("email"));
            pass.setText(Paper.book().read("pass"));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.user_current.getEmail() != null && Utils.user_current.getPass()!=null){
            email.setText(Utils.user_current.getEmail());
            pass.setText(Utils.user_current.getPass());
        }

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}