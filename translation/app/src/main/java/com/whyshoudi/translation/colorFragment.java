package com.whyshoudi.translation;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class colorFragment extends Fragment {
    private MediaPlayer mediaPlay;
    private MediaPlayer.OnCompletionListener onComplete = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            if (focusChange == AudioManager.AUDIOFOCUS_GAIN){

                mediaPlay.start();
            }
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS){

                releaseMediaPlayer();

            }
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){

                mediaPlay.stop();
                mediaPlay.seekTo(0);
            }
        }
    };

    public colorFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_number, container, false);
        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<word> numbers = new ArrayList<word>();
//        to add new words to the array list
        numbers.add(new word("Black", "lutti", R.drawable.color_black,R.raw.color_black));
        numbers.add(new word("Brown", "otiiko",R.drawable.color_brown,R.raw.color_brown));
        numbers.add(new word("Red", "tolookosu",R.drawable.color_red,R.raw.color_red));
        numbers.add(new word("Dusty Yellow", "oyyisa",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        numbers.add(new word("Green", "massokka",R.drawable.color_green,R.raw.color_green));
        numbers.add(new word("Gray", "temmokka",R.drawable.color_gray,R.raw.color_gray));
        numbers.add(new word("Yellow", "kenekaku",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        numbers.add(new word("White", "kawinta",R.drawable.color_white,R.raw.color_white));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter Adapter = new WordAdapter(getActivity(), numbers,R.color.category_colors);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(Adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                word currentWord = numbers.get(position);
                releaseMediaPlayer();


                int request = audioManager.requestAudioFocus(onFocusChange,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (request == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlay = MediaPlayer.create(getActivity(), currentWord.getAudioResourceId());
                    mediaPlay.start();
                    mediaPlay.setOnCompletionListener(onComplete);
                }
            }
        });
        return rootView;
    }
    /**
     * Clean up the media player by releasing its resources.
     */
    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlay != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlay.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlay = null;
        }

    }


    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }
}
