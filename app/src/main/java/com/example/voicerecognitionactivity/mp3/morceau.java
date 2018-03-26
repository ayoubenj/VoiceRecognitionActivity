// **********************************************************************
//
// Copyright (c) 2003-2017 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************
//
// Ice version 3.7.0
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


public class morceau implements Cloneable, java.io.Serializable
{
    public String name;

    public String genre;

    public String auteur;

    public morceau()
    {
        this.name = "";
        this.genre = "";
        this.auteur = "";
    }

    public morceau(String name, String genre, String auteur)
    {
        this.name = name;
        this.genre = genre;
        this.auteur = auteur;
    }

    public boolean equals(Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        morceau r = null;
        if(rhs instanceof morceau)
        {
            r = (morceau)rhs;
        }

        if(r != null)
        {
            if(this.name != r.name)
            {
                if(this.name == null || r.name == null || !this.name.equals(r.name))
                {
                    return false;
                }
            }
            if(this.genre != r.genre)
            {
                if(this.genre == null || r.genre == null || !this.genre.equals(r.genre))
                {
                    return false;
                }
            }
            if(this.auteur != r.auteur)
            {
                if(this.auteur == null || r.auteur == null || !this.auteur.equals(r.auteur))
                {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::mp3::morceau");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, name);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, genre);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, auteur);
        return h_;
    }

    public morceau clone()
    {
        morceau c = null;
        try
        {
            c = (morceau)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeString(this.name);
        ostr.writeString(this.genre);
        ostr.writeString(this.auteur);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.name = istr.readString();
        this.genre = istr.readString();
        this.auteur = istr.readString();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, morceau v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public morceau ice_read(com.zeroc.Ice.InputStream istr)
    {
        morceau v = new morceau();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<morceau> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, morceau v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            int pos = ostr.startSize();
            ice_write(ostr, v);
            ostr.endSize(pos);
        }
    }

    static public java.util.Optional<morceau> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.FSize))
        {
            istr.skip(4);
            return java.util.Optional.of(morceau.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final morceau _nullMarshalValue = new morceau();

    public static final long serialVersionUID = 2717037004084921246L;
}
