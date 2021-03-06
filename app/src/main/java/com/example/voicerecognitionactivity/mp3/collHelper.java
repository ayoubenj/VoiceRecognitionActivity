// **********************************************************************
//
// Copyright (c) 2003-2018 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.7.1
//
// <auto-generated>
//
// Generated from file `server.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package com.example.voicerecognitionactivity.mp3;

import android.annotation.TargetApi;
import android.os.Build;

public final class collHelper
{
    public static void write(com.zeroc.Ice.OutputStream ostr, java.util.Map<String, morceau> v)
    {
        if(v == null)
        {
            ostr.writeSize(0);
        }
        else
        {
            ostr.writeSize(v.size());
            for(java.util.Map.Entry<String, morceau> e : v.entrySet())
            {
                ostr.writeString(e.getKey());
                morceau.ice_write(ostr, e.getValue());
            }
        }
    }

    public static java.util.Map<String, morceau> read(com.zeroc.Ice.InputStream istr)
    {
        java.util.Map<String, morceau> v;
        v = new java.util.HashMap<String, morceau>();
        int sz0 = istr.readSize();
        for(int i0 = 0; i0 < sz0; i0++)
        {
            String key;
            key = istr.readString();
            morceau value;
            value = morceau.ice_read(istr);
            v.put(key, value);
        }
        return v;
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<java.util.Map<String, morceau>> v)
    {
        if(v != null && v.isPresent())
        {
            write(ostr, tag, v.get());
        }
    }

    public static void write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Map<String, morceau> v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            collHelper.write(ostr, v);
            ostr.endSize(pos);
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static java.util.Optional<java.util.Map<String, morceau>> read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            java.util.Map<String, morceau> v;
            v = collHelper.read(istr);
            return java.util.Optional.of(v);
        }
        else
        {
            return java.util.Optional.empty();
        }
    }
}
