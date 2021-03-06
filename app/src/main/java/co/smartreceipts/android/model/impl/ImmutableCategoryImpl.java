package co.smartreceipts.android.model.impl;

import android.os.Parcel;
import android.support.annotation.NonNull;

import com.google.common.base.Preconditions;

import co.smartreceipts.android.model.Category;
import co.smartreceipts.android.sync.model.SyncState;
import co.smartreceipts.android.sync.model.impl.DefaultSyncState;

public class ImmutableCategoryImpl implements Category {

    private final String mName;
    private final String mCode;
    private final SyncState mSyncState;

    public ImmutableCategoryImpl(@NonNull String name, @NonNull String code) {
        this(name, code, new DefaultSyncState());
    }

    public ImmutableCategoryImpl(@NonNull String name, @NonNull String code, @NonNull SyncState syncState) {
        mName = Preconditions.checkNotNull(name);
        mCode = Preconditions.checkNotNull(code);
        mSyncState = Preconditions.checkNotNull(syncState);
    }

    private ImmutableCategoryImpl(final Parcel in) {
        mName = in.readString();
        mCode = in.readString();
        mSyncState = in.readParcelable(getClass().getClassLoader());
    }

    @NonNull
    @Override
    public String getName() {
        return mName;
    }

    @NonNull
    @Override
    public String getCode() {
        return mCode;
    }

    @NonNull
    @Override
    public SyncState getSyncState() {
        return mSyncState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImmutableCategoryImpl)) return false;

        ImmutableCategoryImpl that = (ImmutableCategoryImpl) o;

        if (!mName.equals(that.mName)) return false;
        return (mCode.equals(that.mCode));
    }

    @Override
    public int hashCode() {
        int result = mName.hashCode();
        result = 31 * result + mCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return mName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(final Parcel out, final int flags) {
        out.writeString(mName);
        out.writeString(mCode);
        out.writeParcelable(mSyncState, flags);
    }

    public static Creator<ImmutableCategoryImpl> CREATOR = new Creator<ImmutableCategoryImpl>() {

        @Override
        public ImmutableCategoryImpl createFromParcel(Parcel source) {
            return new ImmutableCategoryImpl(source);
        }

        @Override
        public ImmutableCategoryImpl[] newArray(int size) {
            return new ImmutableCategoryImpl[size];
        }

    };


    @Override
    public int compareTo(@NonNull Category category) {
        return mName.compareTo(category.getName());
    }
}
