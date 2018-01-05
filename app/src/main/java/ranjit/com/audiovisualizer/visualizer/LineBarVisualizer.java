package ranjit.com.audiovisualizer.visualizer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

public class LineBarVisualizer extends BaseVisualizer {
    private Paint middleLine;
    private float density;
    private int gap;

    public LineBarVisualizer(Context context) {
        super(context);
    }

    public LineBarVisualizer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LineBarVisualizer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void init() {
        density = 150;
        gap = 2;
        middleLine = new Paint();
        middleLine.setColor(Color.TRANSPARENT);
    }

    /**
     * Sets the density to the Bar visualizer i.e the number of bars
     * to be displayed. Density can vary from 10 to 256.
     * by default the value is set to 50.
     *
     * @param density density of the bar visualizer
     */
    public void setDensity(float density) {
        if (this.density > 180) {
            this.middleLine.setStrokeWidth(1);
            this.gap = 1;
        } else {
            this.gap = 1;
        }
        this.density = density;
        if (density > 500) {
            this.density =500;
            this.gap = 0;
        } else if (density <= 500) {
            this.density = 500;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (middleLine.getColor() != Color.BLUE) {
            middleLine.setColor(color);
        }
        middleLine.setColor(Color.TRANSPARENT);
        if (bytes != null) {
            float barWidth = getWidth() / density;
            float div = bytes.length / density;
            canvas.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2, middleLine);
            paint.setStrokeWidth(barWidth - gap);

            for (int i = 0; i < density; i++) {
                int x = (int) Math.ceil(i * div);
                int top = canvas.getHeight() / 2
                        + (128 - Math.abs(bytes[x]))
                        * (canvas.getHeight() / 2) / 128;

                int bottom = canvas.getHeight() / 2
                        - (128 - Math.abs(bytes[x]))
                        * (canvas.getHeight() / 2) / 128;

                canvas.drawLine(i * barWidth, bottom, i * barWidth, getHeight() / 2, paint);
                canvas.drawLine(i * barWidth, top, i * barWidth, getHeight() / 2, paint);
            }
            super.onDraw(canvas);
        }
    }
}
