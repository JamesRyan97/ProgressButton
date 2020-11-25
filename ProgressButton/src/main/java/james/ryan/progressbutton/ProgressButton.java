package james.ryan.progressbutton;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.SpriteFactory;
import com.github.ybq.android.spinkit.Style;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;

import java.util.Timer;
import java.util.TimerTask;

public class ProgressButton extends RelativeLayout {

    private SpinKitView skvLoading;
    private TextView tvContent;
    private ImageView imvLeft;

    private Timer timerLoading;
    private int countTimer;

    private Drawable dwSuccess;
    private Drawable dwError;


    private int mLoadingColor;
    private int mTextColor;
    private String mText;
    private String mTextSuccess;
    private String mTextError;
    private String mTextLoading;
    private float mTextSize;
    private int mWidthLoading;
    private int mHeightLoading;
    private boolean isAllCaps;

    private Style mStyle;

    public SpinKitView getSkvLoading() {
        return skvLoading;
    }

    public void setSkvLoading(SpinKitView skvLoading) {
        this.skvLoading = skvLoading;
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public void setTvContent(TextView tvContent) {
        this.tvContent = tvContent;
    }

    public ImageView getImvLeft() {
        return imvLeft;
    }

    public void setImvLeft(ImageView imvLeft) {
        this.imvLeft = imvLeft;
    }

    public Drawable getDwSuccess() {
        return dwSuccess;
    }

    public void setDwSuccess(Drawable dwSuccess) {
        this.dwSuccess = dwSuccess;
    }

    public Drawable getDwError() {
        return dwError;
    }

    public void setDwError(Drawable dwError) {
        this.dwError = dwError;
    }

    public int getLoadingColor() {
        return mLoadingColor;
    }

    public void setLoadingColor(int mLoadingColor) {
        this.mLoadingColor = mLoadingColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public String getText() {
        return mText;
    }

    public void setText(String mText) {
        this.mText = mText;
    }

    public String getTextSuccess() {
        return mTextSuccess;
    }

    public void setTextSuccess(String mTextSuccess) {
        this.mTextSuccess = mTextSuccess;
    }

    public String getTextError() {
        return mTextError;
    }

    public void setTextError(String mTextError) {
        this.mTextError = mTextError;
    }

    public String getTextLoading() {
        return mTextLoading;
    }

    public void setTextLoading(String mTextLoading) {
        this.mTextLoading = mTextLoading;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }

    public int getWidthLoading() {
        return mWidthLoading;
    }

    public void setWidthLoading(int mWidthLoading) {
        this.mWidthLoading = mWidthLoading;
    }

    public int getHeightLoading() {
        return mHeightLoading;
    }

    public void setHeightLoading(int mHeightLoading) {
        this.mHeightLoading = mHeightLoading;
    }

    public boolean isAllCaps() {
        return isAllCaps;
    }

    public void setAllCaps(boolean allCaps) {
        isAllCaps = allCaps;
    }

    public Style getStyleLoading() {
        return mStyle;
    }

    public void setStyleLoading(Style mStyle) {
        this.mStyle = mStyle;
    }

    public OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener listener){
        this.onClickListener = listener;
    }


    public ProgressButton(Context context) {
        super(context);
    }

    public ProgressButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    public ProgressButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context, attrs);
    }

      @SuppressLint("UseCompatLoadingForDrawables")
    public void initView(Context context, AttributeSet attrs){
        setClickable(true);
        setFocusable(true);


        @SuppressLint("Recycle")
        final TypedArray mTypedArray = context.obtainStyledAttributes(attrs,R.styleable.ProgressButton);

        //Get value
        mLoadingColor = mTypedArray.getColor(R.styleable.ProgressButton_loading_color, Color.parseColor("#000000"));
        mTextColor = mTypedArray.getColor(R.styleable.ProgressButton_text_color, Color.parseColor("#000000"));

        mText = mTypedArray.getString(R.styleable.ProgressButton_text) == null ? "" : mTypedArray.getString(R.styleable.ProgressButton_text);
        mTextError = mTypedArray.getString(R.styleable.ProgressButton_text_error) == null ? "" : mTypedArray.getString(R.styleable.ProgressButton_text_error);
        mTextSuccess = mTypedArray.getString(R.styleable.ProgressButton_text_success) == null ? "" : mTypedArray.getString(R.styleable.ProgressButton_text_success);
        mTextLoading = mTypedArray.getString(R.styleable.ProgressButton_text_loading) == null ? "" : mTypedArray.getString(R.styleable.ProgressButton_text_loading);

        mTextSize = mTypedArray.getDimension(R.styleable.ProgressButton_text_size, 17.0f);
        mWidthLoading = mTypedArray.getDimensionPixelSize(R.styleable.ProgressButton_width_loading, (int)convertDPToPixel(24));
        mHeightLoading = mTypedArray.getDimensionPixelSize(R.styleable.ProgressButton_height_loading, (int)convertDPToPixel(24));

        isAllCaps = mTypedArray.getBoolean(R.styleable.ProgressButton_text_all_caps, true);

        //Get style of loading
        mStyle = Style.values()[mTypedArray.getInt(R.styleable.ProgressButton_loading_style, 9)];

        //Init default the drawables
        dwError = mTypedArray.getDrawable(R.styleable.ProgressButton_icon_fail) == null ?
                context.getDrawable(R.drawable.ic_outline_cancel_24) :
                mTypedArray.getDrawable(R.styleable.ProgressButton_icon_fail);

        dwSuccess =  mTypedArray.getDrawable(R.styleable.ProgressButton_icon_success) == null ?
                context.getDrawable(R.drawable.ic_baseline_check_circle_outline_24) :
                mTypedArray.getDrawable(R.styleable.ProgressButton_icon_success);
        dwError.setTint(mLoadingColor);
        dwSuccess.setTint(mLoadingColor);


        //Create loading view
        skvLoading = new SpinKitView(context);
        skvLoading.setColor(mLoadingColor);
        skvLoading.setIndeterminateDrawable(SpriteFactory.create(mStyle));
        skvLoading.setVisibility(INVISIBLE);
        skvLoading.setId(R.id.skvLoading);



        //Create image left
        imvLeft = new ImageView(context);
        imvLeft.setImageDrawable(dwSuccess);
        imvLeft.setScaleType(ImageView.ScaleType.CENTER);
        imvLeft.setVisibility(INVISIBLE);
        imvLeft.setId(R.id.imvLeftActive);

        //Create text view content
        tvContent = new TextView(context);
        tvContent.setText(isAllCaps ? mText.toUpperCase() : mText);
        tvContent.setTypeface(tvContent.getTypeface(), Typeface.BOLD);
        tvContent.setTextColor(mTextColor);
        tvContent.setGravity(Gravity.CENTER);
        tvContent.setTextSize(mTextSize);
        tvContent.setSingleLine(true);
        tvContent.setEllipsize(TextUtils.TruncateAt.END);
        tvContent.setId(R.id.tvActive);


        RelativeLayout.LayoutParams lpLoading = new RelativeLayout.LayoutParams(mWidthLoading, mHeightLoading);
        lpLoading.setMargins(20,0,0,0);
        lpLoading.addRule(RelativeLayout.ALIGN_PARENT_START,RelativeLayout.TRUE);
        lpLoading.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
        addView(skvLoading,lpLoading);
        addView(imvLeft,lpLoading);


        RelativeLayout.LayoutParams lpText = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        lpText.setMargins(10,4,20 + mWidthLoading ,0);
        lpText.addRule(RelativeLayout.END_OF,skvLoading.getId());
        lpText.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        lpText.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        lpText.addRule(RelativeLayout.END_OF,imvLeft.getId());
        addView(tvContent,lpText);

    }



    private float convertDPToPixel(float dp){
        Resources r = getResources();
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }

    /**
     * State of action
     * @param isSuccess status
     */
    public void onCompleted(boolean isSuccess){

        //Cancel timer
        if(timerLoading != null){
            timerLoading.cancel();
            timerLoading = null;
            countTimer = 0;
        }


        skvLoading.setVisibility(INVISIBLE);
        imvLeft.setVisibility(VISIBLE);

        if(isSuccess){
            tvContent.setText(isAllCaps ? mTextSuccess.toUpperCase() : mTextSuccess);
            imvLeft.setImageDrawable(dwSuccess);
        }else{
            tvContent.setText(isAllCaps ? mTextError.toUpperCase() : mTextError);
            imvLeft.setImageDrawable(dwError);
        }

        //Turn on the click event
        setClickable(true);
    }


    /**
     * Reset button state
     */
    public void resetState(){
        //Turn on the click event
        setClickable(true);

        //Cancel timer
        if(timerLoading != null){
            timerLoading.cancel();
            timerLoading = null;
            countTimer = 0;
        }

        skvLoading.setVisibility(INVISIBLE);
        imvLeft.setVisibility(INVISIBLE);

        tvContent.setText(isAllCaps ? mText.toUpperCase() : mText);
    }


    private void actionClick(){

        if(isClickable()){
            //Turn off the click event
            setClickable(false);

            countTimer = 0;
            //Add timer
            if(timerLoading == null){
                timerLoading = new Timer();
            }

            timerLoading.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(countTimer < 3){
                        //Add marks "." behind the content
                        final StringBuilder content = new StringBuilder(mTextLoading);
                        switch (countTimer){
                            case 0:
                                content.append(".");
                                break;
                            case 1:
                                content.append("..");
                                break;
                            case 2:
                                content.append("...");
                                break;
                        }

                        ((Activity)getContext()).runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                tvContent.setText(isAllCaps ? content.toString().toUpperCase() : content);
                            }
                        });


                        countTimer++;
                    }else{
                        countTimer = 0;
                        ((Activity)getContext()).runOnUiThread(new TimerTask() {
                            @Override
                            public void run() {
                                tvContent.setText(isAllCaps ? mTextLoading.toUpperCase() : mTextLoading);
                            }
                        });
                    }
                }
            },50,500);

            skvLoading.setVisibility(VISIBLE);
            imvLeft.setVisibility(INVISIBLE);

            if(onClickListener != null){
                onClickListener.onClick(ProgressButton.this);
            }

        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            actionClick();
        }
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_UP && (event.getKeyCode() == KeyEvent.KEYCODE_DPAD_CENTER || event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            actionClick();
        }
        return super.dispatchKeyEvent(event);
    }

    public interface OnClickListener{
        void onClick(View v);
    }

}
