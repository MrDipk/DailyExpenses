package com.dip.dailyexpenses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    private List<Expenses_List> Exp_list;
    private Context context;


    public MyRecyclerViewAdapter(List<Expenses_List> Exp_list,Context context){
        this.Exp_list=Exp_list;
        this.context=context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.expenses_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        final Expenses_List ExpList= Exp_list.get(position);
        holder.txtamount.setText(ExpList.getAmount());
        holder.txttitle.setText(ExpList.getTitle());
        holder.txtdate.setText(ExpList.getDate());
        holder.uiid=ExpList.getUiid();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context=view.getContext();
                Intent intent= new Intent();
                intent =  new Intent(context, MainActivity.class);
                //intent.putExtra("name",SGList.getName());
                //intent.putExtra("contact",SGList.getPhno());
                intent.putExtra("uiid",ExpList.getUiid());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return Exp_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtamount,txttitle,txtdate;
        public String uiid;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtamount=(TextView) itemView.findViewById(R.id.textViewAmount);
            txttitle=(TextView) itemView.findViewById(R.id.textViewTitle);
            txtdate=(TextView)itemView.findViewById(R.id.textViewDate);
        }


    }

}
