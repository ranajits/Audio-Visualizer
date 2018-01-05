package ranjit.com.audiovisualizer;

import android.view.View;
import android.widget.ImageButton;

import ranjit.com.audiovisualizer.visualizer.LineBarVisualizer;

public class LineBarVisualizerActivity extends BaseActivity {

    @Override
    protected void init() {
        LineBarVisualizer lineBarVisualizer = findViewById(R.id.visualizer);

        lineBarVisualizer.setColor(R.color.grey);
        // Set your media player to the visualizer.
        lineBarVisualizer.setPlayer(mediaPlayer);
    }

    public void replay(View view) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(0);
        }
    }

    public void playPause(View view) {
        playPauseBtnClicked((ImageButton) view);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_line_bar_visualizer;
    }
}
