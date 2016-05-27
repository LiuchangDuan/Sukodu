package com.example.sudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by Administrator on 2016/5/26.
 */
public class SukoduView extends View {

    //单元格的宽度和高度
    private float width;
    private float height;

    public SukoduView(Context context) {
        super(context);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        //计算当前单元格的宽度和高度
        this.width = w / 9f;
        this.height = h / 9f;
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        //绘制背景
        //生成用于绘制背景色的画笔
        Paint backgroundPaint = new Paint();

//        backgroundPaint.setColor(getResources().getColor(R.color.sudoku_background, null));
//        backgroundPaint.setColor(getResources().getColor(R.color.sudoku_background)); // deprecated
        //设置画笔的颜色
        backgroundPaint.setColor(ContextCompat.getColor(getContext(), R.color.sudoku_background));
        //绘制背景色
        canvas.drawRect(0, 0, getWidth(), getHeight(), backgroundPaint);

        //醒目颜色
        Paint hilitePaint = new Paint();
        hilitePaint.setColor(ContextCompat.getColor(getContext(), R.color.sudoku_hilite));

        Paint lightPaint = new Paint();
        lightPaint.setColor(ContextCompat.getColor(getContext(), R.color.sudoku_light));

        Paint darkPaint = new Paint();
        darkPaint.setColor(ContextCompat.getColor(getContext(), R.color.sudoku_dark));

        //两条颜色深浅的线相隔一像素，造成一种线嵌入布局的感觉（凹槽效果）
        for (int i = 0; i < 9; i++) {
            //横向的单元格线
            canvas.drawLine(0, i * height, getWidth(), i * height, lightPaint);
            canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilitePaint);
            //纵向的单元格线
            canvas.drawLine(i * width, 0, i * width, getHeight(), lightPaint);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilitePaint);
        }

        //深色的线用于分成九个格子
        for (int i = 0; i < 9; i++) {
            if (i % 3 != 0) {
                continue;
            }
            //横向的单元格线
            canvas.drawLine(0, i * height, getWidth(), i * height, darkPaint);
            canvas.drawLine(0, i * height + 1, getWidth(), i * height + 1, hilitePaint);
            //纵向的单元格线
            canvas.drawLine(i * width, 0, i * width, getHeight(), darkPaint);
            canvas.drawLine(i * width + 1, 0, i * width + 1, getHeight(), hilitePaint);
        }

        //画数字
        Paint numberPaint = new Paint();
        numberPaint.setColor(Color.BLACK);
        numberPaint.setStyle(Paint.Style.STROKE);
        numberPaint.setTextSize(height * 0.75f);
        //水平居中
        numberPaint.setTextAlign(Paint.Align.CENTER);

        float x = width / 2;
        canvas.drawText("1", 3 * width + x, 150, numberPaint);

        super.onDraw(canvas);
    }
}