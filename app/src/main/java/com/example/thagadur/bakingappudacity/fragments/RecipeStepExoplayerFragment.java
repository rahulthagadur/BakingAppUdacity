package com.example.thagadur.bakingappudacity.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.thagadur.bakingappudacity.R;
import com.example.thagadur.bakingappudacity.model.RecipeSteps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RecipeStepExoplayerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RecipeStepExoplayerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecipeStepExoplayerFragment extends Fragment implements ExoPlayer.EventListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    protected final String STATE_PLAYER_READY = "playerState";
    public long currentExoPlayerPosition;
    boolean playWhenReady = false;
    Context mContext;
    Uri mp4VideoUri;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    private SimpleExoPlayer exoPlayer;
    private ArrayList<RecipeSteps> recipeSteps;
    private int currentpostionIndex;
    private View mRootView;
    private TextView tv_error_url;
    private SimpleExoPlayerView exoPlayerView;

    public RecipeStepExoplayerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecipeStepExoplayerFragment.
     */
    public static RecipeStepExoplayerFragment newInstance(String param1, String param2) {
        RecipeStepExoplayerFragment fragment = new RecipeStepExoplayerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mRootView = inflater.inflate(R.layout.fragment_recipe_exoplayer, container, false);

        tv_error_url = mRootView.findViewById(R.id.tv_error_url);
        exoPlayerView = mRootView.findViewById(R.id.exo_playerView);
        mContext = getActivity();
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(getString(R.string.key_arrayrecipesteps)) &&
                    (savedInstanceState.containsKey(getString(R.string.key_steps_postion)) &&
                            savedInstanceState.containsKey(getString(R.string.key_exoplayer_position)))) {
                recipeSteps = savedInstanceState.getParcelableArrayList(getString(R.string.key_arrayrecipesteps));
                currentpostionIndex = savedInstanceState.getInt(getString(R.string.key_steps_postion));
                currentExoPlayerPosition = savedInstanceState.getLong(getString(R.string.key_exoplayer_position));
                playWhenReady = savedInstanceState.getBoolean(STATE_PLAYER_READY);


            }

        }

        Log.e("jkfdhfdlf", "" + currentExoPlayerPosition);
        if (recipeSteps != null)
            if (!recipeSteps.get(currentpostionIndex).getmVideoUrl().isEmpty())
                initializePlayer();
            else {
                showErrorMessage();
            }

        return mRootView;
    }

    public void showErrorMessage() {
        tv_error_url.setVisibility(View.VISIBLE);
        exoPlayerView.setVisibility(View.GONE);
    }

    @SuppressWarnings("deprecation")
    private void initializePlayer() {
        if (exoPlayer == null) {

            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
            TrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);
            exoPlayer = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector, new DefaultLoadControl());
            exoPlayerView.requestFocus();
            exoPlayerView.setUseController(true);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.addListener(this);

            if (exoPlayer != null) {
                mp4VideoUri = Uri.parse(recipeSteps.get(currentpostionIndex).getmVideoUrl());
            }
            String userAgent = Util.getUserAgent(getContext(), "BakingApp");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(mp4VideoUri,
                    new DefaultDataSourceFactory(getContext(), userAgent), extractorsFactory, null, null);

            if (currentExoPlayerPosition > 0)
                exoPlayer.seekTo(currentExoPlayerPosition);
            exoPlayer.prepare(mediaSource);
            exoPlayer.getCurrentPosition();
            exoPlayer.setPlayWhenReady(playWhenReady);
        } else {

        }
    }

    private void releasePlayer() {
        if (exoPlayer != null) {
            exoPlayer.stop();
            exoPlayer.release();
            exoPlayer = null;
        }
    }

    public void setRecipeSteps(ArrayList<RecipeSteps> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public void setcurrentpostionIndex(int currentpostionIndex) {
        this.currentpostionIndex = currentpostionIndex;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        releasePlayer();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (exoPlayer != null) {
            currentExoPlayerPosition = exoPlayer.getContentPosition();
            playWhenReady = exoPlayer.getPlayWhenReady();
        }
        outState.putParcelableArrayList(getString(R.string.key_arrayrecipesteps), recipeSteps);
        outState.putInt(getString(R.string.key_steps_postion), currentpostionIndex);
        outState.putLong(getString(R.string.key_exoplayer_position), Math.max(0, exoPlayer.getContentPosition()));
        outState.putBoolean(STATE_PLAYER_READY, playWhenReady);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (exoPlayer != null) {
            exoPlayer.release();

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (exoPlayer == null) {
            initializePlayer();
        }
    }
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {

    }

    @Override
    public void onRepeatModeChanged(int repeatMode) {

    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
