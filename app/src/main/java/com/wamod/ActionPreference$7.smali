.class Lcom/wamod/ActionPreference$7;
.super Ljava/lang/Object;
.source "ActionPreference.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


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
    .line 130
    iput-object p1, p0, Lcom/wamod/ActionPreference$7;->this$0:Lcom/wamod/ActionPreference;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 5
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "which"    # I

    .prologue
    .line 133
    new-instance v0, Lcom/wamod/themes/QTSUpload;

    invoke-direct {v0}, Lcom/wamod/themes/QTSUpload;-><init>()V

    .line 134
    .local v0, "qtsUpload":Lcom/wamod/themes/QTSUpload;
    iget-object v1, p0, Lcom/wamod/ActionPreference$7;->this$0:Lcom/wamod/ActionPreference;

    iget-object v1, v1, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    sput-object v1, Lcom/wamod/themes/QTSUpload;->activity:Landroid/support/v7/app/AppCompatActivity;

    .line 135
    const/4 v1, 0x0

    new-array v1, v1, [Ljava/lang/Void;

    invoke-virtual {v0, v1}, Lcom/wamod/themes/QTSUpload;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    .line 136
    return-void
.end method
