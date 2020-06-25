package com.rahulkashyap.calldetailsfromrecentcalllog;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.fragment.app.Fragment;


import java.util.HashSet;


import butterknife.BindView;
import butterknife.OnClick;

import static android.telephony.PhoneNumberUtils.WAIT;

public class DialpadFragment extends Fragment implements View.OnClickListener {

    private static final String TAG = DialpadFragment.class.getSimpleName();
    public static final String ARG_DIALER = "dialer";

    // Text
    private OnKeyDownListener mOnKeyDownListener = null;
//    private SharedDialViewModel mViewModel;
    private PhoneNumberFormattingTextWatcher mPhoneNumberFormattingTextWatcher;

    // Booleans
    private boolean mIsDialer = true;
    private boolean mIsSilent = true;
    private boolean mIsNotVibrate = true;

    // Tone
    private ToneGenerator mToneGenerator;
    private final Object mToneGeneratorLock = new Object();
    // The length of DTMF tones in milliseconds
    private static final int TONE_LENGTH_MS = 150;
    private static final int TONE_LENGTH_INFINITE = -1;
    // The DTMF tone volume relative to other sounds in the stream
    private static final int TONE_RELATIVE_VOLUME = 80;
    // Stream type used to play the DTMF tones off call, and mapped to the volume control keys
    private static final int DIAL_TONE_STREAM_TYPE = AudioManager.STREAM_DTMF;

    private final HashSet<View> mPressedDialpadKeys = new HashSet<View>(12);

    // Edit Texts
    private DigitsEditText mDigits;
//
//    // Buttons
//    @BindView(R.id.button_call) ImageView mCallButton;
//    @BindView(R.id.button_delete) ImageView mDelButton;
//
//    // Layouts
//    @BindView(R.id.dialpad) TableLayout mNumbersTable;
//
//    @BindView(R.id.dialpad_view) DialpadView mDialpadView;

    private DialpadKeyButton key1,key2,key3,key4,key5,key6,key7,key8,key9,key0,keyStar,keyHas;

    public static DialpadFragment newInstance(boolean isDialer) {
        Bundle args = new Bundle();
        args.putBoolean(ARG_DIALER, isDialer);
        DialpadFragment fragment = new DialpadFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.dialpad_fragment, container, false);
        fragmentView.buildLayer();
        return fragmentView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        key1 = view.findViewById(R.id.key_1);
        key2 = view.findViewById(R.id.key_2);
        key3 = view.findViewById(R.id.key_3);
        key4 = view.findViewById(R.id.key_4);
        key5 = view.findViewById(R.id.key_5);
        key6 = view.findViewById(R.id.key_6);
        key7 = view.findViewById(R.id.key_7);
        key8 = view.findViewById(R.id.key_8);
        key9 = view.findViewById(R.id.key_9);
        key0 = view.findViewById(R.id.key_0);
        keyStar = view.findViewById(R.id.key_star);
        keyHas = view.findViewById(R.id.key_hex);
        mDigits = view.findViewById(R.id.digits_edit_text);


        key1.setOnClickListener(this);
        key2.setOnClickListener(this);
        key3.setOnClickListener(this);
        key4.setOnClickListener(this);
        key5.setOnClickListener(this);
        key6.setOnClickListener(this);
        key7.setOnClickListener(this);
        key8.setOnClickListener(this);
        key9.setOnClickListener(this);
        key0.setOnClickListener(this);
        keyStar.setOnClickListener(this);
        keyHas.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.key_1: {
                System.out.println(">>>>>>>>>>>>>>>1");
                keyPressed(KeyEvent.KEYCODE_1);
                break;
            }
            case R.id.key_2: {
                keyPressed(KeyEvent.KEYCODE_2);
                break;
            }
            case R.id.key_3: {
                keyPressed(KeyEvent.KEYCODE_3);
                break;
            }
            case R.id.key_4: {
                keyPressed(KeyEvent.KEYCODE_4);
                break;
            }
            case R.id.key_5: {
                keyPressed(KeyEvent.KEYCODE_5);
                break;
            }
            case R.id.key_6: {
                keyPressed(KeyEvent.KEYCODE_6);
                break;
            }
            case R.id.key_7: {
                keyPressed(KeyEvent.KEYCODE_7);
                break;
            }
            case R.id.key_8: {
                keyPressed(KeyEvent.KEYCODE_8);
                break;
            }
            case R.id.key_9: {
                keyPressed(KeyEvent.KEYCODE_9);
                break;
            }
            case R.id.key_0: {
                keyPressed(KeyEvent.KEYCODE_0);
                break;
            }
            case R.id.key_hex: {
                keyPressed(KeyEvent.KEYCODE_POUND);
                break;
            }
            case R.id.key_star: {
                keyPressed(KeyEvent.KEYCODE_STAR);
                break;
            }
            default: {
                break;
            }
        }
        mPressedDialpadKeys.add(view);
    }

    private void keyPressed(int keyCode) {
//        updatePreferences();
        if (getView().getTranslationY() != 0) {
            return;
        }
        if (!mIsSilent) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_1:
                    playTone(ToneGenerator.TONE_DTMF_1);
                    break;
                case KeyEvent.KEYCODE_2:
                    playTone(ToneGenerator.TONE_DTMF_2);
                    break;
                case KeyEvent.KEYCODE_3:
                    playTone(ToneGenerator.TONE_DTMF_3);
                    break;
                case KeyEvent.KEYCODE_4:
                    playTone(ToneGenerator.TONE_DTMF_4);
                    break;
                case KeyEvent.KEYCODE_5:
                    playTone(ToneGenerator.TONE_DTMF_5);
                    break;
                case KeyEvent.KEYCODE_6:
                    playTone(ToneGenerator.TONE_DTMF_6);
                    break;
                case KeyEvent.KEYCODE_7:
                    playTone(ToneGenerator.TONE_DTMF_7);
                    break;
                case KeyEvent.KEYCODE_8:
                    playTone(ToneGenerator.TONE_DTMF_8);
                    break;
                case KeyEvent.KEYCODE_9:
                    playTone(ToneGenerator.TONE_DTMF_9);
                    break;
                case KeyEvent.KEYCODE_0:
                    playTone(ToneGenerator.TONE_DTMF_0);
                    break;
                case KeyEvent.KEYCODE_POUND:
                    playTone(ToneGenerator.TONE_DTMF_P);
                    break;
                case KeyEvent.KEYCODE_STAR:
                    playTone(ToneGenerator.TONE_DTMF_S);
                    break;
                default:
                    break;
            }
        }
//        vibrate();
        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);

        // Add the digit to mDigits
        mDigits.onKeyDown(keyCode, event);

        // An option for an outer response
        if (mOnKeyDownListener != null) mOnKeyDownListener.onKeyPressed(keyCode, event);

        // If the cursor is at the end of the text we hide it.
        final int length = mDigits.length();
        if (length == mDigits.getSelectionStart() && length == mDigits.getSelectionEnd()) {
            mDigits.setCursorVisible(false);
        }
    }

    /**
     * Plays the specified tone for TONE_LENGTH_MS milliseconds.
     */
    private void playTone(int tone) {
        playTone(tone, TONE_LENGTH_MS);
    }

    /**
     * Stop the tone if it is played.
     */
    private void stopTone() {
        // if local tone playback is disabled, just return.
        synchronized (mToneGeneratorLock) {
            if (mToneGenerator == null) {
                System.out.print("stopTone: mToneGenerator == null");
                return;
            }
            mToneGenerator.stopTone();
        }
    }

    private void playTone(int tone, int durationMs) {
        // Also do nothing if the phone is in silent mode.
        // We need to re-check the ringer mode for *every* playTone()
        // call, rather than keeping a local flag that's updated in
        // onResume(), since it's possible to toggle silent mode without
        // leaving the current activity (via the ENDCALL-longpress menu.)
        AudioManager audioManager =
                (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        int ringerMode = audioManager.getRingerMode();
        if ((ringerMode == AudioManager.RINGER_MODE_SILENT)
                || (ringerMode == AudioManager.RINGER_MODE_VIBRATE)) {
            return;
        }
        synchronized (mToneGeneratorLock) {
            if (mToneGenerator == null) {
                System.out.println("playTone: mToneGenerator == null, tone: " + tone);
                return;
            }
            // Start the new tone (will stop any playing tone)
            mToneGenerator.startTone(tone, durationMs);
        }
    }

    public void setOnKeyDownListener(OnKeyDownListener onKeyDownListener) {
        mOnKeyDownListener = onKeyDownListener;
    }

    public interface OnKeyDownListener {
        void onKeyPressed(int keyCode, KeyEvent event);
    }




}
