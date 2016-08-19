package com.example.qiyue.customprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

/**
 * Created by qiyue on 2016/8/19 0019.
 * 自定义个ProgressBar，也是为了学习，参考ProgressWheel
 *
 */
public class ProgressView extends View {

    private RectF circleBounds;

    private Paint barPaint = new Paint();
    /**
     * draw结束时间
     */
    private long lastTimeAnimated;
    /**
     * 记录进度值，这里是一个圆形，因此是0到360度
     */
    private float mProgress;
    /**
     * 每秒度量
     */
    private float spinSpeed = 230f;
    /**
     * 进度条开始长度
     */
    private final int barLength = 16;
    /**
     * 增加进度
     */
    private int barExtraLength = 0;
    /**
     * 增加的最大值
     */
    private int max = 200;

    private boolean isMax = false;

    private boolean isMin = false;
    /**
     * 默认进度条宽度
     */
    private int barWidth = 10;

    private int barColor = 0xFF00FF00;

    private int speed = 5;

    public ProgressView(Context context) {
        super(context);
    }

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        TypedArray a =  context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        barWidth = (int) a.getDimension(R.styleable.ProgressView_barWidth, barWidth);
        barColor = (int) a.getDimension(R.styleable.ProgressView_barColor,barColor);
        max = (int) a.getDimension(R.styleable.ProgressView_barExpandMax,max);
        speed = (int) a.getInt(R.styleable.ProgressView_speed,speed);
        // Recycle
        a.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        long deltaTime = (SystemClock.uptimeMillis() - lastTimeAnimated);
       // Log.i("qiyue","deltaTime1=" + deltaTime);
        float deltaNormalized = deltaTime * spinSpeed / 1000.0f;
      //  Log.i("qiyue","deltaNormalized=" + deltaNormalized);

        mProgress += deltaNormalized+speed;
        if (mProgress > 360) {
            mProgress -= 360f;
            /**
             * 这里就是让进度值不要无线大，因为第一次这个差值比较大（因为lastTimeAnimated第一次为0）
             */
            Log.i("qiyue",">>>>>>>>>>>>>>>>>>>360");
        }
        lastTimeAnimated = SystemClock.uptimeMillis();
        float length = barLength;
        float from = mProgress - 90;
        Log.i("qiyue","from"+from);
        if(deltaNormalized>100){
            /**
             * 第一次很大，不能使用，所以我就跳过了，哈哈
             */
        }else{
            if (isMax){
                /**减少**/
                if (barExtraLength>0) {
                    barExtraLength -= (deltaNormalized);
                    if (barExtraLength<=0){
                        barExtraLength = 0;
                        isMax = false;
                    }
                }else{
                    isMax = false;
                }
                //   canvas.drawArc(circleBounds, from, (barExtraLength+barLength), false, barPaint);
            }else{
                /**增加**/
                if (barExtraLength<max) {
                    barExtraLength += (deltaNormalized);
                    Log.i("x","length="+length+"barExtraLength="+barExtraLength);
                    if (barExtraLength>=max){
                        barExtraLength = max;
                        isMax = true;
                    }
                   // canvas.drawArc(circleBounds, from, -(barExtraLength+barLength), false, barPaint);
                }
            }
        }
        /**
         * 长度计算，增加的长度 和初始长度相加
         */
        Log.i("qiyue","from="+from+"lenght="+(barExtraLength+barLength));
        canvas.drawArc(circleBounds, from, -(barExtraLength+barLength), false, barPaint);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        circleBounds = new RectF();                     //RectF对象
        /**
         * w,h 就是layout_width 和 layout_height
         */
        int circleRight = w - paddingRight;
        int circleBottom = h - paddingBottom;
        /**
         * 故意多留10px
         */
        circleBounds.left = paddingLeft + 10;                              //左边
        circleBounds.top = paddingTop + 10;                                   //上边
        circleBounds.right = circleRight - 10;
        circleBounds.bottom = circleBottom - 10;

        barPaint.setColor(0xFF00FF00);
        barPaint.setAntiAlias(true);
        barPaint.setStyle(Paint.Style.STROKE);
        barPaint.setStrokeWidth(barWidth);
    }


}
