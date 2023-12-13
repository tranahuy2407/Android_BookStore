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

import java.text.DecimalFormat;
import java.util.List;
import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.model.SachMoi;

public class SachMoiAdapter extends RecyclerView.Adapter<SachMoiAdapter.MyViewHolder> {
    Context context;
    List<SachMoi> array;

    public SachMoiAdapter(Context context, List<SachMoi> array) {
        this.context = context;
        this.array = array;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach_moi,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        SachMoi sachMoi = array.get(position);
        holder.txtTen.setText(sachMoi.getTENSACH());
        DecimalFormat decimalFormat = new DecimalFormat("##,###");
        holder.txtGiaGoc.setText("Giá gốc: "+decimalFormat.format(Double.parseDouble(sachMoi.getGIAGOC()))+"đ");
        holder.txtGiaGiam.setText("Giá giảm: "+decimalFormat.format(Double.parseDouble(sachMoi.getGIAGIAM()))+"đ");
        Glide.with(context).load(sachMoi.getHINHANH()).into(holder.imgHinhAnh);
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
            txtTen = itemView.findViewById(R.id.itemsachmoi_ten);
            txtGiaGoc= itemView.findViewById(R.id.itemsachmoi_giagoc);
            txtGiaGiam = itemView.findViewById(R.id.itemsachmoi_giagiam);
            imgHinhAnh = itemView.findViewById(R.id.itemsachmoi_img);
        }
    }

}
