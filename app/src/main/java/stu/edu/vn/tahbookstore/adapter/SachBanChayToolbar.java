package stu.edu.vn.tahbookstore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import stu.edu.vn.tahbookstore.Interface.ItemClickLitener;
import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.activity.ChiTietActivity;
import stu.edu.vn.tahbookstore.model.SachBanChay;

public class SachBanChayToolbar extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<SachBanChay> array;
    private static final int VIEW_TYPE_DATA=0;
    private static final int VIEW_TYPE_LOADING=1;

    public SachBanChayToolbar(Context context, List<SachBanChay> array) {
        this.context = context;
        this.array = array;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==VIEW_TYPE_DATA){
            View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sachbanchaytoolbar, parent,false);
            return new MyViewHolder(view) ;
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false);
            return new LoadingViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof MyViewHolder){
            MyViewHolder myViewHolder =(MyViewHolder) holder;
            SachBanChay sachBanChay = array.get(position);
            myViewHolder.ten.setText(sachBanChay.getTENSACH());
            myViewHolder.idsach.setText(sachBanChay.getID()+ "");
            DecimalFormat decimalFormat = new DecimalFormat("##,###");
            myViewHolder.giaGoc.setText("Giá gốc"+ decimalFormat.format(Double.parseDouble(sachBanChay.getGIAGOC()))+ "Đ");
            myViewHolder.giaGiam.setText("Giá giảm"+ decimalFormat.format(Double.parseDouble(sachBanChay.getGIAGIAM()))+ "Đ");
            Glide.with(context).load(sachBanChay.getHINHANH()).into(myViewHolder.hinhAnh);
            myViewHolder.setItemClickLitener(new ItemClickLitener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if(!isLongClick){
                        Intent intent = new Intent(context, ChiTietActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });
        }
        else{
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return array.get(position)==null?VIEW_TYPE_LOADING:VIEW_TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;
        public  LoadingViewHolder(@NonNull View itemView){
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView hinhAnh;
        TextView ten,giaGoc,giaGiam,idsach;
        private ItemClickLitener itemClickLitener;
        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            ten= itemView.findViewById(R.id.itemsbc_ten);
            idsach= itemView.findViewById(R.id.itemsbc_idsach);
            giaGoc= itemView.findViewById(R.id.itemsbc_giagoc);
            giaGiam= itemView.findViewById(R.id.itemscb_giagiam);
            hinhAnh=itemView.findViewById(R.id.itemsbc_image);
            itemView.setOnClickListener(this);
        }

        public void setItemClickLitener(ItemClickLitener itemClickLitener) {
            this.itemClickLitener = itemClickLitener;
        }

        public  void onClick(View view){
            itemClickLitener.onClick(view,getAdapterPosition(),false);
        }
    }

}
