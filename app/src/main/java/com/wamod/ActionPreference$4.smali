.class Lcom/wamod/ActionPreference$4;
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
    .line 105
    iput-object p1, p0, Lcom/wamod/ActionPreference$4;->this$0:Lcom/wamod/ActionPreference;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 3
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "id"    # I

    .prologue
    .line 106
    return-void
.end method
