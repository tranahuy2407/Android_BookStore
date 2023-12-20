package stu.edu.vn.tahbookstore.retrofix;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import stu.edu.vn.tahbookstore.model.LoaiSachModel;
import stu.edu.vn.tahbookstore.model.SachBanChayModel;
import stu.edu.vn.tahbookstore.model.UserModel;

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
    @POST("dangky.php")
    @FormUrlEncoded
    Observable<UserModel> dangKy(
            @Field("email") String email,
            @Field("pass") String pass,
            @Field("username") String username,
            @Field("mobile") String mobile
    );
    @POST("dangnhap.php")
    @FormUrlEncoded
    Observable<UserModel> dangNhap(
            @Field("email") String email,
            @Field("pass") String pass
    );

}
