.class final Lcom/wamod/utils$1;
.super Ljava/lang/Object;
.source "utils.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/wamod/utils;->tintToolbarItems(Landroid/view/ViewGroup;Landroid/content/res/Resources;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# instance fields
.field final synthetic val$actionbar:Landroid/view/ViewGroup;


# direct methods
.method constructor <init>(Landroid/view/ViewGroup;)V
    .registers 2

    .prologue
    .line 608
    iput-object p1, p0, Lcom/wamod/utils$1;->val$actionbar:Landroid/view/ViewGroup;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .registers 13

    .prologue
    const/4 v11, 0x2

    const/4 v10, 0x1

    const/4 v9, 0x0

    .line 611
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "#"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    sget-object v6, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v7, "general_toolbarforeground"

    const-string v8, "FFFFFF"

    invoke-interface {v6, v7, v8}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    .line 614
    .local v0, "color":I
    iget-object v5, p0, Lcom/wamod/utils$1;->val$actionbar:Landroid/view/ViewGroup;

    invoke-virtual {v5, v10}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    instance-of v5, v5, Landroid/support/v7/widget/LinearLayoutCompat;

    if-eqz v5, :cond_6f

    .line 615
    iget-object v5, p0, Lcom/wamod/utils$1;->val$actionbar:Landroid/view/ViewGroup;

    invoke-virtual {v5, v10}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/support/v7/widget/LinearLayoutCompat;

    .line 622
    .local v3, "icons":Landroid/support/v7/widget/LinearLayoutCompat;
    :goto_36
    iget-object v5, p0, Lcom/wamod/utils$1;->val$actionbar:Landroid/view/ViewGroup;

    invoke-virtual {v5, v9}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    instance-of v5, v5, Landroid/widget/TextView;

    if-eqz v5, :cond_4b

    .line 623
    iget-object v5, p0, Lcom/wamod/utils$1;->val$actionbar:Landroid/view/ViewGroup;

    invoke-virtual {v5, v9}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    check-cast v5, Landroid/widget/TextView;

    invoke-virtual {v5, v0}, Landroid/widget/TextView;->setTextColor(I)V

    .line 626
    :cond_4b
    const/4 v1, 0x0

    .local v1, "i":I
    :goto_4c
    invoke-virtual {v3}, Landroid/support/v7/widget/LinearLayoutCompat;->getChildCount()I

    move-result v5

    if-ge v1, v5, :cond_8c

    .line 627
    invoke-virtual {v3, v1}, Landroid/support/v7/widget/LinearLayoutCompat;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    instance-of v5, v5, Landroid/widget/ImageView;

    if-eqz v5, :cond_6c

    .line 628
    invoke-virtual {v3, v1}, Landroid/support/v7/widget/LinearLayoutCompat;->getChildAt(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/widget/ImageView;

    .line 629
    .local v2, "icon":Landroid/widget/ImageView;
    invoke-virtual {v2}, Landroid/widget/ImageView;->getDrawable()Landroid/graphics/drawable/Drawable;

    move-result-object v4

    .line 630
    .local v4, "overflow":Landroid/graphics/drawable/Drawable;
    sget-object v5, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {v4, v0, v5}, Landroid/graphics/drawable/Drawable;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    .line 631
    invoke-virtual {v2, v4}, Landroid/widget/ImageView;->setImageDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 626
    .end local v2    # "icon":Landroid/widget/ImageView;
    .end local v4    # "overflow":Landroid/graphics/drawable/Drawable;
    :cond_6c
    add-int/lit8 v1, v1, 0x1

    goto :goto_4c

    .line 616
    .end local v1    # "i":I
    .end local v3    # "icons":Landroid/support/v7/widget/LinearLayoutCompat;
    :cond_6f
    iget-object v5, p0, Lcom/wamod/utils$1;->val$actionbar:Landroid/view/ViewGroup;

    invoke-virtual {v5, v11}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v5

    instance-of v5, v5, Landroid/support/v7/widget/LinearLayoutCompat;

    if-eqz v5, :cond_82

    .line 617
    iget-object v5, p0, Lcom/wamod/utils$1;->val$actionbar:Landroid/view/ViewGroup;

    invoke-virtual {v5, v11}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/support/v7/widget/LinearLayoutCompat;

    .restart local v3    # "icons":Landroid/support/v7/widget/LinearLayoutCompat;
    goto :goto_36

    .line 619
    .end local v3    # "icons":Landroid/support/v7/widget/LinearLayoutCompat;
    :cond_82
    iget-object v5, p0, Lcom/wamod/utils$1;->val$actionbar:Landroid/view/ViewGroup;

    const/4 v6, 0x3

    invoke-virtual {v5, v6}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v3

    check-cast v3, Landroid/support/v7/widget/LinearLayoutCompat;

    .restart local v3    # "icons":Landroid/support/v7/widget/LinearLayoutCompat;
    goto :goto_36

    .line 634
    .restart local v1    # "i":I
    :cond_8c
    return-void
.end method
