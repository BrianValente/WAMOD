package com.wamod;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.wamod.view.AccountRow;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by brianvalente on 9/14/16.
 */
public class AccountsManager {
    private String appPath;
    private String sharedPrefsPath;
    File accountsFolder;
    int sharedPrefsIndex = 0;
    int lastAccountId = 0;
    Context context;

    AccountsManager(Context appContext) {
        context = appContext;
        appPath = Utils.getApplicationPath(App.getContext()) + "/";
        sharedPrefsPath = appPath + "shared_prefs/";
        accountsFolder = new File(appPath + "WAMOD/Accounts");
        if (!accountsFolder.exists()) accountsFolder.mkdirs();
        setLastAccountId();
    }

    private void setLastAccountId() {
        for (File accountFolder : accountsFolder.listFiles()) {
            if (!accountFolder.isFile()) {
                try {
                    int accountId = Integer.parseInt(accountFolder.getName());
                    lastAccountId = accountId > lastAccountId? accountId : lastAccountId;
                } catch (Exception e) {}
            }
        }
    }

    public ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        int index;
        try {
            for (File accountFolder : accountsFolder.listFiles()) {
                if (!accountFolder.isFile()) {
                    File whatsappPrefs = new File(accountFolder.getAbsolutePath() + "/shared_prefs/com.whatsapp_preferences.xml");
                    File registerPhone = new File(accountFolder.getAbsolutePath() + "/shared_prefs/registration.RegisterPhone.xml");
                    if (whatsappPrefs.exists() && registerPhone.exists()) {
                        index = getSharedPrefsIndex();
                        FileUtils.copyFile(whatsappPrefs, new File(sharedPrefsPath + "wamod_temp" + index + ".xml"));
                        SharedPreferences prefs = App.getContext().getSharedPreferences("wamod_temp" + index, 0);
                        String name = prefs.getString("push_name", "");

                        index = getSharedPrefsIndex();
                        FileUtils.copyFile(registerPhone, new File(sharedPrefsPath + "wamod_temp" + index + ".xml"));
                        prefs = App.getContext().getSharedPreferences("wamod_temp" + index, 0);
                        String phone = "+" + prefs.getString("com.whatsapp.registration.RegisterPhone.country_code", "") + " " + prefs.getString("com.whatsapp.registration.RegisterPhone.input_phone_number", "");

                        int id = Integer.parseInt(accountFolder.getName());

                        Drawable picture = null;
                        File pictureFile = new File(accountFolder.getAbsolutePath() + "/files/me.jpg");
                        if (pictureFile.exists()) picture = Drawable.createFromPath(pictureFile.getAbsolutePath());

                        accounts.add(new Account(name, phone, picture, id));
                    }
                }
            }
        } catch (IOException e) {
            Utils.manageException(e);
        }

        return accounts;
    }

    public void switchToAccount(Account account) {
        int accountId = account.getId();
        try {
            File backupFolder = new File(accountsFolder.getAbsolutePath() + "/" + (lastAccountId + 1) + "/");
            File restoreFolder = new File(accountsFolder.getAbsolutePath() + "/" + accountId + "/");
            String backupPath = backupFolder.getAbsolutePath();
            String restorePath = restoreFolder.getAbsolutePath();

            if (!restoreFolder.exists())
                throw new RuntimeException("The account does NOT exist!");

            backupFolder.mkdir();

            File logs = new File(appPath + "files/Logs");
            FileUtils.deleteDirectory(logs);

            FileUtils.moveDirectory(new File(appPath + "/shared_prefs"), new File(backupPath + "/shared_prefs"));
            FileUtils.moveDirectory(new File(appPath + "/databases"), new File(backupPath + "/databases"));
            FileUtils.moveDirectory(new File(appPath + "/files"), new File(backupPath + "/files"));

            FileUtils.moveDirectory(new File(restorePath + "/shared_prefs"), new File(appPath + "/shared_prefs"));
            FileUtils.moveDirectory(new File(restorePath + "/databases"), new File(appPath + "/databases"));
            FileUtils.moveDirectory(new File(restorePath + "/files"), new File(appPath + "/files"));

            FileUtils.deleteDirectory(restoreFolder);

            Utils.restartWAMOD(App.getContext());
        } catch (IOException e) {
            Utils.manageException(e);
        }
    }

    private int getSharedPrefsIndex() {
        return sharedPrefsIndex++;
    }

    public class Account {
        private String   name;
        private String   phoneNumber;
        private int      id;
        private Drawable profilePicture;

        private Account(String name, String phoneNumber, Drawable profilePicture, int id) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.profilePicture = profilePicture;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public Drawable getProfilePicture() {
            return profilePicture;
        }

        public int getId() {
            return id;
        }
    }

    public void showAddAccountPrompt(Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(context.getResources().getString(Resources.getString("wamod_accounts_add_prompt_title")));
        alertDialog.setMessage(context.getResources().getString(Resources.getString("wamod_accounts_add_prompt_message")));
        alertDialog.setPositiveButton(context.getResources().getString(android.R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                addAccountAndSwitch();
            }
        });
        alertDialog.setNegativeButton(context.getResources().getString(android.R.string.no), null);
        Utils.tintAndShowDialog(alertDialog);
    }

    private void addAccountAndSwitch() {
        int newAccountId = lastAccountId + 1;
        lastAccountId++;

        File newAccount = new File(accountsFolder.getAbsolutePath() + "/" + (newAccountId) + "/");
        File newAccount_sharedPrefs = new File(accountsFolder.getAbsolutePath() + "/" + (newAccountId) + "/shared_prefs/");
        File newAccount_databases = new File(accountsFolder.getAbsolutePath() + "/" + (newAccountId) + "/databases/");
        File newAccount_files = new File(accountsFolder.getAbsolutePath() + "/" + (newAccountId) + "/files/");

        newAccount.mkdirs();
        newAccount_sharedPrefs.mkdirs();
        newAccount_databases.mkdirs();
        newAccount_files.mkdirs();

        switchToAccount(new Account(null, null, null, newAccountId));
    }

    public void showAccountsList(Context context) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(context.getResources().getString(Resources.getString("wamod_accounts_select_title")));
        alertDialog.setView(getAccountsListLinearLayout(context));
        alertDialog.show();
    }

    public LinearLayout getAccountsListLinearLayout(Context context) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        for (AccountsManager.Account account : getAccounts()) {
            AccountRow accountRow = new AccountRow(context);
            accountRow.setAccount(account);
            linearLayout.addView(accountRow, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }

        return linearLayout;
    }
}
