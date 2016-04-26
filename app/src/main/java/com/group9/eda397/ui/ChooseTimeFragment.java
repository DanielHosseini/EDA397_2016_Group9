package com.group9.eda397.ui;

import android.app.NotificationManager;
import android.content.Context;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.group9.eda397.R;
import com.group9.eda397.ui.fragments.BaseFragment;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Isabelle on 2016-04-21.
 */
public class ChooseTimeFragment extends BaseFragment {

    private static final String ARG_TEXT = ChooseTimeFragment.class.getCanonicalName() + ".arg_text";
    @Bind(R.id.editText) EditText editText;
    @Bind(R.id.text) TextView textView;
    @Bind(R.id.startButton) Button startButton;
    @Bind(R.id.cancelButton) Button cancelButton;
    @Bind(R.id.pauseButton) Button pauseButton;

    private int timerVisibleCount = 0;
    private long timerStartTime = 0;
    private long timerCurrentTotalTime = 0;
    private long timerPausedRemainingTime = 0;
    private long timerTotalTime;
    private CountDownTimer timer = null;
    private static final String PAUSE_BUTTON_TEXT = "Pause";
    private static final String UNPAUSE_BUTTON_TEXT = "Resume";
    private static final String RESTART_BUTTON_TEXT = "Restart";

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.countdown_timer, container, false);
        ButterKnife.bind(this, view);
        editText.setText("00");
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId== EditorInfo.IME_ACTION_DONE){
                    onClickStart();
                }
                return false;
            }
        });
        return view;
    }

    @OnClick(R.id.editText)
    public void onClickText() {
        editText.setText("");
    }

    @OnClick(R.id.startButton)
    public void onClickStart() {
        this.editText.setVisibility(View.GONE);
        this.textView.setVisibility(View.VISIBLE);
        this.startButton.setVisibility(View.GONE);
        this.cancelButton.setVisibility(View.VISIBLE);
        this.pauseButton.setVisibility(View.VISIBLE);

        int timeSeconds = Integer.parseInt(editText.getText().toString());
        textView.setText(timeSeconds + "");
        pauseButton.setText(PAUSE_BUTTON_TEXT);
        timerTotalTime = timeSeconds;
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        startTimer(timeSeconds);
    }

    @OnClick(R.id.cancelButton)
    public void onClickCancel() {
        this.cancelButton.setVisibility(View.GONE);
        this.editText.setVisibility(View.VISIBLE);
        this.startButton.setVisibility(View.VISIBLE);
        this.pauseButton.setVisibility(View.GONE);
        this.textView.setVisibility(View.GONE);
   }

    @OnClick(R.id.pauseButton)
    public void onClickPause() {
        if (timer != null && pauseButton.getText().equals(PAUSE_BUTTON_TEXT)) {
            pauseButton.setText(UNPAUSE_BUTTON_TEXT);
            timer.cancel();
            timerPausedRemainingTime = timerCurrentTotalTime - (System.currentTimeMillis() - timerStartTime);
        } else if (timer != null && pauseButton.getText().equals(UNPAUSE_BUTTON_TEXT)) {
            pauseButton.setText(PAUSE_BUTTON_TEXT);
            long initialDelay = timerPausedRemainingTime % 1000;
            Timer delay = new Timer();
            delay.schedule(new TimerTask() {
                @Override
                public void run() {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startTimer((int) (timerPausedRemainingTime/1000));
                        }
                    });
                }
            }, initialDelay);
        }else if (timer != null && pauseButton.getText().equals(RESTART_BUTTON_TEXT)) {
            pauseButton.setText(PAUSE_BUTTON_TEXT);
            startTimer((int) timerTotalTime);
        }
    }


    private void startTimer(int time){
        timerStartTime = System.currentTimeMillis();
        timerCurrentTotalTime = time*1000;
        timerVisibleCount = time;
        timer = new CountDownTimer(timerCurrentTotalTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timerVisibleCount = timerVisibleCount - 1;
                        textView.setText(timerVisibleCount + "");
                    }
                });

            }
            @Override
            public void onFinish() {
                pauseButton.setText(RESTART_BUTTON_TEXT);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("Done");

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
                        builder.setContentTitle("Pair programming timer is finished");
                        builder.setSmallIcon(R.drawable.ic_menu_gallery); // TODO fix icon

                        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                        builder.setSound(alarmSound);

                        NotificationManager nm = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                        nm.notify(0, builder.build());
                    }
                });

            }
        };
        timer.start();
    }



    public static ChooseTimeFragment newInstance(final String text) {
        ChooseTimeFragment fragment = new ChooseTimeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    public int getLayout(){
        return R.layout.countdown_timer;
    }

}
