package stu.edu.vn.tahbookstore.retrofix;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import stu.edu.vn.tahbookstore.model.LoaiSachModel;

public interface APIBookStore
{
    @GET("getloaisach.php")
    Observable<LoaiSachModel> getLoaiSach();
}
