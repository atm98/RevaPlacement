package com.agnt45.revaplacement.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.agnt45.revaplacement.Classes.CompanyInfo;
import com.agnt45.revaplacement.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.ObservableSnapshotArray;
import com.squareup.picasso.Picasso;

public class CompanyAdapter extends FirestoreRecyclerAdapter<CompanyInfo,CompanyAdapter.CompanyViewHolder>{

    public CompanyAdapter(@NonNull FirestoreRecyclerOptions<CompanyInfo> options) {
        super(options);
    }

    @NonNull
    @Override
    public ObservableSnapshotArray<CompanyInfo> getSnapshots() {
        return super.getSnapshots();
    }

    @Override
    protected void onBindViewHolder(@NonNull CompanyViewHolder holder, int position, @NonNull CompanyInfo model) {
        holder.cName.setText(model.getCompanyName());
        holder.cDesc.setText(model.getCompanyDesc());
        Picasso.get().load(model.getCompantPic()).into(holder.pic);
        holder.cPay.setText(String.valueOf(model.getCompanyPay())+" PA");
        holder.cCoutoff.setText(String.valueOf(model.getCompanyCutoff())+" CGPA");
    }

    @NonNull
    @Override
    public CompanyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_card_company,parent,false);
        return new CompanyViewHolder(view);
    }

    public class CompanyViewHolder extends RecyclerView.ViewHolder{

        ImageView pic;
        TextView cName,cDesc,cPay,cCoutoff;
        public CompanyViewHolder(View itemView) {
            super(itemView);
            pic = itemView.findViewById(R.id.pic_company);
            cName = itemView.findViewById(R.id.name_company);
            cDesc = itemView.findViewById(R.id.desc_company);
            cPay =itemView.findViewById(R.id.pay_company);
            cCoutoff = itemView.findViewById(R.id.cuttoff_company);
        }
    }
}
