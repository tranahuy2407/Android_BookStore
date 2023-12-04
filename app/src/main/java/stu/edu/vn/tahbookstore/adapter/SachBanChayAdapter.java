package stu.edu.vn.tahbookstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.model.SachBanChay;

public class SachBanChayAdapter extends RecyclerView.Adapter<SachBanChayAdapter.MyViewHolder> {
    Context context;
    List<SachBanChay> array;

    public SachBanChayAdapter(Context context, List<SachBanChay> array) {
        this.context = context;
        this.array = array;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach_ban_chay,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SachBanChay sachBanChay = array.get(position);
        holder.txtTen.setText(sachBanChay.getTen());
        holder.txtGiaGoc.setText(sachBanChay.getGiaGoc());
        holder.txtGiaGiam.setText(sachBanChay.getGiaGiam());
        Glide.with(context).load(sachBanChay.getHinhAnh()).into(holder.imgHinhAnh);
    }

    @Override
    public int getItemCount() {
        return array.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtGiaGiam,txtGiaGoc,txtTen;
        ImageView imgHinhAnh;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            txtGiaGoc= itemView.findViewById(R.id.itemsach_giagoc);
            txtGiaGiam = itemView.findViewById(R.id.itemsach_giagiam);
            txtTen = itemView.findViewById(R.id.itemsach_ten);
            imgHinhAnh = itemView.findViewById(R.id.itemsach_img);

        }
    }

}
