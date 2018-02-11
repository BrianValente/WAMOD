package wamod.utils;

import android.graphics.Bitmap;

import com.whatsapp.contact.NumberParser;
import com.whatsapp.contact.StockPicture;
import com.whatsapp.contact.a.Picture;
import com.whatsapp.data.ContactInfo;
import com.whatsapp.data.ContactsManager;
import com.whatsapp.data.ConversationsData;

public class ContactHelper {

    private String mJabberId;
    private ContactInfo mContactInfo;

    public ContactHelper(String jabberId) {
        mJabberId = jabberId;
        mContactInfo = ContactsManager.getContactsManager().getContactByJabberId(mJabberId);
    }

    public String getBestName() {
        if (mContactInfo.mFullName != null) {
            return mContactInfo.mFullName;
        } else if (mContactInfo.mPushName != null) {
            return mContactInfo.mPushName;
        } else {
            return mJabberId;
        }
    }

    public String getPushName() {
        return mContactInfo.mPushName;
    }

    public String getJabberId() {
        return mJabberId;
    }

    public String getPhoneNumber() {
        // TODO: Return number
        return NumberParser.parseNumber(mContactInfo);
    }

    public String getFullName() {
        return mContactInfo.mFullName;
    }

    public Bitmap getBestBitmap() {
        Picture pictureManager = Picture.getPicture();
        Bitmap pictureBitmap = pictureManager.getBitmap(mContactInfo, 200, -1.0f, true);

        if (pictureBitmap == null) {
            pictureBitmap = StockPicture.getStockPicture().getStockPicture(mContactInfo);
        }

        return pictureBitmap;
    }

    public int getUnreadCount() {
        return ConversationsData.mInstance.getUnreadCount(mJabberId);
    }

}
