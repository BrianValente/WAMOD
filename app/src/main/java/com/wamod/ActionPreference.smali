.class public Lcom/wamod/ActionPreference;
.super Landroid/preference/Preference;
.source "ActionPreference.java"


# instance fields
.field activity:Landroid/support/v7/app/AppCompatActivity;


# direct methods
.method public constructor <init>(Landroid/content/Context;)V
    .registers 2
    .param p1, "context"    # Landroid/content/Context;

    .prologue
    .line 29
    invoke-direct {p0, p1}, Landroid/preference/Preference;-><init>(Landroid/content/Context;)V

    .line 30
    check-cast p1, Landroid/support/v7/app/AppCompatActivity;

    .end local p1    # "context":Landroid/content/Context;
    iput-object p1, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    .line 31
    invoke-direct {p0}, Lcom/wamod/ActionPreference;->onCreate()V

    .line 32
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .registers 3
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;

    .prologue
    .line 35
    invoke-direct {p0, p1, p2}, Landroid/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    .line 36
    check-cast p1, Landroid/support/v7/app/AppCompatActivity;

    .end local p1    # "context":Landroid/content/Context;
    iput-object p1, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    .line 37
    invoke-direct {p0}, Lcom/wamod/ActionPreference;->onCreate()V

    .line 38
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .registers 4
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyleAttr"    # I

    .prologue
    .line 41
    invoke-direct {p0, p1, p2, p3}, Landroid/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    .line 42
    check-cast p1, Landroid/support/v7/app/AppCompatActivity;

    .end local p1    # "context":Landroid/content/Context;
    iput-object p1, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    .line 43
    invoke-direct {p0}, Lcom/wamod/ActionPreference;->onCreate()V

    .line 44
    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V
    .registers 5
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "attrs"    # Landroid/util/AttributeSet;
    .param p3, "defStyleAttr"    # I
    .param p4, "defStyleRes"    # I

    .prologue
    .line 47
    invoke-direct {p0, p1, p2, p3, p4}, Landroid/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;II)V

    .line 48
    check-cast p1, Landroid/support/v7/app/AppCompatActivity;

    .end local p1    # "context":Landroid/content/Context;
    iput-object p1, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    .line 49
    invoke-direct {p0}, Lcom/wamod/ActionPreference;->onCreate()V

    .line 50
    return-void
.end method

.method private onCreate()V
    .registers 4

    .prologue
    .line 53
    invoke-virtual {p0}, Lcom/wamod/ActionPreference;->getKey()Ljava/lang/String;

    move-result-object v1

    const/4 v0, -0x1

    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v2

    packed-switch v2, :pswitch_data_22

    :cond_c
    :goto_c
    packed-switch v0, :pswitch_data_28

    .line 58
    :goto_f
    return-void

    .line 53
    :pswitch_10
    const-string v2, "device_id"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_c

    const/4 v0, 0x0

    goto :goto_c

    .line 55
    :pswitch_1a
    invoke-static {}, Lcom/wamod/utils;->getDeviceID()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {p0, v0}, Lcom/wamod/ActionPreference;->setSummary(Ljava/lang/CharSequence;)V

    goto :goto_f

    .line 53
    :pswitch_data_22
    .packed-switch 0x180aba4
        :pswitch_10
    .end packed-switch

    :pswitch_data_28
    .packed-switch 0x0
        :pswitch_1a
    .end packed-switch
.end method


# virtual methods
.method protected onBindView(Landroid/view/View;)V
    .registers 5
    .param p1, "view"    # Landroid/view/View;

    .prologue
    .line 177
    invoke-super {p0, p1}, Landroid/preference/Preference;->onBindView(Landroid/view/View;)V

    .line 178
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v2

    if-eqz v2, :cond_2f

    .line 179
    const v2, 0x1020016

    invoke-virtual {p1, v2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/TextView;

    .line 180
    .local v1, "title":Landroid/widget/TextView;
    if-eqz v1, :cond_1c

    const/4 v2, 0x0

    invoke-static {v2}, Lcom/wamod/utils;->getDarkColor(I)I

    move-result v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setTextColor(I)V

    .line 182
    :cond_1c
    const v2, 0x1020010

    invoke-virtual {p1, v2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    .line 183
    .local v0, "summary":Landroid/widget/TextView;
    if-eqz v0, :cond_2f

    const/4 v2, 0x1

    invoke-static {v2}, Lcom/wamod/utils;->getDarkColor(I)I

    move-result v2

    invoke-virtual {v0, v2}, Landroid/widget/TextView;->setTextColor(I)V

    .line 185
    .end local v0    # "summary":Landroid/widget/TextView;
    .end local v1    # "title":Landroid/widget/TextView;
    :cond_2f
    return-void
.end method

.method protected onClick()V
    .registers 15

    .prologue
    const v13, 0x104000a

    const v12, 0x1040009

    const/high16 v11, 0x1040000

    const/4 v7, 0x0

    const/4 v8, 0x1

    .line 62
    invoke-super {p0}, Landroid/preference/Preference;->onClick()V

    .line 64
    invoke-virtual {p0}, Lcom/wamod/ActionPreference;->getKey()Ljava/lang/String;

    move-result-object v9

    const/4 v6, -0x1

    invoke-virtual {v9}, Ljava/lang/String;->hashCode()I

    move-result v10

    sparse-switch v10, :sswitch_data_1da

    :cond_19
    :goto_19
    packed-switch v6, :pswitch_data_1f8

    .line 173
    :goto_1c
    return-void

    .line 64
    :sswitch_1d
    const-string v10, "credits"

    invoke-virtual {v9, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_19

    move v6, v7

    goto :goto_19

    :sswitch_27
    const-string v10, "conversation_style_entry_config"

    invoke-virtual {v9, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_19

    move v6, v8

    goto :goto_19

    :sswitch_31
    const-string v10, "checkforupdates"

    invoke-virtual {v9, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_19

    const/4 v6, 0x2

    goto :goto_19

    :sswitch_3b
    const-string v10, "restoredefaults"

    invoke-virtual {v9, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_19

    const/4 v6, 0x3

    goto :goto_19

    :sswitch_45
    const-string v10, "wamodthemes_unlinkdevice"

    invoke-virtual {v9, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_19

    const/4 v6, 0x4

    goto :goto_19

    :sswitch_4f
    const-string v10, "wamodthemes_qts_upload"

    invoke-virtual {v9, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_19

    const/4 v6, 0x5

    goto :goto_19

    :sswitch_59
    const-string v10, "wamodthemes_qts_download"

    invoke-virtual {v9, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v9

    if-eqz v9, :cond_19

    const/4 v6, 0x6

    goto :goto_19

    .line 66
    :pswitch_63
    new-instance v0, Landroid/support/v7/app/AlertDialog$Builder;

    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-direct {v0, v6}, Landroid/support/v7/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 67
    .local v0, "alertDialog":Landroid/support/v7/app/AlertDialog$Builder;
    const-string v6, "Credits"

    invoke-virtual {v0, v6}, Landroid/support/v7/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 68
    new-instance v5, Landroid/webkit/WebView;

    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-direct {v5, v6}, Landroid/webkit/WebView;-><init>(Landroid/content/Context;)V

    .line 69
    .local v5, "wv":Landroid/webkit/WebView;
    const-string v6, "file:///android_asset/wamod_credits.html"

    invoke-virtual {v5, v6}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 70
    new-instance v6, Lcom/wamod/ActionPreference$1;

    invoke-direct {v6, p0}, Lcom/wamod/ActionPreference$1;-><init>(Lcom/wamod/ActionPreference;)V

    invoke-virtual {v5, v6}, Landroid/webkit/WebView;->setWebViewClient(Landroid/webkit/WebViewClient;)V

    .line 77
    invoke-virtual {v0, v5}, Landroid/support/v7/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 78
    const-string v6, "Close"

    new-instance v7, Lcom/wamod/ActionPreference$2;

    invoke-direct {v7, p0}, Lcom/wamod/ActionPreference$2;-><init>(Lcom/wamod/ActionPreference;)V

    invoke-virtual {v0, v6, v7}, Landroid/support/v7/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 84
    invoke-virtual {v0}, Landroid/support/v7/app/AlertDialog$Builder;->show()Landroid/support/v7/app/AlertDialog;

    goto :goto_1c

    .line 87
    .end local v0    # "alertDialog":Landroid/support/v7/app/AlertDialog$Builder;
    .end local v5    # "wv":Landroid/webkit/WebView;
    :pswitch_94
    new-instance v4, Landroid/content/Intent;

    invoke-static {}, Lcom/wamod/app;->getContext()Landroid/content/Context;

    move-result-object v6

    const-class v7, Lcom/wamod/EntryConfigActivity;

    invoke-direct {v4, v6, v7}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    .line 88
    .local v4, "intent":Landroid/content/Intent;
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6, v4}, Landroid/support/v7/app/AppCompatActivity;->startActivity(Landroid/content/Intent;)V

    goto/16 :goto_1c

    .line 91
    .end local v4    # "intent":Landroid/content/Intent;
    :pswitch_a6
    new-instance v6, Lcom/wamod/checkinv2;

    invoke-direct {v6}, Lcom/wamod/checkinv2;-><init>()V

    new-array v8, v8, [Landroid/support/v7/app/AppCompatActivity;

    iget-object v9, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    aput-object v9, v8, v7

    invoke-virtual {v6, v8}, Lcom/wamod/checkinv2;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    goto/16 :goto_1c

    .line 94
    :pswitch_b6
    new-instance v0, Landroid/support/v7/app/AlertDialog$Builder;

    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-direct {v0, v6}, Landroid/support/v7/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 95
    .restart local v0    # "alertDialog":Landroid/support/v7/app/AlertDialog$Builder;
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    sget v7, Lcom/wamod/id;->restoredefaults:I

    invoke-virtual {v6, v7}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Landroid/support/v7/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 96
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    sget v7, Lcom/wamod/id;->restoredefaultsprompt:I

    invoke-virtual {v6, v7}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Landroid/support/v7/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 97
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    const v7, 0x1040013

    invoke-virtual {v6, v7}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-instance v7, Lcom/wamod/ActionPreference$3;

    invoke-direct {v7, p0}, Lcom/wamod/ActionPreference$3;-><init>(Lcom/wamod/ActionPreference;)V

    invoke-virtual {v0, v6, v7}, Landroid/support/v7/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 105
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    invoke-virtual {v6, v12}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-instance v7, Lcom/wamod/ActionPreference$4;

    invoke-direct {v7, p0}, Lcom/wamod/ActionPreference$4;-><init>(Lcom/wamod/ActionPreference;)V

    invoke-virtual {v0, v6, v7}, Landroid/support/v7/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 108
    invoke-virtual {v0}, Landroid/support/v7/app/AlertDialog$Builder;->show()Landroid/support/v7/app/AlertDialog;

    goto/16 :goto_1c

    .line 111
    .end local v0    # "alertDialog":Landroid/support/v7/app/AlertDialog$Builder;
    :pswitch_107
    new-instance v0, Landroid/support/v7/app/AlertDialog$Builder;

    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-direct {v0, v6}, Landroid/support/v7/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 112
    .restart local v0    # "alertDialog":Landroid/support/v7/app/AlertDialog$Builder;
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    sget v7, Lcom/wamod/id;->wamod_unlinkdevice_title:I

    invoke-virtual {v6, v7}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Landroid/support/v7/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 113
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    sget v7, Lcom/wamod/id;->wamod_unlinkdevice_message:I

    invoke-virtual {v6, v7}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v0, v6}, Landroid/support/v7/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 114
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    const v7, 0x1040013

    invoke-virtual {v6, v7}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-instance v7, Lcom/wamod/ActionPreference$5;

    invoke-direct {v7, p0}, Lcom/wamod/ActionPreference$5;-><init>(Lcom/wamod/ActionPreference;)V

    invoke-virtual {v0, v6, v7}, Landroid/support/v7/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 121
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    invoke-virtual {v6, v12}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-instance v7, Lcom/wamod/ActionPreference$6;

    invoke-direct {v7, p0}, Lcom/wamod/ActionPreference$6;-><init>(Lcom/wamod/ActionPreference;)V

    invoke-virtual {v0, v6, v7}, Landroid/support/v7/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 124
    invoke-virtual {v0}, Landroid/support/v7/app/AlertDialog$Builder;->show()Landroid/support/v7/app/AlertDialog;

    goto/16 :goto_1c

    .line 127
    .end local v0    # "alertDialog":Landroid/support/v7/app/AlertDialog$Builder;
    :pswitch_158
    new-instance v2, Landroid/support/v7/app/AlertDialog$Builder;

    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-direct {v2, v6}, Landroid/support/v7/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 128
    .local v2, "builder2":Landroid/support/v7/app/AlertDialog$Builder;
    const-string v6, "Upload a QTS theme"

    invoke-virtual {v2, v6}, Landroid/support/v7/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 129
    const-string v6, "Are you sure you want to upload your theme to WAMOD Quick Theme Sharing?"

    invoke-virtual {v2, v6}, Landroid/support/v7/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 130
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    invoke-virtual {v6, v13}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-instance v7, Lcom/wamod/ActionPreference$7;

    invoke-direct {v7, p0}, Lcom/wamod/ActionPreference$7;-><init>(Lcom/wamod/ActionPreference;)V

    invoke-virtual {v2, v6, v7}, Landroid/support/v7/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 138
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    invoke-virtual {v6, v11}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-instance v7, Lcom/wamod/ActionPreference$8;

    invoke-direct {v7, p0}, Lcom/wamod/ActionPreference$8;-><init>(Lcom/wamod/ActionPreference;)V

    invoke-virtual {v2, v6, v7}, Landroid/support/v7/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 144
    invoke-virtual {v2}, Landroid/support/v7/app/AlertDialog$Builder;->show()Landroid/support/v7/app/AlertDialog;

    goto/16 :goto_1c

    .line 147
    .end local v2    # "builder2":Landroid/support/v7/app/AlertDialog$Builder;
    :pswitch_192
    new-instance v1, Landroid/support/v7/app/AlertDialog$Builder;

    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-direct {v1, v6}, Landroid/support/v7/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 148
    .local v1, "builder":Landroid/support/v7/app/AlertDialog$Builder;
    const-string v6, "Download a QTS theme"

    invoke-virtual {v1, v6}, Landroid/support/v7/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 150
    new-instance v3, Landroid/widget/EditText;

    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-direct {v3, v6}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 151
    .local v3, "input":Landroid/widget/EditText;
    invoke-virtual {v3, v8}, Landroid/widget/EditText;->setInputType(I)V

    .line 152
    const-string v6, "Theme ID"

    invoke-virtual {v3, v6}, Landroid/widget/EditText;->setHint(Ljava/lang/CharSequence;)V

    .line 153
    invoke-virtual {v1, v3}, Landroid/support/v7/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 155
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    invoke-virtual {v6, v13}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-instance v7, Lcom/wamod/ActionPreference$9;

    invoke-direct {v7, p0, v3}, Lcom/wamod/ActionPreference$9;-><init>(Lcom/wamod/ActionPreference;Landroid/widget/EditText;)V

    invoke-virtual {v1, v6, v7}, Landroid/support/v7/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 164
    iget-object v6, p0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v6}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v6

    invoke-virtual {v6, v11}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v6

    new-instance v7, Lcom/wamod/ActionPreference$10;

    invoke-direct {v7, p0}, Lcom/wamod/ActionPreference$10;-><init>(Lcom/wamod/ActionPreference;)V

    invoke-virtual {v1, v6, v7}, Landroid/support/v7/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 170
    invoke-virtual {v1}, Landroid/support/v7/app/AlertDialog$Builder;->show()Landroid/support/v7/app/AlertDialog;

    goto/16 :goto_1c

    .line 64
    nop

    :sswitch_data_1da
    .sparse-switch
        -0x61acf27a -> :sswitch_45
        -0x607c1ef3 -> :sswitch_4f
        -0x5bd4b517 -> :sswitch_31
        -0x4f9b1b47 -> :sswitch_27
        -0x412aa920 -> :sswitch_3b
        -0x3a0df76c -> :sswitch_59
        0x3d4fb49a -> :sswitch_1d
    .end sparse-switch

    :pswitch_data_1f8
    .packed-switch 0x0
        :pswitch_63
        :pswitch_94
        :pswitch_a6
        :pswitch_b6
        :pswitch_107
        :pswitch_158
        :pswitch_192
    .end packed-switch
.end method
