package com.wamod.activity.conversation;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.*;
import android.widget.*;
import com.wamod.*;
import com.whatsapp.contact.ContactProvider;

import java.util.List;

/**
 * Created by brianvalente on 9/24/16.
 */
public class ConversationActivity extends AppCompatActivity {
    String mJabberId;
    Contact mContact;
    MessagesManager mMessagesManager;
    List<MessagesManager.Message> mMessageList;
    RecyclerView mMessagesRV;
    Toolbar mToolbar;
    MessagesAdapter mMessagesAdapter;
    LinearLayoutManager mLayoutManager;
    private static int NAVIGATION_BAR_SIZE = Utils.getNavigationBarHeight();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(Resources.getLayout("wamod_activity_conversation"));





        mToolbar             = (Toolbar) findViewById(Resources.getID("toolbar"));
        mMessagesRV          = (RecyclerView) findViewById(Resources.getID("messagesRV"));
        mMessagesManager     = new MessagesManager(this);
        mLayoutManager       = new LinearLayoutManager(this);







        // Obtaining contact

        Intent intent = getIntent();
        mJabberId = intent.getStringExtra("jid");
        Uri uri = intent.getData();
        boolean uriIsValid = ContactProvider.a(uri);

        if (uriIsValid)
            mContact = Contact.getContactFromUri(uri);
        else
            mContact = Contact.getContactFromJabberId(mJabberId);

        mJabberId = mContact.jabberId;








        // Setting up
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);   // Show back button
        actionBar.setDisplayShowTitleEnabled(false); // Hide the "WhatsApp" title

        String conversationTitle = mContact.fullName;
        if (conversationTitle == null || conversationTitle.isEmpty()) conversationTitle = mContact.jabberId;
        mToolbar.setTitle(conversationTitle);

        mMessagesRV.setLayoutManager(mLayoutManager);







        // Setting up views
        final View toolbarContainer = findViewById(Resources.getID("toolbarContainer"));
        View statusBarPadding = findViewById(Resources.getID("statusBarPadding"));

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, Utils.getStatusBarHeight(this));
        statusBarPadding.setLayoutParams(params);

        toolbarContainer.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.i("Conversation", "ListView top padding: " + toolbarContainer.getHeight());
                mMessagesRV.setPadding(0, toolbarContainer.getHeight(), 0, NAVIGATION_BAR_SIZE);
            }
        });


        // Set colors

        statusBarPadding.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_STATUSBAR));
        mToolbar.setBackgroundColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR));
        mToolbar.setTitleTextColor(ColorsManager.getColor(ColorsManager.UI_ACTIVITY_TOOLBAR_TITLE));
        if (Utils.prefs.getBoolean("general_darkstatusbaricons", false))
            findViewById(android.R.id.content).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        // Fully transparent bars
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            w.setStatusBarColor(Color.parseColor("#00000000"));
            w.setNavigationBarColor(Color.parseColor("#00000000"));
        }








        // Obtaining and loading messages
        new LoadMessages().execute();

    }

    private List<MessagesManager.Message> getMessages(int limit, int offset) {
        return mMessagesManager.getConversationMessages(mJabberId, limit, offset);
    }

    private class LoadMessages extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            mMessageList = mMessagesManager.getConversationMessages(mJabberId, 0, 0);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mMessagesAdapter = new MessagesAdapter(ConversationActivity.this, mMessageList); // Better performance
            mMessagesAdapter.setHasStableIds(true);
            mMessagesRV.setAdapter(mMessagesAdapter);
            mMessagesRV.setHasFixedSize(true); // Better performance
            mMessagesRV.scrollToPosition(mMessagesAdapter.getItemCount() - 1); // Why "- 1"? Only God knows, but works
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) onBackPressed();
        return true;
    }
}
