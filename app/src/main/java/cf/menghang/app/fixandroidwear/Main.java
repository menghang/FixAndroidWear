package cf.menghang.app.fixandroidwear;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Main implements IXposedHookLoadPackage {

    private XC_MethodHook mVerifyDeviceBuildHook = new XC_MethodReplacement() {
        @Override
        protected Object replaceHookedMethod(MethodHookParam methodHookParam) throws Throwable {
            return true;
        }
    };

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.google.android.wearable.app")) {
            return;
        }

        try {
            XposedHelpers.findAndHookMethod("com.google.android.clockwork.companion.AppConfigurationVerifier", loadPackageParam.classLoader, "verifyDeviceBuild", mVerifyDeviceBuildHook);
        } catch (Throwable t) {
            XposedBridge.log(t);
        }
    }
}
