package com.bwie.juan_mao.jingdong_kanglijuan.contact;

import com.bwie.juan_mao.jingdong_kanglijuan.bean.CategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductCategoryBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ProductDetailsBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.ShopListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 卷猫~ on 2018/11/8.
 */

public interface IClassifyApi {

    @GET("product/getCatagory")
    Observable<CategoryBean> getCategory();

    @GET("product/getProductCatagory")
    Observable<ProductCategoryBean> getProductCategory(@Query("cid") int cid);

    @GET("product/searchProducts")
    Observable<ShopListBean> searchProducts(@Query("keywords") String keywords, @Query("page") int page);

    @GET("product/getProducts")
    Observable<ShopListBean> getProducts(@Query("pscid") int pscid, @Query("page") int page);

    @GET("product/getProductDetail")
    Observable<ProductDetailsBean> getProductDetail(@Query("pid") int pid);

    @GET("product/addCart")
    Observable<RegisterBean> addCart(@Query("pid") int pid, @Query("uid") int uid);


}
