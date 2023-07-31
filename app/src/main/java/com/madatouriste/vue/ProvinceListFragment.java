package com.madatouriste.vue;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.madatouriste.R;
import com.madatouriste.adapter.ProvinceAdapter;
import com.madatouriste.databinding.FragmentProvinceListBinding;
import com.madatouriste.modele.Province;
import com.madatouriste.utils.Utils;

import java.util.ArrayList;
public class ProvinceListFragment extends Fragment {
    FragmentProvinceListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProvinceListBinding.inflate(inflater, container, false);
        init();
        return binding.getRoot();
    }

    public void init() {
        ArrayList<String> imageUrls = Utils.getImage();

        String[] nameList = {"Pasta", "Maggi", "Cake", "Pancake", "Pizza", "Burgers", "Fries"};
        String[] timeList = {"30 mins", "2 mins", "45 mins", "10 mins", "60 mins", "45 mins", "30 mins"};

        ArrayList<Province> dataArrayList = new ArrayList<>();
        for (int i = 0; i < imageUrls.size(); i++) {
            Province province = new Province(nameList[i], imageUrls.get(i));
            dataArrayList.add(province);
        }

        ProvinceAdapter listAdapter = new ProvinceAdapter(getActivity(), dataArrayList);
        binding.listview.setAdapter(listAdapter);
        binding.listview.setClickable(true);

        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ProvinceDetailFragment detail = new ProvinceDetailFragment();
                Bundle b = new Bundle();
                b.putSerializable("province", dataArrayList.get(i) );
                detail.setArguments(b);
                Utils.fragmentNavig(getActivity(),detail);
            }
        });
    }
}
