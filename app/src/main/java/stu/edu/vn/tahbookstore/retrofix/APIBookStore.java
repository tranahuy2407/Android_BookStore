package stu.edu.vn.tahbookstore.retrofix;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import stu.edu.vn.tahbookstore.model.LoaiSachModel;
import stu.edu.vn.tahbookstore.model.SachBanChay;
import stu.edu.vn.tahbookstore.model.SachBanChayModel;
import stu.edu.vn.tahbookstore.model.SachMoiModel;

public interface APIBookStore
{
    @GET("getloaisach.php")
    Observable<LoaiSachModel> getLoaiSach();

    @GET("getsachbanchay.php")
    Observable<SachBanChayModel> getSachBanChayAPI();

    @GET("getsachmoi.php")
    Observable<SachMoiModel> getSachMoiAPI();
    @POST("chitiet.php")
    @FormUrlEncoded
    Observable<SachBanChayModel> getSachBanChayToolBar(
            @Field("page") int page,
            @Field("THELOAI") int theloai
    );

}
