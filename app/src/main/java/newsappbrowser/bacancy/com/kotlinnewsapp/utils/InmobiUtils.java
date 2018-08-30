package newsappbrowser.bacancy.com.kotlinnewsapp.utils;

import android.app.Activity;
import android.util.Log;

import com.inmobi.ads.InMobiAdRequestStatus;
import com.inmobi.ads.InMobiBanner;
import com.inmobi.ads.InMobiInterstitial;
import com.inmobi.sdk.InMobiSdk;

import java.util.Map;

/**
 * Created by siddharth on 1/8/18.
 */

public class InmobiUtils {
    static InMobiInterstitial interstitialAd = null;


    public static void loadInterstitial(Activity act) {
        InMobiSdk.init(act, "f528cd62fe774de4a18298aaf1607d69");

        interstitialAd =
                new InMobiInterstitial(act, 1533678593792L, new InMobiInterstitial.InterstitialAdListener2() {
                    @Override
                    public void onAdLoadFailed(InMobiInterstitial inMobiInterstitial, InMobiAdRequestStatus inMobiAdRequestStatus) {
                        Log.e("inMobi", "=>" + inMobiAdRequestStatus.getMessage() + " :: " + inMobiAdRequestStatus.getStatusCode());
                    }

                    @Override
                    public void onAdReceived(InMobiInterstitial inMobiInterstitial) {
                        Log.e("inMobi", "=>" + inMobiInterstitial.getCreativeId());


                    }

                    @Override
                    public void onAdLoadSucceeded(InMobiInterstitial inMobiInterstitial) {
                        interstitialAd = inMobiInterstitial;
                        if (inMobiInterstitial.isReady()) {
                            inMobiInterstitial.show();
                        }
                    }

                    @Override
                    public void onAdRewardActionCompleted(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

                    }

                    @Override
                    public void onAdDisplayFailed(InMobiInterstitial inMobiInterstitial) {

                    }

                    @Override
                    public void onAdWillDisplay(InMobiInterstitial inMobiInterstitial) {

                    }

                    @Override
                    public void onAdDisplayed(InMobiInterstitial inMobiInterstitial) {

                    }

                    @Override
                    public void onAdInteraction(InMobiInterstitial inMobiInterstitial, Map<Object, Object> map) {

                    }

                    @Override
                    public void onAdDismissed(InMobiInterstitial inMobiInterstitial) {

                    }

                    @Override
                    public void onUserLeftApplication(InMobiInterstitial inMobiInterstitial) {

                    }
                });
        interstitialAd.load();

    }

    public static void showInterstitial(Activity act) {
        if (interstitialAd != null && interstitialAd.isReady()) {
            interstitialAd.show();
        } else {
            loadInterstitial(act);
        }
    }

    public static void loadBanner(Activity act,InMobiBanner inmobibanner) {
        InMobiSdk.init(act, "72c0fd1accc24c2fba216055b3b2247f");

        inmobibanner.load();
        inmobibanner.setListener(new InMobiBanner.BannerAdListener() {
            @Override
            public void onAdLoadSucceeded(InMobiBanner inMobiBanner) {

            }

            @Override
            public void onAdLoadFailed(InMobiBanner inMobiBanner, InMobiAdRequestStatus inMobiAdRequestStatus) {

            }

            @Override
            public void onAdDisplayed(InMobiBanner inMobiBanner) {

            }

            @Override
            public void onAdDismissed(InMobiBanner inMobiBanner) {

            }

            @Override
            public void onAdInteraction(InMobiBanner inMobiBanner, Map<Object, Object> map) {

            }

            @Override
            public void onUserLeftApplication(InMobiBanner inMobiBanner) {

            }

            @Override
            public void onAdRewardActionCompleted(InMobiBanner inMobiBanner, Map<Object, Object> map) {

            }
        });
    }

}
