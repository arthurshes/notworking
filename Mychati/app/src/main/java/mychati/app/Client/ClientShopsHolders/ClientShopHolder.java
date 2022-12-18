package mychati.app.Client.ClientShopsHolders;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import mychati.app.Client.ItemClickListner.ItemClickListener;
import mychati.app.Client.TovarActivity;
import mychati.app.R;

public class ClientShopHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView aotekaname,textcityapt,textadresssapt;
    public RoundedImageView imageLogoApteka;
    public CardView card_item;
public ItemClickListener itemClickListener;

    public ClientShopHolder(View iteemView){
        super(iteemView);
        aotekaname=iteemView.findViewById(R.id.aotekaname);
       /// textcityapt=iteemView.findViewById(R.id.textcityapt);
       /// textadresssapt=iteemView.findViewById(R.id.textadresssapt);
        imageLogoApteka=iteemView.findViewById(R.id.imageLogoApteka);

        card_item=iteemView.findViewById(R.id.card_item);
card_item.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(view.getContext(), TovarActivity.class);
        intent.putExtra("ShopUid",aotekaname.getHint().toString());
view.getContext().startActivity(intent);
    }
});
    }
    public void setItemClickListner(ItemClickListener listner){this.itemClickListener=listner;}

    @Override
    public void onClick(View view){
       itemClickListener.onClick(view,getAdapterPosition(),false);
    }
}

