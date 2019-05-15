package com.example.lenovo.everywheretravel.ui.personaldetails;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.lenovo.everywheretravel.R;
import com.example.lenovo.everywheretravel.base.BaseActivity;
import com.example.lenovo.everywheretravel.base.Constants;
import com.example.lenovo.everywheretravel.presenter.personaldetails.PersonalDetailsPresenter;
import com.example.lenovo.everywheretravel.ui.login.LoginActivity;
import com.example.lenovo.everywheretravel.bean.UpLoadBean;
import com.example.lenovo.everywheretravel.utils.SpUtil;
import com.example.lenovo.everywheretravel.utils.ToastUtil;
import com.example.lenovo.everywheretravel.view.personaldetails.PersonalDetailsView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PersonalDetailsActivity extends BaseActivity<PersonalDetailsView, PersonalDetailsPresenter> implements PersonalDetailsView {
    private static final String TAG = "PersonalDetailsActivity";

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.head_portrait)
    ImageView headPortrait;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.gender)
    TextView mGender;
    @BindView(R.id.personalized_signature)
    TextView personalizedSignature;
    @BindView(R.id.change_password)
    TextView changePassword;
    @BindView(R.id.bind_phone)
    TextView bindPhone;
    @BindView(R.id.log_out)
    Button logOut;
    @BindView(R.id.rl_head_portrait)
    RelativeLayout rlHeadPortrait;
    @BindView(R.id.rl_name)
    RelativeLayout rlName;
    @BindView(R.id.rl_gender)
    RelativeLayout rlGender;
    @BindView(R.id.rl_personalized_signature)
    RelativeLayout rlPersonalizedSignature;
    @BindView(R.id.ll)
    LinearLayout ll;
    private String photo;
    private File mFile;
    private Uri mImageUri;
    String token = (String) SpUtil.getParam(Constants.TOKEN, "");

    @Override
    protected PersonalDetailsPresenter initPresenter() {
        return new PersonalDetailsPresenter();
    }

    @Override
    protected int initLayoutId() {
        return R.layout.activity_personal_details;
    }

    @Override
    protected void initView() {
        super.initView();
        //亮色的模式,会讲状态栏文字修改为黑色的
        StatusBarUtil.setLightMode(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        // 头像
        photo = (String) SpUtil.getParam(Constants.PHOTO, "");
        RequestOptions requestOptions = new RequestOptions()
                .circleCrop()
                .placeholder(R.drawable.zhanweitu_touxiang);
        Glide.with(PersonalDetailsActivity.this).load(photo).apply(requestOptions).into(headPortrait);
        // 用户名
        String name = (String) SpUtil.getParam(Constants.USERNAME, "");
        mName.setText(name);
        // 性别
        String gender = (String) SpUtil.getParam(Constants.GENDER, "");
        if (gender.equals("U")) {
            mGender.setText("保密");
        } else if (gender.equals("M")){
            mGender.setText("男");
        }else if (gender.equals("F")){
            mGender.setText("女");
        }else {
            mGender.setText(gender);
        }
        // 个性签名
        String signature = (String) SpUtil.getParam(Constants.DESC, "");
        personalizedSignature.setText(signature);
    }

    @OnClick({R.id.back, R.id.change_password, R.id.bind_phone, R.id.log_out, R.id.rl_head_portrait, R.id.rl_name, R.id.rl_gender, R.id.rl_personalized_signature})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.rl_head_portrait:
                initPopupwindow();
                break;
            case R.id.rl_name:
                ChangeInfoActivity.startAct(PersonalDetailsActivity.this, ChangeInfoActivity.TYPE_NAME,mName.getText().toString());
                break;
            case R.id.rl_gender:
                initGender();
                break;
            case R.id.rl_personalized_signature:
                ChangeInfoActivity.startAct(PersonalDetailsActivity.this, ChangeInfoActivity.TYPE_PERSONALIZED_SIGNATURE,personalizedSignature.getText().toString());
                break;
            case R.id.change_password:
                break;
            case R.id.bind_phone:
                break;
            case R.id.log_out:
                logOut();
                break;
        }
    }

    private void initGender() {
        View gender_popupwindow_item = LayoutInflater.from(PersonalDetailsActivity.this).inflate(R.layout.gender_popupwindow_item, null);
        final PopupWindow popupWindow = new PopupWindow(gender_popupwindow_item, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //设置popupwindow的弹出和回收动画
        popupWindow.setAnimationStyle(R.style.PopAnimation);
        //popupwindow获取焦点 锁定后面的界面
//        popupWindow.setFocusable(true);
        // 设置阴影（相当于背景颜色）
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.c_60000000)));
        //点击popupwindow以外的地方和返回键 可以消失
        popupWindow.setOutsideTouchable(true);   //这个方法应该是在new PopupWindow()时都设置成 ViewGroup.LayoutParams.MATCH_PARENT 时才使用
        //popupwindow放在界面的下面
        popupWindow.showAtLocation(ll, Gravity.BOTTOM, 0, 0);

        gender_popupwindow_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        gender_popupwindow_item.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        gender_popupwindow_item.findViewById(R.id.sectet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGender.setText(getResources().getString(R.string.sectet));
                updateData();
                popupWindow.dismiss();
            }
        });

        gender_popupwindow_item.findViewById(R.id.goddess).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGender.setText(getResources().getString(R.string.goddess));
                updateData();
                popupWindow.dismiss();
            }
        });

        gender_popupwindow_item.findViewById(R.id.man).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGender.setText(getResources().getString(R.string.man));
                updateData();
                popupWindow.dismiss();
            }
        });
    }

    private void logOut() {
//        LoginActivity.startAct(PersonalDetailsActivity.this,LoginActivity.TYPE_LOGIN);
//        SpUtil.setParam(Constants.TOKEN,"");
//        finish();

        SpUtil.setParam(Constants.TOKEN,"");
        Intent intent = new Intent();
//        intent.setClass(PersonalDetailsActivity.this,LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意本行FLAG设置
        startActivity(intent);
        finish();
    }

    private void initPopupwindow() {
        View head_portrait_popupwindow_item = LayoutInflater.from(PersonalDetailsActivity.this).inflate(R.layout.head_portrait_popupwindow_item, null);
        final PopupWindow popupWindow = new PopupWindow(head_portrait_popupwindow_item, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //设置popupwindow的弹出和回收动画
        popupWindow.setAnimationStyle(R.style.PopAnimation);
        //popupwindow获取焦点 锁定后面的界面
//        popupWindow.setFocusable(true);
        // 设置阴影（相当于背景颜色）
        popupWindow.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.c_60000000)));
        //点击popupwindow以外的地方和返回键 可以消失
        popupWindow.setOutsideTouchable(true);   //这个方法应该是在new PopupWindow()时都设置成 ViewGroup.LayoutParams.MATCH_PARENT 时才使用
        //popupwindow放在界面的下面
        popupWindow.showAtLocation(ll, Gravity.BOTTOM, 0, 0);

        head_portrait_popupwindow_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        head_portrait_popupwindow_item.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });

        // 拍照
        head_portrait_popupwindow_item.findViewById(R.id.photograph).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
                popupWindow.dismiss();
            }
        });

        // 相册
        head_portrait_popupwindow_item.findViewById(R.id.photo_album).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePICK();
                popupWindow.dismiss();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            if (requestCode == 1 && resultCode == 3) {
                String name = data.getStringExtra(Constants.CONTENT);
                Log.e(TAG, "onActivityResult: " + name);
                mName.setText(name);
                updateData();
            }
            if (requestCode == 2 && resultCode == 3) {
                String personalized_signature = data.getStringExtra(Constants.CONTENT);
                Log.e(TAG, "personalized_signature: " + personalized_signature);
                personalizedSignature.setText(personalized_signature);
                updateData();
            }
            if (resultCode == RESULT_OK){//判断回调成功

                if (requestCode == CAMERA_CODE){//拍照

                    //显示拍照后的图片
                /*try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mImageUri));
                    img.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/

                    //拍照后的图片上传
                    uploadFile(mFile);
                }else if (requestCode == ALBUM_CODE) {//相册

                    //获取到相册选中后的图片URI路径
                    Uri imageUri = data.getData();

                    //显示相册选中后的图片
                /*try {
                    Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                    img.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/

                    //文件上传，将Uri路径转换为File对象
                    //处理uri,7.0以后的fileProvider 把URI 以content provider 方式 对外提供的解析方法
                    File file = getFileFromUri(imageUri, this);

                    if (file.exists()){
                        uploadFile(file);
                        showLoading();
                    }
                }
            }

        }

    }

    private void updateData() {
        showLoading();
        basePresenter.upDateInfo(mName.getText().toString(),personalizedSignature.getText().toString(),mGender.getText().toString(), photo,token);
    }

    @Override
    public void onSuccess(String string) {
//        Log.e(TAG, "onSuccess: " + string);
        SpUtil.setParam(Constants.PHOTO,photo);
        SpUtil.setParam(Constants.USERNAME,mName.getText().toString());
        SpUtil.setParam(Constants.GENDER,mGender.getText().toString());
        SpUtil.setParam(Constants.DESC,personalizedSignature.getText().toString());
//        Log.e(TAG, "PERSONALIZED_SIGNATURE: " + personalizedSignature.getText().toString());
        hideLoading();
        ToastUtil.showShort("修改成功");
    }

    @Override
    public void onFail(String string) {
        hideLoading();
    }

    // 相机权限
    private void takePhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED){
            openCamera();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},100);
        }
    }

    // 相册权限
    private void takePICK() {
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            openAlbum();
        }else{
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},200);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
            if (requestCode==100){
                openCamera();
            }else if (requestCode == 200){
                openAlbum();
            }
        }
    }

    private static final int CAMERA_CODE = 100;
    private static final int ALBUM_CODE = 200;

    //打开相机
    private void openCamera() {

        //创建文件用于保存图片
        mFile = new File(getExternalCacheDir(), System.currentTimeMillis() + ".jpg");
        if (!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //适配7.0,  等到对应的mImageUri路径地址
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mImageUri = Uri.fromFile(mFile);
        } else {
            //第二个参数要和清单文件中的配置保持一致
            mImageUri = FileProvider.getUriForFile(this, "com.baidu.upload.provider", mFile);
        }

        //启动相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageUri);//将拍照图片存入mImageUri
        startActivityForResult(intent, CAMERA_CODE);
    }

    //打开相册
    private void openAlbum() {

        //启动相册
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        startActivityForResult(intent,ALBUM_CODE);
    }

    public File getFileFromUri(Uri uri, Context context) {
        if (uri == null) {
            return null;
        }
        switch (uri.getScheme()) {
            case "content":
                return getFileFromContentUri(uri, context);
            case "file":
                return new File(uri.getPath());
            default:
                return null;
        }
    }

    /**
     通过内容解析中查询uri中的文件路径
     */
    private File getFileFromContentUri(Uri contentUri, Context context) {
        if (contentUri == null) {
            return null;
        }
        File file = null;
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};
        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(contentUri, filePathColumn, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            filePath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();

            if (!TextUtils.isEmpty(filePath)) {
                file = new File(filePath);
            }
        }
        return file;
    }

    private void uploadFile(File mFile) {

        String url = "http://yun918.cn/study/public/file_upload.php";

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        //  file-->RequestBody
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), mFile);

        // 创建多媒体 请求对象
        MultipartBody body = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("key", "H1808C")
                .addFormDataPart("file", mFile.getName(), requestBody)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();

        Call call = client.newCall(request);

        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                Gson gson = new Gson();
                final UpLoadBean upLoadBean = gson.fromJson(string, UpLoadBean.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (upLoadBean!=null){
                            if (upLoadBean.getCode() == 200){
//                                Toast.makeText(PersonalDetailsActivity.this,upLoadBean.getRes(),Toast.LENGTH_SHORT).show();
                                RequestOptions requestOptions = new RequestOptions()
                                        .circleCrop()
                                        .placeholder(R.drawable.zhanweitu_touxiang);
                                Glide.with(PersonalDetailsActivity.this).load(upLoadBean.getData().getUrl()).apply(requestOptions).into(headPortrait);
                                SpUtil.setParam(Constants.AVATAR_HD, upLoadBean.getData().getUrl());
                                photo=upLoadBean.getData().getUrl();
                                updateData();
//                                Log.e(TAG, "run: "+upLoadBean.getData().getUrl() );
                            }else{
                                Toast.makeText(PersonalDetailsActivity.this,upLoadBean.getRes(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
    }
}
