echo ""
echo "Removing annoying files/XMLs..."
find . -name ".DS_Store" -depth -exec rm {} \;
find . -name "calls.xml" -depth -exec rm {} \;
find . -name "contact_picker_list.xml" -depth -exec rm {} \;
find . -name "conversation_actionbar.xml" -depth -exec rm {} \;
find . -name "conversations_row.xml" -depth -exec rm {} \;
find . -name "conversations.xml" -depth -exec rm {} \;
find . -name "eula.xml" -depth -exec rm {} \;
find . -name "home.xml" -depth -exec rm {} \;
find . -name "preferences.xml" -depth -exec rm {} \;
find . -name "icon.png" -depth -exec rm {} \;
find . -name "notifybar.png" -depth -exec rm {} \;
find . -name "balloon_outgoing_normal.9.png" -depth -exec rm {} \;
find . -name "balloon_outgoing_normal_ext.9.png" -depth -exec rm {} \;


echo ""
echo "Moving Android Studio code to WhatsApp..."
#echo "mv ~/wamod/AndroidStudioProject/app/src/main/java/com/wamod/*.smali smali/com/wamod/"
mv ~/wamod/AndroidStudioProject/app/src/main/java/com/wamod/*.smali smali/com/wamod 2>/dev/null

mv ~/wamod/AndroidStudioProject/app/src/main/java/com/wamod/view/*.smali smali/com/wamod/view 2>/dev/null
mv ~/wamod/AndroidStudioProject/app/src/main/java/com/wamod/themes/*.smali smali/com/wamod/themes 2>/dev/null
mv ~/wamod/AndroidStudioProject/app/src/main/java/com/wamod/themes/QTS/*.smali smali/com/wamod/themes/QTS 2>/dev/null
mv ~/wamod/AndroidStudioProject/app/src/main/java/com/wamod/entry/*.smali smali/com/wamod/entry 2>/dev/null
mv ~/wamod/AndroidStudioProject/app/src/main/java/com/wamod/WAclass/*.smali smali/com/wamod/WAclass 2>/dev/null
mv ~/wamod/AndroidStudioProject/app/src/main/java/com/wamod/preference/*.smali smali/com/wamod/preference 2>/dev/null
mv ~/wamod/AndroidStudioProject/app/src/main/java/com/wamod/settings/*.smali smali/com/wamod/preference 2>/dev/null

#cp -r ~/wamod/AndroidStudioProject/app/src/main/res/layout/wamod_* res/layout
cp -r ~/wamod/AndroidStudioProject/app/src/main/res/*-v1 res/
cp ~/wamod/AndroidStudioProject/app/src/main/res/drawable-v21/* res/drawable-v21

cp ~/wamod/AndroidStudioProject/app/src/main/assets/wamod_credits.html assets/wamod_credits.html

echo ""
echo "Compiling..."
echo ""
apktool b -f -o WAMOD_unsigned.apk
echo ""
echo "Signing..."
jarsigner -sigalg SHA1withRSA -digestalg SHA1 -keystore [YOUR_FILE].jks -storepass [YOUR_PASSWORD] -signedjar WAMOD_signed.apk WAMOD_unsigned.apk [YOUR_KEY]
echo ""
echo "Zipalign..."
zipalign -f 4 WAMOD_signed.apk WAMOD_signed_zipalign.apk
echo ""
echo "Installing..."
echo ""
adb install -r -d WAMOD_signed.apk
echo ""
echo "Opening..."
echo ""
adb shell am start -n com.whatsapp/com.whatsapp.HomeActivity
echo ""
echo "Done."
echo ""