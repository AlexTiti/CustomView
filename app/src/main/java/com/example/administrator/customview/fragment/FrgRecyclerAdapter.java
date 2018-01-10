package com.example.administrator.customview.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.customview.Bean;
import com.example.administrator.customview.R;
import com.example.administrator.customview.recyclerview.enclosure.BaseRecyAdapter;

import java.util.List;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/10
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class FrgRecyclerAdapter extends BaseRecyAdapter<FrgRecyclerAdapter.ViewHolder,Bean>{

    private List<Bean> beanList;
    private Context context;



    public FrgRecyclerAdapter(Context context,List<Bean> beanList) {
        this.beanList = beanList;
        this.context = context;
    }

    @Override
    public List<Bean> getDataList() {
        return beanList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageDrawable(context.getResources().getDrawable(beanList.get(position).getImageId()));
        holder.textView.setText(beanList.get(position).getTittle());

    }

    @Override
    public int getItemCount() {
        return beanList == null ? 0 : beanList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView textView;
        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
