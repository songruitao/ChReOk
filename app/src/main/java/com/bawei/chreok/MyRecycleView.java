package com.bawei.chreok;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.chreok.bean.Bean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/5/14.
 */
public class MyRecycleView extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM = 0;//条目1
    private static final int ITEM1 = 1;//条目1
    private static final int ITEM2 = 2;//条目1

    private List<Bean.DataBean> list;
    private Context context;
    private final LayoutInflater inflater;

    public MyRecycleView(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType==ITEM){
            view= inflater.from(context).inflate(R.layout.item_two,parent,false);
            return new ViewHolder1(view);
        }else if (viewType==ITEM1){
            view=inflater.from(context).inflate(R.layout.item_three,parent,false);
            return new ViewHolder2(view);
        }else if (viewType==ITEM2){
            view=inflater.from(context).inflate(R.layout.item_four,parent,false);
            return new ViewHolder3(view);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.itemView.setTag(position);
        if(holder instanceof  ViewHolder1){
            ViewHolder1 holder1= (ViewHolder1) holder;
            holder1.two_tv.setText(list.get(position).getGoods_name());
            Glide.with(context).load(list.get(position).getGoods_img()).into(holder1.two_im);
        }else if (holder instanceof  ViewHolder2){
            ViewHolder2 holder2= (ViewHolder2) holder;
            holder2.three_tv.setText(list.get(position).getGoods_name());
            Glide.with(context).load(list.get(position).getGoods_img()).into(holder2.three_im01);
            Glide.with(context).load(list.get(position).getGoods_img()).into(holder2.three_im02);

        }else if (holder instanceof ViewHolder3){
            ViewHolder3 holder3= (ViewHolder3) holder;
            holder3.four_tv.setText(list.get(position).getGoods_name());
            Glide.with(context).load(list.get(position).getGoods_img()).into(holder3.four_im);
        }


    }

    @Override
    public int getItemViewType(int position) {
        int count = position/3;
        switch (count){
            case 0:
                return ITEM;
            case 1:
                return ITEM1;
            case 2:
                return ITEM2;
        }
        return ITEM;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private final TextView two_tv;
        private final ImageView two_im;

        public ViewHolder1(View itemView) {
            super(itemView);
            two_tv = (TextView) itemView.findViewById(R.id.two_tv);
            two_im = (ImageView) itemView.findViewById(R.id.two_im);

        }
    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView three_tv;
        private final ImageView three_im01;
        private final ImageView three_im02;

        public ViewHolder2(View itemView) {
            super(itemView);
            three_tv = (TextView) itemView.findViewById(R.id.three_tv);
            three_im01 = (ImageView) itemView.findViewById(R.id.three_im01);
            three_im02 = (ImageView) itemView.findViewById(R.id.three_im02);
        }
    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {

        private final TextView four_tv;
        private final ImageView four_im;

        public ViewHolder3(View itemView) {
            super(itemView);
            four_tv = (TextView) itemView.findViewById(R.id.four_tv);
            four_im = (ImageView) itemView.findViewById(R.id.four_im);
        }
    }
}
