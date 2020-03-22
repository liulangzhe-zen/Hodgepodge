package com.basics.common.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.basics.common.R;

/**
 * @Description: 个人中心  等场景使用   为左图 右文字 及图
 * @Author: xueshijie
 * @CreateDate: 2019-12-22 19:07
 * @Version: 1.0
 */
public class MenuItemLayout extends FrameLayout {

    private Context mContext;
    private View mView;

    //顶部横线
    private View mViewLine;
    //竖线
    private View mViewVertical;
    // 左边
    private RelativeLayout mLinLeft;
    private  TextView mTvLeft;
    private  ImageView mImageLeft;
    private  TextView mTvContentText;

    private LinearLayout mlinRight;
    private TextView mTvRght;
    private ImageView mRightRemind;
    private ImageView mImgRight;

    private OnClickListener onClickListener;



    public MenuItemLayout(Context context) {
        this(context, null);
    }

    public MenuItemLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MenuItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.item_menuitem, this, true);

        initView(mView);

        TypedArray a = mContext.obtainStyledAttributes(attrs, R.styleable.Ui_MenuItemLayout);

        setTitleText(a.getString(R.styleable.Ui_MenuItemLayout_title_text));
        setLeftWhetherShow(a.getBoolean(R.styleable.Ui_MenuItemLayout_left_whetherShow,false));
        setRightWhetherShow(a.getBoolean(R.styleable.Ui_MenuItemLayout_right_whetherShow,false));
        setLeftTitle(a.getString(R.styleable.Ui_MenuItemLayout_left_title));
        setLeftImage(a.getDrawable(R.styleable.Ui_MenuItemLayout_left_img));
        setRightTitle(a.getString(R.styleable.Ui_MenuItemLayout_right_title));
        setRightImage(a.getDrawable(R.styleable.Ui_MenuItemLayout_right_img));
        setDivideLine(a.getInt(R.styleable.Ui_MenuItemLayout_divide_line_style, 1));

//        setDivideLine(divideLineStyle);
    }

    private void setRightWhetherShow(boolean aBoolean) {

        if (aBoolean){
            mImgRight.setVisibility(View.GONE);
            mTvRght.setVisibility(View.VISIBLE);
        } else {
            mImgRight.setVisibility(View.VISIBLE);
            mTvRght.setVisibility(View.GONE);
        }


    }

    private void setDivideLine(Integer dimension) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mViewLine.getLayoutParams();
        layoutParams.height = dimension;
        mViewLine.setLayoutParams(layoutParams);

    }

    private void setRightImage(Drawable drawable) {
        if (drawable != null){
            mImgRight.setImageDrawable(drawable);
        }

    }

    private void setRightTitle(String rightTitle) {

        if (TextUtils.isEmpty(rightTitle)){
            mTvRght.setText(rightTitle);
        }

    }

    private void setLeftImage(Drawable drawable) {
        if (drawable != null){
            mImageLeft.setImageDrawable(drawable);
        }
    }

    private void setLeftTitle(String left_title) {
        if (!TextUtils.isEmpty(left_title)){
            mTvLeft.setText(left_title);
        }
    }

    private void setLeftWhetherShow(boolean whetherShow) {
        if (whetherShow){
            mImageLeft.setVisibility(View.GONE);
            mTvLeft.setVisibility(View.VISIBLE);
        } else {
            mImageLeft.setVisibility(View.VISIBLE);
            mTvLeft.setVisibility(View.GONE);
        }


    }

    private void initView(View mView) {

        mViewLine =  mView.findViewById(R.id.view_line);
        mViewVertical =  mView.findViewById(R.id.view_vertical);
        mLinLeft  =  mView.findViewById(R.id.lin_left);
        mTvLeft  =  mView.findViewById(R.id.tv_left);
        mImageLeft  = mView.findViewById(R.id.img_left);
        mTvContentText  = mView.findViewById(R.id.tv_content_text);
        mlinRight  = mView.findViewById(R.id.lin_right);
        mTvRght  = mView.findViewById(R.id.tv_right);
        mRightRemind  = mView.findViewById(R.id.tv_remind);
        mImgRight  = mView.findViewById(R.id.img_right);

    }

    private void setTitleText(String string) {

        if (!TextUtils.isEmpty(string)){
            mTvContentText.setText(string);
        }

    }

    public void setViewOnlickListener(OnClickListener onlickListener) {
        this.onClickListener = onlickListener;
        mView.setOnClickListener(onlickListener);
    }
}
