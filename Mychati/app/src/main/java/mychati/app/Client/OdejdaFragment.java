package mychati.app.Client;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import mychati.app.Client.ClientShopsHolders.ClientShopHolder;
import mychati.app.Client.ClientShopsModel.ShopAdapter;
import mychati.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OdejdaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OdejdaFragment extends Fragment {
    private String odejda;
    View Odejda;
    private DatabaseReference apteka;
    RecyclerView.LayoutManager layoutManager;
    private RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OdejdaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OdejdaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OdejdaFragment newInstance(String param1, String param2) {
        OdejdaFragment fragment = new OdejdaFragment();
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
 Odejda=inflater.inflate(R.layout.fragment_odejda, container, false);

 recyclerView=Odejda.findViewById(R.id.rec_odejda);


        layoutManager=new LinearLayoutManager(this.getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);


        apteka= FirebaseDatabase.getInstance().getReference().child("shops");
 odejda="Одежда и обувь";





 return Odejda;
    }
    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<ShopAdapter> options=new FirebaseRecyclerOptions.Builder<ShopAdapter>()
                .setQuery(apteka.orderByChild("MagCategory").equalTo(odejda),ShopAdapter.class).build();
        FirebaseRecyclerAdapter<ShopAdapter, ClientShopHolder> adapter=new FirebaseRecyclerAdapter<ShopAdapter, ClientShopHolder>(options) {
            @Override
            protected void onBindViewHolder( @androidx.annotation.NonNull ClientShopHolder holder, int position,  @androidx.annotation.NonNull ShopAdapter model) {

                holder.aotekaname.setText(model.getMagName());
                holder.aotekaname.setHint(model.getMagUid());



                Transformation transformation=new RoundedTransformationBuilder().borderColor(Color.WHITE).borderWidthDp(3).cornerRadius(11).oval(false).build();


                Picasso.get().load(model.getMagLogo()).transform(transformation).into(holder.imageLogoApteka);
            }

            @Override
            public ClientShopHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_apteka,parent,false);
                ClientShopHolder holder=new ClientShopHolder(view);


                return holder;
            }
        };
        recyclerView.setAdapter(adapter);
        adapter.startListening();

    }
}