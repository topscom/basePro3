package com.example.xiaojin20135.mybaseapp.webview;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.example.xiaojin20135.basemodule.activity.ToolBarActivity;
import com.example.xiaojin20135.mybaseapp.R;
import butterknife.BindView;
import static com.example.xiaojin20135.basemodule.util.ConstantUtil.URLWEB;

public class BaseWebViewActivity extends ToolBarActivity {
    @BindView (R.id.content_WV)
    WebView content_WV;
    @BindView (R.id.load_progress_PB)
    ProgressBar load_progress_PB;
    private WebSettings webSettings;
    private String url = "";
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
    }

    @Override
    protected int getLayoutId () {
        return R.layout.activity_base_web_view;
    }

    @Override
    protected void initView () {
        webSettings = content_WV.getSettings ();
        commonSetting ();
        content_WV.loadUrl (url);
        //设置不用系统浏览器，直接在当前WebView中打开
        content_WV.setWebViewClient (new WebViewClient ());
        //设置WebChromeClient类
        content_WV.setWebChromeClient (new WebChromeClient (){
            ///页面标题
            @Override
            public void onReceivedTitle (WebView view, String title) {
                super.onReceivedTitle (view, title);
                Log.d (TAG,"title = " + title);
                setTitleText (title);
            }
            //加载进度
            @Override
            public void onProgressChanged (WebView view, int newProgress) {
                super.onProgressChanged (view, newProgress);
                load_progress_PB.setProgress (newProgress);
                Log.d (TAG,"newProgress = " + newProgress);
            }
        });
        //设置WebViewClient类
        content_WV.setWebViewClient (new WebViewClient (){
            @Override
            public void onPageStarted (WebView view, String url, Bitmap favicon) {
                super.onPageStarted (view, url, favicon);
                load_progress_PB.setVisibility (View.VISIBLE);
                Log.d (TAG,"url = " + url);
            }

            @Override
            public void onPageFinished (WebView view, String url) {
                super.onPageFinished (view, url);
                load_progress_PB.setVisibility (View.GONE);
                Log.d (TAG,"加载完成");
                Log.d (TAG,"url = " + url);
            }
        });
    }

    @Override
    protected void initEvents () {

    }

    @Override
    protected void loadData () {
        url = getIntent ().getStringExtra (URLWEB);
    }

    /**
     * @author lixiaojin
     * @createon 2018-09-18 10:50
     * @Describe 通用设置
     */
    private void commonSetting(){
        //设置WebView支持JavaScript
        webSettings.setJavaScriptEnabled (true);
        //设置自适应屏幕
        webSettings.setUseWideViewPort (true); //将图片调整到适合WebView的大小
        webSettings.setLoadWithOverviewMode (true); //缩放值屏幕大小
        //缩放操作
        webSettings.setSupportZoom (true);//支持缩放
        webSettings.setBuiltInZoomControls (true);//设置内置的缩放控件，
        webSettings.setDisplayZoomControls (false);//隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//关闭webview中缓存
        webSettings.setAllowFileAccess(true);//设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
    }


    @Override
    public boolean onKeyDown (int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK && content_WV.canGoBack ()){
            content_WV.goBack ();
            return true;
        }
        return super.onKeyDown (keyCode, event);
    }

    @Override
    protected void onDestroy () {
        Log.d (TAG,"onDestroy");
        if(content_WV != null){
            //清除缓存数据
            content_WV.clearHistory ();
            ((ViewGroup)content_WV.getParent ()).removeView (content_WV);
            content_WV.destroy ();
            content_WV = null;
        }
        super.onDestroy ();
    }
}