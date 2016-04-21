.class final Lcom/wamod/utils$2;
.super Ljava/lang/Object;
.source "utils.java"

# interfaces
.implements Ljavax/crypto/SecretKey;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/wamod/utils;->getSecretKey()Ljavax/crypto/SecretKey;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x8
    name = null
.end annotation


# direct methods
.method constructor <init>()V
    .registers 1

    .prologue
    .line 793
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public getAlgorithm()Ljava/lang/String;
    .registers 2

    .prologue
    .line 796
    const-string v0, "PBKDF2WithHmacSHA1And8bit"

    return-object v0
.end method

.method public getEncoded()[B
    .registers 4

    .prologue
    .line 806
    const-string v1, "eQV5aq/Cg63Gsq1sshN9T3gh+UUp0wIw0xgHYT1bnCjEqOJQKCRrWxdAe2yvsDeCJL+Y4G3PRD2H\nUF7oUgiGow=="

    const/4 v2, 0x0

    invoke-static {v1, v2}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object v0

    .line 808
    .local v0, "bytes":[B
    return-object v0
.end method

.method public getFormat()Ljava/lang/String;
    .registers 2

    .prologue
    .line 801
    const-string v0, "RAW"

    return-object v0
.end method
