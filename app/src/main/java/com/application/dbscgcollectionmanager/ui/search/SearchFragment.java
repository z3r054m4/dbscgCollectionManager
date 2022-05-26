package com.application.dbscgcollectionmanager.ui.search;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import com.application.dbscgcollectionmanager.R;
import com.application.dbscgcollectionmanager.database.DatabaseHelper;
import com.application.dbscgcollectionmanager.databinding.FragmentCollectionBinding;

import java.util.ArrayList;

public class SearchFragment extends Fragment implements AdapterView.OnItemClickListener {


    //DB Stuff
    DatabaseHelper _db;

    //List stuffs
    private ArrayList<String> listItem;
    private ListView cardList;
    private ArrayAdapter adapter;
    Cursor c = null;

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

        System.out.println("-----" + getContext());
        listAllCards();

        return root;
    }

    public void listAllCards() {

        _db = new DatabaseHelper(getContext());
        SQLiteDatabase sqldb = _db.getReadableDatabase();
        c = _db.getAllCards(sqldb);

        listItem = new ArrayList<>();
        while (c.moveToNext()) {
            listItem.add(c.getString(1) + "\n" + c.getString(2));
        }
        this.adapter = new ArrayAdapter(getActivity(), R.layout.row, listItem);
        this.cardList.setAdapter(adapter);

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