package com.whyshoudi.translation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class phrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        //        work done under this comment
        ArrayList<word> numbers = new ArrayList<word>();
//        to add new words to the array list
        numbers.add(new word("one", "lutti"));
        numbers.add(new word("two", "otiiko"));
        numbers.add(new word("three", "tolookosu"));
        numbers.add(new word("four", "oyyisa"));
        numbers.add(new word("five", "massokka"));
        numbers.add(new word("six", "temmokka"));
        numbers.add(new word("seven", "kenekaku"));
        numbers.add(new word("eight", "kawinta"));
        numbers.add(new word("nine", "wo’e"));
        numbers.add(new word("ten", "na’aacha"));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter Adapter = new WordAdapter(this, numbers,R.color.category_phrases);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(Adapter);



    }
}
