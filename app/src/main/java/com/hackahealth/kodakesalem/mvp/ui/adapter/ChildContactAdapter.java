package com.hackahealth.kodakesalem.mvp.ui.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hackahealth.kodakesalem.R;
import com.hackahealth.kodakesalem.mvp.objects.ChildContact;
import com.hackahealth.kodakesalem.mvp.ui.ContactSelectFragment;

import java.util.ArrayList;
import java.util.List;

import android.widget.FrameLayout;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by siavash on 5/20/16.
 */
public class ChildContactAdapter extends RecyclerView.Adapter<ChildContactAdapter.ChildViewHolder>{
    private final Context context;
    private final ContactSelectFragment.CallbackClass callbackClass;
    private List<ChildContact> childContacts;

    public ChildContactAdapter(Context context,List<ChildContact> childContacts,ContactSelectFragment.CallbackClass callbackClass) {
        this.context=context;
        this.childContacts=childContacts;
        this.callbackClass=callbackClass;

    }

    @Override
    public ChildViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.child_contact_row, parent, false);
        ChildViewHolder dataObjectHolder = new ChildViewHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(ChildViewHolder holder, int position) {
        final int pos=position;
        holder.tvName.setText(childContacts.get(position).getName());
        holder.tvNum.setText(childContacts.get(position).getId());
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callbackClass.onItemClick(pos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return childContacts.size();
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.contacot_row_tvName)
        TextView tvName;
        @Bind(R.id.contacot_row_tvNum)
        TextView tvNum;
        @Bind(R.id.contacot_row_rootview)
        FrameLayout rootView;

        public ChildViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public void cleardata(){
        childContacts.clear();
        notifyDataSetChanged();
    }
    public void addData(List<ChildContact> childContactList){
        childContacts.addAll(childContactList);
        notifyDataSetChanged();
    }
}
