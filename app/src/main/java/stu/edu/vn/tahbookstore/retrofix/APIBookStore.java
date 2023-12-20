package stu.edu.vn.tahbookstore.retrofix;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import stu.edu.vn.tahbookstore.model.LoaiSachModel;
import stu.edu.vn.tahbookstore.model.SachBanChayModel;

public interface APIBookStore
{
    @GET("getloaisach.php")
    Observable<LoaiSachModel> getLoaiSach();

    @GET("getsachbanchay.php")
    Observable<SachBanChayModel> getSachBanChayAPI();
    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SachBanChayModel> getSachBanChayToolBar(
            @Field("page") int page,
            @Field("THELOAI") int theloai
    );

}
