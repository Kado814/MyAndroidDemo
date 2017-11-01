package com.myapplication.myandroiddemo.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;


import com.myapplication.myandroiddemo.R;
import com.myapplication.myandroiddemo.myutils.MyToast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
* 为了告知系统程序是基于相机的，需要在清单文件中添加标签：
* <uses-feature
            android:name="android.hardware.Camera"
            android:required="true"/>
如果程序需要使用，但是为了整个功能而不强制要求相机，那么可以设置
Android:required为false。这样做的话，Google Play会允许不带相机的设备下载你的程序。
不过你有责任需要在运行时通过调用 hasSystemFeature(PackageManager.FEATURE_CAMERA) 方法检查设备上的相机是否可用。
如果相机是不可用的，你应该禁用掉与相机相关的功能。
* */
public class CameraActivity extends MyBaseActivity implements View.OnClickListener {
    private static int REQUEST_IMAGE_CAMERA = 1;
    private static int REQUEST_TAKE_PHOTE = 2;
    private static int REQUEST_VIDEO_CAPTURE = 3;
    private Button systemcarema;
    private Button systemcarema2;
    private Button systemvideo;
    private ImageView image1;
    private VideoView videoview;
    private String mCurrentPhotoPath;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        creatView();
    }

    private void creatView() {
        systemcarema = (Button) findViewById(R.id.systemcarema);
        systemcarema2 = (Button) findViewById(R.id.systemcarema2);
        systemvideo = (Button) findViewById(R.id.systemvideo);
        image1 = (ImageView) findViewById(R.id.image1);
        videoview = (VideoView) findViewById(R.id.videoview);

        systemcarema.setOnClickListener(this);
        systemcarema2.setOnClickListener(this);
        systemvideo.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.systemcarema:
                dispatchTakePictureIntent();
                break;
            case R.id.systemcarema2:
                dispatchTakePictureandSaveIntent();
                break;
            case R.id.systemvideo:
                dispatchTakeVideoIntent();
                break;
        }
    }

    /**
     * 调用系统相机拍照返回所拍照片的小图
     **/
    private void dispatchTakePictureIntent() {
        Intent takePicktureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicktureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePicktureIntent, REQUEST_IMAGE_CAMERA);
        }
    }

    /**
     * 调用系统相机拍照并保存至指定位置
     **/
    private void dispatchTakePictureandSaveIntent() {
        Intent takePicktureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePicktureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (photoFile != null) {
                takePicktureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                startActivityForResult(takePicktureIntent, REQUEST_TAKE_PHOTE);
            }
        }
    }

    /**
     * 调用相机摄像
     **/
    private void dispatchTakeVideoIntent() {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        /**Android的相机应用会将照片作为一个小的 Bitmap(缩略图) 对象打包进Intent中，
         * 然后通过 onActivityResult() 方法将该Intent返回。具体的Bitmap对象会附加在键”data”后
         * 从”data”中获得的缩略图像可能适合用于图标上面，但不适用于很大的图标。处理全尺寸的图像需要花费更多一点的工作**/
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAMERA) {
                Bundle extra = data.getExtras();
                Bitmap bitmap = (Bitmap) extra.get("data");
                image1.setImageBitmap(bitmap);
            } else if (requestCode == REQUEST_TAKE_PHOTE) {
                MyToast.showShort(context,"照片已经保存至" + mCurrentPhotoPath);
            } else if (requestCode == REQUEST_VIDEO_CAPTURE) {
                Uri videoUri = data.getData();
                videoview.setVideoURI(videoUri);
                videoview.start();
            }
        }
    }


    /**
     * 通过日期时间戳来对新照片返回一个唯一的文件名称：
     **/
    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        Log.e("kkk", mCurrentPhotoPath);
        return image;
    }

    /**
     * 调用系统的扫描器来添加你的照片到媒体扫描器(Media Provider)的数据库中，
     * 使得这些照片可以被系统的相册应用或者其它APP可访问
     **/
    private void galleryAddPic(String mCurrentPhotoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);
        Uri uri = Uri.fromFile(f);
        mediaScanIntent.setData(uri);
        this.sendBroadcast(mediaScanIntent);
    }

    /**
     * JPEG图像缩放到目标视图的尺寸大小的方式来显著的降低堆内存的使用量
     **/
    private void setPic(ImageView imageView, String mCurrentPhotoPath) {
        int targetW = imageView.getWidth();
        int targetH = imageView.getHeight();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, options);
        int photoW = options.outWidth;
        int photoH = options.outHeight;
        int scaleFactor = Math.min(photoW / targetW, photoH / targetH);
        options.inJustDecodeBounds = false;
        options.inSampleSize = scaleFactor;
        options.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, options);
        imageView.setImageBitmap(bitmap);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
