package newsappbrowser.bacancy.com.kotlinnewsapp.activity

import android.app.PendingIntent.getActivity
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

import kotlinx.android.synthetic.main.activity_web.*
import android.support.annotation.RequiresApi
import android.transition.Explode
import android.view.Window
import android.content.pm.ActivityInfo
import newsappbrowser.bacancy.com.kotlinnewsapp.R
import android.support.design.widget.BottomSheetDialog
import android.widget.TextView
import kotlinx.android.synthetic.main.bottomsheet.*


class WebActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (window.sharedElementEnterTransition != null) {
                window.sharedElementEnterTransition.duration = 1000
                window.sharedElementEnterTransition.duration = 1000
            }
        }
        with(window) {
            requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
            window.enterTransition = Explode()
            // set an exit transition
            exitTransition = Explode()
        }
//        window.enterTransition = Explode()
// set an exit transition
        window.exitTransition = Explode()


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        setSupportActionBar(toolbar)

        titleTxt.text = intent.getStringExtra("title")

        val settings = webview.settings

        // Enable java script in web view
        settings.javaScriptEnabled = true

        // Enable and setup web view cache
        settings.setAppCacheEnabled(true)
        settings.cacheMode = WebSettings.LOAD_DEFAULT
        settings.setAppCachePath(cacheDir.path)

        toolbar.setNavigationIcon(R.drawable.ic_back_24dp)
        toolbar.setNavigationOnClickListener {
            //            supportFinishAfterTransition()
            onBackPressed()
            overridePendingTransition(0, 0)
        }

        // inside your activity (if you did not enable transitions in your theme)

//         More setting ....
        // Enable zooming in web view
        settings.setSupportZoom(true)
        settings.builtInZoomControls = false
        settings.displayZoomControls = false

        more_image.setOnClickListener {
            loadBottomsheet()
        }

        // Zoom web view text
//        settings.textZoom = 125


        // Enable disable images in web view
        settings.blockNetworkImage = false
        // Whether the WebView should load image resources
        settings.loadsImagesAutomatically = true


        // More web view settings
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            settings.safeBrowsingEnabled = true  // api 26
        }
        //settings.pluginState = WebSettings.PluginState.ON
        settings.useWideViewPort = true
        settings.loadWithOverviewMode = true
        settings.javaScriptCanOpenWindowsAutomatically = true
        settings.mediaPlaybackRequiresUserGesture = false


        // More optional settings, you can enable it by yourself
        settings.domStorageEnabled = true
        settings.setSupportMultipleWindows(true)
        settings.loadWithOverviewMode = true
        settings.allowContentAccess = true
        settings.setGeolocationEnabled(true)
        settings.allowUniversalAccessFromFileURLs = true
        settings.allowFileAccess = true

        // WebView settings
        webview.fitsSystemWindows = true

        webview.setLayerType(View.LAYER_TYPE_HARDWARE, null)

        webview.loadUrl(intent.getStringExtra("weburl"))
//        webview.loadUrl("file:///android_asset/web/Player.html")
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
        }


        // Set web view client
        webview.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                // Page loading started
                // Do something
                webviewloader.visibility = View.VISIBLE

                // Enable disable back forward button
//                button_back.isEnabled = webview.canGoBack()
//                button_forward.isEnabled = webview.canGoForward()
            }

            override fun onPageFinished(view: WebView, url: String) {
                // Page loading finished
                // Display the loaded page title in a toast message
                /*   toast("Page loaded: ${view.title}")

                   // Enable disable back forward button
                   button_back.isEnabled = webview.canGoBack()
                   button_forward.isEnabled = webview.canGoForward()*/
                webviewloader.visibility = View.GONE
            }
        }


        // Set web view chrome client
        webview.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                webviewloader.progress = newProgress
            }
        }

    }

    fun loadBottomsheet() {
        val mBottomSheetDialog = BottomSheetDialog(this)

        val sheetView = layoutInflater.inflate(R.layout.bottomsheet, null)
        val refreshbtn = sheetView.findViewById<TextView>(R.id.refresh_btmsheet)
        refreshbtn.setOnClickListener {
            webview.loadUrl(intent.getStringExtra("weburl"))
            mBottomSheetDialog.dismiss()
        }
        mBottomSheetDialog.setContentView(sheetView)
        mBottomSheetDialog.setCanceledOnTouchOutside(true)
        mBottomSheetDialog.show()

    }

    override fun onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack()
        } else {
            finish()
        }
//        super.onBackPressed()
    }

}
