package com.group9.eda397.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.group9.eda397.R;
import com.group9.eda397.ui.fragments.BaseFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by Isabelle on 2016-04-21.
 */
public class ChooseTimeFragment extends BaseFragment {

    private static final String ARG_TEXT = ChooseTimeFragment.class.getCanonicalName() + ".arg_text";
    private static final String PAUSE_BUTTON_TEXT = "Pause";
    private static final String UNPAUSE_BUTTON_TEXT = "Resume";
    private static final String RESTART_BUTTON_TEXT = "Restart";
    private static final String CLARIFYING_TEXT_SET_TIMER = "Set time";
    private static int timerVisibleCount = 0;
    private static long timerStartTime = 0;
    private static long timerCurrentTotalTime = 0;
    private static long timerPausedRemainingTime = 0;
    private static long timerTotalTime = 600; // 600 seconds = 10 minutes
    private static PairProgrammingTimer timer = null;
    @Bind(R.id.editText) EditText editText;
    @Bind(R.id.text) TextView textView;
    @Bind(R.id.startButton) Button startButton;
    @Bind(R.id.cancelButton) Button cancelButton;
    @Bind(R.id.clarifyingText) TextView clarifyingText;
    @Bind(R.id.pauseButton) Button pauseButton;

    public static ChooseTimeFragment newInstance(final String text) {
        ChooseTimeFragment fragment = new ChooseTimeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TEXT, text);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.countdown_timer, container, false);
        ButterKnife.bind(this, view);
        getAppCompatActivity().getSupportActionBar().setTitle(R.string.title_timer);
        clarifyingText.setText(CLARIFYING_TEXT_SET_TIMER);
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    onClickStart();
                }
                return false;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
        editText.setText("" + timerTotalTime/60);
        if(timer != null && timer.isRunning()) {
            this.editText.setVisibility(View.GONE);
            this.textView.setVisibility(View.VISIBLE);
            this.startButton.setVisibility(View.GONE);
            this.cancelButton.setVisibility(View.VISIBLE);
            if(!timer.isPaused) {
                pauseButton.setText(PAUSE_BUTTON_TEXT);
                //this.textView.setText(getTimerString(timerVisibleCount));
                this.textView.setText(getTimerString(timer.getRemainingTicks()));
                timerVisibleCount = timer.getRemainingTicks();
            } else {
                pauseButton.setText(UNPAUSE_BUTTON_TEXT);
                this.textView.setText(getTimerString((int)timerPausedRemainingTime / 1000));
            }
            this.pauseButton.setVisibility(View.VISIBLE);
            this.clarifyingText.setVisibility(View.GONE);

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
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
        this.clarifyingText.setVisibility(View.GONE);
        pauseButton.setText(PAUSE_BUTTON_TEXT);

        int timeSeconds = 0;
        if(!editText.getText().toString().equals("")) {
            timeSeconds = 60 * Integer.parseInt(editText.getText().toString());
        }
        timerTotalTime = timeSeconds;
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        startTimer(timeSeconds);
    }

    @OnClick(R.id.cancelButton)
    public void onClickCancel() {
        timer.cancelPairTimer();
        this.cancelButton.setVisibility(View.GONE);
        this.editText.setVisibility(View.VISIBLE);
        this.startButton.setVisibility(View.VISIBLE);
        this.pauseButton.setVisibility(View.GONE);
        this.textView.setVisibility(View.GONE);
        this.clarifyingText.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.pauseButton)
    public void onClickPause() {
        if (timer != null && pauseButton.getText().equals(PAUSE_BUTTON_TEXT)) {
            pauseButton.setText(UNPAUSE_BUTTON_TEXT);
            timer.cancelPairTimer();
            timer.isPaused = true;
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
                            startTimer((int) (timerPausedRemainingTime / 1000));
                        }
                    });
                }
            }, initialDelay);
        } else if (timer != null && pauseButton.getText().equals(RESTART_BUTTON_TEXT)) {
            pauseButton.setText(PAUSE_BUTTON_TEXT);
            startTimer((int) timerTotalTime);
        }
    }

    @Subscribe
    public void onTickEvent(final TickEvent event) {
        Timber.v("Sending tick event to the event bus------------------------");
        timerVisibleCount = timerVisibleCount - 1;
        textView.setText(getTimerString(timerVisibleCount));
    }

    @Subscribe
    public void onFinishedTimerEvent(final FinishedTimerEvent event) {
        pauseButton.setText(RESTART_BUTTON_TEXT);
        textView.setText(getTimerString(0));
    }

    private void startTimer(int time) {
        timerStartTime = System.currentTimeMillis();
        timerCurrentTotalTime = time * 1000;
        timerVisibleCount = time;
        timer = new PairProgrammingTimer(timerCurrentTotalTime, 1000);
        timer.startPairTimer();
    }

    public String getTimerString(int totalSeconds) {
        int seconds = totalSeconds % 60;
        String secondsString = "00";
        if (seconds > 9) {
            secondsString = "" + seconds;
        } else if (seconds > 0) {
            secondsString = "0" + seconds;
        }
        int minutes = totalSeconds / 60;
        if (minutes == 0) {
            return "0:" + secondsString;
        }
        return totalSeconds / 60 + ":" + secondsString;
    }

    public int getLayout() {
        return R.layout.countdown_timer;
    }

    private class TickEvent {
        private final int time;

        public TickEvent(final int time) {
            this.time = time;
        }

        public int getTime() {
            return time;
        }
    }

    public class FinishedTimerEvent {

        public FinishedTimerEvent() {}
    }

    private class PairProgrammingTimer extends CountDownTimer {
        private boolean isRunning = false;
        private boolean isPaused = false;
        private int remainingTicks = 0;

        public PairProgrammingTimer(long millisuntilFinished, long tick) {
            super(millisuntilFinished, tick);
            remainingTicks = (int) (millisuntilFinished / tick) + 1;
        }

        public CountDownTimer startPairTimer()  {
            isRunning = true;
            isPaused = false;
            return super.start();
        }

        public void cancelPairTimer()  {
            isRunning = false;
            isPaused = false;
            super.cancel();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Timber.v("Sending tick event to the event bus");
            EventBus.getDefault().post(new TickEvent(remainingTicks));
            remainingTicks--;
        }

        public int getRemainingTicks() {
            return remainingTicks;
        }

        @Override
        public void onFinish() {
            Timber.v("Sending finishTimer event to the event bus");
            EventBus.getDefault().post(new FinishedTimerEvent());
            isRunning = false;
        }

        /**
         * A paused timer is considered running, i.e. will return true.
         * @return
         */
        private boolean isRunning() {
            return isRunning || isPaused;
        }

        private boolean isPaused() {
            return isPaused;
        }
    }
}
