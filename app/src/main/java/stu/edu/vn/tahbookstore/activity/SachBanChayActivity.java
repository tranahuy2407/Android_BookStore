package stu.edu.vn.tahbookstore.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.adapter.SachBanChayToolbar;
import stu.edu.vn.tahbookstore.model.SachBanChay;
import stu.edu.vn.tahbookstore.retrofix.APIBookStore;
import stu.edu.vn.tahbookstore.retrofix.RetrofixClient;
import stu.edu.vn.tahbookstore.utils.Utils;

public class SachBanChayActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    APIBookStore apiBookStore;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page = 1;
    int theloai;
    SachBanChayToolbar sbcToolbarAdapter;
    List <SachBanChay> sachBanChayList;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach_ban_chay);
        apiBookStore = RetrofixClient.getInstane(Utils.BASE_URL).create(APIBookStore.class);
        theloai = getIntent().getIntExtra("theloai",    1);
        addControls();
        actionToolBar();
        getData(page);
        addEventLoad();
    }

    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoading==false){
                    if(linearLayoutManager.findLastCompletelyVisibleItemPosition()==sachBanChayList.size()-1){
                        isLoading = true;
                        loadMore();
                    }
                }
            }

        });
    }
    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                sachBanChayList.add(null);
                sbcToolbarAdapter.notifyItemInserted(sachBanChayList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sachBanChayList.remove(sachBanChayList.size()-1);
                sbcToolbarAdapter.notifyItemRemoved(sachBanChayList.size());
                page = page +1;
                getData(page);
                sbcToolbarAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        },2000);
    }

    private void getData(int page) {
        compositeDisposable.add(apiBookStore.getSachBanChayToolBar(page,theloai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sachBanChayModel -> {
                            if(sachBanChayModel.isSuccess()){
                                if(sbcToolbarAdapter==null){
                                    sachBanChayList = sachBanChayModel.getResult();
                                    sbcToolbarAdapter = new SachBanChayToolbar(getApplicationContext(),sachBanChayList);
                                    recyclerView.setAdapter(sbcToolbarAdapter);
                                }else{
                                    int vitri =sachBanChayList.size()-1;
                                    int soluongadd= sachBanChayModel.getResult().size();
                                    for (int i =0;i<soluongadd;i++){
                                        sachBanChayList.add(sachBanChayModel.getResult().get(i));
                                    }
                                    sbcToolbarAdapter.notifyItemRangeInserted(vitri,soluongadd);
                                }

                            }else{
                                Toast.makeText(getApplicationContext(), "Hết dữ liệu sách rồi",Toast.LENGTH_LONG).show();
                            }
                        },
                       throwable -> {
                           Toast.makeText(getApplicationContext(), "Không kết nối được server",Toast.LENGTH_LONG).show();
                       }
                ));
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

    private void addControls() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView =findViewById(R.id.recyleview_sbc);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        sachBanChayList = new ArrayList<>();
    }
    public void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }
}