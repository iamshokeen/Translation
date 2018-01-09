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
public class numberFragment extends Fragment {
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

    public numberFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_number, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
//        work done under this comment
        final ArrayList<word> numbers = new ArrayList<word>();
//        to add new words to the array list
        numbers.add(new word("one", "lutti", R.drawable.number_two, R.raw.number_two));
        numbers.add(new word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        numbers.add(new word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        numbers.add(new word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        numbers.add(new word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        numbers.add(new word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        numbers.add(new word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        numbers.add(new word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        numbers.add(new word("nine", "wo’e", R.drawable.number_nine, R.raw.number_nine));
        numbers.add(new word("ten", "na’aacha", R.drawable.number_ten, R.raw.number_ten));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter Adapter = new WordAdapter(getActivity(), numbers, R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list);


        // Make the {@link ListView} use the {@link ArrayAdapter} we created above, so that the
        // {@link ListView} will display list items for each word in the list of words.
        // Do this by calling the setAdapter method on the {@link ListView} object and pass in
        // 1 argument, which is the {@link ArrayAdapter} with the variable name itemsAdapter.
        listView.setAdapter(Adapter);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // activity_numbers.xml layout file.



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
