package fr.nikho.epitech.intra.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import fr.nikho.epitech.intra.R;

public class InstantInfoIconVIew extends View {

    private Paint rectPaint;
    private Rect rect;

    private Bitmap icon;
    private Drawable iconDrawable;

    private int rectX, rectY;

    public InstantInfoIconVIew(Context context) {
        super(context);
        init();
    }

    public InstantInfoIconVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public InstantInfoIconVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public InstantInfoIconVIew(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        icon = BitmapFactory.decodeResource(getResources(), R.raw.epitech_logo_white);
        iconDrawable = new BitmapDrawable(getResources(), icon);

/*        rectPaint = new Paint();
        rectPaint.setColor(Color.BLACK);

        rect = new Rect(0, 0, 100, 100);

        rectX = 0;
        rectY = 0;*/

        iconDrawable.setBounds(0, 0, 100, 100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
