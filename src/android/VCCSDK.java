package com.alliance.vccsdk;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alliance.sdklib.VCCIntent;
import com.alliance.sdklib.VCCWebActivity;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class VCCSDK extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        Context context = cordova.getActivity().getApplicationContext();
        if (action.equals("coolMethod")) {
            String message = args.getString(0);
            this.coolMethod(message, callbackContext);
            return true;
        } else if (action.equals("launchVcc")) {
            String vccHost = args.getString(0);
            String licenseKey = args.getString(1);
            String partnerKey = args.getString(2);
            String partnerProfileId = args.getString(3);
            String icNo = args.getString(4);
            String aomUserInfo = args.getString(5);
            String onboardingData = args.getString(6);
            Log.d("VCCSDK", vccHost);
            Log.d("VCCSDK", licenseKey);
            Log.d("VCCSDK", partnerKey);
            Log.d("VCCSDK", partnerProfileId);
            Log.d("VCCSDK", icNo);
            Log.d("VCCSDK", aomUserInfo);
            Log.d("VCCSDK", onboardingData);
            this.launchVcc(vccHost, licenseKey, partnerKey, partnerProfileId, icNo, aomUserInfo, onboardingData, context, callbackContext);
            return true;
        }
        return false;
    }

    private void coolMethod(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) {
            callbackContext.success(message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    private void launchVcc(String vccHost, String licenseKey, String partnerKey, String partnerProfileId, String icNo, String aomUserInfo, String onboardingData, Context context, CallbackContext callbackContext) {
        if ( (vccHost != null && vccHost.length() > 0) || (licenseKey != null && licenseKey.length() > 0) || (partnerKey != null && partnerKey.length() > 0) || (partnerProfileId != null && partnerProfileId.length() > 0)) {
            this.openNewActivity(context, vccHost, licenseKey, partnerKey, partnerProfileId, icNo, aomUserInfo, onboardingData );
            callbackContext.success("ok");
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
      //sdk done all the things, nothing to handle the callback
    }

    private void openNewActivity(Context context, String vccHost, String licenseKey, String partnerKey, String partnerProfileId, String icNo, String aomUserInfo, String onboardingData) {
      Intent intent = new Intent(cordova.getActivity(), VCCWebActivity.class);
      intent.putExtra(VCCIntent.VCC_HOST, vccHost);
      intent.putExtra(VCCIntent.LICENSE_KEY, licenseKey );
      intent.putExtra(VCCIntent.PARTNER_KEY, partnerKey );
      intent.putExtra(VCCIntent.IC_NO, icNo);
      intent.putExtra(VCCIntent.PARTNER_PROFILE_ID, partnerProfileId);
      intent.putExtra("aomUserInfo", aomUserInfo);
      intent.putExtra("onboardingData", onboardingData);
      this.cordova.startActivityForResult(this, intent, 100);
    }
}
