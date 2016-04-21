.class public Lcom/wamod/utils;
.super Landroid/app/Activity;
.source "utils.java"


# static fields
.field public static final COLOR_FOREGROUND:I = 0x3

.field public static final COLOR_NAVBAR:I = 0x2

.field public static final COLOR_STATUSBAR:I = 0x0

.field public static final COLOR_TOOLBAR:I = 0x1

.field public static final COLOR_TOOLBARTEXT:I = 0x4

.field public static context:Landroid/content/Context;

.field public static debug:Z

.field public static edit:Landroid/content/SharedPreferences$Editor;

.field public static prefs:Landroid/content/SharedPreferences;

.field public static switchReady:Z

.field public static timeSinceLastCheckin:J

.field public static wamodVersionCode:I

.field public static wamodVersionName:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .registers 3

    .prologue
    const/4 v2, 0x0

    .line 83
    const-string v0, "1.0.8"

    sput-object v0, Lcom/wamod/utils;->wamodVersionName:Ljava/lang/String;

    .line 84
    const/16 v0, 0x19

    sput v0, Lcom/wamod/utils;->wamodVersionCode:I

    .line 86
    sput-boolean v2, Lcom/wamod/utils;->debug:Z

    .line 88
    const-wide/16 v0, 0x0

    sput-wide v0, Lcom/wamod/utils;->timeSinceLastCheckin:J

    .line 96
    sput-boolean v2, Lcom/wamod/utils;->switchReady:Z

    return-void
.end method

.method public constructor <init>()V
    .registers 1

    .prologue
    .line 80
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    return-void
.end method

.method public static Nexus6PResToActualDevice(Landroid/content/Context;II)[I
    .registers 7
    .param p0, "ctx"    # Landroid/content/Context;
    .param p1, "x"    # I
    .param p2, "y"    # I

    .prologue
    .line 1033
    const/4 v2, 0x2

    new-array v1, v2, [I

    .line 1034
    .local v1, "newValues":[I
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-virtual {v2}, Landroid/content/res/Resources;->getDisplayMetrics()Landroid/util/DisplayMetrics;

    move-result-object v0

    .line 1036
    .local v0, "metrics":Landroid/util/DisplayMetrics;
    const/4 v2, 0x0

    iget v3, v0, Landroid/util/DisplayMetrics;->widthPixels:I

    mul-int/2addr v3, p1

    div-int/lit16 v3, v3, 0x5a0

    aput v3, v1, v2

    .line 1037
    const/4 v2, 0x1

    iget v3, v0, Landroid/util/DisplayMetrics;->heightPixels:I

    mul-int/2addr v3, p2

    div-int/lit16 v3, v3, 0xa00

    aput v3, v1, v2

    .line 1039
    return-object v1
.end method

.method static synthetic access$000(Landroid/content/Context;Ljava/lang/String;)V
    .registers 2
    .param p0, "x0"    # Landroid/content/Context;
    .param p1, "x1"    # Ljava/lang/String;

    .prologue
    .line 80
    invoke-static {p0, p1}, Lcom/wamod/utils;->copyWhatsAppFolderTemporary(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$100(Landroid/content/Context;Ljava/lang/String;)V
    .registers 2
    .param p0, "x0"    # Landroid/content/Context;
    .param p1, "x1"    # Ljava/lang/String;

    .prologue
    .line 80
    invoke-static {p0, p1}, Lcom/wamod/utils;->deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$200(Landroid/content/Context;Ljava/lang/String;)V
    .registers 2
    .param p0, "x0"    # Landroid/content/Context;
    .param p1, "x1"    # Ljava/lang/String;

    .prologue
    .line 80
    invoke-static {p0, p1}, Lcom/wamod/utils;->copyToWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$300(Landroid/content/Context;Ljava/lang/String;)V
    .registers 2
    .param p0, "x0"    # Landroid/content/Context;
    .param p1, "x1"    # Ljava/lang/String;

    .prologue
    .line 80
    invoke-static {p0, p1}, Lcom/wamod/utils;->copyWhatsAppFolderFromTemp(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$400(Landroid/content/Context;)V
    .registers 1
    .param p0, "x0"    # Landroid/content/Context;

    .prologue
    .line 80
    invoke-static {p0}, Lcom/wamod/utils;->deleteWAMODTempFolder(Landroid/content/Context;)V

    return-void
.end method

.method public static asdfdsf()V
    .registers 0

    .prologue
    .line 1059
    invoke-static {}, Lcom/wamod/utils;->toastHome()V

    .line 1060
    return-void
.end method

.method public static copyToClipboard(Ljava/lang/String;)V
    .registers 5
    .param p0, "s"    # Ljava/lang/String;

    .prologue
    .line 1131
    sget-object v2, Lcom/wamod/utils;->context:Landroid/content/Context;

    sget-object v3, Lcom/wamod/utils;->context:Landroid/content/Context;

    const-string v3, "clipboard"

    invoke-virtual {v2, v3}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/content/ClipboardManager;

    .line 1132
    .local v1, "clipboard":Landroid/content/ClipboardManager;
    const-string v2, ""

    invoke-static {v2, p0}, Landroid/content/ClipData;->newPlainText(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Landroid/content/ClipData;

    move-result-object v0

    .line 1133
    .local v0, "clip":Landroid/content/ClipData;
    invoke-virtual {v1, v0}, Landroid/content/ClipboardManager;->setPrimaryClip(Landroid/content/ClipData;)V

    .line 1134
    return-void
.end method

.method private static copyToWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    .registers 7
    .param p0, "ctx"    # Landroid/content/Context;
    .param p1, "name"    # Ljava/lang/String;

    .prologue
    .line 922
    const/4 v3, 0x0

    sput-boolean v3, Lcom/wamod/utils;->switchReady:Z

    .line 924
    :try_start_3
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    .line 925
    .local v0, "appPath":Ljava/lang/String;
    new-instance v1, Ljava/io/File;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 926
    .local v1, "dest":Ljava/io/File;
    new-instance v2, Ljava/io/File;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/WAMOD/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 927
    .local v2, "source":Ljava/io/File;
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v3

    if-nez v3, :cond_48

    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    .line 928
    :cond_48
    invoke-static {v2, v1}, Lorg/apache/commons/io/FileUtils;->copyDirectory(Ljava/io/File;Ljava/io/File;)V
    :try_end_4b
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_4b} :catch_4f

    .line 930
    .end local v0    # "appPath":Ljava/lang/String;
    .end local v1    # "dest":Ljava/io/File;
    .end local v2    # "source":Ljava/io/File;
    :goto_4b
    const/4 v3, 0x1

    sput-boolean v3, Lcom/wamod/utils;->switchReady:Z

    .line 931
    return-void

    .line 929
    :catch_4f
    move-exception v3

    goto :goto_4b
.end method

.method private static copyWhatsAppFolderFromTemp(Landroid/content/Context;Ljava/lang/String;)V
    .registers 7
    .param p0, "ctx"    # Landroid/content/Context;
    .param p1, "name"    # Ljava/lang/String;

    .prologue
    .line 910
    const/4 v3, 0x0

    sput-boolean v3, Lcom/wamod/utils;->switchReady:Z

    .line 912
    :try_start_3
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    .line 913
    .local v0, "appPath":Ljava/lang/String;
    new-instance v1, Ljava/io/File;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/WAMOD/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 914
    .local v1, "dest":Ljava/io/File;
    new-instance v2, Ljava/io/File;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/WAMOD_temp/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 915
    .local v2, "source":Ljava/io/File;
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v3

    if-nez v3, :cond_48

    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    .line 916
    :cond_48
    invoke-static {v2, v1}, Lorg/apache/commons/io/FileUtils;->copyDirectory(Ljava/io/File;Ljava/io/File;)V
    :try_end_4b
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_4b} :catch_4f

    .line 918
    .end local v0    # "appPath":Ljava/lang/String;
    .end local v1    # "dest":Ljava/io/File;
    .end local v2    # "source":Ljava/io/File;
    :goto_4b
    const/4 v3, 0x1

    sput-boolean v3, Lcom/wamod/utils;->switchReady:Z

    .line 919
    return-void

    .line 917
    :catch_4f
    move-exception v3

    goto :goto_4b
.end method

.method private static copyWhatsAppFolderTemporary(Landroid/content/Context;Ljava/lang/String;)V
    .registers 7
    .param p0, "ctx"    # Landroid/content/Context;
    .param p1, "name"    # Ljava/lang/String;

    .prologue
    .line 934
    const/4 v3, 0x0

    sput-boolean v3, Lcom/wamod/utils;->switchReady:Z

    .line 936
    :try_start_3
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    .line 937
    .local v0, "appPath":Ljava/lang/String;
    new-instance v1, Ljava/io/File;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/WAMOD_temp/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v1, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 938
    .local v1, "dest":Ljava/io/File;
    new-instance v2, Ljava/io/File;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 939
    .local v2, "source":Ljava/io/File;
    invoke-virtual {v1}, Ljava/io/File;->exists()Z

    move-result v3

    if-nez v3, :cond_48

    invoke-virtual {v1}, Ljava/io/File;->mkdirs()Z

    .line 940
    :cond_48
    invoke-static {v2, v1}, Lorg/apache/commons/io/FileUtils;->copyDirectory(Ljava/io/File;Ljava/io/File;)V
    :try_end_4b
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_4b} :catch_4f

    .line 942
    .end local v0    # "appPath":Ljava/lang/String;
    .end local v1    # "dest":Ljava/io/File;
    .end local v2    # "source":Ljava/io/File;
    :goto_4b
    const/4 v3, 0x1

    sput-boolean v3, Lcom/wamod/utils;->switchReady:Z

    .line 943
    return-void

    .line 941
    :catch_4f
    move-exception v3

    goto :goto_4b
.end method

.method public static crashWAMOD()V
    .registers 3

    .prologue
    .line 701
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v1, "wamodversion"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 702
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v1, "crash"

    const/4 v2, 0x1

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 703
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 704
    return-void
.end method

.method public static crashWAMOD(Landroid/support/v7/app/AppCompatActivity;)V
    .registers 4
    .param p0, "a"    # Landroid/support/v7/app/AppCompatActivity;

    .prologue
    .line 693
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v1, "wamodversion"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 694
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v1, "crash"

    const/4 v2, 0x1

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 695
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 697
    invoke-static {p0}, Lcom/wamod/utils;->restartWAMOD(Landroid/support/v7/app/AppCompatActivity;)V

    .line 698
    return-void
.end method

.method public static ddarkMode()Z
    .registers 3

    .prologue
    .line 452
    sget-object v1, Lcom/wamod/utils;->context:Landroid/content/Context;

    const-string v2, "uimode"

    invoke-virtual {v1, v2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/UiModeManager;

    .line 453
    .local v0, "uiModeManager":Landroid/app/UiModeManager;
    invoke-virtual {v0}, Landroid/app/UiModeManager;->getNightMode()I

    move-result v1

    const/4 v2, 0x2

    if-ne v1, v2, :cond_13

    const/4 v1, 0x1

    :goto_12
    return v1

    :cond_13
    const/4 v1, 0x0

    goto :goto_12
.end method

.method public static decodeStrings(Ljava/lang/String;[Ljava/lang/String;)V
    .registers 5
    .param p0, "className"    # Ljava/lang/String;
    .param p1, "strings"    # [Ljava/lang/String;

    .prologue
    .line 756
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_1
    array-length v1, p1

    if-ge v0, v1, :cond_2d

    .line 757
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " string "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " : "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    aget-object v2, p1, v0

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/wamod/utils;->log(Ljava/lang/String;)V

    .line 756
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 759
    :cond_2d
    return-void
.end method

.method public static decodeStrings([Ljava/lang/String;)V
    .registers 4
    .param p0, "strings"    # [Ljava/lang/String;

    .prologue
    .line 750
    const/4 v0, 0x0

    .local v0, "i":I
    :goto_1
    array-length v1, p0

    if-ge v0, v1, :cond_29

    .line 751
    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "String "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, v0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, " : "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    aget-object v2, p0, v0

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Lcom/wamod/utils;->log(Ljava/lang/String;)V

    .line 750
    add-int/lit8 v0, v0, 0x1

    goto :goto_1

    .line 753
    :cond_29
    return-void
.end method

.method private static deleteWAMODTempFolder(Landroid/content/Context;)V
    .registers 5
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 957
    const/4 v2, 0x0

    sput-boolean v2, Lcom/wamod/utils;->switchReady:Z

    .line 959
    :try_start_3
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    .line 960
    .local v0, "appPath":Ljava/lang/String;
    new-instance v1, Ljava/io/File;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "/WAMOD_temp"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 961
    .local v1, "source":Ljava/io/File;
    invoke-static {v1}, Lorg/apache/commons/io/FileUtils;->deleteDirectory(Ljava/io/File;)V
    :try_end_22
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_22} :catch_26

    .line 963
    .end local v0    # "appPath":Ljava/lang/String;
    .end local v1    # "source":Ljava/io/File;
    :goto_22
    const/4 v2, 0x1

    sput-boolean v2, Lcom/wamod/utils;->switchReady:Z

    .line 964
    return-void

    .line 962
    :catch_26
    move-exception v2

    goto :goto_22
.end method

.method private static deleteWhatsAppFolder(Landroid/content/Context;Ljava/lang/String;)V
    .registers 6
    .param p0, "ctx"    # Landroid/content/Context;
    .param p1, "name"    # Ljava/lang/String;

    .prologue
    .line 947
    const/4 v2, 0x0

    sput-boolean v2, Lcom/wamod/utils;->switchReady:Z

    .line 949
    :try_start_3
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    .line 950
    .local v0, "appPath":Ljava/lang/String;
    new-instance v1, Ljava/io/File;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "/"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 951
    .local v1, "source":Ljava/io/File;
    invoke-static {v1}, Lorg/apache/commons/io/FileUtils;->deleteDirectory(Ljava/io/File;)V
    :try_end_26
    .catch Ljava/io/IOException; {:try_start_3 .. :try_end_26} :catch_2a

    .line 953
    .end local v0    # "appPath":Ljava/lang/String;
    .end local v1    # "source":Ljava/io/File;
    :goto_26
    const/4 v2, 0x1

    sput-boolean v2, Lcom/wamod/utils;->switchReady:Z

    .line 954
    return-void

    .line 952
    :catch_2a
    move-exception v2

    goto :goto_26
.end method

.method public static get2ndNumberUserName(Landroid/content/Context;)Ljava/lang/String;
    .registers 7
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 968
    :try_start_0
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    .line 969
    .local v0, "appPath":Ljava/lang/String;
    new-instance v1, Ljava/io/File;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "/shared_prefs/com.whatsapp_preferences_2nd.xml"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v1, v4}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 970
    .local v1, "dest":Ljava/io/File;
    new-instance v3, Ljava/io/File;

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "/WAMOD/shared_prefs/com.whatsapp_preferences.xml"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v3, v4}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 971
    .local v3, "source":Ljava/io/File;
    invoke-static {v3, v1}, Lorg/apache/commons/io/FileUtils;->copyDirectory(Ljava/io/File;Ljava/io/File;)V

    .line 972
    const-string v4, "com.whatsapp_preferences_2nd"

    const/4 v5, 0x0

    invoke-virtual {p0, v4, v5}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v2

    .line 973
    .local v2, "prefs":Landroid/content/SharedPreferences;
    const-string v4, "push_name"

    const-string v5, ""

    invoke-interface {v2, v4, v5}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    :try_end_45
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_45} :catch_47

    move-result-object v4

    .line 975
    .end local v0    # "appPath":Ljava/lang/String;
    .end local v1    # "dest":Ljava/io/File;
    .end local v2    # "prefs":Landroid/content/SharedPreferences;
    .end local v3    # "source":Ljava/io/File;
    :goto_46
    return-object v4

    .line 974
    :catch_47
    move-exception v4

    .line 975
    const/4 v4, 0x0

    goto :goto_46
.end method

.method public static get2ndNumberUserPhoneNumber(Landroid/content/Context;)Ljava/lang/String;
    .registers 10
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 980
    :try_start_0
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    .line 981
    .local v0, "appPath":Ljava/lang/String;
    new-instance v2, Ljava/io/File;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    const-string v8, "/shared_prefs/RegisterPhone_2nd.xml"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-direct {v2, v7}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 982
    .local v2, "dest":Ljava/io/File;
    new-instance v6, Ljava/io/File;

    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v7, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    const-string v8, "/WAMOD/shared_prefs/RegisterPhone.xml"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v7

    invoke-direct {v6, v7}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 983
    .local v6, "source":Ljava/io/File;
    invoke-static {v6, v2}, Lorg/apache/commons/io/FileUtils;->copyDirectory(Ljava/io/File;Ljava/io/File;)V

    .line 984
    const-string v7, "RegisterPhone_2nd"

    const/4 v8, 0x0

    invoke-virtual {p0, v7, v8}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v5

    .line 985
    .local v5, "prefs":Landroid/content/SharedPreferences;
    const-string v7, "com.whatsapp.RegisterPhone.input_phone_number"

    const-string v8, ""

    invoke-interface {v5, v7, v8}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    .line 986
    .local v4, "number":Ljava/lang/String;
    const-string v7, "com.whatsapp.RegisterPhone.country_code"

    const-string v8, ""

    invoke-interface {v5, v7, v8}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 987
    .local v1, "country":Ljava/lang/String;
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "+"

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    const-string v8, " "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v7

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;
    :try_end_6a
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_6a} :catch_6c

    move-result-object v3

    .line 990
    .end local v0    # "appPath":Ljava/lang/String;
    .end local v1    # "country":Ljava/lang/String;
    .end local v2    # "dest":Ljava/io/File;
    .end local v4    # "number":Ljava/lang/String;
    .end local v5    # "prefs":Landroid/content/SharedPreferences;
    .end local v6    # "source":Ljava/io/File;
    :goto_6b
    return-object v3

    .line 989
    :catch_6c
    move-exception v7

    .line 990
    const/4 v3, 0x0

    goto :goto_6b
.end method

.method public static get2ndNumberUserPicture(Landroid/content/Context;)Landroid/graphics/drawable/Drawable;
    .registers 6
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 994
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v2

    .line 995
    .local v2, "s":Ljava/lang/String;
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/WAMOD/files/me.jpg"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 996
    .local v1, "pathName":Ljava/lang/String;
    invoke-static {v1}, Landroid/graphics/drawable/Drawable;->createFromPath(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 997
    .local v0, "d":Landroid/graphics/drawable/Drawable;
    return-object v0
.end method

.method public static getApplicationPath(Landroid/content/Context;)Ljava/lang/String;
    .registers 6
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 847
    :try_start_0
    invoke-virtual {p0}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v1

    .line 848
    .local v1, "m":Landroid/content/pm/PackageManager;
    invoke-virtual {p0}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v3

    .line 849
    .local v3, "s":Ljava/lang/String;
    const/4 v4, 0x0

    invoke-virtual {v1, v3, v4}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v2

    .line 850
    .local v2, "p":Landroid/content/pm/PackageInfo;
    iget-object v4, v2, Landroid/content/pm/PackageInfo;->applicationInfo:Landroid/content/pm/ApplicationInfo;

    iget-object v3, v4, Landroid/content/pm/ApplicationInfo;->dataDir:Ljava/lang/String;
    :try_end_11
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_0 .. :try_end_11} :catch_12

    .line 853
    .end local v1    # "m":Landroid/content/pm/PackageManager;
    .end local v2    # "p":Landroid/content/pm/PackageInfo;
    .end local v3    # "s":Ljava/lang/String;
    :goto_11
    return-object v3

    .line 852
    :catch_12
    move-exception v0

    .line 853
    .local v0, "e":Landroid/content/pm/PackageManager$NameNotFoundException;
    const/4 v3, 0x0

    goto :goto_11
.end method

.method public static getCertificateBytes()[B
    .registers 3

    .prologue
    .line 738
    invoke-static {}, Lcom/wamod/utils;->getSignature()[Landroid/content/pm/Signature;

    move-result-object v1

    const/4 v2, 0x0

    aget-object v0, v1, v2

    .line 739
    .local v0, "LeakedSignature":Landroid/content/pm/Signature;
    invoke-virtual {v0}, Landroid/content/pm/Signature;->toByteArray()[B

    move-result-object v1

    return-object v1
.end method

.method public static getDarkColor(I)I
    .registers 3
    .param p0, "id"    # I

    .prologue
    .line 565
    packed-switch p0, :pswitch_data_14

    .line 574
    :pswitch_3
    const-string v1, "#303030"

    .line 579
    .local v1, "colorStr":Ljava/lang/String;
    :goto_5
    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    .line 580
    .local v0, "color":I
    return v0

    .line 567
    .end local v0    # "color":I
    .end local v1    # "colorStr":Ljava/lang/String;
    :pswitch_a
    const-string v1, "#ffffff"

    .line 568
    .restart local v1    # "colorStr":Ljava/lang/String;
    goto :goto_5

    .line 570
    .end local v1    # "colorStr":Ljava/lang/String;
    :pswitch_d
    const-string v1, "#aaaaaa"

    .line 571
    .restart local v1    # "colorStr":Ljava/lang/String;
    goto :goto_5

    .line 577
    .end local v1    # "colorStr":Ljava/lang/String;
    :pswitch_10
    const-string v1, "#424242"

    .restart local v1    # "colorStr":Ljava/lang/String;
    goto :goto_5

    .line 565
    nop

    :pswitch_data_14
    .packed-switch 0x0
        :pswitch_a
        :pswitch_d
        :pswitch_3
        :pswitch_10
    .end packed-switch
.end method

.method public static getDeviceID()Ljava/lang/String;
    .registers 3

    .prologue
    .line 308
    sget-object v0, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v1, "device_id"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public static getDrawerBackground(Landroid/content/Context;)Landroid/graphics/drawable/Drawable;
    .registers 7
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 858
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v3

    .line 859
    .local v3, "s":Ljava/lang/String;
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v4, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "/files/wamod_drawer_bg.png"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 861
    .local v2, "pathName":Ljava/lang/String;
    :try_start_17
    invoke-static {v2}, Landroid/graphics/drawable/Drawable;->createFromPath(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 862
    .local v0, "d":Landroid/graphics/drawable/Drawable;
    if-nez v0, :cond_27

    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    sget v5, Lcom/wamod/Resources$drawable;->wamod_drawer_bg:I

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;
    :try_end_26
    .catch Ljava/lang/OutOfMemoryError; {:try_start_17 .. :try_end_26} :catch_28

    move-result-object v0

    .line 865
    .end local v0    # "d":Landroid/graphics/drawable/Drawable;
    :cond_27
    :goto_27
    return-object v0

    .line 864
    :catch_28
    move-exception v1

    .line 865
    .local v1, "e":Ljava/lang/OutOfMemoryError;
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v4

    sget v5, Lcom/wamod/Resources$drawable;->wamod_drawer_bg:I

    invoke-virtual {v4, v5}, Landroid/content/res/Resources;->getDrawable(I)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    goto :goto_27
.end method

.method public static final getHexID(Ljava/lang/String;Ljava/lang/String;)I
    .registers 4
    .param p0, "name"    # Ljava/lang/String;
    .param p1, "type"    # Ljava/lang/String;

    .prologue
    .line 1043
    sget-object v0, Lcom/wamod/utils;->context:Landroid/content/Context;

    invoke-virtual {v0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v0

    sget-object v1, Lcom/wamod/utils;->context:Landroid/content/Context;

    invoke-virtual {v1}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, p0, p1, v1}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    return v0
.end method

.method public static getPrivacyConfig(I)Z
    .registers 5
    .param p0, "id"    # I

    .prologue
    const/4 v3, 0x0

    .line 584
    const/4 v0, 0x0

    .line 585
    .local v0, "value":Z
    packed-switch p0, :pswitch_data_32

    .line 600
    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "privacy_alwaysOnline"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    .line 603
    :goto_d
    return v0

    .line 587
    :pswitch_e
    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "privacy_freezelastseen"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    .line 588
    goto :goto_d

    .line 590
    :pswitch_17
    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "privacy_no2ndTick"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    .line 591
    goto :goto_d

    .line 593
    :pswitch_20
    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "privacy_noBlueTick"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    .line 594
    goto :goto_d

    .line 596
    :pswitch_29
    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "privacy_hideTyping"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    .line 597
    goto :goto_d

    .line 585
    :pswitch_data_32
    .packed-switch 0x0
        :pswitch_e
        :pswitch_17
        :pswitch_20
        :pswitch_29
    .end packed-switch
.end method

.method public static getRegistrationClass(Ljava/lang/String;)Lcom/whatsapp/registration/a;
    .registers 9
    .param p0, "number"    # Ljava/lang/String;

    .prologue
    const/4 v7, 0x0

    .line 1010
    const/4 v1, 0x0

    .line 1012
    .local v1, "mac":Ljavax/crypto/Mac;
    :try_start_2
    invoke-static {}, Lcom/wamod/utils;->getSecretKey()Ljavax/crypto/SecretKey;

    move-result-object v2

    .line 1013
    .local v2, "secretKey":Ljavax/crypto/SecretKey;
    const-string v5, "HMACSHA1"

    invoke-static {v5}, Ljavax/crypto/Mac;->getInstance(Ljava/lang/String;)Ljavax/crypto/Mac;

    move-result-object v1

    .line 1014
    invoke-virtual {v1, v2}, Ljavax/crypto/Mac;->init(Ljava/security/Key;)V

    .line 1015
    invoke-static {}, Lcom/wamod/utils;->getSignature()[Landroid/content/pm/Signature;

    move-result-object v5

    const/4 v6, 0x0

    aget-object v3, v5, v6

    .line 1016
    .local v3, "sign":Landroid/content/pm/Signature;
    invoke-virtual {v3}, Landroid/content/pm/Signature;->toByteArray()[B

    move-result-object v4

    .line 1017
    .local v4, "signBytes":[B
    invoke-virtual {v1, v4}, Ljavax/crypto/Mac;->update([B)V

    .line 1018
    invoke-static {}, Lcom/wamod/utils;->getb9()[B

    move-result-object v5

    invoke-virtual {v1, v5}, Ljavax/crypto/Mac;->update([B)V

    .line 1019
    const-string v5, "UTF-8"

    invoke-virtual {p0, v5}, Ljava/lang/String;->getBytes(Ljava/lang/String;)[B

    move-result-object v5

    invoke-virtual {v1, v5}, Ljavax/crypto/Mac;->update([B)V
    :try_end_2d
    .catch Ljava/security/NoSuchAlgorithmException; {:try_start_2 .. :try_end_2d} :catch_44
    .catch Ljava/security/InvalidKeyException; {:try_start_2 .. :try_end_2d} :catch_42
    .catch Ljava/io/UnsupportedEncodingException; {:try_start_2 .. :try_end_2d} :catch_40

    .line 1023
    .end local v2    # "secretKey":Ljavax/crypto/SecretKey;
    .end local v3    # "sign":Landroid/content/pm/Signature;
    .end local v4    # "signBytes":[B
    :goto_2d
    invoke-virtual {v1}, Ljavax/crypto/Mac;->doFinal()[B

    move-result-object v0

    .line 1024
    .local v0, "_final":[B
    const-string v5, "WAMOD"

    invoke-static {v0, v7}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v6

    invoke-static {v5, v6}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 1025
    new-instance v5, Lcom/whatsapp/registration/a;

    invoke-direct {v5, v0}, Lcom/whatsapp/registration/a;-><init>([B)V

    return-object v5

    .line 1022
    .end local v0    # "_final":[B
    :catch_40
    move-exception v5

    goto :goto_2d

    .line 1021
    :catch_42
    move-exception v5

    goto :goto_2d

    .line 1020
    :catch_44
    move-exception v5

    goto :goto_2d
.end method

.method public static getSecretKey()Ljavax/crypto/SecretKey;
    .registers 1

    .prologue
    .line 793
    new-instance v0, Lcom/wamod/utils$2;

    invoke-direct {v0}, Lcom/wamod/utils$2;-><init>()V

    return-object v0
.end method

.method public static getSignature()[Landroid/content/pm/Signature;
    .registers 4

    .prologue
    .line 743
    const/4 v2, 0x1

    new-array v0, v2, [Landroid/content/pm/Signature;

    .line 744
    .local v0, "LeakedSignatureArray":[Landroid/content/pm/Signature;
    const-string v1, "30820332308202f0a00302010202044c2536a4300b06072a8648ce3804030500307c310b3009060355040613025553311330110603550408130a43616c69666f726e6961311430120603550407130b53616e746120436c61726131163014060355040a130d576861747341707020496e632e31143012060355040b130b456e67696e656572696e67311430120603550403130b427269616e204163746f6e301e170d3130303632353233303731365a170d3434303231353233303731365a307c310b3009060355040613025553311330110603550408130a43616c69666f726e6961311430120603550407130b53616e746120436c61726131163014060355040a130d576861747341707020496e632e31143012060355040b130b456e67696e656572696e67311430120603550403130b427269616e204163746f6e308201b83082012c06072a8648ce3804013082011f02818100fd7f53811d75122952df4a9c2eece4e7f611b7523cef4400c31e3f80b6512669455d402251fb593d8d58fabfc5f5ba30f6cb9b556cd7813b801d346ff26660b76b9950a5a49f9fe8047b1022c24fbba9d7feb7c61bf83b57e7c6a8a6150f04fb83f6d3c51ec3023554135a169132f675f3ae2b61d72aeff22203199dd14801c70215009760508f15230bccb292b982a2eb840bf0581cf502818100f7e1a085d69b3ddecbbcab5c36b857b97994afbbfa3aea82f9574c0b3d0782675159578ebad4594fe67107108180b449167123e84c281613b7cf09328cc8a6e13c167a8b547c8d28e0a3ae1e2bb3a675916ea37f0bfa213562f1fb627a01243bcca4f1bea8519089a883dfe15ae59f06928b665e807b552564014c3bfecf492a0381850002818100d1198b4b81687bcf246d41a8a725f0a989a51bce326e84c828e1f556648bd71da487054d6de70fff4b49432b6862aa48fc2a93161b2c15a2ff5e671672dfb576e9d12aaff7369b9a99d04fb29d2bbbb2a503ee41b1ff37887064f41fe2805609063500a8e547349282d15981cdb58a08bede51dd7e9867295b3dfb45ffc6b259300b06072a8648ce3804030500032f00302c021400a602a7477acf841077237be090df436582ca2f0214350ce0268d07e71e55774ab4eacd4d071cd1efad"

    .line 745
    .local v1, "LeakedSignature_String":Ljava/lang/String;
    const/4 v2, 0x0

    new-instance v3, Landroid/content/pm/Signature;

    invoke-direct {v3, v1}, Landroid/content/pm/Signature;-><init>(Ljava/lang/String;)V

    aput-object v3, v0, v2

    .line 746
    return-object v0
.end method

.method public static getStatusBarHeight(Landroid/content/Context;)I
    .registers 7
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 1001
    const/4 v1, 0x0

    .line 1002
    .local v1, "result":I
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    const-string v3, "status_bar_height"

    const-string v4, "dimen"

    const-string v5, "android"

    invoke-virtual {v2, v3, v4, v5}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v0

    .line 1003
    .local v0, "resourceId":I
    if-lez v0, :cond_19

    .line 1004
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v2

    invoke-virtual {v2, v0}, Landroid/content/res/Resources;->getDimensionPixelSize(I)I

    move-result v1

    .line 1006
    :cond_19
    return v1
.end method

.method public static getTickDrawableHex(I)I
    .registers 13
    .param p0, "optionID"    # I

    .prologue
    .line 458
    sget-object v9, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v10, "conversation_style_tick"

    const-string v11, "0"

    invoke-interface {v9, v10, v11}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 468
    .local v0, "bubbleID":Ljava/lang/String;
    const/4 v9, -0x1

    invoke-virtual {v0}, Ljava/lang/String;->hashCode()I

    move-result v10

    packed-switch v10, :pswitch_data_114

    :cond_12
    :goto_12
    packed-switch v9, :pswitch_data_124

    .line 530
    const v7, 0x7f02104b

    .line 531
    .local v7, "message_unsent":I
    const v3, 0x7f02104c

    .line 532
    .local v3, "message_got_receipt_from_server":I
    const v5, 0x7f02104d

    .line 533
    .local v5, "message_got_receipt_from_target":I
    const v1, 0x7f02104e

    .line 534
    .local v1, "message_got_read_receipt_from_target":I
    const v8, 0x7f02104f

    .line 535
    .local v8, "message_unsent_onmedia":I
    const v4, 0x7f021050

    .line 536
    .local v4, "message_got_receipt_from_server_onmedia":I
    const v6, 0x7f021051

    .line 537
    .local v6, "message_got_receipt_from_target_onmedia":I
    const v2, 0x7f021052

    .line 541
    .local v2, "message_got_read_receipt_from_target_onmedia":I
    :goto_2d
    packed-switch p0, :pswitch_data_134

    .line 560
    .end local v7    # "message_unsent":I
    :goto_30
    :pswitch_30
    return v7

    .line 468
    .end local v1    # "message_got_read_receipt_from_target":I
    .end local v2    # "message_got_read_receipt_from_target_onmedia":I
    .end local v3    # "message_got_receipt_from_server":I
    .end local v4    # "message_got_receipt_from_server_onmedia":I
    .end local v5    # "message_got_receipt_from_target":I
    .end local v6    # "message_got_receipt_from_target_onmedia":I
    .end local v8    # "message_unsent_onmedia":I
    :pswitch_31
    const-string v10, "0"

    invoke-virtual {v0, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_12

    const/4 v9, 0x0

    goto :goto_12

    :pswitch_3b
    const-string v10, "1"

    invoke-virtual {v0, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_12

    const/4 v9, 0x1

    goto :goto_12

    :pswitch_45
    const-string v10, "2"

    invoke-virtual {v0, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_12

    const/4 v9, 0x2

    goto :goto_12

    :pswitch_4f
    const-string v10, "3"

    invoke-virtual {v0, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_12

    const/4 v9, 0x3

    goto :goto_12

    :pswitch_59
    const-string v10, "4"

    invoke-virtual {v0, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_12

    const/4 v9, 0x4

    goto :goto_12

    :pswitch_63
    const-string v10, "5"

    invoke-virtual {v0, v10}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v10

    if-eqz v10, :cond_12

    const/4 v9, 0x5

    goto :goto_12

    .line 470
    :pswitch_6d
    sget v7, Lcom/wamod/Resources$drawable;->message_unsent:I

    .line 471
    .restart local v7    # "message_unsent":I
    sget v3, Lcom/wamod/Resources$drawable;->message_got_receipt_from_server:I

    .line 472
    .restart local v3    # "message_got_receipt_from_server":I
    sget v5, Lcom/wamod/Resources$drawable;->message_got_receipt_from_target:I

    .line 473
    .restart local v5    # "message_got_receipt_from_target":I
    sget v1, Lcom/wamod/Resources$drawable;->message_got_read_receipt_from_target:I

    .line 474
    .restart local v1    # "message_got_read_receipt_from_target":I
    sget v8, Lcom/wamod/Resources$drawable;->message_unsent_onmedia:I

    .line 475
    .restart local v8    # "message_unsent_onmedia":I
    sget v4, Lcom/wamod/Resources$drawable;->message_got_receipt_from_server_onmedia:I

    .line 476
    .restart local v4    # "message_got_receipt_from_server_onmedia":I
    sget v6, Lcom/wamod/Resources$drawable;->message_got_receipt_from_target_onmedia:I

    .line 477
    .restart local v6    # "message_got_receipt_from_target_onmedia":I
    sget v2, Lcom/wamod/Resources$drawable;->message_got_read_receipt_from_target_onmedia:I

    .line 478
    .restart local v2    # "message_got_read_receipt_from_target_onmedia":I
    goto :goto_2d

    .line 480
    .end local v1    # "message_got_read_receipt_from_target":I
    .end local v2    # "message_got_read_receipt_from_target_onmedia":I
    .end local v3    # "message_got_receipt_from_server":I
    .end local v4    # "message_got_receipt_from_server_onmedia":I
    .end local v5    # "message_got_receipt_from_target":I
    .end local v6    # "message_got_receipt_from_target_onmedia":I
    .end local v7    # "message_unsent":I
    .end local v8    # "message_unsent_onmedia":I
    :pswitch_7e
    const v7, 0x7f021023

    .line 481
    .restart local v7    # "message_unsent":I
    const v3, 0x7f021024

    .line 482
    .restart local v3    # "message_got_receipt_from_server":I
    const v5, 0x7f021025

    .line 483
    .restart local v5    # "message_got_receipt_from_target":I
    const v1, 0x7f021026

    .line 484
    .restart local v1    # "message_got_read_receipt_from_target":I
    const v8, 0x7f021027

    .line 485
    .restart local v8    # "message_unsent_onmedia":I
    const v4, 0x7f021028

    .line 486
    .restart local v4    # "message_got_receipt_from_server_onmedia":I
    const v6, 0x7f021029

    .line 487
    .restart local v6    # "message_got_receipt_from_target_onmedia":I
    const v2, 0x7f02102a

    .line 488
    .restart local v2    # "message_got_read_receipt_from_target_onmedia":I
    goto :goto_2d

    .line 490
    .end local v1    # "message_got_read_receipt_from_target":I
    .end local v2    # "message_got_read_receipt_from_target_onmedia":I
    .end local v3    # "message_got_receipt_from_server":I
    .end local v4    # "message_got_receipt_from_server_onmedia":I
    .end local v5    # "message_got_receipt_from_target":I
    .end local v6    # "message_got_receipt_from_target_onmedia":I
    .end local v7    # "message_unsent":I
    .end local v8    # "message_unsent_onmedia":I
    :pswitch_97
    const v7, 0x7f02102b

    .line 491
    .restart local v7    # "message_unsent":I
    const v3, 0x7f02102c

    .line 492
    .restart local v3    # "message_got_receipt_from_server":I
    const v5, 0x7f02102d

    .line 493
    .restart local v5    # "message_got_receipt_from_target":I
    const v1, 0x7f02102e

    .line 494
    .restart local v1    # "message_got_read_receipt_from_target":I
    const v8, 0x7f02102f

    .line 495
    .restart local v8    # "message_unsent_onmedia":I
    const v4, 0x7f021030

    .line 496
    .restart local v4    # "message_got_receipt_from_server_onmedia":I
    const v6, 0x7f021031

    .line 497
    .restart local v6    # "message_got_receipt_from_target_onmedia":I
    const v2, 0x7f021032

    .line 498
    .restart local v2    # "message_got_read_receipt_from_target_onmedia":I
    goto/16 :goto_2d

    .line 500
    .end local v1    # "message_got_read_receipt_from_target":I
    .end local v2    # "message_got_read_receipt_from_target_onmedia":I
    .end local v3    # "message_got_receipt_from_server":I
    .end local v4    # "message_got_receipt_from_server_onmedia":I
    .end local v5    # "message_got_receipt_from_target":I
    .end local v6    # "message_got_receipt_from_target_onmedia":I
    .end local v7    # "message_unsent":I
    .end local v8    # "message_unsent_onmedia":I
    :pswitch_b1
    const v7, 0x7f021033

    .line 501
    .restart local v7    # "message_unsent":I
    const v3, 0x7f021034

    .line 502
    .restart local v3    # "message_got_receipt_from_server":I
    const v5, 0x7f021035

    .line 503
    .restart local v5    # "message_got_receipt_from_target":I
    const v1, 0x7f021036

    .line 504
    .restart local v1    # "message_got_read_receipt_from_target":I
    const v8, 0x7f021037

    .line 505
    .restart local v8    # "message_unsent_onmedia":I
    const v4, 0x7f021038

    .line 506
    .restart local v4    # "message_got_receipt_from_server_onmedia":I
    const v6, 0x7f021039

    .line 507
    .restart local v6    # "message_got_receipt_from_target_onmedia":I
    const v2, 0x7f02103a

    .line 508
    .restart local v2    # "message_got_read_receipt_from_target_onmedia":I
    goto/16 :goto_2d

    .line 510
    .end local v1    # "message_got_read_receipt_from_target":I
    .end local v2    # "message_got_read_receipt_from_target_onmedia":I
    .end local v3    # "message_got_receipt_from_server":I
    .end local v4    # "message_got_receipt_from_server_onmedia":I
    .end local v5    # "message_got_receipt_from_target":I
    .end local v6    # "message_got_receipt_from_target_onmedia":I
    .end local v7    # "message_unsent":I
    .end local v8    # "message_unsent_onmedia":I
    :pswitch_cb
    const v7, 0x7f02103b

    .line 511
    .restart local v7    # "message_unsent":I
    const v3, 0x7f02103c

    .line 512
    .restart local v3    # "message_got_receipt_from_server":I
    const v5, 0x7f02103d

    .line 513
    .restart local v5    # "message_got_receipt_from_target":I
    const v1, 0x7f02103e

    .line 514
    .restart local v1    # "message_got_read_receipt_from_target":I
    const v8, 0x7f02103f

    .line 515
    .restart local v8    # "message_unsent_onmedia":I
    const v4, 0x7f021040

    .line 516
    .restart local v4    # "message_got_receipt_from_server_onmedia":I
    const v6, 0x7f021041

    .line 517
    .restart local v6    # "message_got_receipt_from_target_onmedia":I
    const v2, 0x7f021042

    .line 518
    .restart local v2    # "message_got_read_receipt_from_target_onmedia":I
    goto/16 :goto_2d

    .line 520
    .end local v1    # "message_got_read_receipt_from_target":I
    .end local v2    # "message_got_read_receipt_from_target_onmedia":I
    .end local v3    # "message_got_receipt_from_server":I
    .end local v4    # "message_got_receipt_from_server_onmedia":I
    .end local v5    # "message_got_receipt_from_target":I
    .end local v6    # "message_got_receipt_from_target_onmedia":I
    .end local v7    # "message_unsent":I
    .end local v8    # "message_unsent_onmedia":I
    :pswitch_e5
    const v7, 0x7f021043

    .line 521
    .restart local v7    # "message_unsent":I
    const v3, 0x7f021044

    .line 522
    .restart local v3    # "message_got_receipt_from_server":I
    const v5, 0x7f021045

    .line 523
    .restart local v5    # "message_got_receipt_from_target":I
    const v1, 0x7f021046

    .line 524
    .restart local v1    # "message_got_read_receipt_from_target":I
    const v8, 0x7f021047

    .line 525
    .restart local v8    # "message_unsent_onmedia":I
    const v4, 0x7f021048

    .line 526
    .restart local v4    # "message_got_receipt_from_server_onmedia":I
    const v6, 0x7f021049

    .line 527
    .restart local v6    # "message_got_receipt_from_target_onmedia":I
    const v2, 0x7f02104a

    .line 528
    .restart local v2    # "message_got_read_receipt_from_target_onmedia":I
    goto/16 :goto_2d

    :pswitch_ff
    move v7, v3

    .line 545
    goto/16 :goto_30

    :pswitch_102
    move v7, v5

    .line 547
    goto/16 :goto_30

    :pswitch_105
    move v7, v1

    .line 549
    goto/16 :goto_30

    :pswitch_108
    move v7, v8

    .line 551
    goto/16 :goto_30

    :pswitch_10b
    move v7, v4

    .line 553
    goto/16 :goto_30

    :pswitch_10e
    move v7, v6

    .line 555
    goto/16 :goto_30

    :pswitch_111
    move v7, v2

    .line 557
    goto/16 :goto_30

    .line 468
    :pswitch_data_114
    .packed-switch 0x30
        :pswitch_31
        :pswitch_3b
        :pswitch_45
        :pswitch_4f
        :pswitch_59
        :pswitch_63
    .end packed-switch

    :pswitch_data_124
    .packed-switch 0x0
        :pswitch_6d
        :pswitch_7e
        :pswitch_97
        :pswitch_b1
        :pswitch_cb
        :pswitch_e5
    .end packed-switch

    .line 541
    :pswitch_data_134
    .packed-switch 0x0
        :pswitch_30
        :pswitch_ff
        :pswitch_102
        :pswitch_105
        :pswitch_108
        :pswitch_10b
        :pswitch_10e
        :pswitch_111
    .end packed-switch
.end method

.method public static getUIColor(I)I
    .registers 6
    .param p0, "color"    # I

    .prologue
    .line 281
    const/4 v1, 0x0

    .line 282
    .local v1, "value":Ljava/lang/String;
    packed-switch p0, :pswitch_data_34

    .line 299
    :goto_4
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "#"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    sget-object v3, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v4, "ffffff"

    invoke-interface {v3, v1, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    .line 300
    .local v0, "colorint":I
    return v0

    .line 284
    .end local v0    # "colorint":I
    :pswitch_24
    const-string v1, "general_statusbarcolor"

    .line 285
    goto :goto_4

    .line 287
    :pswitch_27
    const-string v1, "general_toolbarcolor"

    .line 288
    goto :goto_4

    .line 290
    :pswitch_2a
    const-string v1, "general_navbarcolor"

    .line 291
    goto :goto_4

    .line 293
    :pswitch_2d
    const-string v1, "general_toolbarforeground"

    .line 294
    goto :goto_4

    .line 296
    :pswitch_30
    const-string v1, "general_toolbartextcolor"

    goto :goto_4

    .line 282
    nop

    :pswitch_data_34
    .packed-switch 0x0
        :pswitch_24
        :pswitch_27
        :pswitch_2a
        :pswitch_2d
        :pswitch_30
    .end packed-switch
.end method

.method public static getUserName(Landroid/content/Context;)Ljava/lang/String;
    .registers 4
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 826
    const-string v1, "com.whatsapp_preferences"

    const/4 v2, 0x0

    invoke-virtual {p0, v1, v2}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    .line 827
    .local v0, "prefs":Landroid/content/SharedPreferences;
    const-string v1, "push_name"

    const-string v2, ""

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method

.method public static getUserPhoneNumber(Landroid/content/Context;)Ljava/lang/String;
    .registers 7
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 831
    const-string v4, "RegisterPhone"

    const/4 v5, 0x0

    invoke-virtual {p0, v4, v5}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v3

    .line 832
    .local v3, "prefs":Landroid/content/SharedPreferences;
    const-string v4, "com.whatsapp.RegisterPhone.input_phone_number"

    const-string v5, ""

    invoke-interface {v3, v4, v5}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 833
    .local v2, "number":Ljava/lang/String;
    const-string v4, "com.whatsapp.RegisterPhone.country_code"

    const-string v5, ""

    invoke-interface {v3, v4, v5}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 834
    .local v0, "country":Ljava/lang/String;
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "+"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, " "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 835
    .local v1, "entireNumber":Ljava/lang/String;
    return-object v1
.end method

.method public static getUserPicture(Landroid/content/Context;)Landroid/graphics/drawable/Drawable;
    .registers 6
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 839
    invoke-static {p0}, Lcom/wamod/utils;->getApplicationPath(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v2

    .line 840
    .local v2, "s":Ljava/lang/String;
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/files/me.jpg"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    .line 841
    .local v1, "pathName":Ljava/lang/String;
    invoke-static {v1}, Landroid/graphics/drawable/Drawable;->createFromPath(Ljava/lang/String;)Landroid/graphics/drawable/Drawable;

    move-result-object v0

    .line 842
    .local v0, "d":Landroid/graphics/drawable/Drawable;
    return-object v0
.end method

.method public static getVersionCode()I
    .registers 1

    .prologue
    .line 1051
    sget v0, Lcom/wamod/utils;->wamodVersionCode:I

    return v0
.end method

.method public static getVersionName()Ljava/lang/String;
    .registers 1

    .prologue
    .line 1047
    sget-object v0, Lcom/wamod/utils;->wamodVersionName:Ljava/lang/String;

    return-object v0
.end method

.method public static getb9()[B
    .registers 3

    .prologue
    .line 821
    const-string v1, "HQ3bHbhJnKQdh+B/qpAHhQ=="

    const/4 v2, 0x0

    invoke-static {v1, v2}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object v0

    .line 822
    .local v0, "official":[B
    return-object v0
.end method

.method public static initWAMOD()V
    .registers 7

    .prologue
    const/4 v6, 0x1

    const/4 v5, 0x0

    .line 317
    sget-object v2, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v3, "wamodversion"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v2

    packed-switch v2, :pswitch_data_310

    .line 421
    :goto_d
    :pswitch_d
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "wamodversion"

    sget v4, Lcom/wamod/utils;->wamodVersionCode:I

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    .line 422
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v2}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 424
    sget-boolean v2, Lcom/wamod/utils;->debug:Z

    if-nez v2, :cond_4f

    .line 426
    :try_start_1f
    sget-object v2, Lcom/wamod/utils;->context:Landroid/content/Context;

    invoke-virtual {v2}, Landroid/content/Context;->getPackageManager()Landroid/content/pm/PackageManager;

    move-result-object v2

    sget-object v3, Lcom/wamod/utils;->context:Landroid/content/Context;

    invoke-virtual {v3}, Landroid/content/Context;->getPackageName()Ljava/lang/String;

    move-result-object v3

    const/16 v4, 0x40

    invoke-virtual {v2, v3, v4}, Landroid/content/pm/PackageManager;->getPackageInfo(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;

    move-result-object v2

    iget-object v2, v2, Landroid/content/pm/PackageInfo;->signatures:[Landroid/content/pm/Signature;

    const/4 v3, 0x0

    aget-object v1, v2, v3

    .line 427
    .local v1, "sign":Landroid/content/pm/Signature;
    invoke-virtual {v1}, Landroid/content/pm/Signature;->hashCode()I

    move-result v2

    const v3, -0x10da1b66

    if-ne v2, v3, :cond_4f

    sget-object v2, Lcom/wamod/utils;->context:Landroid/content/Context;

    const/4 v3, 0x1

    new-array v3, v3, [Lio/fabric/sdk/android/Kit;

    const/4 v4, 0x0

    new-instance v5, Lcom/crashlytics/android/Crashlytics;

    invoke-direct {v5}, Lcom/crashlytics/android/Crashlytics;-><init>()V

    aput-object v5, v3, v4

    invoke-static {v2, v3}, Lio/fabric/sdk/android/Fabric;->with(Landroid/content/Context;[Lio/fabric/sdk/android/Kit;)Lio/fabric/sdk/android/Fabric;
    :try_end_4f
    .catch Landroid/content/pm/PackageManager$NameNotFoundException; {:try_start_1f .. :try_end_4f} :catch_309

    .line 432
    :cond_4f
    return-void

    .line 319
    .end local v1    # "sign":Landroid/content/pm/Signature;
    :pswitch_50
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "general_statusbarcolor"

    const-string v4, "0b8043"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 320
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "general_toolbarcolor"

    const-string v4, "0f9d58"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 321
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "general_toolbarforeground"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 322
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "general_navbarcolor"

    const-string v4, "555555"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 323
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "general_darkmode"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 324
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_theme"

    const-string v4, "1"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 325
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_hideprofilephoto"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 326
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_hidetoolbarattach"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 327
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_proximitysensor"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 328
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_rightbubblecolor"

    const-string v4, "cfd8dc"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 329
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_rightbubbletextcolor"

    const-string v4, "263238"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 330
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_rightbubbledatecolor"

    const-string v4, "263238"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 331
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_leftbubblecolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 332
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_leftbubbletextcolor"

    const-string v4, "263238"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 333
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_leftbubbledatecolor"

    const-string v4, "263238"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 334
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_customparticipantcolorbool"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 335
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_customparticipantcolor"

    const-string v4, "000000"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 336
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_style_bubble"

    const-string v4, "7"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 337
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_style_tick"

    const-string v4, "1"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 339
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "privacy_freezelastseen"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 340
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "privacy_no2ndTick"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 341
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "privacy_noBlueTick"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 342
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "privacy_hideTyping"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 343
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "privacy_alwaysOnline"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 345
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_wamod_conversation_entry_bgcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 346
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_wamod_conversation_entry_entrybgcolor"

    const-string v4, "cfd8dc"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 347
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_wamod_conversation_entry_hinttextcolor"

    const-string v4, "263238"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 348
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_wamod_conversation_entry_textcolor"

    const-string v4, "263238"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 349
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_wamod_conversation_entry_emojibtncolor"

    const-string v4, "263238"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 350
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_wamod_conversation_entry_btncolor"

    const-string v4, "263238"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 351
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_wamod_conversation_entry_sendbtncolor"

    const-string v4, "263238"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 353
    :pswitch_153
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_hangouts_conversation_entry_bgcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 354
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_hangouts_conversation_entry_hintcolor"

    const-string v4, "607d8b"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 355
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_hangouts_conversation_entry_textcolor"

    const-string v4, "607d8b"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 356
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_hangouts_conversation_attach_color"

    const-string v4, "307d8b"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 357
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_hangouts_conversation_mic_bg"

    const-string v4, "eceff1"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 358
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_hangouts_conversation_mic_color"

    const-string v4, "98aab4"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 359
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_hangouts_conversation_send_bg"

    const-string v4, "0f9d58"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 360
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_hangouts_conversation_send_color"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 362
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "debug_disablecolorpicker"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 364
    :pswitch_1a2
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "overview_cardcolor"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 365
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "overview_multiplechats"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 366
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_style_entry"

    const-string v4, "2"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 367
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_style_bubble_layout"

    const-string v4, "0"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 369
    :pswitch_1c2
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_custombackcolorbool"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 370
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_custombackcolor"

    const-string v4, "eceff1"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 371
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_style_toolbar"

    const-string v4, "2"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 372
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_toolbarexit"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 374
    :pswitch_1e2
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "general_darkstatusbaricons"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 376
    :pswitch_1e9
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "wamodthemes_constantlycheck"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 378
    :pswitch_1f0
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "conversation_androidgallery"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 380
    :pswitch_1f7
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_aran_conversation_bgcolor"

    const-string v4, "000000"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 381
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_aran_conversation_entry_bgcolor"

    const-string v4, "222222"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 382
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_aran_conversation_entry_hintcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 383
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_aran_conversation_entry_textcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 384
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_aran_conversation_mic_color"

    const-string v4, "ee5555"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 385
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_aran_conversation_send_color"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 386
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_aran_conversation_emoji_color"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 388
    :pswitch_236
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_simple_conversation_bgcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 389
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_simple_conversation_entry_hintcolor"

    const-string v4, "606060"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 390
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_simple_conversation_entry_textcolor"

    const-string v4, "2a2a2a"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 391
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_simple_conversation_mic_color"

    const-string v4, "606060"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 392
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_simple_conversation_send_color"

    const-string v4, "606060"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 393
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_simple_conversation_entry_textsize"

    const/high16 v4, 0x41a00000    # 20.0f

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putFloat(Ljava/lang/String;F)Landroid/content/SharedPreferences$Editor;

    .line 395
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_mood_conversation_background_color"

    const-string v4, "55ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 396
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_mood_conversation_entry_hintcolor"

    const-string v4, "000000"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 397
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_mood_conversation_entry_textcolor"

    const-string v4, "000000"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 398
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_mood_conversation_mic_color"

    const-string v4, "000000"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 399
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_mood_conversation_send_color"

    const-string v4, "000000"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 400
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "theme_mood_conversation_emoji_color"

    const-string v4, "000000"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 402
    :pswitch_2a2
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_tabsindicatorcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 404
    :pswitch_2ab
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "privacy_no2ndTick"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 405
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_drawer_blackheadertext"

    invoke-interface {v2, v3, v5}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 406
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_drawer_dark"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 408
    :pswitch_2c0
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "general_toolbartextcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 410
    :pswitch_2c9
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_bottomnavigationbar"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 411
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_bottomnavigationbar_autocolor"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 412
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_bottomnavigationbar_colors_bg"

    const-string v4, "555555"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 413
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_bottomnavigationbar_colors_activeitem"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 414
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_bottomnavigationbar_colors_inactiveitem"

    const-string v4, "888888"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 416
    :pswitch_2f2
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_bottomnavigationbar"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 417
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_bottomnavigationbar_autocolor"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    .line 418
    sget-object v2, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v3, "home_drawer_showsecondaccount"

    invoke-interface {v2, v3, v6}, Landroid/content/SharedPreferences$Editor;->putBoolean(Ljava/lang/String;Z)Landroid/content/SharedPreferences$Editor;

    goto/16 :goto_d

    .line 428
    .restart local v1    # "sign":Landroid/content/pm/Signature;
    :catch_309
    move-exception v0

    .line 429
    .local v0, "e":Landroid/content/pm/PackageManager$NameNotFoundException;
    new-instance v2, Ljava/lang/RuntimeException;

    invoke-direct {v2, v0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v2

    .line 317
    :pswitch_data_310
    .packed-switch 0x0
        :pswitch_50
        :pswitch_153
        :pswitch_1a2
        :pswitch_1c2
        :pswitch_1e2
        :pswitch_1e9
        :pswitch_1f0
        :pswitch_1f7
        :pswitch_236
        :pswitch_2a2
        :pswitch_d
        :pswitch_d
        :pswitch_2ab
        :pswitch_2c0
        :pswitch_d
        :pswitch_d
        :pswitch_d
        :pswitch_d
        :pswitch_d
        :pswitch_d
        :pswitch_d
        :pswitch_d
        :pswitch_d
        :pswitch_2c9
        :pswitch_2f2
    .end packed-switch
.end method

.method public static initWAMODfromHome(Landroid/support/v7/app/AppCompatActivity;)V
    .registers 4
    .param p0, "a"    # Landroid/support/v7/app/AppCompatActivity;

    .prologue
    .line 435
    invoke-static {}, Lcom/wamod/utils;->initWAMOD()V

    .line 438
    new-instance v0, Lcom/wamod/checkinv2;

    invoke-direct {v0}, Lcom/wamod/checkinv2;-><init>()V

    const/4 v1, 0x1

    new-array v1, v1, [Landroid/support/v7/app/AppCompatActivity;

    const/4 v2, 0x0

    aput-object p0, v1, v2

    invoke-virtual {v0, v1}, Lcom/wamod/checkinv2;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    .line 439
    return-void
.end method

.method public static loadColors(Landroid/support/v7/app/ActionBar;Landroid/view/Window;)V
    .registers 7
    .param p0, "actionBar"    # Landroid/support/v7/app/ActionBar;
    .param p1, "window"    # Landroid/view/Window;

    .prologue
    .line 100
    new-instance v0, Landroid/graphics/drawable/ColorDrawable;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "#"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget-object v2, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v3, "general_toolbarcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    invoke-direct {v0, v1}, Landroid/graphics/drawable/ColorDrawable;-><init>(I)V

    invoke-virtual {p0, v0}, Landroid/support/v7/app/ActionBar;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 101
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x15

    if-lt v0, v1, :cond_77

    .line 102
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "#"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "general_statusbarcolor"

    const-string v3, "ffffff"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/Window;->setStatusBarColor(I)V

    .line 103
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "#"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "general_navbarcolor"

    const-string v3, "ffffff"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/Window;->setNavigationBarColor(I)V

    .line 105
    :cond_77
    return-void
.end method

.method public static loadColors(Landroid/support/v7/app/AppCompatActivity;)V
    .registers 7
    .param p0, "activity"    # Landroid/support/v7/app/AppCompatActivity;

    .prologue
    .line 121
    invoke-virtual {p0}, Landroid/support/v7/app/AppCompatActivity;->getSupportActionBar()Landroid/support/v7/app/ActionBar;

    move-result-object v0

    new-instance v1, Landroid/graphics/drawable/ColorDrawable;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "#"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    sget-object v3, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v4, "general_toolbarcolor"

    const-string v5, "ffffff"

    invoke-interface {v3, v4, v5}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v2

    invoke-direct {v1, v2}, Landroid/graphics/drawable/ColorDrawable;-><init>(I)V

    invoke-virtual {v0, v1}, Landroid/support/v7/app/ActionBar;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 122
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x15

    if-lt v0, v1, :cond_83

    .line 123
    invoke-virtual {p0}, Landroid/support/v7/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "#"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget-object v2, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v3, "general_statusbarcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/view/Window;->setStatusBarColor(I)V

    .line 124
    invoke-virtual {p0}, Landroid/support/v7/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v0

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "#"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    sget-object v2, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v3, "general_navbarcolor"

    const-string v4, "ffffff"

    invoke-interface {v2, v3, v4}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/view/Window;->setNavigationBarColor(I)V

    .line 127
    :cond_83
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x17

    if-lt v0, v1, :cond_a0

    sget-object v0, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v1, "general_darkstatusbaricons"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_a0

    .line 128
    const v0, 0x1020002

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    const/16 v1, 0x2000

    invoke-virtual {v0, v1}, Landroid/view/View;->setSystemUiVisibility(I)V

    .line 130
    :cond_a0
    return-void
.end method

.method public static loadColors(Landroid/support/v7/widget/Toolbar;Landroid/view/Window;)V
    .registers 6
    .param p0, "toolbar"    # Landroid/support/v7/widget/Toolbar;
    .param p1, "window"    # Landroid/view/Window;

    .prologue
    .line 108
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "#"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "general_toolbarforeground"

    const-string v3, "ffffff"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Landroid/support/v7/widget/Toolbar;->setTitleTextColor(I)V

    .line 109
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "#"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "general_toolbarcolor"

    const-string v3, "ffffff"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Landroid/support/v7/widget/Toolbar;->setBackgroundColor(I)V

    .line 110
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x15

    if-lt v0, v1, :cond_96

    .line 111
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "#"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "general_statusbarcolor"

    const-string v3, "ffffff"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/Window;->setStatusBarColor(I)V

    .line 112
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "#"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "general_navbarcolor"

    const-string v3, "ffffff"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p1, v0}, Landroid/view/Window;->setNavigationBarColor(I)V

    .line 115
    :cond_96
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x17

    if-lt v0, v1, :cond_ac

    sget-object v0, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v1, "general_darkstatusbaricons"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_ac

    .line 116
    const/16 v0, 0x2000

    invoke-virtual {p0, v0}, Landroid/support/v7/widget/Toolbar;->setSystemUiVisibility(I)V

    .line 118
    :cond_ac
    return-void
.end method

.method public static loadColorsBeforeSuper(Landroid/preference/PreferenceActivity;)V
    .registers 4
    .param p0, "a"    # Landroid/preference/PreferenceActivity;

    .prologue
    .line 1104
    const-string v0, "WAMOD"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Loaded activity: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 1105
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_2c

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme:I

    invoke-virtual {p0, v0}, Landroid/preference/PreferenceActivity;->setTheme(I)V

    .line 1107
    :goto_2b
    return-void

    .line 1106
    :cond_2c
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Day:I

    invoke-virtual {p0, v0}, Landroid/preference/PreferenceActivity;->setTheme(I)V

    goto :goto_2b
.end method

.method public static loadColorsBeforeSuper(Landroid/support/v7/app/AppCompatActivity;)V
    .registers 4
    .param p0, "a"    # Landroid/support/v7/app/AppCompatActivity;

    .prologue
    .line 1063
    const-string v0, "WAMOD"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Loaded activity: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 1064
    instance-of v0, p0, Lcom/wamod/WAMODSettings;

    if-eqz v0, :cond_36

    .line 1065
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_30

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Settings:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    .line 1101
    :cond_2f
    :goto_2f
    return-void

    .line 1066
    :cond_30
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Settings_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1067
    :cond_36
    instance-of v0, p0, Lcom/whatsapp/HomeActivity;

    if-eqz v0, :cond_4c

    .line 1068
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_46

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1069
    :cond_46
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1070
    :cond_4c
    instance-of v0, p0, Lcom/whatsapp/StarredMessagesActivity;

    if-eqz v0, :cond_62

    .line 1071
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_5c

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1072
    :cond_5c
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1073
    :cond_62
    instance-of v0, p0, Lcom/whatsapp/VoipActivity;

    if-eqz v0, :cond_78

    .line 1074
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_72

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1075
    :cond_72
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1076
    :cond_78
    instance-of v0, p0, Lcom/whatsapp/QuickContactActivity;

    if-eqz v0, :cond_8e

    .line 1077
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_88

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1078
    :cond_88
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1079
    :cond_8e
    instance-of v0, p0, Lcom/whatsapp/wallpaper/SolidColorWallpaper;

    if-eqz v0, :cond_a4

    .line 1080
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_9e

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1081
    :cond_9e
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto :goto_2f

    .line 1082
    :cond_a4
    instance-of v0, p0, Lcom/whatsapp/wallpaper/SolidColorWallpaperPreview;

    if-eqz v0, :cond_bc

    .line 1083
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_b5

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f

    .line 1084
    :cond_b5
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Home_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f

    .line 1085
    :cond_bc
    instance-of v0, p0, Lcom/whatsapp/Conversation;

    if-eqz v0, :cond_d4

    .line 1086
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_cd

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Conversation:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f

    .line 1087
    :cond_cd
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Conversation_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f

    .line 1088
    :cond_d4
    instance-of v0, p0, Lcom/whatsapp/ContactInfo;

    if-eqz v0, :cond_ec

    .line 1089
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_e5

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Conversation:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f

    .line 1090
    :cond_e5
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Conversation_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f

    .line 1091
    :cond_ec
    instance-of v0, p0, Lcom/whatsapp/GroupChatInfo;

    if-eqz v0, :cond_104

    .line 1092
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_fd

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Conversation:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f

    .line 1093
    :cond_fd
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Conversation_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f

    .line 1094
    :cond_104
    instance-of v0, p0, Lcom/whatsapp/MediaGallery;

    if-eqz v0, :cond_2f

    .line 1095
    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v0

    if-eqz v0, :cond_115

    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Conversation:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f

    .line 1096
    :cond_115
    sget v0, Lcom/wamod/Resources$style;->WAMOD_Theme_Conversation_Day:I

    invoke-virtual {p0, v0}, Landroid/support/v7/app/AppCompatActivity;->setTheme(I)V

    goto/16 :goto_2f
.end method

.method public static loadColorsV2(Landroid/preference/PreferenceActivity;)V
    .registers 16
    .param p0, "a"    # Landroid/preference/PreferenceActivity;

    .prologue
    const/4 v14, 0x2

    const/4 v13, 0x1

    .line 238
    :try_start_2
    invoke-virtual {p0}, Landroid/preference/PreferenceActivity;->getActionBar()Landroid/app/ActionBar;

    move-result-object v1

    .line 239
    .local v1, "actionbar":Landroid/app/ActionBar;
    if-eqz v1, :cond_5b

    .line 240
    new-instance v9, Landroid/graphics/drawable/ColorDrawable;

    const/4 v10, 0x1

    invoke-static {v10}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v10

    invoke-direct {v9, v10}, Landroid/graphics/drawable/ColorDrawable;-><init>(I)V

    invoke-virtual {v1, v9}, Landroid/app/ActionBar;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 241
    invoke-virtual {p0}, Landroid/preference/PreferenceActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v9

    const-string v10, "action_bar"

    const-string v11, "id"

    invoke-virtual {p0}, Landroid/preference/PreferenceActivity;->getPackageName()Ljava/lang/String;

    move-result-object v12

    invoke-virtual {v9, v10, v11, v12}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v3

    .line 242
    .local v3, "actionbarid":I
    invoke-virtual {p0, v3}, Landroid/preference/PreferenceActivity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/view/ViewGroup;

    .line 243
    .local v2, "actionbarVG":Landroid/view/ViewGroup;
    if-eqz v2, :cond_5b

    .line 244
    const/4 v6, 0x0

    .local v6, "i":I
    :goto_2e
    invoke-virtual {v2}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v9

    if-ge v6, v9, :cond_5b

    .line 245
    invoke-virtual {v2, v6}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    .line 246
    .local v4, "child":Landroid/view/View;
    instance-of v9, v4, Landroid/widget/TextView;

    if-eqz v9, :cond_48

    .line 247
    move-object v0, v4

    check-cast v0, Landroid/widget/TextView;

    move-object v9, v0

    const/4 v10, 0x4

    invoke-static {v10}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v10

    invoke-virtual {v9, v10}, Landroid/widget/TextView;->setTextColor(I)V

    .line 248
    :cond_48
    instance-of v9, v4, Landroid/widget/ImageButton;

    if-eqz v9, :cond_58

    .line 249
    check-cast v4, Landroid/widget/ImageButton;

    .end local v4    # "child":Landroid/view/View;
    const/4 v9, 0x3

    invoke-static {v9}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v9

    sget-object v10, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {v4, v9, v10}, Landroid/widget/ImageButton;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    .line 244
    :cond_58
    add-int/lit8 v6, v6, 0x1

    goto :goto_2e

    .line 254
    .end local v2    # "actionbarVG":Landroid/view/ViewGroup;
    .end local v3    # "actionbarid":I
    .end local v6    # "i":I
    :cond_5b
    sget v9, Lcom/wamod/Resources$id;->toolbar:I

    invoke-virtual {p0, v9}, Landroid/preference/PreferenceActivity;->findViewById(I)Landroid/view/View;

    move-result-object v8

    check-cast v8, Landroid/support/v7/widget/Toolbar;

    .line 255
    .local v8, "toolbar":Landroid/support/v7/widget/Toolbar;
    if-eqz v8, :cond_75

    .line 256
    const/4 v9, 0x1

    invoke-static {v9}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v9

    invoke-virtual {v8, v9}, Landroid/support/v7/widget/Toolbar;->setBackgroundColor(I)V

    .line 257
    const/4 v9, 0x4

    invoke-static {v9}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v9

    invoke-virtual {v8, v9}, Landroid/support/v7/widget/Toolbar;->setTitleTextColor(I)V

    .line 260
    :cond_75
    sget v9, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v10, 0x15

    if-lt v9, v10, :cond_aa

    .line 261
    invoke-virtual {p0}, Landroid/preference/PreferenceActivity;->getWindow()Landroid/view/Window;

    move-result-object v9

    const/4 v10, 0x0

    invoke-static {v10}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v10

    invoke-virtual {v9, v10}, Landroid/view/Window;->setStatusBarColor(I)V

    .line 262
    invoke-virtual {p0}, Landroid/preference/PreferenceActivity;->getWindow()Landroid/view/Window;

    move-result-object v9

    const/4 v10, 0x2

    invoke-static {v10}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v10

    invoke-virtual {v9, v10}, Landroid/view/Window;->setNavigationBarColor(I)V

    .line 263
    sget-object v9, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v10, "general_darkstatusbaricons"

    const/4 v11, 0x0

    invoke-interface {v9, v10, v11}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v9

    if-eqz v9, :cond_c7

    .line 264
    const v9, 0x1020002

    invoke-virtual {p0, v9}, Landroid/preference/PreferenceActivity;->findViewById(I)Landroid/view/View;

    move-result-object v9

    const/16 v10, 0x2000

    invoke-virtual {v9, v10}, Landroid/view/View;->setSystemUiVisibility(I)V
    :try_end_aa
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_aa} :catch_d3

    .line 272
    .end local v1    # "actionbar":Landroid/app/ActionBar;
    .end local v8    # "toolbar":Landroid/support/v7/widget/Toolbar;
    :cond_aa
    :goto_aa
    instance-of v9, p0, Lcom/whatsapp/DialogToastPreferenceActivity;

    if-eqz v9, :cond_c6

    .line 273
    const v9, 0x102000a

    invoke-virtual {p0, v9}, Landroid/preference/PreferenceActivity;->findViewById(I)Landroid/view/View;

    move-result-object v7

    check-cast v7, Landroid/widget/ListView;

    .line 274
    .local v7, "list":Landroid/widget/ListView;
    if-eqz v7, :cond_c6

    invoke-static {}, Lcom/wamod/utils;->nightModeShouldRun()Z

    move-result v9

    if-eqz v9, :cond_c6

    .line 275
    invoke-static {v14}, Lcom/wamod/utils;->getDarkColor(I)I

    move-result v9

    invoke-virtual {v7, v9}, Landroid/widget/ListView;->setBackgroundColor(I)V

    .line 278
    .end local v7    # "list":Landroid/widget/ListView;
    :cond_c6
    return-void

    .line 266
    .restart local v1    # "actionbar":Landroid/app/ActionBar;
    .restart local v8    # "toolbar":Landroid/support/v7/widget/Toolbar;
    :cond_c7
    const v9, 0x1020002

    :try_start_ca
    invoke-virtual {p0, v9}, Landroid/preference/PreferenceActivity;->findViewById(I)Landroid/view/View;

    move-result-object v9

    const/4 v10, 0x0

    invoke-virtual {v9, v10}, Landroid/view/View;->setSystemUiVisibility(I)V
    :try_end_d2
    .catch Ljava/lang/Exception; {:try_start_ca .. :try_end_d2} :catch_d3

    goto :goto_aa

    .line 268
    .end local v1    # "actionbar":Landroid/app/ActionBar;
    .end local v8    # "toolbar":Landroid/support/v7/widget/Toolbar;
    :catch_d3
    move-exception v5

    .line 269
    .local v5, "e":Ljava/lang/Exception;
    sget-object v9, Lcom/wamod/utils;->context:Landroid/content/Context;

    invoke-virtual {v5}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v10

    invoke-static {v9, v10, v13}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v9

    invoke-virtual {v9}, Landroid/widget/Toast;->show()V

    goto :goto_aa
.end method

.method public static loadColorsV2(Landroid/support/v7/app/AppCompatActivity;)V
    .registers 17
    .param p0, "a"    # Landroid/support/v7/app/AppCompatActivity;

    .prologue
    const/4 v5, 0x1

    const/4 v12, 0x0

    .line 136
    :try_start_2
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/app/AppCompatActivity;->getSupportActionBar()Landroid/support/v7/app/ActionBar;

    move-result-object v1

    .line 137
    .local v1, "actionbar":Landroid/support/v7/app/ActionBar;
    move-object/from16 v0, p0

    instance-of v13, v0, Lcom/whatsapp/ChatInfoActivity;

    if-nez v13, :cond_77

    move-object/from16 v0, p0

    instance-of v13, v0, Lcom/whatsapp/MediaView;

    if-nez v13, :cond_77

    move-object/from16 v0, p0

    instance-of v13, v0, Lcom/whatsapp/ViewProfilePhoto;

    if-nez v13, :cond_77

    move-object/from16 v0, p0

    instance-of v13, v0, Lcom/whatsapp/QuickContactActivity;

    if-nez v13, :cond_77

    .line 141
    .local v5, "coloredToolbarColor":Z
    :goto_1e
    if-eqz v1, :cond_79

    if-eqz v5, :cond_79

    .line 142
    new-instance v12, Landroid/graphics/drawable/ColorDrawable;

    const/4 v13, 0x1

    invoke-static {v13}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v13

    invoke-direct {v12, v13}, Landroid/graphics/drawable/ColorDrawable;-><init>(I)V

    invoke-virtual {v1, v12}, Landroid/support/v7/app/ActionBar;->setBackgroundDrawable(Landroid/graphics/drawable/Drawable;)V

    .line 143
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v12

    const-string v13, "action_bar"

    const-string v14, "id"

    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/app/AppCompatActivity;->getPackageName()Ljava/lang/String;

    move-result-object v15

    invoke-virtual {v12, v13, v14, v15}, Landroid/content/res/Resources;->getIdentifier(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I

    move-result v3

    .line 144
    .local v3, "actionbarid":I
    move-object/from16 v0, p0

    invoke-virtual {v0, v3}, Landroid/support/v7/app/AppCompatActivity;->findViewById(I)Landroid/view/View;

    move-result-object v2

    check-cast v2, Landroid/view/ViewGroup;

    .line 145
    .local v2, "actionbarVG":Landroid/view/ViewGroup;
    if-eqz v2, :cond_79

    .line 146
    const/4 v9, 0x0

    .local v9, "i":I
    :goto_4a
    invoke-virtual {v2}, Landroid/view/ViewGroup;->getChildCount()I

    move-result v12

    if-ge v9, v12, :cond_79

    .line 147
    invoke-virtual {v2, v9}, Landroid/view/ViewGroup;->getChildAt(I)Landroid/view/View;

    move-result-object v4

    .line 148
    .local v4, "child":Landroid/view/View;
    instance-of v12, v4, Landroid/widget/TextView;

    if-eqz v12, :cond_64

    move-object v0, v4

    check-cast v0, Landroid/widget/TextView;

    move-object v12, v0

    const/4 v13, 0x4

    invoke-static {v13}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v13

    invoke-virtual {v12, v13}, Landroid/widget/TextView;->setTextColor(I)V

    .line 149
    :cond_64
    instance-of v12, v4, Landroid/widget/ImageButton;

    if-eqz v12, :cond_74

    check-cast v4, Landroid/widget/ImageButton;

    .end local v4    # "child":Landroid/view/View;
    const/4 v12, 0x3

    invoke-static {v12}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v12

    sget-object v13, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {v4, v12, v13}, Landroid/widget/ImageButton;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    .line 146
    :cond_74
    add-int/lit8 v9, v9, 0x1

    goto :goto_4a

    .end local v2    # "actionbarVG":Landroid/view/ViewGroup;
    .end local v3    # "actionbarid":I
    .end local v5    # "coloredToolbarColor":Z
    .end local v9    # "i":I
    :cond_77
    move v5, v12

    .line 137
    goto :goto_1e

    .line 154
    .restart local v5    # "coloredToolbarColor":Z
    :cond_79
    sget v12, Lcom/wamod/Resources$id;->toolbar:I

    move-object/from16 v0, p0

    invoke-virtual {v0, v12}, Landroid/support/v7/app/AppCompatActivity;->findViewById(I)Landroid/view/View;

    move-result-object v10

    check-cast v10, Landroid/support/v7/widget/Toolbar;

    .line 155
    .local v10, "toolbar":Landroid/support/v7/widget/Toolbar;
    if-eqz v10, :cond_cd

    if-eqz v5, :cond_cd

    .line 156
    const/4 v12, 0x1

    invoke-static {v12}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v12

    invoke-virtual {v10, v12}, Landroid/support/v7/widget/Toolbar;->setBackgroundColor(I)V

    .line 157
    const/4 v12, 0x4

    invoke-static {v12}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v12

    invoke-virtual {v10, v12}, Landroid/support/v7/widget/Toolbar;->setTitleTextColor(I)V

    .line 159
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/Conversation;

    if-eqz v12, :cond_cd

    .line 160
    sget v12, Lcom/wamod/Resources$id;->up:I

    invoke-virtual {v10, v12}, Landroid/support/v7/widget/Toolbar;->findViewById(I)Landroid/view/View;

    move-result-object v11

    check-cast v11, Landroid/widget/ImageView;

    .line 161
    .local v11, "up":Landroid/widget/ImageView;
    sget v12, Lcom/wamod/Resources$id;->conversation_contact_name:I

    invoke-virtual {v10, v12}, Landroid/support/v7/widget/Toolbar;->findViewById(I)Landroid/view/View;

    move-result-object v6

    check-cast v6, Landroid/widget/TextView;

    .line 162
    .local v6, "conversation_contact_name":Landroid/widget/TextView;
    sget v12, Lcom/wamod/Resources$id;->conversation_contact_status:I

    invoke-virtual {v10, v12}, Landroid/support/v7/widget/Toolbar;->findViewById(I)Landroid/view/View;

    move-result-object v7

    check-cast v7, Landroid/widget/TextView;

    .line 163
    .local v7, "conversation_contact_status":Landroid/widget/TextView;
    const/4 v12, 0x3

    invoke-static {v12}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v12

    invoke-virtual {v11, v12}, Landroid/widget/ImageView;->setColorFilter(I)V

    .line 164
    const/4 v12, 0x4

    invoke-static {v12}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v12

    invoke-virtual {v6, v12}, Landroid/widget/TextView;->setTextColor(I)V

    .line 165
    const/4 v12, 0x4

    invoke-static {v12}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v12

    invoke-virtual {v7, v12}, Landroid/widget/TextView;->setTextColor(I)V

    .line 169
    .end local v6    # "conversation_contact_name":Landroid/widget/TextView;
    .end local v7    # "conversation_contact_status":Landroid/widget/TextView;
    .end local v11    # "up":Landroid/widget/ImageView;
    :cond_cd
    sget v12, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v13, 0x15

    if-lt v12, v13, :cond_148

    .line 170
    if-eqz v5, :cond_e1

    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v12

    const/4 v13, 0x0

    invoke-static {v13}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v13

    invoke-virtual {v12, v13}, Landroid/view/Window;->setStatusBarColor(I)V

    .line 171
    :cond_e1
    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v12

    const/4 v13, 0x2

    invoke-static {v13}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v13

    invoke-virtual {v12, v13}, Landroid/view/Window;->setNavigationBarColor(I)V

    .line 172
    sget-object v12, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v13, "general_darkstatusbaricons"

    const/4 v14, 0x0

    invoke-interface {v12, v13, v14}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v12

    if-eqz v12, :cond_154

    .line 173
    const v12, 0x1020002

    move-object/from16 v0, p0

    invoke-virtual {v0, v12}, Landroid/support/v7/app/AppCompatActivity;->findViewById(I)Landroid/view/View;

    move-result-object v12

    const/16 v13, 0x2000

    invoke-virtual {v12, v13}, Landroid/view/View;->setSystemUiVisibility(I)V

    .line 176
    :goto_106
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/QuickContactActivity;

    if-eqz v12, :cond_114

    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/app/AppCompatActivity;->getWindow()Landroid/view/Window;

    move-result-object v12

    const/4 v13, 0x0

    invoke-virtual {v12, v13}, Landroid/view/Window;->setStatusBarColor(I)V

    .line 178
    :cond_114
    sget-object v12, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v13, "overview_cardcolor"

    const/4 v14, 0x1

    invoke-interface {v12, v13, v14}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v12

    if-eqz v12, :cond_148

    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/Conversation;

    if-nez v12, :cond_148

    .line 179
    new-instance v12, Landroid/app/ActivityManager$TaskDescription;

    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v13

    sget v14, Lcom/wamod/Resources$string;->app_name:I

    invoke-virtual {v13, v14}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v13

    invoke-virtual/range {p0 .. p0}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v14

    sget v15, Lcom/wamod/Resources$drawable;->icon:I

    invoke-static {v14, v15}, Landroid/graphics/BitmapFactory;->decodeResource(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;

    move-result-object v14

    const/4 v15, 0x1

    invoke-static {v15}, Lcom/wamod/utils;->getUIColor(I)I

    move-result v15

    invoke-direct {v12, v13, v14, v15}, Landroid/app/ActivityManager$TaskDescription;-><init>(Ljava/lang/String;Landroid/graphics/Bitmap;I)V

    move-object/from16 v0, p0

    invoke-virtual {v0, v12}, Landroid/support/v7/app/AppCompatActivity;->setTaskDescription(Landroid/app/ActivityManager$TaskDescription;)V
    :try_end_148
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_148} :catch_162

    .line 208
    .end local v1    # "actionbar":Landroid/support/v7/app/ActionBar;
    .end local v5    # "coloredToolbarColor":Z
    .end local v10    # "toolbar":Landroid/support/v7/widget/Toolbar;
    :cond_148
    :goto_148
    :try_start_148
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/HomeActivity;

    if-eqz v12, :cond_167

    .line 209
    check-cast p0, Lcom/whatsapp/HomeActivity;

    .end local p0    # "a":Landroid/support/v7/app/AppCompatActivity;
    invoke-static/range {p0 .. p0}, Lcom/wamod/HomeActivity;->initHomeActivity(Lcom/whatsapp/HomeActivity;)V
    :try_end_153
    .catch Ljava/lang/Exception; {:try_start_148 .. :try_end_153} :catch_173

    .line 234
    :cond_153
    :goto_153
    return-void

    .line 175
    .restart local v1    # "actionbar":Landroid/support/v7/app/ActionBar;
    .restart local v5    # "coloredToolbarColor":Z
    .restart local v10    # "toolbar":Landroid/support/v7/widget/Toolbar;
    .restart local p0    # "a":Landroid/support/v7/app/AppCompatActivity;
    :cond_154
    const v12, 0x1020002

    :try_start_157
    move-object/from16 v0, p0

    invoke-virtual {v0, v12}, Landroid/support/v7/app/AppCompatActivity;->findViewById(I)Landroid/view/View;

    move-result-object v12

    const/4 v13, 0x0

    invoke-virtual {v12, v13}, Landroid/view/View;->setSystemUiVisibility(I)V
    :try_end_161
    .catch Ljava/lang/Exception; {:try_start_157 .. :try_end_161} :catch_162

    goto :goto_106

    .line 182
    .end local v1    # "actionbar":Landroid/support/v7/app/ActionBar;
    .end local v5    # "coloredToolbarColor":Z
    .end local v10    # "toolbar":Landroid/support/v7/widget/Toolbar;
    :catch_162
    move-exception v8

    .line 183
    .local v8, "e":Ljava/lang/Exception;
    invoke-static {v8}, Lcom/wamod/utils;->manageException(Ljava/lang/Exception;)V

    goto :goto_148

    .line 210
    .end local v8    # "e":Ljava/lang/Exception;
    :cond_167
    :try_start_167
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/Conversation;

    if-eqz v12, :cond_178

    .line 211
    check-cast p0, Lcom/whatsapp/Conversation;

    .end local p0    # "a":Landroid/support/v7/app/AppCompatActivity;
    invoke-static/range {p0 .. p0}, Lcom/wamod/Conversation;->initConversation(Lcom/whatsapp/Conversation;)V
    :try_end_172
    .catch Ljava/lang/Exception; {:try_start_167 .. :try_end_172} :catch_173

    goto :goto_153

    .line 229
    .restart local p0    # "a":Landroid/support/v7/app/AppCompatActivity;
    :catch_173
    move-exception v8

    .line 230
    .end local p0    # "a":Landroid/support/v7/app/AppCompatActivity;
    .restart local v8    # "e":Ljava/lang/Exception;
    invoke-static {v8}, Lcom/wamod/utils;->manageException(Ljava/lang/Exception;)V

    goto :goto_153

    .line 212
    .end local v8    # "e":Ljava/lang/Exception;
    .restart local p0    # "a":Landroid/support/v7/app/AppCompatActivity;
    :cond_178
    :try_start_178
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/Settings;

    if-eqz v12, :cond_182

    .line 213
    invoke-static/range {p0 .. p0}, Lcom/wamod/Settings;->_onCreate(Landroid/support/v7/app/AppCompatActivity;)V

    goto :goto_153

    .line 214
    :cond_182
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/ProfileInfoActivity;

    if-eqz v12, :cond_18c

    .line 215
    invoke-static/range {p0 .. p0}, Lcom/wamod/ProfileInfoActivity;->_onCreate(Landroid/support/v7/app/AppCompatActivity;)V

    goto :goto_153

    .line 216
    :cond_18c
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/NewGroup;

    if-eqz v12, :cond_196

    .line 217
    invoke-static/range {p0 .. p0}, Lcom/wamod/NewGroup;->_onCreate(Landroid/support/v7/app/AppCompatActivity;)V

    goto :goto_153

    .line 218
    :cond_196
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/StarredMessagesActivity;

    if-eqz v12, :cond_1a0

    .line 219
    invoke-static/range {p0 .. p0}, Lcom/wamod/StarredMessagesActivity;->_onCreate(Landroid/support/v7/app/AppCompatActivity;)V

    goto :goto_153

    .line 220
    :cond_1a0
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/SetStatus;

    if-eqz v12, :cond_1aa

    .line 221
    invoke-static/range {p0 .. p0}, Lcom/wamod/SetStatus;->_onCreate(Landroid/support/v7/app/AppCompatActivity;)V

    goto :goto_153

    .line 222
    :cond_1aa
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/WebSessionsActivity;

    if-eqz v12, :cond_1b4

    .line 223
    invoke-static/range {p0 .. p0}, Lcom/wamod/WebSessionsActivity;->_onCreate(Landroid/support/v7/app/AppCompatActivity;)V

    goto :goto_153

    .line 224
    :cond_1b4
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/ContactInfo;

    if-eqz v12, :cond_1be

    .line 225
    invoke-static/range {p0 .. p0}, Lcom/wamod/ContactInfo;->_onCreate(Landroid/support/v7/app/AppCompatActivity;)V

    goto :goto_153

    .line 226
    :cond_1be
    move-object/from16 v0, p0

    instance-of v12, v0, Lcom/whatsapp/GroupChatInfo;

    if-eqz v12, :cond_153

    .line 227
    invoke-static/range {p0 .. p0}, Lcom/wamod/GroupChatInfo;->_onCreate(Landroid/support/v7/app/AppCompatActivity;)V
    :try_end_1c7
    .catch Ljava/lang/Exception; {:try_start_178 .. :try_end_1c7} :catch_173

    goto :goto_153
.end method

.method public static log(Landroid/support/v7/app/AppCompatActivity;Ljava/lang/String;)V
    .registers 5
    .param p0, "a"    # Landroid/support/v7/app/AppCompatActivity;
    .param p1, "message"    # Ljava/lang/String;

    .prologue
    .line 707
    sget-object v0, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v1, "log_in_toasts"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    if-eqz v0, :cond_15

    const/16 v0, 0x1f4

    invoke-static {p0, p1, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 709
    :goto_14
    return-void

    .line 708
    :cond_15
    const-string v0, "WAMOD"

    invoke-static {v0, p1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_14
.end method

.method public static log(Ljava/lang/String;)V
    .registers 2
    .param p0, "message"    # Ljava/lang/String;

    .prologue
    .line 712
    const-string v0, "WAMOD"

    invoke-static {v0, p0}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 713
    return-void
.end method

.method public static logByteArray([B[BII)V
    .registers 9
    .param p0, "bytes1"    # [B
    .param p1, "bytes2"    # [B
    .param p2, "int1"    # I
    .param p3, "int2"    # I

    .prologue
    const/4 v3, 0x0

    .line 762
    invoke-static {p0, v3}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v0

    .line 763
    .local v0, "bytesString1":Ljava/lang/String;
    invoke-static {p1, v3}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v1

    .line 764
    .local v1, "bytesString2":Ljava/lang/String;
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Starting: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "\n[SPACE]\n"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "\n"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "\n[SPACE]\n"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "\n"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "[SPACE]\n"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "\n"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "[SPACE]\n"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    .line 769
    .local v2, "message":Ljava/lang/String;
    invoke-static {v2}, Lcom/wamod/utils;->toTxt(Ljava/lang/String;)V

    .line 770
    return-void
.end method

.method public static logItWorks()V
    .registers 2

    .prologue
    .line 1029
    const-string v0, "WAMOD"

    const-string v1, "It works!"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 1030
    return-void
.end method

.method public static logMac(Ljavax/crypto/Mac;)V
    .registers 4
    .param p0, "mac"    # Ljavax/crypto/Mac;

    .prologue
    .line 814
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "Final: "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {p0}, Ljavax/crypto/Mac;->doFinal()[B

    move-result-object v1

    const/4 v2, 0x0

    invoke-static {v1, v2}, Landroid/util/Base64;->encodeToString([BI)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lcom/wamod/utils;->log(Ljava/lang/String;)V

    .line 815
    return-void
.end method

.method public static logSignatures([Landroid/content/pm/Signature;)V
    .registers 8
    .param p0, "sign"    # [Landroid/content/pm/Signature;

    .prologue
    .line 716
    const/4 v2, 0x0

    .local v2, "i":I
    :goto_1
    array-length v5, p0

    if-ge v2, v5, :cond_84

    .line 717
    aget-object v5, p0, v2

    invoke-virtual {v5}, Landroid/content/pm/Signature;->toByteArray()[B

    move-result-object v3

    .line 718
    .local v3, "rawCert":[B
    new-instance v1, Ljava/io/ByteArrayInputStream;

    invoke-direct {v1, v3}, Ljava/io/ByteArrayInputStream;-><init>([B)V

    .line 723
    .local v1, "certStream":Ljava/io/InputStream;
    :try_start_f
    const-string v5, "X509"

    invoke-static {v5}, Ljava/security/cert/CertificateFactory;->getInstance(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;

    move-result-object v0

    .line 724
    .local v0, "certFactory":Ljava/security/cert/CertificateFactory;
    invoke-virtual {v0, v1}, Ljava/security/cert/CertificateFactory;->generateCertificate(Ljava/io/InputStream;)Ljava/security/cert/Certificate;

    move-result-object v4

    check-cast v4, Ljava/security/cert/X509Certificate;

    .line 726
    .local v4, "x509Cert":Ljava/security/cert/X509Certificate;
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Certificate subject: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v4}, Ljava/security/cert/X509Certificate;->getSubjectDN()Ljava/security/Principal;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, "<br>"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Lcom/wamod/utils;->log(Ljava/lang/String;)V

    .line 727
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Certificate issuer: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v4}, Ljava/security/cert/X509Certificate;->getIssuerDN()Ljava/security/Principal;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, "<br>"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Lcom/wamod/utils;->log(Ljava/lang/String;)V

    .line 728
    new-instance v5, Ljava/lang/StringBuilder;

    invoke-direct {v5}, Ljava/lang/StringBuilder;-><init>()V

    const-string v6, "Certificate serial number: "

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v4}, Ljava/security/cert/X509Certificate;->getSerialNumber()Ljava/math/BigInteger;

    move-result-object v6

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v5

    const-string v6, "<br>"

    invoke-virtual {v5, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v5

    invoke-virtual {v5}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v5

    invoke-static {v5}, Lcom/wamod/utils;->log(Ljava/lang/String;)V

    .line 729
    const-string v5, "<br>"

    invoke-static {v5}, Lcom/wamod/utils;->log(Ljava/lang/String;)V
    :try_end_80
    .catch Ljava/security/cert/CertificateException; {:try_start_f .. :try_end_80} :catch_85

    .line 716
    .end local v0    # "certFactory":Ljava/security/cert/CertificateFactory;
    .end local v4    # "x509Cert":Ljava/security/cert/X509Certificate;
    :goto_80
    add-int/lit8 v2, v2, 0x1

    goto/16 :goto_1

    .line 735
    .end local v1    # "certStream":Ljava/io/InputStream;
    .end local v3    # "rawCert":[B
    :cond_84
    return-void

    .line 731
    .restart local v1    # "certStream":Ljava/io/InputStream;
    .restart local v3    # "rawCert":[B
    :catch_85
    move-exception v5

    goto :goto_80
.end method

.method public static manageException(Ljava/lang/Exception;)V
    .registers 4
    .param p0, "e"    # Ljava/lang/Exception;

    .prologue
    .line 1126
    sget-boolean v0, Lcom/wamod/utils;->debug:Z

    if-eqz v0, :cond_a

    new-instance v0, Ljava/lang/RuntimeException;

    invoke-direct {v0, p0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v0

    .line 1127
    :cond_a
    sget-object v0, Lcom/wamod/utils;->context:Landroid/content/Context;

    invoke-virtual {p0}, Ljava/lang/Exception;->getMessage()Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x1

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 1128
    return-void
.end method

.method public static nightModeShouldRun()Z
    .registers 7

    .prologue
    const/4 v4, 0x1

    const/4 v3, 0x0

    .line 442
    sget-object v5, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v6, "nightmode_enable"

    invoke-interface {v5, v6, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v5

    if-nez v5, :cond_d

    .line 448
    .local v0, "cal":Ljava/util/Calendar;
    .local v1, "hour":I
    .local v2, "isNight":Ljava/lang/Boolean;
    :goto_c
    return v3

    .line 443
    .end local v0    # "cal":Ljava/util/Calendar;
    .end local v1    # "hour":I
    .end local v2    # "isNight":Ljava/lang/Boolean;
    :cond_d
    sget-object v5, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v6, "nightmode_atnightonly"

    invoke-interface {v5, v6, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v5

    if-nez v5, :cond_19

    move v3, v4

    goto :goto_c

    .line 445
    :cond_19
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v0

    .line 446
    .restart local v0    # "cal":Ljava/util/Calendar;
    const/16 v5, 0xb

    invoke-virtual {v0, v5}, Ljava/util/Calendar;->get(I)I

    move-result v1

    .line 447
    .restart local v1    # "hour":I
    const/4 v5, 0x6

    if-lt v1, v5, :cond_2a

    const/16 v5, 0x12

    if-le v1, v5, :cond_2b

    :cond_2a
    move v3, v4

    :cond_2b
    invoke-static {v3}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v2

    .line 448
    .restart local v2    # "isNight":Ljava/lang/Boolean;
    invoke-virtual {v2}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v3

    goto :goto_c
.end method

.method public static parseBooleanToJson(Z)Ljava/lang/String;
    .registers 2
    .param p0, "bool"    # Z

    .prologue
    .line 674
    if-eqz p0, :cond_5

    const-string v0, "1"

    .line 675
    :goto_4
    return-object v0

    :cond_5
    const-string v0, "0"

    goto :goto_4
.end method

.method public static parseJsonBoolean(Ljava/lang/String;)Z
    .registers 2
    .param p0, "bool"    # Ljava/lang/String;

    .prologue
    .line 669
    const-string v0, "1"

    invoke-virtual {p0, v0}, Ljava/lang/String;->contentEquals(Ljava/lang/CharSequence;)Z

    move-result v0

    if-eqz v0, :cond_a

    const/4 v0, 0x1

    .line 670
    :goto_9
    return v0

    :cond_a
    const/4 v0, 0x0

    goto :goto_9
.end method

.method public static readStream(Ljava/io/InputStream;)Ljava/lang/String;
    .registers 5
    .param p0, "is"    # Ljava/io/InputStream;

    .prologue
    .line 645
    :try_start_0
    new-instance v0, Ljava/io/ByteArrayOutputStream;

    invoke-direct {v0}, Ljava/io/ByteArrayOutputStream;-><init>()V

    .line 646
    .local v0, "bo":Ljava/io/ByteArrayOutputStream;
    invoke-virtual {p0}, Ljava/io/InputStream;->read()I

    move-result v2

    .line 647
    .local v2, "i":I
    :goto_9
    const/4 v3, -0x1

    if-eq v2, v3, :cond_14

    .line 648
    invoke-virtual {v0, v2}, Ljava/io/ByteArrayOutputStream;->write(I)V

    .line 649
    invoke-virtual {p0}, Ljava/io/InputStream;->read()I

    move-result v2

    goto :goto_9

    .line 651
    :cond_14
    invoke-virtual {v0}, Ljava/io/ByteArrayOutputStream;->toString()Ljava/lang/String;
    :try_end_17
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_17} :catch_19

    move-result-object v3

    .line 653
    .end local v0    # "bo":Ljava/io/ByteArrayOutputStream;
    .end local v2    # "i":I
    :goto_18
    return-object v3

    .line 652
    :catch_19
    move-exception v1

    .line 653
    .local v1, "e":Ljava/io/IOException;
    const-string v3, ""

    goto :goto_18
.end method

.method public static restartWAMOD(Landroid/content/Context;)V
    .registers 9
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 686
    const/4 v2, 0x0

    new-instance v3, Landroid/content/Intent;

    const-class v4, Lcom/wamod/HomeActivity;

    invoke-direct {v3, p0, v4}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const/high16 v4, 0x40000000    # 2.0f

    invoke-static {p0, v2, v3, v4}, Landroid/app/PendingIntent;->getActivity(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;

    move-result-object v0

    .line 687
    .local v0, "intent":Landroid/app/PendingIntent;
    const-string v2, "alarm"

    invoke-virtual {p0, v2}, Landroid/content/Context;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/app/AlarmManager;

    .line 688
    .local v1, "manager":Landroid/app/AlarmManager;
    const/4 v2, 0x1

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    const-wide/16 v6, 0x0

    add-long/2addr v4, v6

    invoke-virtual {v1, v2, v4, v5, v0}, Landroid/app/AlarmManager;->set(IJLandroid/app/PendingIntent;)V

    .line 689
    const/4 v2, 0x2

    invoke-static {v2}, Ljava/lang/System;->exit(I)V

    .line 690
    return-void
.end method

.method public static restartWAMOD(Landroid/support/v7/app/AppCompatActivity;)V
    .registers 9
    .param p0, "a"    # Landroid/support/v7/app/AppCompatActivity;

    .prologue
    .line 679
    invoke-virtual {p0}, Landroid/support/v7/app/AppCompatActivity;->getBaseContext()Landroid/content/Context;

    move-result-object v2

    const/4 v3, 0x0

    new-instance v4, Landroid/content/Intent;

    invoke-virtual {p0}, Landroid/support/v7/app/AppCompatActivity;->getIntent()Landroid/content/Intent;

    move-result-object v5

    invoke-direct {v4, v5}, Landroid/content/Intent;-><init>(Landroid/content/Intent;)V

    const/high16 v5, 0x40000000    # 2.0f

    invoke-static {v2, v3, v4, v5}, Landroid/app/PendingIntent;->getActivity(Landroid/content/Context;ILandroid/content/Intent;I)Landroid/app/PendingIntent;

    move-result-object v0

    .line 680
    .local v0, "intent":Landroid/app/PendingIntent;
    const-string v2, "alarm"

    invoke-virtual {p0, v2}, Landroid/support/v7/app/AppCompatActivity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Landroid/app/AlarmManager;

    .line 681
    .local v1, "manager":Landroid/app/AlarmManager;
    const/4 v2, 0x1

    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v4

    const-wide/16 v6, 0x0

    add-long/2addr v4, v6

    invoke-virtual {v1, v2, v4, v5, v0}, Landroid/app/AlarmManager;->set(IJLandroid/app/PendingIntent;)V

    .line 682
    const/4 v2, 0x2

    invoke-static {v2}, Ljava/lang/System;->exit(I)V

    .line 683
    return-void
.end method

.method public static setDeviceID(Ljava/lang/String;)V
    .registers 3
    .param p0, "deviceID"    # Ljava/lang/String;

    .prologue
    .line 312
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    const-string v1, "device_id"

    invoke-interface {v0, v1, p0}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 313
    sget-object v0, Lcom/wamod/utils;->edit:Landroid/content/SharedPreferences$Editor;

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 314
    return-void
.end method

.method public static setTaskDescription(Landroid/support/v7/app/AppCompatActivity;)V
    .registers 8
    .param p0, "activity"    # Landroid/support/v7/app/AppCompatActivity;

    .prologue
    const/4 v5, 0x1

    .line 658
    sget v3, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v4, 0x15

    if-lt v3, v4, :cond_62

    .line 659
    sget v3, Lcom/wamod/Resources$string;->app_name:I

    invoke-virtual {p0, v3}, Landroid/support/v7/app/AppCompatActivity;->getString(I)Ljava/lang/String;

    move-result-object v2

    .line 660
    .local v2, "title":Ljava/lang/String;
    sget-object v3, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v4, "overview_multiplechats"

    invoke-interface {v3, v4, v5}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v3

    if-eqz v3, :cond_1f

    invoke-virtual {p0}, Landroid/support/v7/app/AppCompatActivity;->getTitle()Ljava/lang/CharSequence;

    move-result-object v3

    invoke-interface {v3}, Ljava/lang/CharSequence;->toString()Ljava/lang/String;

    move-result-object v2

    .line 661
    :cond_1f
    const-string v3, "#075e54"

    invoke-static {v3}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    .line 662
    .local v0, "color":I
    sget-object v3, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v4, "overview_cardcolor"

    invoke-interface {v3, v4, v5}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v3

    if-eqz v3, :cond_50

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "#"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    sget-object v4, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v5, "general_toolbarcolor"

    const-string v6, "ffffff"

    invoke-interface {v4, v5, v6}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    .line 663
    :cond_50
    new-instance v1, Landroid/app/ActivityManager$TaskDescription;

    invoke-virtual {p0}, Landroid/support/v7/app/AppCompatActivity;->getResources()Landroid/content/res/Resources;

    move-result-object v3

    sget v4, Lcom/wamod/Resources$drawable;->icon:I

    invoke-static {v3, v4}, Landroid/graphics/BitmapFactory;->decodeResource(Landroid/content/res/Resources;I)Landroid/graphics/Bitmap;

    move-result-object v3

    invoke-direct {v1, v2, v3, v0}, Landroid/app/ActivityManager$TaskDescription;-><init>(Ljava/lang/String;Landroid/graphics/Bitmap;I)V

    .line 664
    .local v1, "taskDesc":Landroid/app/ActivityManager$TaskDescription;
    invoke-virtual {p0, v1}, Landroid/support/v7/app/AppCompatActivity;->setTaskDescription(Landroid/app/ActivityManager$TaskDescription;)V

    .line 666
    .end local v0    # "color":I
    .end local v1    # "taskDesc":Landroid/app/ActivityManager$TaskDescription;
    .end local v2    # "title":Ljava/lang/String;
    :cond_62
    return-void
.end method

.method public static switchAccount(Landroid/content/Context;)Z
    .registers 4
    .param p0, "ctx"    # Landroid/content/Context;

    .prologue
    .line 870
    new-instance v0, Landroid/support/v7/app/AlertDialog$Builder;

    invoke-direct {v0, p0}, Landroid/support/v7/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 871
    .local v0, "builder":Landroid/support/v7/app/AlertDialog$Builder;
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/wamod/Resources$string;->wamod_switchaccount_prompt_title:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/support/v7/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 872
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    sget v2, Lcom/wamod/Resources$string;->wamod_switchaccount_prompt_message:I

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/support/v7/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 873
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const v2, 0x104000a

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    new-instance v2, Lcom/wamod/utils$3;

    invoke-direct {v2, p0}, Lcom/wamod/utils$3;-><init>(Landroid/content/Context;)V

    invoke-virtual {v0, v1, v2}, Landroid/support/v7/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 904
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    const/high16 v2, 0x1040000

    invoke-virtual {v1, v2}, Landroid/content/res/Resources;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Landroid/support/v7/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/support/v7/app/AlertDialog$Builder;

    .line 905
    invoke-virtual {v0}, Landroid/support/v7/app/AlertDialog$Builder;->show()Landroid/support/v7/app/AlertDialog;

    .line 906
    const/4 v1, 0x1

    return v1
.end method

.method public static tintHomeTabs(Landroid/widget/HorizontalScrollView;)V
    .registers 5
    .param p0, "tabs"    # Landroid/widget/HorizontalScrollView;

    .prologue
    .line 304
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "#"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "general_toolbarcolor"

    const-string v3, "ffffff"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    invoke-virtual {p0, v0}, Landroid/widget/HorizontalScrollView;->setBackgroundColor(I)V

    .line 305
    return-void
.end method

.method public static tintToColor(Landroid/graphics/drawable/Drawable;I)Landroid/graphics/drawable/Drawable;
    .registers 10
    .param p0, "drawable"    # Landroid/graphics/drawable/Drawable;
    .param p1, "color"    # I

    .prologue
    const/4 v7, 0x0

    .line 1110
    if-nez p0, :cond_5

    const/4 p0, 0x0

    .line 1122
    .end local p0    # "drawable":Landroid/graphics/drawable/Drawable;
    :goto_4
    return-object p0

    .line 1111
    .restart local p0    # "drawable":Landroid/graphics/drawable/Drawable;
    :cond_5
    const/high16 v5, 0xff0000

    and-int/2addr v5, p1

    const v6, 0xffff

    div-int v4, v5, v6

    .line 1112
    .local v4, "red":I
    const v5, 0xff00

    and-int/2addr v5, p1

    div-int/lit16 v2, v5, 0xff

    .line 1113
    .local v2, "green":I
    and-int/lit16 v0, p1, 0xff

    .line 1114
    .local v0, "blue":I
    const/16 v5, 0x14

    new-array v3, v5, [F

    const/4 v5, 0x0

    aput v7, v3, v5

    const/4 v5, 0x1

    aput v7, v3, v5

    const/4 v5, 0x2

    aput v7, v3, v5

    const/4 v5, 0x3

    aput v7, v3, v5

    const/4 v5, 0x4

    int-to-float v6, v4

    aput v6, v3, v5

    const/4 v5, 0x5

    aput v7, v3, v5

    const/4 v5, 0x6

    aput v7, v3, v5

    const/4 v5, 0x7

    aput v7, v3, v5

    const/16 v5, 0x8

    aput v7, v3, v5

    const/16 v5, 0x9

    int-to-float v6, v2

    aput v6, v3, v5

    const/16 v5, 0xa

    aput v7, v3, v5

    const/16 v5, 0xb

    aput v7, v3, v5

    const/16 v5, 0xc

    aput v7, v3, v5

    const/16 v5, 0xd

    aput v7, v3, v5

    const/16 v5, 0xe

    int-to-float v6, v0

    aput v6, v3, v5

    const/16 v5, 0xf

    aput v7, v3, v5

    const/16 v5, 0x10

    aput v7, v3, v5

    const/16 v5, 0x11

    aput v7, v3, v5

    const/16 v5, 0x12

    const/high16 v6, 0x3f800000    # 1.0f

    aput v6, v3, v5

    const/16 v5, 0x13

    aput v7, v3, v5

    .line 1118
    .local v3, "matrix":[F
    new-instance v1, Landroid/graphics/ColorMatrixColorFilter;

    invoke-direct {v1, v3}, Landroid/graphics/ColorMatrixColorFilter;-><init>([F)V

    .line 1120
    .local v1, "colorFilter":Landroid/graphics/ColorFilter;
    invoke-virtual {p0, v1}, Landroid/graphics/drawable/Drawable;->setColorFilter(Landroid/graphics/ColorFilter;)V

    goto :goto_4
.end method

.method public static tintToolbarIcon(Landroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;
    .registers 5
    .param p0, "icon"    # Landroid/graphics/drawable/Drawable;

    .prologue
    .line 639
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "#"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/wamod/utils;->prefs:Landroid/content/SharedPreferences;

    const-string v2, "general_toolbarforeground"

    const-string v3, "FFFFFF"

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v0

    sget-object v1, Landroid/graphics/PorterDuff$Mode;->MULTIPLY:Landroid/graphics/PorterDuff$Mode;

    invoke-virtual {p0, v0, v1}, Landroid/graphics/drawable/Drawable;->setColorFilter(ILandroid/graphics/PorterDuff$Mode;)V

    .line 640
    return-object p0
.end method

.method public static tintToolbarItems(Landroid/view/ViewGroup;Landroid/content/res/Resources;)V
    .registers 6
    .param p0, "actionbar"    # Landroid/view/ViewGroup;
    .param p1, "resources"    # Landroid/content/res/Resources;

    .prologue
    .line 607
    new-instance v0, Landroid/os/Handler;

    invoke-direct {v0}, Landroid/os/Handler;-><init>()V

    .line 608
    .local v0, "handler":Landroid/os/Handler;
    new-instance v1, Lcom/wamod/utils$1;

    invoke-direct {v1, p0}, Lcom/wamod/utils$1;-><init>(Landroid/view/ViewGroup;)V

    const-wide/16 v2, 0x0

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 636
    return-void
.end method

.method public static toTxt(Ljava/lang/String;)V
    .registers 7
    .param p0, "str"    # Ljava/lang/String;

    .prologue
    .line 775
    :try_start_0
    new-instance v2, Ljava/io/File;

    invoke-static {}, Landroid/os/Environment;->getExternalStorageDirectory()Ljava/io/File;

    move-result-object v4

    const-string v5, "Notes"

    invoke-direct {v2, v4, v5}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 776
    .local v2, "root":Ljava/io/File;
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result v4

    if-nez v4, :cond_14

    .line 777
    invoke-virtual {v2}, Ljava/io/File;->mkdirs()Z

    .line 779
    :cond_14
    new-instance v1, Ljava/io/File;

    const-string v4, "wamod.txt"

    invoke-direct {v1, v2, v4}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    .line 780
    .local v1, "gpxfile":Ljava/io/File;
    new-instance v3, Ljava/io/FileWriter;

    invoke-direct {v3, v1}, Ljava/io/FileWriter;-><init>(Ljava/io/File;)V

    .line 781
    .local v3, "writer":Ljava/io/FileWriter;
    invoke-virtual {v3, p0}, Ljava/io/FileWriter;->append(Ljava/lang/CharSequence;)Ljava/io/Writer;

    .line 782
    invoke-virtual {v3}, Ljava/io/FileWriter;->flush()V

    .line 783
    invoke-virtual {v3}, Ljava/io/FileWriter;->close()V

    .line 784
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "File saved! "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v1}, Ljava/io/File;->getAbsolutePath()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v4}, Lcom/wamod/utils;->log(Ljava/lang/String;)V
    :try_end_43
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_43} :catch_44

    .line 790
    .end local v1    # "gpxfile":Ljava/io/File;
    .end local v2    # "root":Ljava/io/File;
    .end local v3    # "writer":Ljava/io/FileWriter;
    :goto_43
    return-void

    .line 786
    :catch_44
    move-exception v0

    .line 788
    .local v0, "e":Ljava/io/IOException;
    invoke-virtual {v0}, Ljava/io/IOException;->printStackTrace()V

    goto :goto_43
.end method

.method public static toastHome()V
    .registers 3

    .prologue
    .line 1055
    sget-object v0, Lcom/wamod/utils;->context:Landroid/content/Context;

    const-string v1, "Content loaded"

    const/4 v2, 0x0

    invoke-static {v0, v1, v2}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 1056
    return-void
.end method
