package com.packtpub.androidcookbook.searchview;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchResultActivity extends AppCompatActivity {

    TextView mTextViewSearchResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        mTextViewSearchResult = (TextView)findViewById(R.id.textViewSearchResult);

        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            handleSearch(getIntent().getStringExtra(SearchManager.QUERY));
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (Intent.ACTION_SEARCH.equals(getIntent().getAction())) {
            handleSearch(intent.getStringExtra(SearchManager.QUERY));
        }
    }

    private void handleSearch(String searchQuery) {
        mTextViewSearchResult.setText(searchQuery);
    }
}
