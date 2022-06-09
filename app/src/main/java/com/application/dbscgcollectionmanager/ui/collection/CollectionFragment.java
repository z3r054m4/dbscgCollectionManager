package com.application.dbscgcollectionmanager.ui.collection;

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

import com.application.dbscgcollectionmanager.R;
import com.application.dbscgcollectionmanager.database.CardsDatabase;
import com.application.dbscgcollectionmanager.database.UserDatabase;
import com.application.dbscgcollectionmanager.databinding.FragmentCollectionBinding;
import com.application.dbscgcollectionmanager.databinding.FragmentSearchBinding;

import java.util.ArrayList;

public class CollectionFragment extends Fragment implements AdapterView.OnItemClickListener {

    //DB Stuff
    UserDatabase _db;

    //List stuffs
    private ArrayList<String> listItem;
    private ArrayAdapter adapter;
    Cursor c = null;

    private FragmentCollectionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CollectionViewModel collectionViewModel =
                new ViewModelProvider(this).get(CollectionViewModel.class);

        binding = FragmentCollectionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textCollection;
        collectionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        //Prepping list
        final ListView lv = binding.lvCollection;
        lv.setOnItemClickListener(this);

        this.listItem = new ArrayList<>();

        listAllCards(lv);

        return root;
    }

    public void listAllCards(ListView lv) {
        _db = new UserDatabase(getContext());
        SQLiteDatabase sqldb = _db.getReadableDatabase();
        c = _db.getAll(sqldb);

        listItem = new ArrayList<>();
        while (c.moveToNext()) {
            listItem.add(c.getString(1) + "\n" + c.getString(2));
        }
        this.adapter = new ArrayAdapter(getActivity(), R.layout.row, listItem);
        lv.setAdapter(adapter);

        _db.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}