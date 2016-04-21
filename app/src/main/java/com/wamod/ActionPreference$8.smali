.class Lcom/wamod/ActionPreference$8;
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
    .line 138
    iput-object p1, p0, Lcom/wamod/ActionPreference$8;->this$0:Lcom/wamod/ActionPreference;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 3
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "which"    # I

    .prologue
    .line 141
    invoke-interface {p1}, Landroid/content/DialogInterface;->cancel()V

    .line 142
    return-void
.end method
