package com.application.dbscgcollectionmanager.ui.search;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.application.dbscgcollectionmanager.MainActivity;
import com.application.dbscgcollectionmanager.database.DatabaseHelper;
import com.application.dbscgcollectionmanager.databinding.FragmentCollectionBinding;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener {

    //DB Stuff
    DatabaseHelper _db = new DatabaseHelper(this);

    //List stuffs
    private ArrayList<String> listItem;
    private ListView cardList;
    private ArrayAdapter adapter;

    private FragmentCollectionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SearchViewModel searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);

        binding = FragmentCollectionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCollection;
        searchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        this.listItem = new ArrayList<>();
        /*
        this.cardList = new ListView(items);
        this.cardList.setOnItemClickListener(this);*/

        listAllCards();

        return root;
    }

    public void listAllCards() {

        Cursor cursor = _db.getAllCards();

        /*
        listItem = new ArrayList<>();
        while (cursor.moveToNext()) {
            listItem.add(cursor.getString(1) + "\n" + cursor.getString(2));
        }
        //this.adapter = new ArrayAdapter<>(this, R.layout.row, listItem);
        this.cardList.setAdapter(adapter);*/

        _db.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}