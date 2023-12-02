package stu.edu.vn.tahbookstore.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import stu.edu.vn.tahbookstore.model.SachBanChay;

public class SachBanChayAdapter extends RecyclerView.Adapter<SachBanChayAdapter.MyViewHoder> {
    Context context;
    List<SachBanChay> array;

    @NonNull
    @Override
    public SachBanChayAdapter.MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull SachBanChayAdapter.MyViewHoder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
        }
    }
}
