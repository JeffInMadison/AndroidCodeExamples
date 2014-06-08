package com.jeffinmadison.rotatetest;

import android.animation.Animator;
import android.content.res.Configuration;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class MainFragment extends Fragment {
    private static final String TAG = MainFragment.class.getSimpleName();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Button mTestButton;
    private RotationOrientationEventListener mOrientationEventListener;
    private TextView mOrientationTextView;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mOrientationEventListener = new RotationOrientationEventListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mTestButton = (Button) rootView.findViewById(R.id.testButton);
        mOrientationTextView = (TextView) rootView.findViewById(R.id.orientationTextView);
        return rootView;
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onResume() {
        super.onResume();
        mOrientationEventListener.enable();
    }

    @Override
    public void onPause() {
        super.onPause();
        mOrientationEventListener.disable();
    }

    private class RotationOrientationEventListener extends OrientationEventListener {

        boolean mIsAnimating = false;
        private int mCurrentRotation = 0;

        public RotationOrientationEventListener() {
            super(MainFragment.this.getActivity(), SensorManager.SENSOR_DELAY_UI);
        }

        @Override
        public void onOrientationChanged(int orientation) {
            Log.d(TAG, "Orientation: " + String.valueOf(orientation));
            mOrientationTextView.setText(String.valueOf(orientation));
            int rotationDegrees = 0;
            Orientation rotationOrientation = Orientation.unspecified;
            if ((orientation >= 315 && orientation < 360) ||
                (orientation > 0 && orientation <= 45)){
                rotationDegrees = 0;
            } else if (orientation >= 45 && orientation <= 135) {
                rotationDegrees = -90;
            } else if (orientation >= 135 && orientation <= 230) {
                rotationDegrees = 180;
            } else if (orientation >= 230 && orientation <= 315) {
                rotationDegrees = 90;
            }

            if (!mIsAnimating && mCurrentRotation != rotationDegrees) {
                mCurrentRotation = rotationDegrees;

                mTestButton.animate().rotation(rotationDegrees).setDuration(100).setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(final Animator animation) {
                        mIsAnimating = true;
                    }

                    @Override
                    public void onAnimationEnd(final Animator animation) {
                        mIsAnimating = false;
                    }

                    @Override
                    public void onAnimationCancel(final Animator animation) {
                        mIsAnimating = false;
                    }

                    @Override
                    public void onAnimationRepeat(final Animator animation) {

                    }
                });
            }
        }
    }

    private enum Orientation {
        landscape,
        reverseLandscape,
        portrait,
        reversePortrait,
        unspecified
    }
}
