package com.lapremavera.materialtodolist;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ToDoListActivity extends AppCompatActivity {

    private EditText mItemInput;
    private FloatingActionButton mAddButton;
    private ListView mDynamicListView;
    private List<String> mItemsList;
    private ArrayAdapter<String> mItemListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        mItemInput = (EditText) findViewById(R.id.itemEditText);
        mAddButton =(FloatingActionButton)findViewById(R.id.add_item_button);
        mDynamicListView = (ListView) findViewById(R.id.itemListView);

        mItemsList = new ArrayList<>();
        mItemsList.add("Android ATC");

        mItemListAdapter = new ArrayAdapter<>(
                ToDoListActivity.this,
                R.layout.list_individual_item,
                R.id.listItemText,
                mItemsList);

        mDynamicListView.setAdapter(mItemListAdapter);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = mItemInput.getText().toString();
                if (todoItem.length() >0)
                {
                    mItemsList.add(todoItem);
                    mItemListAdapter.notifyDataSetChanged();
                    mItemInput.setText("");
                }
            }
        });

        mDynamicListView.setOnItemLongClickListener(
                new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(
                            AdapterView<?> parent,
                            View view,
                            int position,
                            long id) {
                        mItemsList.remove(position);
                        mItemListAdapter.notifyDataSetChanged();
                        return true;
                    }
                }
        );
        mDynamicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAddButton.hide();
                String transitionName = getString(R.string.transition_name);
                View sharedView = view.findViewById(R.id.listItemText);
                Intent intent = new Intent(ToDoListActivity.this, ToDoDetailsActivity.class);
                String textOfClickedItem = mItemListAdapter.getItem(position);
                intent.putExtra("Name", textOfClickedItem);

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(ToDoListActivity.this, sharedView, transitionName);
                startActivity(intent, transitionActivityOptions.toBundle());

            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        mAddButton.show();


    }
}
