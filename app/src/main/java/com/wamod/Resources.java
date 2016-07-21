package com.wamod;

/**
 * Created by BrianValente on 2/29/16.
 */

public final class Resources {
    public static final class id {
        /** Drawer */
        public static final int wamod_drawer_parent = getHexID("wamod_drawer_parent", "id");
        public static final int wamod_drawer_overlay = getHexID("wamod_drawer_overlay", "id");
        public static final int wamod_drawer = getHexID("wamod_drawer", "id");
        public static final int wamod_drawer_usernametv = getHexID("wamod_drawer_usernametv", "id");
        public static final int wamod_drawer_usernumbertv = getHexID("wamod_drawer_usernumbertv", "id");
        public static final int wamod_drawer_newgroup = getHexID("wamod_drawer_newgroup", "id");
        public static final int wamod_drawer_newbroadcast = getHexID("wamod_drawer_newbroadcast", "id");
        public static final int wamod_drawer_wamodweb = getHexID("wamod_drawer_wamodweb", "id");
        public static final int wamod_drawer_setstatus = getHexID("wamod_drawer_setstatus", "id");
        public static final int wamod_drawer_changeprofilepic = getHexID("wamod_drawer_changeprofilepic", "id");
        public static final int wamod_drawer_starredmessages = getHexID("wamod_drawer_starredmessages", "id");
        public static final int wamod_drawer_search = getHexID("wamod_drawer_search", "id");
        public static final int wamod_drawer_settings = getHexID("wamod_drawer_settings", "id");
        public static final int wamod_drawer_wamodsettings = getHexID("wamod_drawer_wamodsettings", "id");
        public static final int wamod_drawer_container = getHexID("wamod_drawer_container", "id");
        public static final int wamod_drawer_back = getHexID("wamod_drawer_back", "id");
        public static final int wamod_drawer_photo = getHexID("wamod_drawer_photo", "id");
        public static final int wamod_drawer_header = getHexID("wamod_drawer_header", "id");
        public static final int wamod_drawer_buttons = getHexID("wamod_drawer_buttons", "id");
        public static final int wamod_drawer_bgview = getHexID("wamod_drawer_bgview", "id");
        public static final int wamod_drawer_debug1 = getHexID("wamod_drawer_debug1", "id");
        public static final int wamod_drawer_statusbar = getHexID("wamod_drawer_statusbar", "id");
        public static final int wamod_drawer_privacy = getHexID("wamod_drawer_privacy", "id");

        /** Switch account */
        public static final int wamod_switchaccount = getHexID("wamod_switchaccount", "id");
        public static final int wamod_drawer_header_2ndprofilepic = getHexID("wamod_drawer_header_2ndprofilepic", "id");

        /** BubbleRelativeLayout */
        public static int name_in_group_tv = getHexID("name_in_group_tv", "id");
        public static int main_layout = getHexID("main_layout", "id");
        public static int message_text = getHexID("message_text", "id");
        public static int status = getHexID("status", "id");
        public static int image_holder = getHexID("image_holder", "id");
        public static int caption = getHexID("caption", "id");
        public static int thumb = getHexID("thumb", "id");
        public static int date_wrapper = getHexID("date_wrapper", "id");
        public static int date = getHexID("date", "id");
        public static int content = getHexID("content", "id");
        public static int title = getHexID("title", "id");
        public static int info = getHexID("info", "id");
        public static int bullet_info = getHexID("bullet_info", "id");
        public static int file_type = getHexID("file_type", "id");
        public static int web_page_preview_holder = getHexID("web_page_preview_holder", "id");
        public static int url = getHexID("url", "id");
        public static int thumbnail = getHexID("thumbnail", "id");
        public static int duration = getHexID("duration", "id");
        public static int control_btn_holder = getHexID("control_btn_holder", "id");
        public static int contact_card = getHexID("contact_card", "id");
        public static int vcard_text = getHexID("vcard_text", "id");
        public static int msg_contact_btn = getHexID("msg_contact_btn", "id");
        public static int add_contact_btn = getHexID("add_contact_btn", "id");
        public static int text_content_layout = getHexID("text_content_layout", "id");


        /** General */
        public static int toolbar = getHexID("toolbar", "id");
        public static int wamod_fragment = getHexID("wamod_fragment", "id");

        /** FABS */
        public static int wamod_fab_reloadcontacts = getHexID("wamod_fab_reloadcontacts", "id");
        public static int wamod_fab_clearcalls = getHexID("wamod_fab_clearcalls", "id");
        public static int pager = getHexID("pager", "id");


        /** HomeActivity */
        public static int tabs         = getHexID("tabs", "id");
        public static int pager_holder = getHexID("pager_holder", "id");

        public static int conversations_row_contact_name = getHexID("conversations_row_contact_name", "id");
        public static int conversations_row_date         = getHexID("conversations_row_date", "id");
        public static int single_msg_tv                  = getHexID("single_msg_tv", "id");
        public static int msg_from_tv                    = getHexID("msg_from_tv", "id");
        public static int contact_name                   = getHexID("contact_name", "id");
        public static int date_time                      = getHexID("date_time", "id");
        public static int contactpicker_row_name         = getHexID("contactpicker_row_name", "id");
        public static int contactpicker_row_status       = getHexID("contactpicker_row_status", "id");
        public static int contactpicker_row_phone_type   = getHexID("contactpicker_row_phone_type", "id");

        public static int wamod_bottomnav          = getHexID("wamod_bottomnav", "id");
        public static int wamod_bottomnav_viewstub = getHexID("wamod_bottomnav_viewstub", "id");
        public static int wamod_bottomnav_calls    = getHexID("wamod_bottomnav_calls", "id");
        public static int wamod_bottomnav_chats    = getHexID("wamod_bottomnav_chats", "id");
        public static int wamod_bottomnav_contacts = getHexID("wamod_bottomnav_contacts", "id");


        /** Conversation */
        public static int conversation_contact_name     = getHexID("conversation_contact_name", "id");
        public static int conversation_contact_status   = getHexID("conversation_contact_status", "id");
        public static int conversation_contact_photo    = getHexID("conversation_contact_photo", "id");
        public static int conversation_background       = getHexID("conversation_background", "id");
        public static int conversation_layout           = getHexID("conversation_layout", "id");
        public static int up                            = getHexID("up", "id");
        public static int entry                         = getHexID("entry", "id");
        public static int voice_note_btn                = getHexID("voice_note_btn", "id");
        public static int send                          = getHexID("send", "id");
        public static int footer                        = getHexID("footer", "id");
        public static int input_layout                  = getHexID("input_layout", "id");
        public static int emoji_picker_btn              = getHexID("emoji_picker_btn", "id");

        public static int wamod_theme_hangouts_conversation_gallery  = getHexID("wamod_theme_hangouts_conversation_gallery", "id");
        public static int wamod_theme_hangouts_conversation_camera   = getHexID("wamod_theme_hangouts_conversation_camera", "id");
        public static int wamod_theme_hangouts_conversation_emoji    = getHexID("wamod_theme_hangouts_conversation_emoji", "id");
        public static int wamod_theme_hangouts_conversation_location = getHexID("wamod_theme_hangouts_conversation_location", "id");

        public static int wamod_theme_test_conversation_entry_attach_btn          = getHexID("wamod_theme_test_conversation_entry_attach_btn", "id");
        public static int wamod_theme_test_conversation_entry_send                = getHexID("wamod_theme_test_conversation_entry_send", "id");
        public static int wamod_theme_test_conversation_entry_mic                 = getHexID("wamod_theme_test_conversation_entry_mic", "id");
        public static int wamod_theme_test_conversation_entry_attach_layout       = getHexID("wamod_theme_test_conversation_entry_attach_layout", "id");
        public static int wamod_theme_test_conversation_entry_reveallinearlayout  = getHexID("wamod_theme_test_conversation_entry_reveallinearlayout", "id");
        public static int wamod_theme_test_conversation_entry_edittext            = getHexID("wamod_theme_test_conversation_entry_edittext", "id");
        public static int wamod_theme_test_conversation_extras_mic_delete         = getHexID("wamod_theme_test_conversation_extras_mic_delete", "id");
        public static int wamod_theme_test_conversation_extras_mic_send           = getHexID("wamod_theme_test_conversation_extras_mic_send", "id");
        public static int wamod_theme_test_conversation_entry_attach_gallery      = getHexID("wamod_theme_test_conversation_entry_attach_gallery", "id");
        public static int wamod_theme_test_conversation_entry_attach_camera       = getHexID("wamod_theme_test_conversation_entry_attach_camera", "id");
        public static int wamod_theme_test_conversation_entry_attach_audio        = getHexID("wamod_theme_test_conversation_entry_attach_audio", "id");
        public static int wamod_theme_test_conversation_entry_attach_contact      = getHexID("wamod_theme_test_conversation_entry_attach_contact", "id");
        public static int wamod_theme_test_conversation_entry_attach_location     = getHexID("wamod_theme_test_conversation_entry_attach_location", "id");
        public static int wamod_theme_test_conversation_entry_attach_edit         = getHexID("wamod_theme_test_conversation_entry_attach_edit", "id");
        public static int wamod_theme_test_conversation_extras_mic_container      = getHexID("wamod_theme_test_conversation_extras_mic_container", "id");
        public static int wamod_theme_test_conversation_extras                    = getHexID("wamod_theme_test_conversation_extras", "id");
        public static int wamod_theme_test_conversation_extras_mic_wave1          = getHexID("wamod_theme_test_conversation_extras_mic_wave1", "id");

        public static int wamod_theme_wamod_conversation_gallery  = getHexID("wamod_theme_wamod_conversation_gallery", "id");
        public static int wamod_theme_wamod_conversation_audio    = getHexID("wamod_theme_wamod_conversation_audio", "id");
        public static int wamod_theme_wamod_conversation_location = getHexID("wamod_theme_wamod_conversation_location", "id");
        public static int wamod_theme_wamod_conversation_contact  = getHexID("wamod_theme_wamod_conversation_contact", "id");
        public static int wamod_theme_wamod_conversation_camera   = getHexID("wamod_theme_wamod_conversation_camera", "id");

        public static int action_mode_bar  = getHexID("action_mode_bar", "id");
        public static int action_bar_title = getHexID("action_bar_title", "id");
        public static int menuitem_star    = getHexID("menuitem_star", "id");
        public static int menuitem_delete  = getHexID("menuitem_delete", "id");
        public static int menuitem_copy    = getHexID("menuitem_copy", "id");
        public static int menuitem_forward = getHexID("menuitem_forward", "id");
        public static int action_mode_close_button = getHexID("action_mode_close_button", "id");


        /** Color picker */
        public static int wamod_colorpicker_seekbar_red   = getHexID("wamod_colorpicker_seekbar_red", "id");
        public static int wamod_colorpicker_seekbar_green = getHexID("wamod_colorpicker_seekbar_green", "id");
        public static int wamod_colorpicker_seekbar_blue  = getHexID("wamod_colorpicker_seekbar_blue", "id");
        public static int wamod_colorpicker_seekbar_alpha = getHexID("wamod_colorpicker_seekbar_alpha", "id");
        public static int wamod_colorpicker_tv_hex        = getHexID("wamod_colorpicker_tv_hex", "id");
        public static int wamod_colorpicker_preview       = getHexID("wamod_colorpicker_preview", "id");


        /** Settings */
        public static final int profile_info_name   = getHexID("profile_info_name", "id");
        public static final int profile_info_status = getHexID("profile_info_status", "id");


        /** ProfileInfoActivity **/
        public static final int registration_name            = getHexID("registration_name", "id");
        public static final int phone                        = getHexID("phone", "id");
        public static final int change_registration_name_btn = getHexID("change_registration_name_btn", "id");


        /** New group **/
        public static final int change_photo_btn   = getHexID("change_photo_btn", "id");
        public static final int emoji_btn          = getHexID("emoji_btn", "id");
        public static final int group_name         = getHexID("group_name", "id");
        public static final int subject_counter_tv = getHexID("subject_counter_tv", "id");
        public static final int image_frame_layout = getHexID("image_frame_layout", "id");


        /** Starred messages **/
        public static final int empty          = getHexID("empty", "id");
        public static final int sender_name    = getHexID("sender_name", "id");
        public static final int bullet         = getHexID("bullet", "id");
        public static final int recipient_name = getHexID("recipient_name", "id");
        public static final int message_date   = getHexID("message_date", "id");
        public static final int chevron        = getHexID("chevron", "id");


        /** SetStatus **/
        public static final int status_title        = getHexID("status_title", "id");
        public static final int select_status_title = getHexID("select_status_title", "id");
        public static final int status_tv           = getHexID("status_tv", "id");
        public static final int round_more_btn      = getHexID("round_more_btn", "id");
        public static final int status_row          = getHexID("status_row", "id");
        public static final int list                = getHexID("list", "id");



        /** WebSessionsActivity **/
        public static final int header         = getHexID("header", "id");
        public static final int logout_all     = getHexID("logout_all", "id");
        public static final int sessions_title = getHexID("sessions_title", "id");
        public static final int hint           = getHexID("hint", "id");
        public static final int name           = getHexID("name", "id");


        /** ContactInfo **/
        public static final int status_info            = getHexID("status_info", "id");
        public static final int title_tv               = getHexID("title_tv", "id");
        public static final int subtitle_tv            = getHexID("subtitle_tv", "id");
        public static final int primary_action_icon    = getHexID("primary_action_icon", "id");
        public static final int secondary_action_btn   = getHexID("secondary_action_btn", "id");
        public static final int mute_layout            = getHexID("mute_layout", "id");
        public static final int notifications_layout   = getHexID("notifications_layout", "id");
        public static final int groups_title           = getHexID("groups_title", "id");
        public static final int media_card             = getHexID("media_card", "id");
        public static final int media_title            = getHexID("media_title", "id");
        public static final int media_info             = getHexID("media_info", "id");
        public static final int encryption_info        = getHexID("encryption_info", "id");
        public static final int encryption_indicator   = getHexID("encryption_indicator", "id");
        public static final int starred_messages_btn   = getHexID("starred_messages_btn", "id");
        public static final int starred_messages_count = getHexID("starred_messages_count", "id");


        /** GroupChatInfo **/
        public static final int participants_title = getHexID("participants_title", "id");
        public static final int owner = getHexID("owner", "id");
        public static final int push_name = getHexID("push_name", "id");
        public static final int exit_group_btn = getHexID("exit_group_btn", "id");
        public static final int add_participant_layout = getHexID("add_participant_layout", "id");
        public static final int add_participant_text = getHexID("add_participant_text", "id");
        public static final int add_participant_icon = getHexID("add_participant_icon", "id");
    }

    public static class drawable {
        /** Drawer */
        public static final int wamod_ic_menu = getHexID("wamod_ic_menu", "drawable");
        public static final int wamod_drawer_bg = getHexID("wamod_drawer_bg", "drawable");


        /** FloatingActionButton */
        public static int wamod_fab_bg = getHexID("wamod_fab_bg", "drawable");


        /** HomeActivity Menu */
        public static int ic_action_search = getHexID("ic_action_search", "drawable");


        /** Conversation */
        public static int wamod_action_close = getHexID("wamod_action_close", "drawable");
        public static int wamod_theme_wamod_conversation_input    = getHexID("wamod_theme_wamod_conversation_input", "drawable");


        /** General */
        public static int icon = getHexID("icon", "drawable");


        /** Ticks */

        // Stock
        public static int message_unsent                               = getHexID("message_unsent", "drawable");
        public static int message_got_receipt_from_server              = getHexID("message_got_receipt_from_server", "drawable");
        public static int message_got_receipt_from_target              = getHexID("message_got_receipt_from_target", "drawable");
        public static int message_got_read_receipt_from_target         = getHexID("message_got_read_receipt_from_target", "drawable");
        public static int message_unsent_onmedia                       = getHexID("message_unsent_onmedia", "drawable");
        public static int message_got_receipt_from_server_onmedia      = getHexID("message_got_receipt_from_server_onmedia", "drawable");
        public static int message_got_receipt_from_target_onmedia      = getHexID("message_got_receipt_from_target_onmedia", "drawable");
        public static int message_got_read_receipt_from_target_onmedia = getHexID("message_got_read_receipt_from_target_onmedia", "drawable");

        // iOS
        public static int wamod_style_tick_ios_message_unsent                               = getHexID("wamod_style_tick_ios_message_unsent", "drawable");
        public static int wamod_style_tick_ios_message_got_receipt_from_server              = getHexID("wamod_style_tick_ios_message_got_receipt_from_server", "drawable");
        public static int wamod_style_tick_ios_message_got_receipt_from_target              = getHexID("wamod_style_tick_ios_message_got_receipt_from_target", "drawable");
        public static int wamod_style_tick_ios_message_got_read_receipt_from_target         = getHexID("wamod_style_tick_ios_message_got_read_receipt_from_target", "drawable");
        public static int wamod_style_tick_ios_message_unsent_onmedia                       = getHexID("wamod_style_tick_ios_message_unsent_onmedia", "drawable");
        public static int wamod_style_tick_ios_message_got_receipt_from_server_onmedia      = getHexID("wamod_style_tick_ios_message_got_receipt_from_server_onmedia", "drawable");
        public static int wamod_style_tick_ios_message_got_receipt_from_target_onmedia      = getHexID("wamod_style_tick_ios_message_got_receipt_from_target_onmedia", "drawable");
        public static int wamod_style_tick_ios_message_got_read_receipt_from_target_onmedia = getHexID("wamod_style_tick_ios_message_got_read_receipt_from_target_onmedia", "drawable");

        // Old WACA
        public static int wamod_style_tick_oldwaca_message_unsent                               = getHexID("wamod_style_tick_oldwaca_message_unsent", "drawable");
        public static int wamod_style_tick_oldwaca_message_got_receipt_from_server              = getHexID("wamod_style_tick_oldwaca_message_got_receipt_from_server", "drawable");
        public static int wamod_style_tick_oldwaca_message_got_receipt_from_target              = getHexID("wamod_style_tick_oldwaca_message_got_receipt_from_target", "drawable");
        public static int wamod_style_tick_oldwaca_message_got_read_receipt_from_target         = getHexID("wamod_style_tick_oldwaca_message_got_read_receipt_from_target", "drawable");
        public static int wamod_style_tick_oldwaca_message_unsent_onmedia                       = getHexID("wamod_style_tick_oldwaca_message_unsent_onmedia", "drawable");
        public static int wamod_style_tick_oldwaca_message_got_receipt_from_server_onmedia      = getHexID("wamod_style_tick_oldwaca_message_got_receipt_from_server_onmedia", "drawable");
        public static int wamod_style_tick_oldwaca_message_got_receipt_from_target_onmedia      = getHexID("wamod_style_tick_oldwaca_message_got_receipt_from_target_onmedia", "drawable");
        public static int wamod_style_tick_oldwaca_message_got_read_receipt_from_target_onmedia = getHexID("wamod_style_tick_oldwaca_message_got_read_receipt_from_target_onmedia", "drawable");

        // New WACA
        public static int wamod_style_tick_newwaca_message_unsent                               = getHexID("wamod_style_tick_newwaca_message_unsent", "drawable");
        public static int wamod_style_tick_newwaca_message_got_receipt_from_server              = getHexID("wamod_style_tick_newwaca_message_got_receipt_from_server", "drawable");
        public static int wamod_style_tick_newwaca_message_got_receipt_from_target              = getHexID("wamod_style_tick_newwaca_message_got_receipt_from_target", "drawable");
        public static int wamod_style_tick_newwaca_message_got_read_receipt_from_target         = getHexID("wamod_style_tick_newwaca_message_got_read_receipt_from_target", "drawable");
        public static int wamod_style_tick_newwaca_message_unsent_onmedia                       = getHexID("wamod_style_tick_newwaca_message_unsent_onmedia", "drawable");
        public static int wamod_style_tick_newwaca_message_got_receipt_from_server_onmedia      = getHexID("wamod_style_tick_newwaca_message_got_receipt_from_server_onmedia", "drawable");
        public static int wamod_style_tick_newwaca_message_got_receipt_from_target_onmedia      = getHexID("wamod_style_tick_newwaca_message_got_receipt_from_target_onmedia", "drawable");
        public static int wamod_style_tick_newwaca_message_got_read_receipt_from_target_onmedia = getHexID("wamod_style_tick_newwaca_message_got_read_receipt_from_target_onmedia", "drawable");

        // Joaquin's WAMD
        public static int wamod_style_tick_oldwamd_message_unsent                               = getHexID("wamod_style_tick_oldwamd_message_unsent", "drawable");
        public static int wamod_style_tick_oldwamd_message_got_receipt_from_server              = getHexID("wamod_style_tick_oldwamd_message_got_receipt_from_server", "drawable");
        public static int wamod_style_tick_oldwamd_message_got_receipt_from_target              = getHexID("wamod_style_tick_oldwamd_message_got_receipt_from_target", "drawable");
        public static int wamod_style_tick_oldwamd_message_got_read_receipt_from_target         = getHexID("wamod_style_tick_oldwamd_message_got_read_receipt_from_target", "drawable");
        public static int wamod_style_tick_oldwamd_message_unsent_onmedia                       = getHexID("wamod_style_tick_oldwamd_message_unsent_onmedia", "drawable");
        public static int wamod_style_tick_oldwamd_message_got_receipt_from_server_onmedia      = getHexID("wamod_style_tick_oldwamd_message_got_receipt_from_server_onmedia", "drawable");
        public static int wamod_style_tick_oldwamd_message_got_receipt_from_target_onmedia      = getHexID("wamod_style_tick_oldwamd_message_got_receipt_from_target_onmedia", "drawable");
        public static int wamod_style_tick_oldwamd_message_got_read_receipt_from_target_onmedia = getHexID("wamod_style_tick_oldwamd_message_got_read_receipt_from_target_onmedia", "drawable");

        // Circles
        public static int wamod_style_tick_circles_message_unsent                               = getHexID("wamod_style_tick_circles_message_unsent", "drawable");
        public static int wamod_style_tick_circles_message_got_receipt_from_server              = getHexID("wamod_style_tick_circles_message_got_receipt_from_server", "drawable");
        public static int wamod_style_tick_circles_message_got_receipt_from_target              = getHexID("wamod_style_tick_circles_message_got_receipt_from_target", "drawable");
        public static int wamod_style_tick_circles_message_got_read_receipt_from_target         = getHexID("wamod_style_tick_circles_message_got_read_receipt_from_target", "drawable");
        public static int wamod_style_tick_circles_message_unsent_onmedia                       = getHexID("wamod_style_tick_circles_message_unsent_onmedia", "drawable");
        public static int wamod_style_tick_circles_message_got_receipt_from_server_onmedia      = getHexID("wamod_style_tick_circles_message_got_receipt_from_server_onmedia", "drawable");
        public static int wamod_style_tick_circles_message_got_receipt_from_target_onmedia      = getHexID("wamod_style_tick_circles_message_got_receipt_from_target_onmedia", "drawable");
        public static int wamod_style_tick_circles_message_got_read_receipt_from_target_onmedia = getHexID("wamod_style_tick_circles_message_got_read_receipt_from_target_onmedia", "drawable");

    }

    public static class string {
        /** General */
        public static final int app_name = getHexID("app_name", "string");
        public static final int search = getHexID("search", "string");

        /** Switch account */
        public static final int wamod_switchaccount_prompt_title = getHexID("wamod_switchaccount_prompt_title", "string");
        public static final int wamod_switchaccount_prompt_message = getHexID("wamod_switchaccount_prompt_message", "string");

        /** WAMOD Crash */
        public static final int wamod_crash_title   = getHexID("wamod_crash_title", "string");
        public static final int wamod_crash_message = getHexID("wamod_crash_message", "string");
        public static final int wamod_crash_button  = getHexID("wamod_crash_button", "string");

        /** Conversation */
        public static final int chat_with   = getHexID("chat_with", "string");

        /** QTS */
        public static final int wamod_qts_upload           = getHexID("wamod_qts_upload", "string");
        public static final int wamod_qts_upload_prompt    = getHexID("wamod_qts_upload_prompt", "string");
        public static final int wamod_qts_upload_success   = getHexID("wamod_qts_upload_success", "string");
        public static final int wamod_qts_download         = getHexID("wamod_qts_download", "string");
        public static final int wamod_qts_download_themeid = getHexID("wamod_qts_download_themeid", "string");

        /** Updater */
        public static final int wamod_updater_title    = getHexID("wamod_updater_title", "string");
        public static final int wamod_updater_message  = getHexID("wamod_updater_message", "string");
        public static final int wamod_updater_download = getHexID("wamod_updater_download", "string");
        public static final int wamod_updater_later    = getHexID("wamod_updater_later", "string");
        public static final int wamod_updater_ignore   = getHexID("wamod_updater_ignore", "string");
        public static final int wamod_updater_uptodate = getHexID("wamod_updater_uptodate", "string");

        /** WAMOD Settings */

        /* WAMOD Themes */
        public static final int wamod_settings_wamodthemes_unlink_title = getHexID("wamod_settings_wamodthemes_unlink_title", "string");
        public static final int wamod_settings_wamodthemes_unlink_message = getHexID("wamod_settings_wamodthemes_unlink_message", "string");

        /* Miscellaneous */
        public static final int wamod_settings_miscellaneous_app_restoredefaults_title = getHexID("wamod_settings_miscellaneous_app_restoredefaults_title", "string");
        public static final int wamod_settings_miscellaneous_app_restoredefaults_message = getHexID("wamod_settings_miscellaneous_app_restoredefaults_message", "string");


        /** General */
        public static final int wamod_error = getHexID("wamod_error", "string");
        public static final int wamod_restartwamod = getHexID("wamod_restartwamod", "string");
    }

    public static class xml {
        /** WAMOD Settings */
        public static final int wamodsettings                      = getHexID("wamodsettings", "xml");
        public static final int wamodsettings_generalcolors        = getHexID("wamodsettings_generalcolors", "xml");
        public static final int wamodsettings_nightmode            = getHexID("wamodsettings_nightmode", "xml");
        public static final int wamodsettings_home                 = getHexID("wamodsettings_home", "xml");
        public static final int wamodsettings_conversation         = getHexID("wamodsettings_conversation", "xml");
        public static final int wamodsettings_conversation_bubbles = getHexID("wamodsettings_conversation_bubbles", "xml");
        public static final int wamodsettings_privacy              = getHexID("wamodsettings_privacy", "xml");
        public static final int wamodsettings_miscellaneous        = getHexID("wamodsettings_miscellaneous", "xml");
        public static final int wamodsettings_wamodthemes          = getHexID("wamodsettings_wamodthemes", "xml");
    }

    public static class layout {
        /** Color picker */
        public static final int wamod_colorpicker_dialog = getHexID("wamod_colorpicker_dialog", "layout");
        public static final int wamod_activity_settings_bubbles = getHexID("wamod_activity_settings_bubbles", "layout");

        /** Home drawer header */
        public static final int wamod_home_drawer_header_wamod = getHexID("wamod_home_drawer_header_wamod", "layout");
        public static final int wamod_home_drawer_header_wamodcentered = getHexID("wamod_home_drawer_header_wamodcentered", "layout");
    }

    public static class styleable {
        /** Color picker */
        public static final int[] NewColorPickerPreference = {getHexID("wamod_supportsAlpha", "attr")};
        public static final int NewColorPickerPreference_alpha = 0;
    }

    public static class style {
        /** Color picker */
        public static final int WAMOD_Theme = getHexID("WAMOD.Theme", "style");
        public static final int WAMOD_Theme_Day = getHexID("WAMOD.Theme.Day", "style");
        public static final int WAMOD_Theme_Settings = getHexID("WAMOD.Theme.Settings", "style");
        public static final int WAMOD_Theme_Settings_Day = getHexID("WAMOD.Theme.Settings.Day", "style");
        public static final int WAMOD_Theme_Home = getHexID("WAMOD.Theme.Home", "style");
        public static final int WAMOD_Theme_Home_Day = getHexID("WAMOD.Theme.Home.Day", "style");
        public static final int WAMOD_Theme_Conversation = getHexID("WAMOD.Theme.Conversation", "style");
        public static final int WAMOD_Theme_Conversation_Day = getHexID("WAMOD.Theme.Conversation.Day", "style");
    }

    public static final int getHexID(String name, String type) {
        return Utils.context.getResources().getIdentifier(name, type, Utils.context.getPackageName());
    }
    public static int getID(String id) {
        return getHexID(id, "id");
    }
    public static int getXml(String xml) {
        return getHexID(xml, "xml");
    }
    public static int getString(String string) {
        return getHexID(string, "string");
    }
    public static int getLayout(String layout) {
        return getHexID(layout, "layout");
    }
    public static int getAttribute(String attribute) {
        return getHexID(attribute, "attr");
    }
    public static int getDrawable(String drawable) {
        int id = 0;
        // Better performance for ticks
        switch (drawable) {
            // Stock
            case "message_unsent":
                id = Resources.drawable.message_unsent;
                break;
            case "message_got_receipt_from_server":
                id = Resources.drawable.message_got_receipt_from_server;
                break;
            case "message_got_receipt_from_target":
                id = Resources.drawable.message_got_receipt_from_target;
                break;
            case "message_got_read_receipt_from_target":
                id = Resources.drawable.message_got_read_receipt_from_target;
                break;
            case "message_unsent_onmedia":
                id = Resources.drawable.message_unsent_onmedia;
                break;
            case "message_got_receipt_from_server_onmedia":
                id = Resources.drawable.message_got_receipt_from_server_onmedia;
                break;
            case "message_got_receipt_from_target_onmedia":
                id = Resources.drawable.message_got_receipt_from_target_onmedia;
                break;
            case "message_got_read_receipt_from_target_onmedia":
                id = Resources.drawable.message_got_read_receipt_from_target_onmedia;
                break;

            // iOS
            case "wamod_style_tick_ios_message_unsent":
                id = Resources.drawable.wamod_style_tick_ios_message_unsent;
                break;
            case "wamod_style_tick_ios_message_got_receipt_from_server":
                id = Resources.drawable.wamod_style_tick_ios_message_got_receipt_from_server;
                break;
            case "wamod_style_tick_ios_message_got_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_ios_message_got_receipt_from_target;
                break;
            case "wamod_style_tick_ios_message_got_read_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_ios_message_got_read_receipt_from_target;
                break;
            case "wamod_style_tick_ios_message_unsent_onmedia":
                id = Resources.drawable.wamod_style_tick_ios_message_unsent_onmedia;
                break;
            case "wamod_style_tick_ios_message_got_receipt_from_server_onmedia":
                id = Resources.drawable.wamod_style_tick_ios_message_got_receipt_from_server_onmedia;
                break;
            case "wamod_style_tick_ios_message_got_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_ios_message_got_receipt_from_target_onmedia;
                break;
            case "wamod_style_tick_ios_message_got_read_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_ios_message_got_read_receipt_from_target_onmedia;
                break;

            // Old WACA
            case "wamod_style_tick_oldwaca_message_unsent":
                id = Resources.drawable.wamod_style_tick_oldwaca_message_unsent;
                break;
            case "wamod_style_tick_oldwaca_message_got_receipt_from_server":
                id = Resources.drawable.wamod_style_tick_oldwaca_message_got_receipt_from_server;
                break;
            case "wamod_style_tick_oldwaca_message_got_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_oldwaca_message_got_receipt_from_target;
                break;
            case "wamod_style_tick_oldwaca_message_got_read_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_oldwaca_message_got_read_receipt_from_target;
                break;
            case "wamod_style_tick_oldwaca_message_unsent_onmedia":
                id = Resources.drawable.wamod_style_tick_oldwaca_message_unsent_onmedia;
                break;
            case "wamod_style_tick_oldwaca_message_got_receipt_from_server_onmedia":
                id = Resources.drawable.wamod_style_tick_oldwaca_message_got_receipt_from_server_onmedia;
                break;
            case "wamod_style_tick_oldwaca_message_got_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_oldwaca_message_got_receipt_from_target_onmedia;
                break;
            case "wamod_style_tick_oldwaca_message_got_read_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_oldwaca_message_got_read_receipt_from_target_onmedia;
                break;

            // New WACA
            case "wamod_style_tick_newwaca_message_unsent":
                id = Resources.drawable.wamod_style_tick_newwaca_message_unsent;
                break;
            case "wamod_style_tick_newwaca_message_got_receipt_from_server":
                id = Resources.drawable.wamod_style_tick_newwaca_message_got_receipt_from_server;
                break;
            case "wamod_style_tick_newwaca_message_got_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_newwaca_message_got_receipt_from_target;
                break;
            case "wamod_style_tick_newwaca_message_got_read_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_newwaca_message_got_read_receipt_from_target;
                break;
            case "wamod_style_tick_newwaca_message_unsent_onmedia":
                id = Resources.drawable.wamod_style_tick_newwaca_message_unsent_onmedia;
                break;
            case "wamod_style_tick_newwaca_message_got_receipt_from_server_onmedia":
                id = Resources.drawable.wamod_style_tick_newwaca_message_got_receipt_from_server_onmedia;
                break;
            case "wamod_style_tick_newwaca_message_got_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_newwaca_message_got_receipt_from_target_onmedia;
                break;
            case "wamod_style_tick_newwaca_message_got_read_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_newwaca_message_got_read_receipt_from_target_onmedia;
                break;

            // Joaquin's WAMD
            case "wamod_style_tick_oldwamd_message_unsent":
                id = Resources.drawable.wamod_style_tick_oldwamd_message_unsent;
                break;
            case "wamod_style_tick_oldwamd_message_got_receipt_from_server":
                id = Resources.drawable.wamod_style_tick_oldwamd_message_got_receipt_from_server;
                break;
            case "wamod_style_tick_oldwamd_message_got_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_oldwamd_message_got_receipt_from_target;
                break;
            case "wamod_style_tick_oldwamd_message_got_read_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_oldwamd_message_got_read_receipt_from_target;
                break;
            case "wamod_style_tick_oldwamd_message_unsent_onmedia":
                id = Resources.drawable.wamod_style_tick_oldwamd_message_unsent_onmedia;
                break;
            case "wamod_style_tick_oldwamd_message_got_receipt_from_server_onmedia":
                id = Resources.drawable.wamod_style_tick_oldwamd_message_got_receipt_from_server_onmedia;
                break;
            case "wamod_style_tick_oldwamd_message_got_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_oldwamd_message_got_receipt_from_target_onmedia;
                break;
            case "wamod_style_tick_oldwamd_message_got_read_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_oldwamd_message_got_read_receipt_from_target_onmedia;
                break;

            // Circles
            case "wamod_style_tick_circles_message_unsent":
                id = Resources.drawable.wamod_style_tick_circles_message_unsent;
                break;
            case "wamod_style_tick_circles_message_got_receipt_from_server":
                id = Resources.drawable.wamod_style_tick_circles_message_got_receipt_from_server;
                break;
            case "wamod_style_tick_circles_message_got_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_circles_message_got_receipt_from_target;
                break;
            case "wamod_style_tick_circles_message_got_read_receipt_from_target":
                id = Resources.drawable.wamod_style_tick_circles_message_got_read_receipt_from_target;
                break;
            case "wamod_style_tick_circles_message_unsent_onmedia":
                id = Resources.drawable.wamod_style_tick_circles_message_unsent_onmedia;
                break;
            case "wamod_style_tick_circles_message_got_receipt_from_server_onmedia":
                id = Resources.drawable.wamod_style_tick_circles_message_got_receipt_from_server_onmedia;
                break;
            case "wamod_style_tick_circles_message_got_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_circles_message_got_receipt_from_target_onmedia;
                break;
            case "wamod_style_tick_circles_message_got_read_receipt_from_target_onmedia":
                id = Resources.drawable.wamod_style_tick_circles_message_got_read_receipt_from_target_onmedia;
                break;

            default:
                id = getHexID(drawable, "drawable");
        }
        return id;
    }
}