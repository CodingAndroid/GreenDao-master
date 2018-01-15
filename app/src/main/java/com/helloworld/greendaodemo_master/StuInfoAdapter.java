package com.helloworld.greendaodemo_master;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

/**
 * Created by lihui1 on 2017/10/24.
 */

public class StuInfoAdapter extends RecyclerView.Adapter<StuInfoAdapter.ViewHolder>{

    private List<Student> mStuList;

    private Context mContext;

    private int resourceId;

    private OnClickItemListener onClickItemListener;

    public interface OnClickItemListener{
        void OnItemClick(int position);
    }

    public void setOnClickItemListener(OnClickItemListener onClickItemListener){
        this.onClickItemListener = onClickItemListener;
    }

    public StuInfoAdapter(List<Student> stuList, Context context, int resourceId){
        this.mStuList = stuList;
        this.mContext = context;
        this.resourceId = resourceId;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(resourceId, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Student student = mStuList.get(position);
        holder.stu_id.setText(String.format(Locale.US, "%d", student.getStuId()));
        holder.stu_name.setText(student.getStuName());
        holder.stu_no.setText(student.getStuNo());
        holder.stu_score.setText(student.getStuScore());
        if (position %2 == 1){
            holder.mCardView.setCardBackgroundColor(Color.parseColor("#B4EEB4"));
        } else if (position %2 == 0){
            holder.mCardView.setCardBackgroundColor(Color.parseColor("#FF4081"));
        }
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickItemListener.OnItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStuList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView stu_id;
        TextView stu_name;
        TextView stu_no;
        TextView stu_sex;
        TextView stu_score;
        CardView mCardView;

        public ViewHolder(View view){
            super(view);
            mCardView = view.findViewById(R.id.card_view);
            stu_id = view.findViewById(R.id.id_tv);
            stu_no = view.findViewById(R.id.stu_no);
            stu_name = view.findViewById(R.id.id_name);

            stu_score = view.findViewById(R.id.stu_score);
        }
    }
}
