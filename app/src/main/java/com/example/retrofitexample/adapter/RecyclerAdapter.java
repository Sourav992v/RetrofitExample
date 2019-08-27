package com.example.retrofitexample.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitexample.R;
import com.example.retrofitexample.databinding.ListViewDataBinding;
import com.example.retrofitexample.model.Post;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.NewViewHolder> {

    private List<Post> posts = new ArrayList<>();

    @NonNull
    @Override
    public NewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        ListViewDataBinding viewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                        R.layout.list_view,parent,false);

        return new NewViewHolder(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull NewViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    public void setData(List<Post> posts){
        this.posts = posts;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NewViewHolder extends RecyclerView.ViewHolder {

        ListViewDataBinding dataBinding;

        NewViewHolder(ListViewDataBinding dataBinding) {
            super(dataBinding.getRoot());
            this.dataBinding = dataBinding;
        }

        void bind(Post post){
            dataBinding.setVariable(BR.post,post);
            dataBinding.executePendingBindings();
        }
    }
}
