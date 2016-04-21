.class Lcom/wamod/ActionPreference$1;
.super Landroid/webkit/WebViewClient;
.source "ActionPreference.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/wamod/ActionPreference;->onClick()V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/wamod/ActionPreference;


# direct methods
.method constructor <init>(Lcom/wamod/ActionPreference;)V
    .registers 2
    .param p1, "this$0"    # Lcom/wamod/ActionPreference;

    .prologue
    .line 70
    iput-object p1, p0, Lcom/wamod/ActionPreference$1;->this$0:Lcom/wamod/ActionPreference;

    invoke-direct {p0}, Landroid/webkit/WebViewClient;-><init>()V

    return-void
.end method


# virtual methods
.method public shouldOverrideUrlLoading(Landroid/webkit/WebView;Ljava/lang/String;)Z
    .registers 4
    .param p1, "view"    # Landroid/webkit/WebView;
    .param p2, "url"    # Ljava/lang/String;

    .prologue
    .line 73
    invoke-virtual {p1, p2}, Landroid/webkit/WebView;->loadUrl(Ljava/lang/String;)V

    .line 74
    const/4 v0, 0x1

    return v0
.end method
