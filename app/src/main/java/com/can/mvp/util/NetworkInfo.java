package com.can.mvp.util;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by can on 2017/11/17.
 */

public class NetworkInfo implements Parcelable {
    NetworkInfo() {
        throw new RuntimeException("Stub!");
    }

    public int getType() {
        throw new RuntimeException("Stub!");
    }

    public int getSubtype() {
        throw new RuntimeException("Stub!");
    }

    public String getTypeName() {
        throw new RuntimeException("Stub!");
    }

    public String getSubtypeName() {
        throw new RuntimeException("Stub!");
    }

    public boolean isConnectedOrConnecting() {
        throw new RuntimeException("Stub!");
    }

    public boolean isConnected() {
        throw new RuntimeException("Stub!");
    }

    public boolean isAvailable() {
        throw new RuntimeException("Stub!");
    }

    public boolean isFailover() {
        throw new RuntimeException("Stub!");
    }

    public boolean isRoaming() {
        throw new RuntimeException("Stub!");
    }

    public NetworkInfo.State getState() {
        throw new RuntimeException("Stub!");
    }

    public NetworkInfo.DetailedState getDetailedState() {
        throw new RuntimeException("Stub!");
    }

    public String getReason() {
        throw new RuntimeException("Stub!");
    }

    public String getExtraInfo() {
        throw new RuntimeException("Stub!");
    }

    public String toString() {
        throw new RuntimeException("Stub!");
    }

    public int describeContents() {
        throw new RuntimeException("Stub!");
    }

    public void writeToParcel(Parcel dest, int flags) {
        throw new RuntimeException("Stub!");
    }

    public static enum DetailedState {
        AUTHENTICATING,
        BLOCKED,
        CAPTIVE_PORTAL_CHECK,
        CONNECTED,
        CONNECTING,
        DISCONNECTED,
        DISCONNECTING,
        FAILED,
        IDLE,
        OBTAINING_IPADDR,
        SCANNING,
        SUSPENDED,
        VERIFYING_POOR_LINK;

        private DetailedState() {
        }
    }

    public static enum State {
        CONNECTED,
        CONNECTING,
        DISCONNECTED,
        DISCONNECTING,
        SUSPENDED,
        UNKNOWN;

        private State() {
        }
    }
}
