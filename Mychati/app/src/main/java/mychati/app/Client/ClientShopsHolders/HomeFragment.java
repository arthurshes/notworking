package mychati.app.Client.ClientShopsHolders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import mychati.app.Client.Aptekaragment;
import mychati.app.Client.BuildFragment;
import mychati.app.Client.CakeFragment;
import mychati.app.Client.MebelFragment;
import mychati.app.Client.OdejdaFragment;
import mychati.app.Client.OrtoFragment;
import mychati.app.ElectronicaFragment;
import mychati.app.IgrushkiFragment;
import mychati.app.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
View Homefrag;
    TabLayout tabLayout1;
    ViewPager viewPager1;
    FragAdapter adapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
     Homefrag= inflater.inflate(R.layout.fragment_home, container, false);
     tabLayout1=Homefrag.findViewById(R.id.tab_layotfrag);
     viewPager1=Homefrag.findViewById(R.id.viewPagerfrag);
     adapter=new FragAdapter(getActivity().getSupportFragmentManager());
        adapter.AddFragmebt(new CakeFragment(),"Кондитерские");
        adapter.AddFragmebt(new MebelFragment(),"Мебель");
        adapter.AddFragmebt(new BuildFragment(),"Строй.магазины");
        adapter.AddFragmebt(new OrtoFragment(),"Ортопедия");
        adapter.AddFragmebt(new Aptekaragment(),"Аптеки");
        adapter.AddFragmebt(new OdejdaFragment(),"Одежда и обувь");
        adapter.AddFragmebt(new IgrushkiFragment(),"Игрушки");
        adapter.AddFragmebt(new ElectronicaFragment(),"Электронника");
        viewPager1.setAdapter(adapter);
        tabLayout1.setupWithViewPager(viewPager1);

        return Homefrag;
    }

    private class FragAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragmentArrayList=new ArrayList<>();
        ArrayList<String>stringArrayList=new ArrayList<>();



        public void AddFragmebt(Fragment fragment,String s){
            fragmentArrayList.add(fragment);
            stringArrayList.add(s);
        }
        public FragAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentArrayList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentArrayList.size();
        }  @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return stringArrayList.get(position);
        }
    }
}