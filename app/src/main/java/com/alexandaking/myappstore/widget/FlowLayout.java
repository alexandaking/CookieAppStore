package com.alexandaking.myappstore.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexandaking on 2017/12/9.
 */

public class FlowLayout extends ViewGroup{

    private Line line ;
    private List<Line> lines = new ArrayList<>() ;
    private int mUserWidth = 0 ;
    /** 行中每个子view的间距 */
    private int mHorizontalSpacing = 20 ;
    /** 行与行之间的间距 */
    private int mVerticalSpacing = 20 ;


    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    //摆放子view的位置
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = getPaddingLeft();
        int top = getPaddingTop() ;
        for(int i = 0 ; i < lines.size() ; i ++){
            Line line = lines.get(i);
            line.layout(left,top);
            top = top + line.mHeight + mVerticalSpacing ;
        }
    }

    //负责测量子View，子view测量完成后才会测量自身
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //获取可用的宽和高
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec) - getPaddingLeft() - getPaddingRight();
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec) - getPaddingTop() - getPaddingBottom();

        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        //测量子view
        int childCount = getChildCount();
        //还原数据
        restoreLine();
        for(int i = 0 ; i < childCount ; i ++){
            View child = getChildAt(i);

            int childWidthMeasureSpec = MeasureSpec.makeMeasureSpec(sizeWidth,
                    modeWidth == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : modeWidth) ;
            int childHeightMeasureSpec = MeasureSpec.makeMeasureSpec(sizeHeight,
                    modeHeight == MeasureSpec.EXACTLY ? MeasureSpec.AT_MOST : modeHeight) ;
            //测量子View
            child.measure(childWidthMeasureSpec,childHeightMeasureSpec);

            if(line == null){
                line = new Line() ;
            }
            mUserWidth += child.getMeasuredWidth();//增加已用的宽度
            if(mUserWidth <= sizeWidth){//使用的宽度小于最大宽度，该child属于这一行
                line.addView(child);//将child添加到行中
                mUserWidth += mHorizontalSpacing ;//加上间隔
                if(mUserWidth >= sizeWidth){//换行
                    newLine() ;
                }
            }else {//换行
                if(line.getViewCount() == 0){//上一行为空
                    line.addView(child);//强行将子View放入行中
                    newLine();
                }else{
                    newLine();
                    line.addView(child);
                    mUserWidth = mUserWidth + child.getMeasuredWidth() + mHorizontalSpacing ;
                }
            }
        }
        //将最后一行添加到集合中
        if(line != null && line.getViewCount() > 0 && !lines.contains(line) ){
            lines.add(line);
        }

        int totalWidth = MeasureSpec.getSize(widthMeasureSpec);
        int totalHeight = 0;

        //1 所有的行的高
        int lineCount = lines.size();
        for(int i = 0 ; i < lineCount ; i ++){
            totalHeight += lines.get(i).mHeight ;
        }
        //2 行之间间隙的高的总和
        totalHeight += mVerticalSpacing * (lineCount - 1) ;

        //3 padding高度  paddingTop paddingBottom
        totalHeight = totalHeight + getPaddingTop() + getPaddingBottom() ;

        //测量自身宽高  测量自身时不用考虑宽度，因为行中子view换自动换行，高度是采用父view传递的高度还是子view高度总和
        setMeasuredDimension(totalWidth,resolveSize(totalHeight,heightMeasureSpec));
    }

    private void restoreLine() {
        lines.clear();
        line = new Line() ;
        mUserWidth = 0 ;
    }

    private void newLine() {
        if(line != null){
            lines.add(line);
        }
        line = new Line() ;
        mUserWidth = 0 ;
    }


    class Line{
        private List<View> views = new ArrayList<>() ;
        public int mHeight; //高度
        public int mWidth ;//宽度

        public void addView(View child) {
            views.add(child);
            mWidth += child.getMeasuredWidth() ;
            //取行中最高的View的高度
            mHeight = mHeight < child.getMeasuredHeight() ? child.getMeasuredHeight() : mHeight ;
        }

        public int getViewCount(){
            return views.size() ;
        }

        public void layout(int left, int top) {
            //总宽度
            int totalWidth = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
            //剩余的部分应该是总宽度-所有的宽度和间隙的宽度
            int surplusWidth = totalWidth - mWidth - mHorizontalSpacing * (views.size() - 1);
            if(surplusWidth > 0){
                //为了转换后更精确 +0.5
                int splitSpacing = (int) (surplusWidth/views.size()*1.0+0.5);
                for(int i = 0 ; i < views.size() ; i ++ ){
                    View view = views.get(i);
                    int childWidth = view.getMeasuredWidth() ;
                    int childHeight = view.getMeasuredHeight() ;

                    //将剩余的空间平均分配给子view
                    childWidth += splitSpacing ;
                    //重新设置子view的宽度
                    view.getLayoutParams().width = childWidth ;
                    //左上角坐标
                    int topOff = (int) ((mHeight - view.getMeasuredHeight())/2 + 0.5);
                    if(topOff < 0){
                        topOff = 0 ;
                    }
                    if(splitSpacing > 0){
                        //因为宽度变化了，所以需要重新测量
                        int widthMeasureSpce = MeasureSpec.makeMeasureSpec(childWidth,MeasureSpec.EXACTLY);
                        int heightMeasureSpce = MeasureSpec.makeMeasureSpec(childHeight,MeasureSpec.EXACTLY) ;
                        view.measure(widthMeasureSpce,heightMeasureSpce);
                    }

                    view.layout(left,top + topOff,left+childWidth,top + topOff + childHeight);
                    left = left + view.getMeasuredWidth() + mHorizontalSpacing ;
                }

            }else {
                if(views.size() == 1){
                    View view = views.get(0);
                    view.layout(left,top,left + view.getMeasuredWidth(),top + view.getMeasuredHeight());
                }
            }



        }
    }

}
