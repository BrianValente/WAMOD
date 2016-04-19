.class public Lcom/whatsapp/DialogToastActivity;
.super Lcom/whatsapp/WAAppCompatActivity;
.source "DialogToastActivity.java"

# interfaces
.implements Lcom/whatsapp/aks;


# direct methods
.method public constructor <init>()V
    .registers 1

    .prologue
    .line 9
    invoke-direct {p0}, Lcom/whatsapp/WAAppCompatActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public setContentView(I)V
    .registers 2
    .param p1, "layoutResID"    # I
        .annotation build Landroid/support/annotation/LayoutRes;
        .end annotation
    .end param

    .prologue
    .line 12
    invoke-super {p0, p1}, Lcom/whatsapp/WAAppCompatActivity;->setContentView(I)V

    .line 13
    return-void
.end method
