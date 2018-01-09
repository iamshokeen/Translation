    package com.whyshoudi.translation;

    import android.app.Activity;
    import android.content.Intent;
    import android.media.MediaPlayer;
    import android.provider.UserDictionary;
    import android.support.annotation.NonNull;
    import android.support.annotation.Nullable;
    import android.support.v4.content.ContextCompat;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.AdapterView;
    import android.widget.ArrayAdapter;
    import android.widget.ImageView;
    import android.widget.LinearLayout;
    import android.widget.ListView;
    import android.widget.TextView;

    import java.util.ArrayList;

    import static android.provider.AlarmClock.EXTRA_MESSAGE;

    /**
     * Created by Saksham Raj Shokeen on 12/21/2017.
     */

    public class WordAdapter extends ArrayAdapter<word> {
        private int mColorId;

//   My Constructor
//   Here @param is numbers  which is a ArrayList<word> data type

        public WordAdapter (Activity context , ArrayList<word> numbers,int colorId){
            super (context,0,numbers);
            mColorId=colorId;
        }



//   Overriding the default getView command because we're passing 2 Strings now

        @NonNull
        @Override
        public View getView(int position, @Nullable final View convertView, @NonNull ViewGroup parent) {

//   Called in case there are no recycled view left

            View listItemView = convertView;
            if(listItemView == null) {
                listItemView = LayoutInflater.from(getContext()).inflate(
                        R.layout.list_layout, parent, false);
            }

//   @param position tells me the position of counted in the array list
            final word currentWord = getItem(position);
//   setting my miwok Text view
            TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok);
//   passing the miwok text
            miwokTextView.setText(currentWord.getMiwokTranslation());

//  setting my english text view
            TextView defaultTextView = (TextView) listItemView.findViewById(R.id.english);
//   passing the english word
            defaultTextView.setText(currentWord.getDefaultTranslation());




//   setting the image view
            ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
//   passing the image
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
            View textLayout = listItemView.findViewById(R.id.texts);
            int color = ContextCompat.getColor(getContext(),mColorId);
            textLayout.setBackgroundColor(color);


//            play button confuguration
            ImageView play = (ImageView) listItemView.findViewById(R.id.play);
            if (mColorId==R.color.category_phrases) {
                play.setVisibility(View.INVISIBLE);
            }



 // so that it can be shown in the ListView
            return listItemView;
        }

    }

