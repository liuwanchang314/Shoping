package com.customview;


import java.util.Calendar;

import com.alljf.jf.R;


import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;


/**
 * 刷新控制view
 * 
 * @author Nono
 * 
 */
public class RefreshableView extends LinearLayout {

    private static final String TAG = "LILITH";
    private Scroller scroller;
    private View refreshView;
    private ImageView refreshIndicatorView;
    private int refreshTargetTop = -80;//这段距离为下拉之后所显示的大小
    private ProgressBar bar;
    private TextView downTextView;
    private TextView timeTextView;
    private LinearLayout reFreshTimeLayout;//显示上次刷新时间的layout
    private RefreshListener refreshListener;

    private String downTextString;
    private String releaseTextString;

 //   private Long refreshTime = null;
    private int lastX;
    private int lastY;//定义Y轴坐标
    // 拉动标记
    private boolean isDragging = false;
    // 是否可刷新标记
    private boolean isRefreshEnabled = true;
    // 在刷新中标记
    private boolean isRefreshing = false;
    Calendar LastRefreshTime;
    
    private Context mContext;
    public RefreshableView(Context context) {
        super(context);
        mContext = context;
        
    }
    public RefreshableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
        
    }
    private void init() {
        // TODO Auto-generated method stub
        //滑动对象，
        LastRefreshTime=Calendar.getInstance();
        scroller = new Scroller(mContext);
        
        //刷新视图顶端的的view，也就是下拉之后显示在的顶端的那部分布局
         refreshView = LayoutInflater.from(mContext).inflate(R.layout.refresh_top_item, null);
        //指示器view
         refreshIndicatorView = (ImageView) refreshView.findViewById(R.id.indicator);
        //刷新bar
        bar = (ProgressBar) refreshView.findViewById(R.id.progress);
        //下拉显示text
         downTextView = (TextView) refreshView.findViewById(R.id.refresh_hint);
        //下来显示时间
         timeTextView = (TextView) refreshView.findViewById(R.id.refresh_time);
         reFreshTimeLayout=(LinearLayout)refreshView.findViewById(R.id.refresh_time_layout);
         
        LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, -refreshTargetTop);
        lp.topMargin = refreshTargetTop;//将刷新区域大小赋予布局参数的顶部margin
        lp.gravity = Gravity.CENTER;//居中显示
        addView(refreshView, lp);//此部分布局是加载在生成参数1的上下文所在的布局中
        downTextString = mContext.getResources().getString(R.string.refresh_down_text);
        releaseTextString = mContext.getResources().getString(R.string.refresh_release_text);   
    }




    /**
     * 设置上次刷新时间
     * @param time
     */
    private void setLastRefreshTimeText() {
        // TODO Auto-generated method stub
        reFreshTimeLayout.setVisibility(View.VISIBLE);
        Calendar NowTime=Calendar.getInstance();
        long l=NowTime.getTimeInMillis()-LastRefreshTime.getTimeInMillis();
        int days=new Long(l/(1000*60*60*24)).intValue();
        int hour=new Long(l/(1000*60*60)).intValue();
        int min=new Long(l/(1000*60)).intValue();
        if(days!=0)
        {
            timeTextView.setText(days+"天"); 
        }
        else  if(hour!=0)
        {
            timeTextView.setText(hour+"小时"); 
        }
        else if(min!=0)
        {
            timeTextView.setText(min+"分钟"); 
        }
       
         
        //timeTextView.setText(time);
    }


    
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        
        int y= (int) event.getRawY();//得到手势焦点相对于屏幕而言的Y轴坐标
        
        
        switch (event.getAction()) {
        case MotionEvent.ACTION_DOWN://手势落下的时候回调
        	 Log.i(TAG, "ACTION_DOWN");
            //记录下y坐标
            lastY = y;
            break;

        case MotionEvent.ACTION_MOVE://移动时回掉
            Log.i(TAG, "ACTION_MOVE");
            //y移动坐标
            int m = y - lastY;//如果在移动距离大于-1小于6时，判断为需要执行滑动
            if(((m < 6) && (m > -1)) || (!isDragging )){
                setLastRefreshTimeText();
                 doMovement(m);
            }
            //记录下此刻y坐标
            this.lastY = y;
            break;
            
        case MotionEvent.ACTION_UP:
            Log.i(TAG, "ACTION_UP");
            
            fling();
            
            break;
        }
        return true;
    }


    /**
     * up事件处理
     */
    private void fling() {
        // TODO Auto-generated method stub
        LinearLayout.LayoutParams lp = (LayoutParams) refreshView.getLayoutParams();
        Log.i(TAG, "fling()" + lp.topMargin);
        if(lp.topMargin > 0){//拉到了触发可刷新事件
            refresh();  
        }else{
            returnInitState();
        }
    }
    

    
    private void returnInitState() {
        // TODO Auto-generated method stub
         LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)this.refreshView.getLayoutParams();
         int i = lp.topMargin;
         scroller.startScroll(0, i, 0, refreshTargetTop);
         invalidate();
    }
    private void refresh() {
        // TODO Auto-generated method stub
         LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)this.refreshView.getLayoutParams();
         int i = lp.topMargin;
         reFreshTimeLayout.setVisibility(View.GONE);
         refreshIndicatorView.setVisibility(View.GONE);
         bar.setVisibility(View.VISIBLE);
         timeTextView.setVisibility(View.GONE);
         downTextView.setVisibility(View.GONE);
         scroller.startScroll(0, i, 0, 0-i);
         invalidate();
         if(refreshListener !=null){
             refreshListener.onRefresh(this);
             isRefreshing = true;
         }
    }
    
    /**
     * 
     */
    @Override
    public void computeScroll() {
        // TODO Auto-generated method stub
        if(scroller.computeScrollOffset()){
            int i = this.scroller.getCurrY();
              LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams)this.refreshView.getLayoutParams();
              int k = Math.max(i, refreshTargetTop);
              lp.topMargin = k;
              this.refreshView.setLayoutParams(lp);
              this.refreshView.invalidate();
              invalidate();
        }
    }
    /**
     * 下拉move事件处理
     * @param moveY
     */
    private void doMovement(int moveY) {
        // TODO Auto-generated method stub
        LinearLayout.LayoutParams lp = (LayoutParams) refreshView.getLayoutParams();
        if(moveY>0){
            //获取view的上边距
            float f1 =lp.topMargin;
            float f2 = moveY * 0.3F;//在这里之所以这么写，是因为滑动距离不同，上面所显示的拉开的范围也会不同
            int i = (int)(f1+f2);
            //修改上边距
            lp.topMargin = i;
            //修改后刷新
            refreshView.setLayoutParams(lp);
            refreshView.invalidate();
            invalidate();//该方法为提醒底层，该重新绘制view了 ，因为参数发生了改变
        }
        else 
        {
            float f1 =lp.topMargin;
            int i=(int)(f1+moveY*0.9F);
            Log.i("aa", String.valueOf(i));
            if(i>=refreshTargetTop)
            {
                lp.topMargin = i;
                //修改后刷新
                refreshView.setLayoutParams(lp);
                refreshView.invalidate();
                invalidate();
            }
            else 
            {
                
            }
        }
        
        timeTextView.setVisibility(View.VISIBLE);
//        if(refreshTime!= null){
//            setRefreshTime(refreshTime);
//        }
        downTextView.setVisibility(View.VISIBLE);
        
        refreshIndicatorView.setVisibility(View.VISIBLE);
        bar.setVisibility(View.GONE);
        if(lp.topMargin >  0){
        	/**
        	 * 这里是取消了图片
        	 * */
            downTextView.setText(R.string.refresh_release_text);
//            refreshIndicatorView.setImageResource(R.drawable.refresh_arrow_up);
        }else{
            downTextView.setText(R.string.refresh_down_text);
//            refreshIndicatorView.setImageResource(R.drawable.refresh_arrow_down);
        }
            
    }

    public void setRefreshEnabled(boolean b) {
        this.isRefreshEnabled = b;
    }

    public void setRefreshListener(RefreshListener listener) {
        this.refreshListener = listener;
    }


    /**
     * 结束刷新事件
     */
    public void finishRefresh(){
        Log.i(TAG, "执行了=====finishRefresh");
         LinearLayout.LayoutParams lp= (LinearLayout.LayoutParams)this.refreshView.getLayoutParams();
            int i = lp.topMargin;
            refreshIndicatorView.setVisibility(View.VISIBLE);
            timeTextView.setVisibility(View.VISIBLE);
            scroller.startScroll(0, i, 0, refreshTargetTop);
            invalidate();
            isRefreshing = false;  
            LastRefreshTime=Calendar.getInstance();
    }

    
    /*该方法一般和ontouchEvent 一起用
     * (non-Javadoc)
     * @see android.view.ViewGroup#onInterceptTouchEvent(android.view.MotionEvent)
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        // TODO Auto-generated method stub
        int action = e.getAction();
        int y= (int) e.getRawY();
        switch (action) {
        case MotionEvent.ACTION_DOWN:
            lastY = y;
            break;

        case MotionEvent.ACTION_MOVE:
            //y移动坐标
            int m = y - lastY;

            //记录下此刻y坐标
            this.lastY = y;
             if(m > 6 &&  canScroll()){
                 return true;
             }
            break;
        case MotionEvent.ACTION_UP:
            
            break;
            
    case MotionEvent.ACTION_CANCEL:
            
            break;
        }
        return false;
    }
    //重点是在这里的处理，也就是说，首先需要判断出来在这个自定义的layout当中，到底存在着那种
    //可拉动布局，然后进行相应的处理
    private boolean canScroll() {
        // TODO Auto-generated method stub
        View childView;
        if(getChildCount()>1){
            childView = this.getChildAt(1);
            if(childView instanceof ListView){
                int top =((ListView)childView).getChildAt(0).getTop(); 
                int pad =((ListView)childView).getListPaddingTop(); 
                if((Math.abs(top-pad)) < 3&&
                        ((ListView) childView).getFirstVisiblePosition() == 0){
                    return true;
                }else{
                    return false;
                }
            }else if(childView instanceof ScrollView){
                if(((ScrollView)childView).getScrollY() == 0){
                    return true;
                }else{
                    return false;
                }
            }
            
        }
        return false;
    }
    /**
     * 刷新监听接口
     * @author Nono
     *
     */
    public interface RefreshListener{
        public void onRefresh(RefreshableView view);
    }


}
