package com.example.akash.gallery;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ToxicBakery.viewpager.transforms.ZoomOutTranformer;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    static int[] images = {R.drawable.back0,
            R.drawable.back1,
            R.drawable.back2,
            R.drawable.back3,
            R.drawable.back4,
            R.drawable.back5,
            R.drawable.back6,
            R.drawable.back7,
            R.drawable.back8,
            R.drawable.back9
    };
    AutoScrollViewPager viewPager;
    ViewAdapter viewAdapter;
   public static ArrayList<albums> arrayList = getData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Gallery");
        setContentView(R.layout.activity_main);
        final AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                recyclerView = (RecyclerView) findViewById(R.id.rv);
                DisplayFragment fragment = new DisplayFragment();
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(R.id.linear_layout, fragment, "MyFrag");
                transaction.commit();

                return null;
            }

        };
        asyncTask.execute();
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this,2);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new MyAdapter());
        viewPager = (AutoScrollViewPager) findViewById(R.id.viewpager);
        viewAdapter = new ViewAdapter(MainActivity.this, images);
        viewPager.setCycle(true);
        viewPager.setAdapter(viewAdapter);
        viewPager.setPageTransformer(true, new ZoomOutTranformer());
        viewPager.startAutoScroll();
        viewPager.setScrollDurationFactor(4);
        viewPager.setCycle(true);
        viewPager.setBorderAnimation(false);

    }

    public class viewHolder extends RecyclerView.ViewHolder {
        private ImageView iv;

        public viewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv_card1);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<viewHolder> {

        @Override
        public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            viewHolder holder = new viewHolder(getLayoutInflater().inflate(R.layout.view, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(viewHolder holder, final int position) {
            Glide.with(getApplicationContext()).load(arrayList.get(position).getAlbum_id()).into(holder.iv);
//            Toast.makeText(MainActivity.this, "position"+position, Toast.LENGTH_SHORT).show();
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, ImageViewer.class);
                    intent.putExtra("position",position);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }
    }

    public static ArrayList<albums> getData() {
        ArrayList<albums> data = new ArrayList<>();
        for (int i = 0; i < images.length; i++) {
            albums album = new albums(i);
            album.setAlbum_id(images[i]);
            data.add(album);
        }
        return data;
    }


}
