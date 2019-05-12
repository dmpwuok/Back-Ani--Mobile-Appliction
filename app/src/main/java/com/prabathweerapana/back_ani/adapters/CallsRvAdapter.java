package com.prabathweerapana.back_ani.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.prabathweerapana.back_ani.R;
import com.prabathweerapana.back_ani.models.ModelCalls;

import java.util.List;

public class CallsRvAdapter extends RecyclerView.Adapter<CallsRvAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;

    private List<ModelCalls> mlistCalls;

    public CallsRvAdapter(Context context, List<ModelCalls> listCalls) {
        mContext = context;
        mlistCalls = listCalls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        layoutInflater = LayoutInflater.from(mContext);


        View view = layoutInflater.inflate(R.layout.item_calls, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView number,name, duration, date;


        name = holder.name;
        number = holder.number;
        duration = holder.duration;
        date = holder.date;

        number.setText(mlistCalls.get(position).getNumber());
        name.setText(mlistCalls.get(position).getName());
        duration.setText(mlistCalls.get(position).getDuration());
        date.setText(mlistCalls.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return mlistCalls.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,number ,duration, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.contact_name);
            number=itemView.findViewById(R.id.contact_number);
            duration = itemView.findViewById(R.id.call_duration);
            date = itemView.findViewById(R.id.call_date);

        }
    }

}
