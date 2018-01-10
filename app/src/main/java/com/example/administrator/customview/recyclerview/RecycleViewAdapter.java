package com.example.administrator.customview.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.customview.R;
import com.example.administrator.customview.recyclerview.enclosure.BaseRecyAdapter;

import java.util.Collections;
import java.util.List;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/08
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public class RecycleViewAdapter extends BaseRecyAdapter<RecycleViewAdapter.ViewHolder,String> {

    private List<String> stringList ;

    @Override
    public List<String> getDataList() {
        return stringList;
    }

    public RecycleViewAdapter(List<String> stringList) {
        this.stringList = stringList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText( stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList == null ? 0 : stringList.size();
    }

    class  ViewHolder extends RecyclerView.ViewHolder{

        TextView textView ;
        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView_item);
        }
    }
}
