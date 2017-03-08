package com.example.akash.gallery;

import android.support.v7.app.AppCompatActivity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class ImageViewer extends AppCompatActivity {
ImageView image_viewer_inside,image_viewer_outside;


//FloatingActionButton share;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_viewer);
        image_viewer_inside= (ImageView) findViewById(R.id.image_viewer_inner);
        image_viewer_outside= (ImageView) findViewById(R.id.image_viewer_outer);
//        share= (FloatingActionButton) findViewById(R.id.share);
        Intent rec_intent=getIntent();
        int position=rec_intent.getIntExtra("position",0);
//        share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_SEND);
//                intent.setType("image/jpeg");
//
//            }
//        });
       Glide.with(getApplicationContext()).load(MainActivity.arrayList.get(position).getAlbum_id()).into(image_viewer_inside);
        Glide.with(getApplicationContext()).load(MainActivity.arrayList.get(position).getAlbum_id())
       .bitmapTransform(new BlurTransformation(getApplicationContext()))
        .into(image_viewer_outside);

    }


}
