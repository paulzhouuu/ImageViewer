package com.mconnect.mobile.imageviewer.ui.view.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mconnect.mobile.imageviewer.R;
import com.mconnect.mobile.imageviewer.domain.model.Image;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created using Android Studio (Beta) 0.8.14
 * www.101apps.co.za
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    List<Image> data = Collections.emptyList();
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, List<Image> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.activity_main_recycle_row_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Image current = data.get(i);
        Uri uri = Uri.parse(current.getLink());
        Context context = viewHolder.articleImage.getContext();
        Picasso.with(context).load(uri)
                .resize(500, 500)
                .centerInside()
                .into(viewHolder.articleImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView articleImage;

        public ViewHolder(View itemView) {
            super(itemView);
            articleImage = (ImageView) itemView.findViewById(R.id.imageView2);
        }
    }
}


