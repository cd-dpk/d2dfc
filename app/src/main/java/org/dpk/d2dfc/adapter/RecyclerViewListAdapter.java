package org.dpk.d2dfc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import org.dpk.d2dfc.data_models.OnRecyclerViewItemListener;

/**
 * Created by chandradasdipok on 5/17/2016.
 */
public class RecyclerViewListAdapter extends RecyclerView.Adapter<RecyclerViewListAdapter.MyViewHolder>{

    OnRecyclerViewItemListener onRecyclerViewItemListener;
    int itemNumber;
    int cardID;

    public RecyclerViewListAdapter(OnRecyclerViewItemListener onRecyclerViewItemListener, int cardID,int itemNumber ) {
        this.onRecyclerViewItemListener = onRecyclerViewItemListener;
        this.itemNumber = itemNumber;
        this.cardID = cardID;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(cardID,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        onRecyclerViewItemListener.listenItem(holder.myView, position);
    }

    @Override
    public int getItemCount() {
        return itemNumber;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        View myView;
        public MyViewHolder(View itemView) {
            super(itemView);
            myView = itemView;
        }
    }
}