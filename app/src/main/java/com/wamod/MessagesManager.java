package com.wamod;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;
import com.whatsapp.aam;
import com.whatsapp.rv;

/**
 * Created by brianvalente on 9/25/16.
 */
public class MessagesManager extends SQLiteOpenHelper {
    Context context;
    private static final String DATABASE_NAME = "msgstore.db";
    private static final int DATABASE_VERSION = 1;
    SQLiteDatabase messagesDb;
    public static final String TABLE_NAME = "messages";
    public static final String[] ALL_COLUMNS = {
            "_id",
            "key_remote_jid",
            "key_from_me",
            "key_id",
            "status",
            "needs_push",
            "data",
            "timestamp",
            "media_url",
            "media_mime_type",
            "media_wa_type",
            "media_size",
            "media_name",
            "media_caption",
            "media_hash",
            "media_duration",
            "origin",
            "latitude",
            "longitude",
            "thumb_image",
            "remote_resource",
            "received_timestamp",
            "send_timestamp",
            "receipt_server_timestamp",
            "receipt_device_timestamp",
            "read_device_timestamp",
            "played_device_timestamp",
            "raw_data",
            "recipient_count",
            "participant_hash",
            "starred",
            "quoted_row_id",
            "mentioned_jids"};

    public MessagesManager(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MessagesManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        messagesDb = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Toast.makeText(context, "Creating database...\nWait, WHAT THE FUCK?", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Toast.makeText(context, "Upgrading database...\nWait, WHAT THE FUCK?\nOld: " + i + " New: " + i1, Toast.LENGTH_LONG).show();
    }

    @Deprecated
    public List<Message> getConversationMessages(String jabberId) {
        List<Message> messagesList = new ArrayList<>();
        Log.i("MessagesManager", "JabberID: " + jabberId);
        Cursor cursor = messagesDb.rawQuery("SELECT * FROM messages WHERE key_remote_jid = ? ORDER BY timestamp DESC LIMIT 50", new String[]{jabberId});
        while (cursor.moveToNext()) {
            Log.i("MessagesManager", cursor.toString());
            Message message = new Message(cursor, false);
            messagesList.add(message);
        }
        cursor.close();
        return messagesList;
    }

    public List<Message> getConversationMessages(String jabberId, int limit, int offset) {
        List<Message> messagesList = new ArrayList<>();
        Log.i("MessagesManager", "JabberID: " + jabberId);
        String limitOffsetQuery = limit > 0? " LIMIT " + limit + " OFFSET " + offset : "";

        // media_size != 19 avoids WhatsApp metadata
        Cursor cursor = messagesDb.rawQuery("SELECT * FROM messages WHERE key_remote_jid = ? AND media_size != 19 ORDER BY timestamp DESC" + limitOffsetQuery, new String[]{jabberId});
        
        cursor.moveToPosition(cursor.getCount() + 1);
        while (cursor.moveToPrevious()) {
            Message message = new Message(cursor, false);
            messagesList.add(message);
        }
        cursor.close();
        return messagesList;
    }

    public Message getQuotedMessage(long quotedRowId) {
        Cursor cursor = messagesDb.rawQuery("SELECT * FROM messages_quotes WHERE _id = ?", new String[]{""+quotedRowId});
        cursor.moveToFirst();
        return new Message(cursor, true);
    }

    public SQLiteDatabase getDatabase() {
        return messagesDb;
    }

    private boolean logged = false;

    public class Message {
        public Contact  sender;
        public Contact  conversation;
        public String   message;
        public String   conversationJabberId;
        public String   senderJabberId;
        public String   mediaName;
        public String   mediaCaption;
        public String   mediaMimeType;
        public String   mediaUrl;
        public String   key;
        public long     mediaSize            = 0;
        public byte[]   mediaThumbnail;
        public int      status               = 0;
        public boolean  sentByMe;
        public long     dateReceived         = 0;
        public boolean  isGroup              = false;
        public boolean  isBroadcast          = false;
        public int      dbRowId              = 0;
        public int      messageType          = MESSAGE_TYPE_UNDEFINED;
        public long     quotedRowId          = 0;
        public Message  quotedMessage;
        public boolean  isQuote = false;
        public Drawable profilePicture;
        public boolean  hasProfilePicture = true;

        public static final int MESSAGE_TYPE_UNDEFINED = -1;

        public static final int MESSAGE_TYPE_TEXT      = 0;
        public static final int MESSAGE_TYPE_IMAGE     = 1;
        public static final int MESSAGE_TYPE_VIDEO     = 2;
        public static final int MESSAGE_TYPE_AUDIO     = 3;
        public static final int MESSAGE_TYPE_CONTACT   = 4;
        public static final int MESSAGE_TYPE_LOCATION  = 5;
        public static final int MESSAGE_TYPE_VOICENOTE = 6;
        public static final int MESSAGE_TYPE_GIF       = 7;
        public static final int MESSAGE_TYPE_DOCUMENT  = 8;

        public static final int MESSAGE_TYPE_NOTIFICATION_GROUP_ME_ADDED        = 10;
        public static final int MESSAGE_TYPE_NOTIFICATION_GROUP_ME_LEFT         = 11;
        public static final int MESSAGE_TYPE_NOTIFICATION_GROUP_ME_REMOVED      = 12;
        public static final int MESSAGE_TYPE_NOTIFICATION_GROUP_ME_ADMIN        = 13;
        public static final int MESSAGE_TYPE_NOTIFICATION_GROUP_SUBJECT_CHANGED = 14;
        public static final int MESSAGE_TYPE_NOTIFICATION_GROUP_CONTACT_ADDED   = 15;
        public static final int MESSAGE_TYPE_NOTIFICATION_GROUP_CONTACT_REMOVED = 16;

        public String allData = "";

        public Message(Cursor cursor, boolean quote) {
            dbRowId        = cursor.getInt   (cursor.getColumnIndex("_id"));
            message        = cursor.getString(cursor.getColumnIndex("data"));
            sentByMe       = cursor.getInt   (cursor.getColumnIndex("key_from_me")) == 1;
            mediaName      = cursor.getString(cursor.getColumnIndex("media_name"));
            mediaCaption   = cursor.getString(cursor.getColumnIndex("media_caption"));
            mediaMimeType  = cursor.getString(cursor.getColumnIndex("media_mime_type"));
            mediaUrl       = cursor.getString(cursor.getColumnIndex("media_url"));
            mediaSize      = cursor.getLong  (cursor.getColumnIndex("media_size"));
            key            = cursor.getString(cursor.getColumnIndex("key_id"));
            mediaThumbnail = cursor.getBlob  (cursor.getColumnIndex("thumb_image"));
            status         = cursor.getInt   (cursor.getColumnIndex("status"));
            dateReceived   = cursor.getLong  (cursor.getColumnIndex("received_timestamp"));
            quotedRowId    = cursor.getLong  (cursor.getColumnIndex("quoted_row_id"));

            conversationJabberId = cursor.getString(cursor.getColumnIndex("key_remote_jid"));
            senderJabberId       = cursor.getString(cursor.getColumnIndex("remote_resource"));

            if (conversationJabberId.contains("@g.us")) isGroup = true;

            if (!isGroup && !sentByMe) senderJabberId = conversationJabberId;

            if (!conversationJabberId.isEmpty() && conversationJabberId.contains("@"))
                conversation = new Contact(aam.a(App.getContext()).i(conversationJabberId));

            if (senderJabberId != null && senderJabberId.contains("@"))
                sender       = new Contact(aam.a(App.getContext()).i(senderJabberId));

            if (senderJabberId != null && senderJabberId.contains("@broadcast"))
                isBroadcast  = true;

            if (!quote && quotedRowId > 0) {
                quotedMessage = getQuotedMessage(quotedRowId);
                isQuote = true;
            }

            for (String column : cursor.getColumnNames()) {
                int columnIndex = cursor.getColumnIndex(column);
                int dataType    = cursor.getType(columnIndex);
                switch (dataType) {
                    case Cursor.FIELD_TYPE_STRING:
                        allData += column + ": " + cursor.getString(columnIndex) + "\n";
                        break;
                    case Cursor.FIELD_TYPE_INTEGER:
                        allData += column + ": " + cursor.getInt(columnIndex) + "\n";
                        break;
                    case Cursor.FIELD_TYPE_FLOAT:
                        allData += column + ": " + cursor.getFloat(columnIndex) + "\n";
                        break;
                }
            }

            switch (cursor.getInt(cursor.getColumnIndex("media_wa_type"))) {
                case 0:
                    messageType = MESSAGE_TYPE_TEXT;
                    break;
                case 1:
                    messageType = MESSAGE_TYPE_IMAGE;
                    break;
                case 2:
                    if (mediaMimeType.contains("audio/ogg; codecs=opus"))
                        messageType = MESSAGE_TYPE_VOICENOTE;
                    else
                        messageType = MESSAGE_TYPE_AUDIO;
                    break;
                case 3:
                    messageType = MESSAGE_TYPE_VIDEO;
                    break;
                case 4:
                    messageType = MESSAGE_TYPE_CONTACT;
                    break;
                case 5:
                    messageType = MESSAGE_TYPE_LOCATION;
                    break;
                case 9:
                    messageType = MESSAGE_TYPE_DOCUMENT;
                    break;
            }
        }

        public Drawable getProfilePicture() {
            if (!App.cachedProfilePictures.containsKey(senderJabberId)) {
                String s = Utils.getApplicationPath(context);
                String pathName = s + "/files/Avatars/" + senderJabberId + ".j";
                App.cachedProfilePictures.put(senderJabberId, Drawable.createFromPath(pathName));
            }
            return App.cachedProfilePictures.get(senderJabberId);
        }

        private class GetProfilePhoto extends AsyncTask<Void, Void, Void> {
            @Override
            protected Void doInBackground(Void... voids) {
                return null;
            }
        }
    }
}
