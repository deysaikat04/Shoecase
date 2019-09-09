package com.example.saikatdey.shoecase;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class Search extends AppCompatActivity {

      String[] items;
    ArrayList<String> listItems;
    ArrayAdapter<String > adapter;
    ListView listView;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        listView = (ListView) findViewById(R.id.listView);
        editText = (EditText) findViewById(R.id.txtsearch);
        initList();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals("")){
                    initList();
                }
                else{
                    searchItem(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void searchItem(String textToSearch){
        for(String item:items){
            if(!item.contains(textToSearch)){
                listItems.remove(item);
            }

            adapter.notifyDataSetChanged();
        }
    }


    public  void initList(){
        items = new String[]{"Nike FLEX 2017 RN Running Shoes For Men  (Black)",
                "Nike VAPOR 12 CLUB FG/MG Football Shoes For Men ",
                "Puma Slyde NU IDP Sneakers For Men  (Grey)",
                "Clarks Coral Reef Oyster Combi Slip On shoes For Women",
                "London Steps Party Wear For Women (Red)",
                "Myra Women Back Strap Maroon Flats",
                "NMD Brown Formal Lace-Up Shoes For Boys& Men ",
                "Gubatti Men Formal Shoes Slip On For Men  (Black)",
                "REEBOK CLASSICS TREAD MAX Sneakers Blue",
                "Sparx Canvas Shoes For Men  (Navy)",
                "Long Walk Girls Canvas Heel Sneakers For Women "
        };
        listItems = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.txtitem,listItems);
        listView.setAdapter(adapter);

    }
}
