package com.krak.olympicmap.activity_main.fragments.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.krak.olympicmap.R;

import java.util.ArrayList;

public class MedalsAdapter extends RecyclerView.Adapter<MedalsAdapter.MedalHolder> {

    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Medal> medals;

    public MedalsAdapter(Context context, ArrayList<Medal> medals) {
        this.context = context;
        this.medals = medals;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MedalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.medal_item, parent, false);
        return new MedalHolder(view);
    }

    @Override
    public void onBindViewHolder(MedalsAdapter.MedalHolder holder, int position) {
        Medal medal = medals.get(position);
        holder.medalsCountry.setText(medal.getCountry());
        holder.medalsGold.setText(medal.getGold() + "");
        holder.medalsSilver.setText(medal.getSilver() + "");
        holder.medalsBronze.setText(medal.getBronze() + "");
        holder.medalsTotal.setText(medal.getTotal() + "");
    }

    @Override
    public int getItemCount() {
        return medals.size();
    }

    class MedalHolder extends RecyclerView.ViewHolder{

        private TextView medalsCountry, medalsGold, medalsSilver, medalsBronze, medalsTotal;

        public MedalHolder(View itemView) {
            super(itemView);
            this.medalsCountry = itemView.findViewById(R.id.medalsCountry);
            this.medalsGold = itemView.findViewById(R.id.medalsGold);
            this.medalsSilver = itemView.findViewById(R.id.medalsSilver);
            this.medalsBronze = itemView.findViewById(R.id.medalsBronze);
            this.medalsTotal = itemView.findViewById(R.id.medalsTotal);
        }
    }
}
