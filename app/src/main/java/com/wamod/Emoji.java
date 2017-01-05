package com.wamod;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.widget.TextView;
import com.wamod.CustomTypefaceSpan;
import com.wamod.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by brianvalente on 12/2/16.
 */
public class Emoji {
    private static final int EMOJISET_WHATSAPP = 0;
    private static final int EMOJISET_SYSTEM   = 1;
    private static final int EMOJISET_APPLE    = 2;
    private static final int EMOJISET_GOOGLE   = 3;
    private static final int EMOJISET_ONE      = 4;

    public static SpannableStringBuilder getEmojiSpannable(CharSequence charSequence, Context context, com.whatsapp.a6g a6g, com.whatsapp.lg lg) {
        if (charSequence == null) return null;

        int emojiSet = Integer.parseInt(Utils.prefs.getString("emojiset", "0"));

        if (emojiSet == EMOJISET_WHATSAPP)
            return com.whatsapp.a_7.a_WaMethod(charSequence, context, a6g, lg);
        else if (emojiSet == EMOJISET_SYSTEM)
            return new SpannableStringBuilder(charSequence);
        else
            return getEmojiSpannable(new SpannableStringBuilder(charSequence));
    }

    public static void getEmojiSpannable(Editable editable, Context context, Paint paint) {
        if (editable == null) return;

        int emojiSet = Integer.parseInt(Utils.prefs.getString("emojiset", "0"));

        if (emojiSet == EMOJISET_WHATSAPP)
            com.whatsapp.a_7.a_WaMethod(editable, context, paint);
        else if (emojiSet == EMOJISET_SYSTEM)
            return;
        else {
            getEmojiSpannable(new SpannableStringBuilder(editable));
        }
    }

    private static SpannableStringBuilder getEmojiSpannable(SpannableStringBuilder spannableStringBuilder) {
        String fontAssetLocation = "";

        int emojiSet = Integer.parseInt(Utils.prefs.getString("emojiset", "0"));

        switch (emojiSet) {
            default:
            case EMOJISET_APPLE:
                /*fontAssetLocation += "fonts/Emoji-Apple.ttf";
                break;*/
            case EMOJISET_GOOGLE:
                fontAssetLocation += "fonts/Emoji-Google.ttf";
                break;
            case EMOJISET_ONE:
                fontAssetLocation += "fonts/Emoji-One.ttf";
                break;
        }

        Typeface appleEmojis = Typeface.createFromAsset(Utils.context.getResources().getAssets(), fontAssetLocation);

        // TODO: Find a better pattern
        Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
        //Pattern pattern = Pattern.compile("[\uD83D\uDE01-\uD83D\uDE4F]");
        Matcher matcher = pattern.matcher(spannableStringBuilder);
        while (matcher.find()) {
            TypefaceSpan appleEmojisSpan = new CustomTypefaceSpan("", appleEmojis);
            spannableStringBuilder.setSpan(appleEmojisSpan, matcher.start(), matcher.end(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }

        return spannableStringBuilder;
    }
}
