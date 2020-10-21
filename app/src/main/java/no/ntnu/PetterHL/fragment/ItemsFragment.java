package no.ntnu.PetterHL.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import no.ntnu.PetterHL.ApiClient;
import no.ntnu.PetterHL.Item;
import no.ntnu.PetterHL.ItemView.ItemRecViewAdapter;
import no.ntnu.PetterHL.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemsFragment extends Fragment {

    private RecyclerView itemRecView;
    private ItemRecViewAdapter adapter;
    private ArrayList<Item> items = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_items, container, false);

        itemRecView = view.findViewById(R.id.itemsRecView);

        setItemList();

        adapter = new ItemRecViewAdapter(getContext());
        adapter.setItems(items);

        itemRecView.setAdapter(adapter);
        itemRecView.setLayoutManager(new GridLayoutManager(getContext(),1));

        return view;

    }

    public void  setItemList() {

        Call<List<Item>> call = ApiClient
                .getSINGLETON()
                .getApi()
                .getAllItems();

        call.enqueue(new Callback<List<Item>>() {
            @Override
            public void onResponse(Call<List<Item>> call, Response<List<Item>> response) {
                if (response.isSuccessful()) {
                    items = (ArrayList<Item>) response.body();
                    System.out.println(response.body().toString());
                    adapter.setItems(items);
                }
                else {
                    Toast.makeText(getContext(), "Failed to fetch items. Try again", Toast.LENGTH_SHORT).show();
                };
            }

            @Override
            public void onFailure(Call<List<Item>> call, Throwable t) {

            }
        });
    }
}
