package wamod.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.whatsapp.Conversation;
import com.whatsapp.QuickContactActivity;
import com.whatsapp.StatusesFragment;
import com.whatsapp.StatusesFragment.StatusesRowAbstract;
import com.whatsapp.camera.CameraActivity;
import com.whatsapp.data.StatusInfo;
import com.whatsapp.statusplayback.StatusPlaybackActivity;

import java.util.ArrayList;

import wamod.activity.HomeActivity;
import wamod.statuses.StatusesAdapter;
import wamod.utils.ContactHelper;
import wamod.utils.Log;
import wamod.utils.Resources;
import wamod.utils.Theme;
import wamod.utils.Utils;

public class ConversationsFragment extends HomePageFragment {

    // Conversations
    RecyclerView mConversationsRecyclerView;
    com.whatsapp.ConversationsFragment mStockConversationsFragment;
    ConversationsAdapter mConversationsAdapter;

    // Statuses
    RecyclerView mStatusesRecyclerView;
    StatusesFragment mStockStatusesFragment;
    StatusesAdapter mStatusesAdapter;

    public ConversationsFragment() {
        mFragmentType = HomeActivity.Fragment.HOME;
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup) {

        if (mRootView != null)
            return mRootView;

        mHomeActivity = (HomeActivity) layoutInflater.getContext();
        mRootView = layoutInflater.inflate(Resources.getLayout(mHomeActivity, "wamod_conversations_fragment"), null);

        // Stock Conversations fragment
        mStockConversationsFragment = new com.whatsapp.ConversationsFragment();
        mHomeActivity.getSupportFragmentManager().beginTransaction().replace(Resources.getId(mHomeActivity, "fragment"), mStockConversationsFragment).commit();


        // Stock Statuses fragment
        mStockStatusesFragment = new StatusesFragment();
        mHomeActivity.getSupportFragmentManager().beginTransaction().replace(Resources.getId(mHomeActivity, "stock_statuses_fragment"), mStockStatusesFragment).commit();


        // Conversations RecyclerView
        mConversationsAdapter = new ConversationsAdapter();
        mConversationsRecyclerView = mRootView.findViewById(Resources.getId(mHomeActivity, "recycler_view"));
        mConversationsRecyclerView.setLayoutManager(new LinearLayoutManager(mHomeActivity, LinearLayoutManager.VERTICAL, false));
        mConversationsRecyclerView.setAdapter(mConversationsAdapter);
        mConversationsRecyclerView.setNestedScrollingEnabled(false);



        // Statuses RecyclerView
        mStatusesAdapter = new StatusesAdapter(mHomeActivity, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StatusesRowAbstract status = getStatusesDataSet().get(mStatusesRecyclerView.getChildAdapterPosition(view));

                if (status instanceof StatusesFragment.StatusesRow) {
                    StatusInfo statusInfo = ((StatusesFragment.StatusesRow) status).mStatusInfo;

                    Intent intent;

                    if (statusInfo.mJabberId.contentEquals("") && statusInfo.mTotal == 0) {
                        intent = new Intent(mHomeActivity, CameraActivity.class);
                    } else {
                        intent = new Intent(mHomeActivity, StatusPlaybackActivity.class);
                        intent.putExtra("jid", statusInfo.mJabberId);
                    }

                    mHomeActivity.startActivity(intent);
                }
            }
        });
        mStatusesRecyclerView = mRootView.findViewById(Resources.getId(mHomeActivity, "statuses_recyclerview"));
        mStatusesRecyclerView.setLayoutManager(new LinearLayoutManager(mHomeActivity, LinearLayoutManager.HORIZONTAL, false));
        mStatusesRecyclerView.setAdapter(mStatusesAdapter);
        mStatusesRecyclerView.setHorizontalScrollBarEnabled(false);
        mStatusesRecyclerView.setNestedScrollingEnabled(false);



        // Toolbar

        mToolbar = mRootView.findViewById(Resources.getId(mHomeActivity, "toolbar"));
        mToolbar.setTitle(Resources.getString(mHomeActivity, "website_type_home"));
        mToolbar.setNavigationOnClickListener(new NavigationClickListener(this));

        loadTheme();

        return mRootView;
    }



    // Conversations Adapter

    public class ConversationsAdapter extends RecyclerView.Adapter<ConversationsAdapter.ViewHolder> {

        public class ViewHolder extends RecyclerView.ViewHolder {
            public RelativeLayout mParent;
            public TextView mContactNameTV;
            public TextView mMessageTV;
            public ImageView mProfilePicture;
            public TextView mUnreadTV;

            public ViewHolder(RelativeLayout relativeLayout, View.OnClickListener onClickListener) {
                super(relativeLayout);
                mParent = relativeLayout;
                mContactNameTV = relativeLayout.findViewById(Resources.getId(relativeLayout.getContext(), "title"));
                mMessageTV = relativeLayout.findViewById(Resources.getId(relativeLayout.getContext(), "message"));
                mProfilePicture = relativeLayout.findViewById(Resources.getId(relativeLayout.getContext(), "profile_picture"));
                mUnreadTV = relativeLayout.findViewById(Resources.getId(relativeLayout.getContext(), "unread"));
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(Resources.getLayout(parent.getContext(), "wamod_conversations_row"), null);
            final ViewHolder viewHolder = new ViewHolder(relativeLayout, null);
            viewHolder.mContactNameTV.setTextColor(Theme.getColor(Theme.Key.COLOR_LIST_ITEM_TITLE));
            viewHolder.mMessageTV.setTextColor(Theme.getColor(Theme.Key.COLOR_LIST_ITEM_SUBTITLE));
            return viewHolder;
        }

        @Override
        public int getItemCount() {
            return getConversationsDataSet().size();
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            com.whatsapp.ConversationsFragment.Row row = getConversationsDataSet().get(position);
            final String jabberId = row.getJabberId();

            ContactHelper contactHelper = new ContactHelper(jabberId);

            int unread = contactHelper.getUnreadCount();
            String unreadString;

            if (unread > 99) {
                unreadString = "99+";
            } else {
                unreadString = Integer.toString(unread);
            }

            String name = contactHelper.getFullName();

            if (name == null) {
                name = contactHelper.getPushName();

                if (name == null) {
                    name = contactHelper.getPhoneNumber();
                    holder.mContactNameTV.setText(name);
                } else {
                    name += " (" + contactHelper.getPhoneNumber() + ")";
                    Spannable spannable = new SpannableString(name);
                    spannable.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.Key.COLOR_LIST_ITEM_SUBTITLE)), contactHelper.getPushName().length(), name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    holder.mContactNameTV.setText(spannable, TextView.BufferType.SPANNABLE);
                }
            } else {
                holder.mContactNameTV.setText(name);
            }


            holder.mProfilePicture.setImageBitmap(contactHelper.getBestBitmap());

            if (unread > 0) {
                holder.mUnreadTV.setVisibility(View.VISIBLE);
                holder.mUnreadTV.setText(unreadString);
            } else {
                holder.mUnreadTV.setVisibility(View.GONE);
            }

            holder.mParent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = holder.mParent.getContext();
                    Intent intent = new Intent(context, Conversation.class);
                    intent.putExtra("jid", jabberId);
                    context.startActivity(intent);
                }
            });

            holder.mProfilePicture.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = holder.mParent.getContext();
                    Intent intent = new Intent(context, QuickContactActivity.class);
                    intent.putExtra("jid", jabberId);
                    context.startActivity(intent);
                }
            });
        }
    }



    // Statuses Data Set

    public void statusesDataSetChanged() {
        Log.info("statusesDataSetChanged");
        mStatusesAdapter.notifyDataSetChanged();
    }

    public ArrayList<StatusesRowAbstract> getStatusesDataSet() {
        ArrayList<StatusesRowAbstract> array = mStockStatusesFragment.mStatusesArrayList;
        return array;
    }


    // Conversations Data Set
    public void conversationsDataSetChanged() {
        Log.info("statusesDataSetChanged");
        mConversationsAdapter.notifyDataSetChanged();
    }

    public ArrayList<com.whatsapp.ConversationsFragment.Row> getConversationsDataSet() {
        ArrayList<com.whatsapp.ConversationsFragment.Row> array = mStockConversationsFragment.mArrayList;
        return array;
    }

}
