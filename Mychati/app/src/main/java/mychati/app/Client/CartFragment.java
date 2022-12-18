package mychati.app.Client;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.HashMap;

import mychati.app.Client.CartAdapter.CartAdapter;
import mychati.app.Client.CartHolder.cartHolder;
import mychati.app.Client.ClientBottomInfo.ClientInfoFromZakaz;
import mychati.app.Client.ClientShopsHolders.ClientShopHolder;
import mychati.app.Client.ClientShopsModel.ShopAdapter;
import mychati.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {

    private DatabaseReference karzinaRef;
    private DatabaseReference shopRef;
    private RoundedImageView shopimege;
    private int overTotalPrice=0;
    private AppCompatButton buybutton;

    private TextView textpricecarti,textkarzininziv;
    private RecyclerView reccart;
    private FirebaseAuth mAuth;
    private DatabaseReference hophh;
    private ImageView deletezakazcart;
    RecyclerView.LayoutManager layoutManager;
View cart;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CartFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CartFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
   cart= inflater.inflate(R.layout.fragment_cart, container, false);
reccart=cart.findViewById(R.id.reccart);
        layoutManager = new LinearLayoutManager(this.getContext());

        deletezakazcart=cart.findViewById(R.id.deletezakazcart);
        textpricecarti=cart.findViewById(R.id.textpricecarti);
        buybutton=cart.findViewById(R.id.buybutton);
        shopimege=cart.findViewById(R.id.shopimege);
        reccart.setHasFixedSize(true);
        reccart.setLayoutManager(layoutManager);
        karzinaRef= FirebaseDatabase.getInstance().getReference().child("DoCart");
        shopRef=FirebaseDatabase.getInstance().getReference().child("shops");
        textkarzininziv=cart.findViewById(R.id.textkarzininziv);
        mAuth=FirebaseAuth.getInstance();




deletezakazcart.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        karzinaRef.child(mAuth.getCurrentUser().getUid()).removeValue();

    }
});







karzinaRef.child(mAuth.getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        if (snapshot.exists() && snapshot.getChildrenCount() > 0) {

        }else{
            deletezakazcart.setVisibility(View.INVISIBLE);
            shopimege.setVisibility(View.INVISIBLE);
            textkarzininziv.setVisibility(View.INVISIBLE);
            textpricecarti.setVisibility(View.INVISIBLE);
            buybutton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }
});













   return cart;

    }
    @Override
    public void onStart() {
        super.onStart();


        FirebaseRecyclerOptions<CartAdapter> options=new FirebaseRecyclerOptions.Builder<CartAdapter>()
                .setQuery(karzinaRef.child(mAuth.getCurrentUser().getUid()), CartAdapter.class).build();
        FirebaseRecyclerAdapter<CartAdapter, cartHolder> adapter=new FirebaseRecyclerAdapter<CartAdapter, cartHolder>(options) {
            @Override
            protected void onBindViewHolder( @androidx.annotation.NonNull cartHolder holder, int position,  @androidx.annotation.NonNull CartAdapter model) {

             holder.textprice.setText(model.getPrice()+"₽");
             holder.textmany.setText(model.getTovarValue());
             holder.textname.setHint(model.getTovarcartShopuid());
             holder.textname.setText(model.getTovarname());
             holder.textplus.setHint(model.getProductId());
          int oneTypeProductPrice=(Integer.valueOf(model.getPrice()))*Integer.valueOf(model.getTovarValue());
holder.textminus.setHint(String.valueOf(oneTypeProductPrice));


                Log.d("value",String.valueOf(oneTypeProductPrice));

buybutton.setText(String.valueOf(overTotalPrice+oneTypeProductPrice));



holder.deletetovar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        karzinaRef.child(mAuth.getCurrentUser().getUid()).child(holder.textplus.getHint().toString()+mAuth.getCurrentUser().getUid()).removeValue();


        Toast.makeText(cart.getContext(), " Товар удален", Toast.LENGTH_SHORT).show();
    }
});


                Transformation transformation=new RoundedTransformationBuilder().borderColor(Color.WHITE).borderWidthDp(3).cornerRadius(9).oval(false).build();


                Picasso.get().load(model.getTovarImage()).transform(transformation).into(holder.cartProductImage);




                holder.textplus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String  CurrentValue=holder.textmany.getText().toString();
                        int value=Integer.parseInt(CurrentValue);
                        value++;
                        holder.textmany.setText(String.valueOf(value));


                        HashMap<String,Object> docart=new HashMap<>();

                        docart.put("TovarValue",holder.textmany.getText().toString());
                     karzinaRef.child(mAuth.getCurrentUser().getUid()).child(holder.textplus.getHint().toString()+mAuth.getCurrentUser().getUid()).updateChildren(docart).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {


                                } else {


                                }
                            }
                        });

                    }
                });

                holder.textminus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String  CurrentValue=holder.textmany.getText().toString();
                        int value=Integer.parseInt(CurrentValue);
                        value--;
                        holder.textmany.setText(String.valueOf(value));



                        HashMap<String,Object> docart=new HashMap<>();

                        docart.put("TovarValue",holder.textmany.getText().toString());
                        karzinaRef.child(mAuth.getCurrentUser().getUid()).child(holder.textplus.getHint().toString()+mAuth.getCurrentUser().getUid()).updateChildren(docart).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {


                                } else {


                                }
                            }
                        });
                    }
                });

       ItemTouchHelper.SimpleCallback itemTouch=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

           }
       };

                String s="0";
                int n1=Integer.parseInt(holder.textmany.getText().toString());
                int n2=Integer.parseInt(s);


if (n1==n2){
    karzinaRef.child(mAuth.getCurrentUser().getUid()).child(holder.textplus.getHint().toString()+mAuth.getCurrentUser().getUid()).removeValue();
}else{

}





                shopRef.child(model.getTovarcartShopuid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists() && snapshot.getChildrenCount() > 0) {
                            Transformation transformation=new RoundedTransformationBuilder().borderColor(Color.WHITE).borderWidthDp(3).cornerRadius(9).oval(false).build();
                            Picasso.get().load(snapshot.child("MagLogo").getValue().toString()).transform(transformation).into(shopimege);

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });






buybutton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {



Intent nba=new Intent(getActivity(), ClientInfoFromZakaz.class);
nba.putExtra("ProductId",model.getProductId());
nba.putExtra("TovarValue",holder.textmany.getText().toString());
startActivity(nba);










    }
});



            }

            @Override
            public cartHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart,parent,false);
              cartHolder holder=new cartHolder(view);


                return holder;
            }
        };
      reccart.setAdapter(adapter);
        adapter.startListening();

    }
}