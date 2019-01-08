package com.bwie.juan_mao.jingdong_kanglijuan.view.user;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.juan_mao.jingdong_kanglijuan.R;
import com.bwie.juan_mao.jingdong_kanglijuan.base.BaseActivity;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.AddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RecommendBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.RegisterBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UpdateAddressBean;
import com.bwie.juan_mao.jingdong_kanglijuan.bean.UserInfoBean;
import com.bwie.juan_mao.jingdong_kanglijuan.presenter.MinePresenter;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.Https2http;
import com.bwie.juan_mao.jingdong_kanglijuan.utils.Utils;
import com.bwie.juan_mao.jingdong_kanglijuan.view.IMineView;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class PInfoActivity extends BaseActivity<MinePresenter> implements IMineView {
    @BindView(R.id.txt_back)
    ImageView txtBack;
    @BindView(R.id.rl_upload_icon)
    RelativeLayout rlUploadIcon;
    @BindView(R.id.txt_username)
    TextView txtUsername;
    @BindView(R.id.txt_nickname)
    TextView txtNickname;
    @BindView(R.id.rl_nickname)
    RelativeLayout rlNickname;
    @BindView(R.id.img_logo)
    SimpleDraweeView imgLogo;

    private final int FLAG_CAMERA_REQUEST = 100;
    private final int FLAG_CROP_REQUEST = 101;
    private final int FLAG_ALBUM_REQUEST = 102;
    private PopupWindow popup;
    private TextView txtCamera;
    private TextView txtChooseAlbum;
    private TextView txtCancel;
    private File imgRoot;
    private Uri uri;
    private Intent intent;

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void updateAddress(UpdateAddressBean data) {

    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void getUserInfo(UserInfoBean loginBean) {
        UserInfoBean.DataBean data = loginBean.getData();
        if (data.getNickname() != null) {
            txtNickname.setText(data.getNickname() + "");
            // 查询过后吧昵称存到里面
            SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
            sp.edit().putString("nickname", String.valueOf(data.getNickname()))
                    .apply();
        } else {
            txtNickname.setText(data.getUsername());
        }
        txtUsername.setText(data.getUsername());
        if (data.getIcon() != null) {
//            imgLogo.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541757331605&di=460eb0fb70b979e44a9333b639f23bd0&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201602%2F13%2F20160213103518_Ktc8J.jpeg"));
            imgLogo.setImageURI(Uri.parse(Https2http.replace(data.getIcon())));
        } else {
            imgLogo.setImageURI(Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1541757377646&di=3401cc95e2f9813767c9907f961a2224&imgtype=0&src=http%3A%2F%2F5b0988e595225.cdn.sohucs.com%2Fimages%2F20171231%2F5ccc64e16cf84a17adfc9d0801a0775b.jpeg"));
        }
    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getRecommend(RecommendBean data) {

    }

    /**
     * 这里是upload接口
     *
     * @param data
     */
    @Override
    public void register(RegisterBean data) {
        presenter.getUserInfo();
    }

    /**
     * 用不到
     *
     * @param data
     */
    @Override
    public void getAddress(AddressBean data) {

    }

    /**
     * 用不到
     *
     * @param isChecked
     * @param msg
     */
    @Override
    public void check(boolean isChecked, String msg) {

    }

    /**
     * 用不到
     *
     * @return
     */
    @Override
    public String getUsername() {
        return null;
    }

    /**
     * 用不到
     *
     * @return
     */
    @Override
    public String getPassword() {
        return null;
    }

    /**
     * 用不到
     *
     * @return
     */
    @Override
    public String getPassword2() {
        return null;
    }

    @Override
    public void onMineFailed(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initData() {
        // 说明外置存储可用，已经被挂载
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            // SD卡根目录
            File rootSD = Environment.getExternalStorageDirectory();
            imgRoot = new File(rootSD.getAbsolutePath(), File.separator
                    + "imgs");
            if (!imgRoot.exists()) {
                // 如果这个路径不存在的话，就要创建这个路径
                imgRoot.mkdirs();
            }
        }
        View view = View.inflate(this, R.layout.popup_upload_icon, null);
        txtCamera = view.findViewById(R.id.txt_camera);
        txtChooseAlbum = view.findViewById(R.id.txt_choose_album);
        txtCancel = view.findViewById(R.id.txt_cancel);
        // 设置点击
        // 拍照
        txtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File f = new File(imgRoot, new Date().getTime() + ".jpg");
                uri = Uri.fromFile(f);

                // ACTION_IMAGE_CAPTURE(动作图像捕获)
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 拍照输出的路径 (EXTRA_OUTPUT额外输出)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, FLAG_CAMERA_REQUEST);

                // 取消弹框
                popup.dismiss();
            }
        });
        // 相册选择
        txtChooseAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK);
                // 设置类型 image/jpeg image/png image/gif,*是通配符
                intent.setType("image/*");
                startActivityForResult(intent, FLAG_ALBUM_REQUEST);

                // 取消弹框
                popup.dismiss();
            }
        });
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup.dismiss();
            }
        });
        popup = new PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, true);
        popup.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
    }

    @Override
    protected MinePresenter providePresenter() {
        return new MinePresenter();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.getUserInfo();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_pinfo;
    }

    @OnClick({R.id.txt_back, R.id.rl_upload_icon, R.id.rl_nickname})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.txt_back:
                finish();
                break;
            case R.id.rl_upload_icon:
                // 点击上传头像
                popup.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                break;
            case R.id.rl_nickname:
                // 点击修改昵称
                Intent intent = new Intent(this, CNicknameActivity.class);
                intent.putExtra("nickname", txtNickname.getText().toString());
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 从系统相机返回的值
        if (requestCode == FLAG_CAMERA_REQUEST && resultCode == RESULT_OK) {
            // 让他等于以前的intent
            intent = crop(uri);
            startActivityForResult(intent, FLAG_CROP_REQUEST);
        } else if (requestCode == FLAG_ALBUM_REQUEST && resultCode == RESULT_OK) {
            // 从相册中返回的值
            uri = data.getData();
            intent = crop(uri);
            startActivityForResult(intent, FLAG_CROP_REQUEST);
        } else if (requestCode == FLAG_CROP_REQUEST) {
            // 从返回值中直接获取bitmap
            Bitmap bmp = (Bitmap) data.getExtras().get("data");
            // 获取到图片的file路径
            File file = Utils.compressImage(bmp);
            presenter.upload(file);
//            imgLogo.setImageBitmap(bmp);
        }
    }

    /**
     * 裁剪
     *
     * @param uri
     * @return
     */
    private Intent crop(Uri uri) {
        //隐式Intent，调用系统的裁剪
        Intent intent = new Intent("com.android.camera.action.CROP");
        // 设置裁剪的数据源和数据类型
        // 设置类型 image/jpeg image/png image/gif,*是通配符
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");// 可裁剪
        intent.putExtra("aspectX", 1);// 裁剪的宽比例
        intent.putExtra("aspectY", 1);// 裁剪的高比例
        intent.putExtra("outputX", 300);// 裁剪的宽度
        intent.putExtra("outputY", 300);// 裁剪的高度
        intent.putExtra("scale", true);// 支持缩放,可以省略,默认为true
        // 将裁剪的结果输出到制定的Uri
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.parse(uri.getPath() + ".bak"));
        // 若为true则表示返回数据,必须加,否则返回值中没有值
        intent.putExtra("return-data", true);
        // 裁剪成的图片的格式 Bitmap(位图n)compressFormat(压缩格式)JPEG(一种压缩格式)【可以省略】
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        // 启用人脸识别 detection(侦查; 察觉; 发觉; 检查;)
        // intent.putExtra("noFaceDetection", true);【可以省略】
        return intent;
    }

}
