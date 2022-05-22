package com.application.dbscgcollectionmanager.ui.search;

import android.database.Cursor;
import android.widget.ArrayAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.application.dbscgcollectionmanager.database.DatabaseHelper;

import java.util.ArrayList;

public class SearchViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SearchViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Search for cards");

    }

    public LiveData<String> getText() {
        return mText;
    }

}