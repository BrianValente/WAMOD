package com.wamod;

import com.whatsapp.afx;

/**
 * Created by brianvalente on 8/2/16.
 */
public class Messaging {
    public static com.whatsapp.rv createContactFromJabberId(String JabberID) {
        return afx.g(JabberID);
    }
}
