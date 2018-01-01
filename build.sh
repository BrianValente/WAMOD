#!/usr/bin/env bash

echo ""
echo "Removing annoying files/XMLs..."
find . -name "home.xml" -depth -exec rm {} \;
find . -name ".DS_Store" -depth -exec rm {} \;
find . -name "*.bak" -depth -exec rm {} \;
echo ""

echo ""
echo "Moving Android Studio code to project..."
echo ""

(
cd /Users/brianvalente/Library/Mobile\ Documents/com\~apple\~CloudDocs/Projects/AndroidStudio/WAMOD
./gradlew assembleDebug
rm -rf /tmp/app-debug/ 2>/dev/null
baksmali disassemble app/build/outputs/apk/debug/app-debug.apk -o /tmp/app-debug
)

rm -rf smali/wamod/
cp -R /tmp/app-debug/wamod/ smali/wamod/

(
cd res/
for d in ~/Projects/AndroidStudio/WhatsApp/app/src/main/res/*-v1
do
    #rm -rf "$d"
    cp -R "$d" ./
done
)

echo ""
echo "Compiling..."
echo ""
apktool b -f -o /tmp/WhatsApp_unsigned.apk

echo ""
echo "Signing..."
jarsigner -sigalg SHA1withRSA -digestalg SHA1 -keystore ../WhatsApp.jks -storepass <password> -signedjar /tmp/WhatsApp_signed.apk /tmp/WhatsApp_unsigned.apk <store>
echo ""

echo ""
echo "Zipalign..."
zipalign -f 4 /tmp/WhatsApp_signed.apk /tmp/WhatsApp_signed_zipalign.apk
echo ""

echo ""
echo "Installing..."
echo ""
adb install -r /tmp/WhatsApp_signed_zipalign.apk

echo ""
echo "Opening..."
echo ""
adb shell am start -n com.whatsapp/com.whatsapp.Main

echo ""
echo "Done."
echo ""