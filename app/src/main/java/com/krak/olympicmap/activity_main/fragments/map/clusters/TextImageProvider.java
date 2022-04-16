package com.krak.olympicmap.activity_main.fragments.map.clusters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.krak.olympicmap.R;
import com.yandex.runtime.image.ImageProvider;

public class TextImageProvider extends ImageProvider {

    private static final float FONT_SIZE = 15;
    private static final float MARGIN_SIZE = 3;
    private static final float STROKE_SIZE = 3;

    private final String text;
    private final Activity activity;

    public TextImageProvider(String text, Activity activity) {
        this.text = text;
        this.activity = activity;
    }

    @Override
    public Bitmap getImage() {
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager manager = (WindowManager)activity.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getMetrics(metrics);

        Paint textPaint = new Paint();
        textPaint.setTextSize(FONT_SIZE * metrics.density);
        textPaint.setColor(activity.getResources().getColor(R.color.white));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);

        float widthF = textPaint.measureText(text);
        Paint.FontMetrics textMetrics = textPaint.getFontMetrics();
        float heightF = Math.abs(textMetrics.bottom) + Math.abs(textMetrics.top);
        float textRadius = (float)Math.sqrt(widthF * widthF + heightF * heightF) / 2;
        float internalRadius = textRadius + MARGIN_SIZE * metrics.density;
        float externalRadius = internalRadius + STROKE_SIZE * metrics.density;

        int width = (int) (2 * externalRadius + 0.5);

        Bitmap bitmap = Bitmap.createBitmap(width, width, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(activity.getColor(R.color.second_dark));
        canvas.drawCircle(width / 2, width / 2, externalRadius, backgroundPaint);

        backgroundPaint.setColor(activity.getColor(R.color.main));
        canvas.drawCircle(width / 2, width / 2, internalRadius, backgroundPaint);

        canvas.drawText(
                text,
                width / 2,
                width / 2 - (textMetrics.ascent + textMetrics.descent) / 2,
                textPaint
        );
        return bitmap;
    }

    @Override
    public String getId() {
        return "text_" + text;
    }
}