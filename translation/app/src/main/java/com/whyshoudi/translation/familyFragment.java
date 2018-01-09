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
public class familyFragment extends Fragment {
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

    public familyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_number, container, false);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        //        work done under this comment
        final ArrayList<word> family = new ArrayList<word>();
//        to add new words to the array list
        family.add(new word("father", "lutti",R.drawable.family_father,R.raw.family_father));
        family.add(new word("mother", "otiiko",R.drawable.family_mother,R.raw.family_mother));
        family.add(new word("son", "tolookosu",R.drawable.family_son,R.raw.family_son));
        family.add(new word("daughter", "oyyisa",R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new word("older bro", "massokka",R.drawable.family_older_brother,R.raw.family_father));
        family.add(new word("younger bro", "temmokka",R.drawable.family_younger_brother,R.raw.family_father));
        family.add(new word("older sis", "kenekaku",R.drawable.family_younger_sister,R.raw.family_father));
        family.add(new word("younger sis", "kawinta",R.drawable.family_younger_sister,R.raw.family_father));
        family.add(new word("grandpa", "wo’e",R.drawable.family_grandfather,R.raw.family_father));
        family.add(new word("grandma", "na’aacha",R.drawable.family_grandmother,R.raw.family_father));


        // Create an {@link ArrayAdapter}, whose data source is a list of Strings. The
        // adapter knows how to create layouts for each item in the list, using the
        // simple_list_item_1.xml layout resource defined in the Android framework.
        // This list item layout contains a single {@link TextView}, which the adapter will set to
        // display a single word.
        WordAdapter Adapter = new WordAdapter(getActivity(), family,R.color.category_family);

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

                word currentWord = family.get(position);
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
