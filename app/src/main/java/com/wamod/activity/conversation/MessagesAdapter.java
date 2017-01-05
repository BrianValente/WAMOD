package com.wamod.activity.conversation;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.wamod.MessagesManager;
import com.wamod.Resources;
import com.wamod.Utils;
import com.wamod.activity.conversation.bubble.Stock;

import java.util.List;

/**
 * Created by brianvalente on 9/27/16.
 */
public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
    private List<MessagesManager.Message> messageList;
    private Context  context;
    private int      incomingBubbleTextColor;
    private int      outgoingBubbleTextColor;
    private int      incomingBubbleSenderTextColor;
    private Drawable defaultProfilePicture;
    private Drawable dummyDrawable;
    private int      bubbleStyle;

    public MessagesAdapter(Context context, List<MessagesManager.Message> messageList) {
        this.context = context;
        this.messageList = messageList;

        incomingBubbleTextColor = Color.parseColor("#" + Utils.prefs.getString("conversation_leftbubbletextcolor", "FFFFFF"));
        outgoingBubbleTextColor = Color.parseColor("#" + Utils.prefs.getString("conversation_rightbubbletextcolor", "FFFFFF"));

        incomingBubbleSenderTextColor = Color.parseColor("#" + Utils.prefs.getString("conversation_customparticipantcolor", "FFFFFF"));

        defaultProfilePicture = context.getResources().getDrawable(Resources.getDrawable("wamod_drawer_account"));
        dummyDrawable         = context.getResources().getDrawable(Resources.getDrawable("dummy"));

        bubbleStyle = Utils.getBubbleStyle();
    }

    private Drawable getOutgoingBubble() {
        Drawable outgoingBubble = context.getResources().getDrawable(Resources.getDrawable("wamod_style_bubble_newhangouts_balloon_outgoing_normal"));
        outgoingBubble = Utils.tintToColor(outgoingBubble, Color.parseColor("#" + Utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF")));
        return outgoingBubble;
    }

    private Drawable getOutgoingExtensionBubble() {
        Drawable outgoingBubble = context.getResources().getDrawable(Resources.getDrawable("wamod_style_bubble_newhangouts_balloon_outgoing_normal_ext"));
        outgoingBubble = Utils.tintToColor(outgoingBubble, Color.parseColor("#" + Utils.prefs.getString("conversation_rightbubblecolor", "FFFFFF")));
        return outgoingBubble;
    }

    private Drawable getIncomingBubble() {
        Drawable incomingBubble = context.getResources().getDrawable(Resources.getDrawable("wamod_style_bubble_newhangouts_balloon_incoming_normal"));
        incomingBubble = Utils.tintToColor(incomingBubble, Color.parseColor("#" + Utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF")));
        return incomingBubble;
    }

    private Drawable getIncomingExtensionBubble() {
        Drawable incomingBubble = context.getResources().getDrawable(Resources.getDrawable("wamod_style_bubble_newhangouts_balloon_incoming_normal_ext"));
        incomingBubble = Utils.tintToColor(incomingBubble, Color.parseColor("#" + Utils.prefs.getString("conversation_leftbubblecolor", "FFFFFF")));
        return incomingBubble;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView  senderNameTV;
        public TextView  messageTextTV;
        public TextView  pushNameTV;
        public TextView  debugInfoTV;
        public ViewGroup senderIdentityVG;
        public ViewGroup bubbleVG;
        public ImageView profilePictureIV;

        public ViewHolder(View v) {
            super(v);
            senderNameTV     = (TextView)  v.findViewById(Resources.getID("sender_name"));
            messageTextTV    = (TextView)  v.findViewById(Resources.getID("message_text"));
            pushNameTV       = (TextView)  v.findViewById(Resources.getID("push_name"));
            debugInfoTV      = (TextView)  v.findViewById(Resources.getID("debug_info"));
            senderIdentityVG = (ViewGroup) v.findViewById(Resources.getID("sender_identity"));
            bubbleVG         = (ViewGroup) v.findViewById(Resources.getID("bubble"));
            profilePictureIV = (ImageView) v.findViewById(Resources.getID("profile_picture"));
        }
    }

    public void add(int position, MessagesManager.Message item) {
        messageList.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(MessagesManager.Message item) {
        int position = messageList.indexOf(item);
        messageList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        switch (bubbleStyle) {
            case Utils.BUBBLE_STOCK:
            default:
                v = LayoutInflater.from(parent.getContext()).inflate(Resources.getLayout("wamod_activity_conversation_bubble"), parent, false);
        }
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        switch (bubbleStyle) {
            case Utils.BUBBLE_STOCK:
                Stock.load(holder, position, messageList);
        }
    }

    /*@Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int messageIndex = (int)getItemId(position); // To avoid repeated messages
        MessagesManager.Message message         = messageList.get(messageIndex);
        MessagesManager.Message previousMessage = getPreviousMessage(messageIndex);
        boolean isExtension = false;

        if (previousMessage != null && message.senderJabberId != null && previousMessage.senderJabberId != null && message.senderJabberId.contentEquals(previousMessage.senderJabberId))
            isExtension = true;


        RelativeLayout.LayoutParams bubbleLayoutParams = (RelativeLayout.LayoutParams) holder.bubbleVG.getLayoutParams();
        if (message.sentByMe) {
            bubbleLayoutParams     .addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.bubbleVG        .setBackground(isExtension? getOutgoingExtensionBubble() : getOutgoingBubble());
            holder.messageTextTV   .setTextColor(outgoingBubbleTextColor);
            holder.debugInfoTV     .setTextColor(outgoingBubbleTextColor);
            holder.profilePictureIV.setVisibility(View.GONE);
        } else {
            bubbleLayoutParams     .removeRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            holder.bubbleVG        .setBackground(isExtension? getIncomingExtensionBubble() : getIncomingBubble());
            holder.senderNameTV    .setTextColor(incomingBubbleSenderTextColor);
            holder.messageTextTV   .setTextColor(incomingBubbleTextColor);
            holder.debugInfoTV     .setTextColor(incomingBubbleTextColor);
            holder.profilePictureIV.setVisibility(View.VISIBLE);

        }
        holder.bubbleVG.setLayoutParams(bubbleLayoutParams);

        if (message.isGroup) {
            if (message.sentByMe) {
                holder.senderIdentityVG.setVisibility(View.GONE);
                holder.pushNameTV      .setVisibility(View.GONE);
                holder.senderNameTV    .setText("Me lol");
            } else if (message.sender.fullName != null) {
                holder.senderIdentityVG.setVisibility(View.VISIBLE);
                holder.pushNameTV      .setVisibility(View.GONE);
                holder.senderNameTV    .setText(message.sender.fullName);
            } else {
                holder.senderIdentityVG.setVisibility(View.VISIBLE);
                holder.pushNameTV      .setVisibility(View.VISIBLE);
                holder.pushNameTV      .setText(message.sender.pushName);
                holder.senderNameTV    .setText(message.sender.jabberId);
            }
        }
        else
            holder.senderIdentityVG.setVisibility(View.GONE);

        if (isExtension && !message.sentByMe) {
            holder.profilePictureIV.setImageDrawable(dummyDrawable);
            holder.senderIdentityVG.setVisibility(View.GONE);
        } else if (!message.sentByMe) {
            Drawable profilePicture = message.getProfilePicture();
            holder.profilePictureIV.setImageDrawable(profilePicture != null ? profilePicture : defaultProfilePicture);
        }

        holder.messageTextTV.setText(message.message);

        holder.bubbleVG.setTag(message);
        holder.bubbleVG.setOnClickListener(new OnItemClick());

        String messageDebugInfo = "";
        messageDebugInfo += "ID: " + message.dbRowId + "\n";
        messageDebugInfo += "Key: " + message.key + "\n";

        if (message.isQuote)
            messageDebugInfo += "Type: " + getMessageTypeString(message) + ", quote" + "\n" + "Quoted message type: " + getMessageTypeString(message.quotedMessage) + "\n";
        else
            messageDebugInfo += "Type: " + getMessageTypeString(message) + "\n";

        switch (message.messageType) {
            case MessagesManager.Message.MESSAGE_TYPE_IMAGE:
                messageDebugInfo += "Mime: "    + message.mediaMimeType        + "\n";
                messageDebugInfo += "Size: "    + message.mediaSize + " bytes" + "\n";
                messageDebugInfo += "Caption: " + message.mediaCaption         + "\n";
                messageDebugInfo += "URL: "     + message.mediaUrl;
                break;
        }
        holder.debugInfoTV.setText(messageDebugInfo);

    }*/

    private class OnItemClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            MessagesManager.Message message = (MessagesManager.Message) view.getTag();
            Log.i("MessagesAdapter", message.allData);
            Toast.makeText(context, "Message data logged. Check Logcat!" + "\n\n" + message.allData, Toast.LENGTH_LONG).show();
        }
    }

    private String getMessageTypeString(MessagesManager.Message message) {
        switch (message.messageType) {
            case MessagesManager.Message.MESSAGE_TYPE_TEXT:
                return "Text";
            case MessagesManager.Message.MESSAGE_TYPE_IMAGE:
                return "Image";
            case MessagesManager.Message.MESSAGE_TYPE_VIDEO:
                return "Video";
            case MessagesManager.Message.MESSAGE_TYPE_AUDIO:
                return "Audio";
            case MessagesManager.Message.MESSAGE_TYPE_CONTACT:
                return "Contact";
            case MessagesManager.Message.MESSAGE_TYPE_LOCATION:
                return "Location";
            case MessagesManager.Message.MESSAGE_TYPE_VOICENOTE:
                return "Voice note";
            case MessagesManager.Message.MESSAGE_TYPE_DOCUMENT:
                return "Document";
            case MessagesManager.Message.MESSAGE_TYPE_UNDEFINED:
                return "Undefined";
            default:
                return "Unknown";
        }
    }

    private MessagesManager.Message getPreviousMessage(int position) {
        position--;
        return position > 0? messageList.get(position) : null;
    }
}