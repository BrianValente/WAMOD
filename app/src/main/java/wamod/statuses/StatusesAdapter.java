package wamod.statuses;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.whatsapp.ContactStatusThumbnail;
import com.whatsapp.StatusesFragment;
import com.whatsapp.StatusesFragment.StatusesRowAbstract;
import com.whatsapp.StatusesFragment.StatusesTitleRow;
import com.whatsapp.TextEmojiLabel;
import com.whatsapp.contact.StockPicture;
import com.whatsapp.contact.a.Picture;
import com.whatsapp.data.ContactInfo;
import com.whatsapp.data.ContactsManager;
import com.whatsapp.data.StatusInfo;

import wamod.activity.HomeActivity;
import wamod.utils.Resources;
import wamod.utils.Theme;

/**
 * Created by brianvalente on 11/27/17.
 */

public class StatusesAdapter extends RecyclerView.Adapter<StatusesAdapter.ViewHolder> {

    private HomeActivity mHomeActivity;
    private View.OnClickListener mOnClickListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public RelativeLayout mParentLayout;
        public ContactStatusThumbnail mContactPhoto;
        public FrameLayout mContactSelector;
        public View mContactMark;
        public TextEmojiLabel mContactName;
        public View mAdd;

        public ViewHolder(RelativeLayout relativeLayout, View.OnClickListener onClickListener) {
            super(relativeLayout);
            mParentLayout = relativeLayout;
            mContactPhoto = mParentLayout.findViewById(Resources.getId(mParentLayout.getContext(), "contact_photo"));
            mContactSelector = mParentLayout.findViewById(Resources.getId(mParentLayout.getContext(), "contact_selector"));
            mContactMark = mParentLayout.findViewById(Resources.getId(mParentLayout.getContext(), "contact_mark"));
            mContactName = mParentLayout.findViewById(Resources.getId(mParentLayout.getContext(), "contact_name"));
            mAdd = mParentLayout.findViewById(Resources.getId(mParentLayout.getContext(), "add"));
            mParentLayout.setOnClickListener(onClickListener);
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public StatusesAdapter(HomeActivity homeActivity, View.OnClickListener onClickListener) {
        mHomeActivity = homeActivity;
        mOnClickListener = onClickListener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StatusesAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        // create a new view
        final RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(Resources.getLayout(parent.getContext(), "wamod_home_statuses_row"), null);
        // set the view's size, margins, paddings and layout parameters

        final ViewHolder viewHolder = new ViewHolder(relativeLayout, mOnClickListener);

        viewHolder.mContactName.setTextColor(Theme.getColor(Theme.Key.COLOR_LIST_ITEM_TITLE));

        return viewHolder;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        StatusesRowAbstract status = mHomeActivity.mConversationsFragment.getStatusesDataSet().get(position);


        // Hide titles plox
        if (status instanceof StatusesTitleRow) {
            holder.mContactSelector.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));
            return;
        } else {
            holder.mContactSelector.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        StatusInfo statusInfo = ((StatusesFragment.StatusesRow) status).mStatusInfo;

        boolean isMe = statusInfo.mJabberId.contentEquals("");

        holder.mContactPhoto.setRingValues(statusInfo.mUnreadCount, statusInfo.mTotal);




        // Reset photo
        //holder.mContactPhoto.setImageDrawable(null);


        // Let's use contact pictures instead ;)

        final ContactInfo contactInfo;

        if (isMe) {
            contactInfo = mHomeActivity.mMeManager.getMeInfo();
        }
        else {
            contactInfo = ContactsManager.getContactsManager().getContactByJabberId(statusInfo.mJabberId);
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Picture pictureManager = Picture.getPicture();
                Bitmap pictureBitmap = pictureManager.getBitmap(contactInfo, 200, -1.0f, true);

                if (pictureBitmap == null) {
                    pictureBitmap = StockPicture.getStockPicture().getStockPicture(contactInfo);
                }

                holder.mContactPhoto.setImageBitmap(pictureBitmap);
            }
        });
        thread.setName("StatusesAdapterThread");
        thread.run();




        // Show or hide add icon
        holder.mAdd.setVisibility(isMe && statusInfo.mTotal == 0? View.VISIBLE : View.GONE);



        /*com.whatsapp.protocol.j j = statusInfo.mProtocol;

        if (j == null) {
            j = mHomeActivity.getStatusesFragment().ag.c.getProtocolFromMessageId(statusInfo.mMessageId);
        }

        if (j != null) {
            MediaData mediaData = j.getMediaData();

            if (mediaData != null) {

                File file = mediaData.file;

                if (file != null && file.exists()) {
                    Bitmap bitmap = mHomeActivity.getStatusesFragment().aF.a(j, false);

                    if (bitmap != null) {
                        holder.mContactPhoto.setImageBitmap(bitmap);
                    }

                }

            }
            else {
                String text = j.getTextStatusText();
                TextData textData = j.getTextData();

                Drawable drawable = new Drawable(mHomeActivity, mHomeActivity.getStatusesFragment().az, text, textData);

                holder.mContactPhoto.setImageDrawable(drawable);
            }

        } else {
            ColorDrawable colorDrawable = new ColorDrawable(Color.BLACK);
            holder.mContactPhoto.setImageDrawable(colorDrawable);
        }

        mProtocol = j;*/




        if (isMe) {
            holder.mContactName.setText("You");
        } else {
            holder.mContactName.setText(contactInfo.mFullName);
        }



        holder.mParentLayout.setVisibility(View.VISIBLE);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mHomeActivity.mConversationsFragment.getStatusesDataSet().size();
    }
}
