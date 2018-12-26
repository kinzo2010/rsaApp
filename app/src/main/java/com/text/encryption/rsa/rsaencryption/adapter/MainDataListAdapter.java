package com.text.encryption.rsa.rsaencryption.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.text.encryption.rsa.rsaencryption.DataBase.MainDataObject;
import com.text.encryption.rsa.rsaencryption.R;
import com.text.encryption.rsa.rsaencryption.databinding.MainDataItemBinding;
import com.text.encryption.rsa.rsaencryption.viewmodels.MainDataItemViewModel;
import java.util.ArrayList;
import java.util.List;

public class MainDataListAdapter extends RecyclerView.Adapter<MainDataListAdapter.ViewHolder>{

    List<MainDataItemViewModel> itemList = new ArrayList<>();

    public void setItemList(List<MainDataObject> mainDataObjectList) {

        itemList.clear();

        for(MainDataObject mainDataObject : mainDataObjectList) {

            itemList.add(new MainDataItemViewModel(mainDataObject));

        }

        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.main_data_item;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        MainDataItemBinding mainDataItemBinding;

        ViewHolder(View itemView) {
            super(itemView);
            mainDataItemBinding = DataBindingUtil.bind(itemView);
        }

        void bind(MainDataItemViewModel mainDataItemViewModel) {

            mainDataItemBinding.setItemViewModel(mainDataItemViewModel);

        }
    }

}
