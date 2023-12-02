package stu.edu.vn.tahbookstore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import stu.edu.vn.tahbookstore.R;
import stu.edu.vn.tahbookstore.model.LoaiSach;

public class LoaiSachAdapter extends BaseAdapter {
    List<LoaiSach>  array;
    Context context;
    public  LoaiSachAdapter( Context context,List<LoaiSach> array){
        this.array=array;
        this.context=context;
    }

    @Override
    public int getCount() {
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    public class ViewHolder{
        TextView txtTenLoaiSach;
        ImageView imgHinhAnh;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder = null;
       if(convertView == null){
           viewHolder = new ViewHolder();
           LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           convertView = layoutInflater.inflate(R.layout.item_loaisach,null);
           viewHolder.txtTenLoaiSach=convertView.findViewById(R.id.item_loaisach);
           viewHolder.imgHinhAnh=convertView.findViewById(R.id.item_image);
           convertView.setTag(viewHolder);

       }
       else{
            viewHolder = (ViewHolder) convertView.getTag();

       }
        viewHolder.txtTenLoaiSach.setText(array.get(position).getTENTHELOAI());
        Glide.with(context).load(array.get(position).getHINHANH()).into(viewHolder.imgHinhAnh);
       return convertView;
    }

}
