.class Lcom/wamod/ActionPreference$3;
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
    .line 97
    iput-object p1, p0, Lcom/wamod/ActionPreference$3;->this$0:Lcom/wamod/ActionPreference;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 6
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "id"    # I

    .prologue
    .line 99
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v1, "wamodversion"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 100
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 101
    invoke-static {}, Lcom/wamod/utils;->initWAMOD()V

    .line 102
    iget-object v0, p0, Lcom/wamod/ActionPreference$3;->this$0:Lcom/wamod/ActionPreference;

    iget-object v0, v0, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    iget-object v1, p0, Lcom/wamod/ActionPreference$3;->this$0:Lcom/wamod/ActionPreference;

    iget-object v1, v1, Lcom/wamod/ActionPreference;->activity:Landroid/support/v7/app/AppCompatActivity;

    invoke-virtual {v1}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/wamod/id;->donerestartwamod:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 103
    return-void
.end method
