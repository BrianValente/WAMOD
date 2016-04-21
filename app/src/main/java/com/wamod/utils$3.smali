.class final Lcom/wamod/utils$3;
.super Ljava/lang/Object;
.source "utils.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/wamod/utils;->switchAccount(Landroid/content/Context;)Z
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# instance fields
.field final synthetic val$ctx:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;)V
    .registers 2

    .prologue
    .line 873
    iput-object p1, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .registers 5
    .param p1, "dialog"    # Landroid/content/DialogInterface;
    .param p2, "dialogID"    # I

    .prologue
    .line 875
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "cache"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderTemporary(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$000(Landroid/content/Context;Ljava/lang/String;)V

    .line 876
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "databases"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderTemporary(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$000(Landroid/content/Context;Ljava/lang/String;)V

    .line 877
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "files"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderTemporary(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$000(Landroid/content/Context;Ljava/lang/String;)V

    .line 878
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "no_backup"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderTemporary(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$000(Landroid/content/Context;Ljava/lang/String;)V

    .line 879
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "shared_prefs"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderTemporary(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$000(Landroid/content/Context;Ljava/lang/String;)V

    .line 880
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "cache"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 881
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "databases"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 882
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "files"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 883
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "no_backup"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 884
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "shared_prefs"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 885
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "cache"

    # invokes: Lcom/wamod/utils;->copyToWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$200(Landroid/content/Context;Ljava/lang/String;)V

    .line 886
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "databases"

    # invokes: Lcom/wamod/utils;->copyToWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$200(Landroid/content/Context;Ljava/lang/String;)V

    .line 887
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "files"

    # invokes: Lcom/wamod/utils;->copyToWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$200(Landroid/content/Context;Ljava/lang/String;)V

    .line 888
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "no_backup"

    # invokes: Lcom/wamod/utils;->copyToWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$200(Landroid/content/Context;Ljava/lang/String;)V

    .line 889
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "shared_prefs"

    # invokes: Lcom/wamod/utils;->copyToWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$200(Landroid/content/Context;Ljava/lang/String;)V

    .line 890
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "WAMOD/cache"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 891
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "WAMOD/databases"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 892
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "WAMOD/files"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 893
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "WAMOD/no_backup"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 894
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "WAMOD/shared_prefs"

    # invokes: Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$100(Landroid/content/Context;Ljava/lang/String;)V

    .line 895
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "cache"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderFromTemp(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$300(Landroid/content/Context;Ljava/lang/String;)V

    .line 896
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "databases"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderFromTemp(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$300(Landroid/content/Context;Ljava/lang/String;)V

    .line 897
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "files"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderFromTemp(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$300(Landroid/content/Context;Ljava/lang/String;)V

    .line 898
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "no_backup"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderFromTemp(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$300(Landroid/content/Context;Ljava/lang/String;)V

    .line 899
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    const-string v1, "shared_prefs"

    # invokes: Lcom/wamod/utils;->copyWhatsAppFolderFromTemp(Landroid/content/Context;Ljava/lang/String;)V
    invoke-static {v0, v1}, Lcom/wamod/utils;->access$300(Landroid/content/Context;Ljava/lang/String;)V

    .line 900
    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    # invokes: Lcom/wamod/utils;->deleteWAMODTempFolder(Landroid/content/Context;)V
    invoke-static {v0}, Lcom/wamod/utils;->access$400(Landroid/content/Context;)V

    .line 901
    :cond_b4
    :goto_b4
    sget-boolean v0, Lcom/wamod/utils;->switchReady:Z

    if-eqz v0, :cond_b4

    iget-object v0, p0, Lcom/wamod/utils$3;->val$ctx:Landroid/content/Context;

    invoke-static {v0}, Lcom/wamod/utils;->restartWAMOD(Landroid/content/Context;)V

    goto :goto_b4
.end method
