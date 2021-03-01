package com.mvp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.android.crypto.keychain.AndroidConceal;
import com.facebook.android.crypto.keychain.SharedPrefsBackedKeyChain;
import com.facebook.crypto.Crypto;
import com.facebook.crypto.CryptoConfig;
import com.facebook.crypto.Entity;
import com.facebook.crypto.exception.CryptoInitializationException;
import com.facebook.crypto.exception.KeyChainException;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.nio.charset.Charset;

public class SecuredPreference {
    private SharedPreferences sharedPreferences;
    private final SharedPrefsBackedKeyChain keyChain;
    private final Crypto crypto;
    private final Entity entity;

    public SecuredPreference(Context context, String prefFileName) {
        sharedPreferences = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
        keyChain = new SharedPrefsBackedKeyChain(context, CryptoConfig.KEY_256);
        crypto = AndroidConceal.get().createDefaultCrypto(keyChain);
        entity = Entity.create(prefFileName);
    }

    boolean putString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(encryptKeyData(key), encryptData(value));
        return editor.commit();
    }

    boolean putBoolean(String key, Boolean value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(encryptKeyData(key), value);
        return editor.commit();
    }

    boolean putFloat(String key, float value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(encryptKeyData(key), value);
        return editor.commit();
    }

    boolean putInt(String key, int value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(encryptKeyData(key), value);
        return editor.commit();
    }

    private String encryptKeyData(String data) {
        return toString(DigestUtils.sha1(fromString(data))).replace("\n", "");
    }

    private byte[] fromString(String data) {
        return data.getBytes(Charset.forName("ISO-8859-1"));
    }

    private String toString(byte[] data) {
        return new String(data, Charset.forName("ISO-8859-1"));
    }

    String encryptData(String data) {
        String encryptedData = "";
        try {
            encryptedData = toString(crypto.encrypt(fromString(data), entity));
        } catch (KeyChainException | CryptoInitializationException | IOException e) {
            e.printStackTrace();
        } finally {
            return encryptedData;
        }
    }

    String decryptData(String data) {
        String decryptedData = "";
        try {
            decryptedData = toString(crypto.decrypt(fromString(data), entity));
        } catch (KeyChainException | CryptoInitializationException | IOException e) {
            e.printStackTrace();
        } finally {
            return decryptedData;
        }
    }

    public String getString(String key, String value) {
        return decryptData(sharedPreferences.getString(encryptKeyData(key), encryptData(value)));
    }

    public Boolean getBoolean(String key, Boolean value) {
        return sharedPreferences.getBoolean(encryptKeyData(key), value);
    }

    public float getFloat(String key, float value) {
        return sharedPreferences.getFloat(encryptKeyData(key), value);
    }

    public int getInt(String key, int value) {
        return sharedPreferences.getInt(encryptKeyData(key), value);
    }

    public Boolean contains(String key) {
        return sharedPreferences.contains(encryptKeyData(key));
    }

    public boolean clear() {
        return sharedPreferences.edit().clear().commit();
    }
}