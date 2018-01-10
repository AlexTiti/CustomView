package com.example.administrator.customview.recyclerview.enclosure;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

/**
 * <pre>
 *
 *   @author   :   Alex
 *   @e_mail   :   18238818283@sina.cn
 *   @time     :   2018/01/09
 *   @desc     :
 *   @version  :   V 1.0.9
 */

public abstract class BaseRecyAdapter<T extends RecyclerView.ViewHolder , V extends Object> extends RecyclerView.Adapter<T>{
    /**
     * 获取数据
     * @return
     */
    public abstract List<V>  getDataList();

}
