package com.example.dyno;



import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;




import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Model> mUploads;
    private AdapterView.OnItemClickListener mListener;

    public ImageAdapter(Context context, List<Model> uploads) {
        mContext = context;
        mUploads = uploads;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        Model uploadCurrent = mUploads.get(position);
        holder.tutor_name.setText(uploadCurrent.getTutor_name());
        holder.courses.setText(uploadCurrent.getCourses());
        holder.timings.setText(uploadCurrent.getTimings());
        holder.base_price.setText(uploadCurrent.getBase_price());
        holder.phone.setText(uploadCurrent.getPhone());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }



    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView tutor_name, courses, timings, base_price, phone;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);

            tutor_name = itemView.findViewById(R.id.tutor);
            courses = itemView.findViewById(R.id.courses);
            timings = itemView.findViewById(R.id.timings);
            base_price = itemView.findViewById(R.id.base);
            phone = itemView.findViewById(R.id.mail);

        }
    }
}
