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

public interface serveRecoPrx extends com.zeroc.Ice.ObjectPrx
{
    default boolean addfile(String nom, String genre, String auteur, String avatar)
    {
        return addfile(nom, genre, auteur, avatar, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean addfile(String nom, String genre, String auteur, String avatar, java.util.Map<String, String> context)
    {
        return _iceI_addfileAsync(nom, genre, auteur, avatar, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Boolean> addfileAsync(String nom, String genre, String auteur, String avatar)
    {
        return _iceI_addfileAsync(nom, genre, auteur, avatar, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Boolean> addfileAsync(String nom, String genre, String auteur, String avatar, java.util.Map<String, String> context)
    {
        return _iceI_addfileAsync(nom, genre, auteur, avatar, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Boolean> _iceI_addfileAsync(String iceP_nom, String iceP_genre, String iceP_auteur, String iceP_avatar, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "addfile", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_nom);
                     ostr.writeString(iceP_genre);
                     ostr.writeString(iceP_auteur);
                     ostr.writeString(iceP_avatar);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default morceau findbyname(String name)
    {
        return findbyname(name, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default morceau findbyname(String name, java.util.Map<String, String> context)
    {
        return _iceI_findbynameAsync(name, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<morceau> findbynameAsync(String name)
    {
        return _iceI_findbynameAsync(name, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<morceau> findbynameAsync(String name, java.util.Map<String, String> context)
    {
        return _iceI_findbynameAsync(name, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<morceau> _iceI_findbynameAsync(String iceP_name, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<morceau> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "findbyname", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_name);
                 }, istr -> {
                     morceau ret;
                     ret = morceau.ice_read(istr);
                     return ret;
                 });
        return f;
    }

    default boolean deletefile(String name)
    {
        return deletefile(name, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean deletefile(String name, java.util.Map<String, String> context)
    {
        return _iceI_deletefileAsync(name, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Boolean> deletefileAsync(String name)
    {
        return _iceI_deletefileAsync(name, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Boolean> deletefileAsync(String name, java.util.Map<String, String> context)
    {
        return _iceI_deletefileAsync(name, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Boolean> _iceI_deletefileAsync(String iceP_name, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "deletefile", null, sync, null);
        f.invoke(true, context, null, ostr -> {
                     ostr.writeString(iceP_name);
                 }, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    default java.util.Map<String, morceau> display()
    {
        return display(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default java.util.Map<String, morceau> display(java.util.Map<String, String> context)
    {
        return _iceI_displayAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<java.util.Map<String, morceau>> displayAsync()
    {
        return _iceI_displayAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<java.util.Map<String, morceau>> displayAsync(java.util.Map<String, String> context)
    {
        return _iceI_displayAsync(context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<java.util.Map<String, morceau>> _iceI_displayAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<java.util.Map<String, morceau>> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "display", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     java.util.Map<String, morceau> ret;
                     ret = collHelper.read(istr);
                     return ret;
                 });
        return f;
    }

    default void playmusic(String nom)
    {
        playmusic(nom, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void playmusic(String nom, java.util.Map<String, String> context)
    {
        _iceI_playmusicAsync(nom, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> playmusicAsync(String nom)
    {
        return _iceI_playmusicAsync(nom, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> playmusicAsync(String nom, java.util.Map<String, String> context)
    {
        return _iceI_playmusicAsync(nom, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_playmusicAsync(String iceP_nom, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "playmusic", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeString(iceP_nom);
                 }, null);
        return f;
    }

    default void pausemusic()
    {
        pausemusic(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void pausemusic(java.util.Map<String, String> context)
    {
        _iceI_pausemusicAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> pausemusicAsync()
    {
        return _iceI_pausemusicAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> pausemusicAsync(java.util.Map<String, String> context)
    {
        return _iceI_pausemusicAsync(context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_pausemusicAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "pausemusic", null, sync, null);
        f.invoke(false, context, null, null, null);
        return f;
    }

    default void alleraT(int temps)
    {
        alleraT(temps, com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default void alleraT(int temps, java.util.Map<String, String> context)
    {
        _iceI_alleraTAsync(temps, context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Void> alleraTAsync(int temps)
    {
        return _iceI_alleraTAsync(temps, com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Void> alleraTAsync(int temps, java.util.Map<String, String> context)
    {
        return _iceI_alleraTAsync(temps, context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Void> _iceI_alleraTAsync(int iceP_temps, java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Void> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "alleraT", null, sync, null);
        f.invoke(false, context, null, ostr -> {
                     ostr.writeInt(iceP_temps);
                 }, null);
        return f;
    }

    default boolean isplaying()
    {
        return isplaying(com.zeroc.Ice.ObjectPrx.noExplicitContext);
    }

    default boolean isplaying(java.util.Map<String, String> context)
    {
        return _iceI_isplayingAsync(context, true).waitForResponse();
    }

    default java.util.concurrent.CompletableFuture<Boolean> isplayingAsync()
    {
        return _iceI_isplayingAsync(com.zeroc.Ice.ObjectPrx.noExplicitContext, false);
    }

    default java.util.concurrent.CompletableFuture<Boolean> isplayingAsync(java.util.Map<String, String> context)
    {
        return _iceI_isplayingAsync(context, false);
    }

    default com.zeroc.IceInternal.OutgoingAsync<Boolean> _iceI_isplayingAsync(java.util.Map<String, String> context, boolean sync)
    {
        com.zeroc.IceInternal.OutgoingAsync<Boolean> f = new com.zeroc.IceInternal.OutgoingAsync<>(this, "isplaying", null, sync, null);
        f.invoke(true, context, null, null, istr -> {
                     boolean ret;
                     ret = istr.readBool();
                     return ret;
                 });
        return f;
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static serveRecoPrx checkedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, ice_staticId(), serveRecoPrx.class, _serveRecoPrxI.class);
    }

    /**
     * Contacts the remote server to verify that the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static serveRecoPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, context, ice_staticId(), serveRecoPrx.class, _serveRecoPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static serveRecoPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, ice_staticId(), serveRecoPrx.class, _serveRecoPrxI.class);
    }

    /**
     * Contacts the remote server to verify that a facet of the object implements this type.
     * Raises a local exception if a communication error occurs.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @param context The Context map to send with the invocation.
     * @return A proxy for this type, or null if the object does not support this type.
     **/
    static serveRecoPrx checkedCast(com.zeroc.Ice.ObjectPrx obj, String facet, java.util.Map<String, String> context)
    {
        return com.zeroc.Ice.ObjectPrx._checkedCast(obj, facet, context, ice_staticId(), serveRecoPrx.class, _serveRecoPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @return A proxy for this type.
     **/
    static serveRecoPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, serveRecoPrx.class, _serveRecoPrxI.class);
    }

    /**
     * Downcasts the given proxy to this type without contacting the remote server.
     * @param obj The untyped proxy.
     * @param facet The name of the desired facet.
     * @return A proxy for this type.
     **/
    static serveRecoPrx uncheckedCast(com.zeroc.Ice.ObjectPrx obj, String facet)
    {
        return com.zeroc.Ice.ObjectPrx._uncheckedCast(obj, facet, serveRecoPrx.class, _serveRecoPrxI.class);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the per-proxy context.
     * @param newContext The context for the new proxy.
     * @return A proxy with the specified per-proxy context.
     **/
    @Override
    default serveRecoPrx ice_context(java.util.Map<String, String> newContext)
    {
        return (serveRecoPrx)_ice_context(newContext);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the adapter ID.
     * @param newAdapterId The adapter ID for the new proxy.
     * @return A proxy with the specified adapter ID.
     **/
    @Override
    default serveRecoPrx ice_adapterId(String newAdapterId)
    {
        return (serveRecoPrx)_ice_adapterId(newAdapterId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoints.
     * @param newEndpoints The endpoints for the new proxy.
     * @return A proxy with the specified endpoints.
     **/
    @Override
    default serveRecoPrx ice_endpoints(com.zeroc.Ice.Endpoint[] newEndpoints)
    {
        return (serveRecoPrx)_ice_endpoints(newEndpoints);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator cache timeout.
     * @param newTimeout The new locator cache timeout (in seconds).
     * @return A proxy with the specified locator cache timeout.
     **/
    @Override
    default serveRecoPrx ice_locatorCacheTimeout(int newTimeout)
    {
        return (serveRecoPrx)_ice_locatorCacheTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the invocation timeout.
     * @param newTimeout The new invocation timeout (in seconds).
     * @return A proxy with the specified invocation timeout.
     **/
    @Override
    default serveRecoPrx ice_invocationTimeout(int newTimeout)
    {
        return (serveRecoPrx)_ice_invocationTimeout(newTimeout);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for connection caching.
     * @param newCache <code>true</code> if the new proxy should cache connections; <code>false</code> otherwise.
     * @return A proxy with the specified caching policy.
     **/
    @Override
    default serveRecoPrx ice_connectionCached(boolean newCache)
    {
        return (serveRecoPrx)_ice_connectionCached(newCache);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the endpoint selection policy.
     * @param newType The new endpoint selection policy.
     * @return A proxy with the specified endpoint selection policy.
     **/
    @Override
    default serveRecoPrx ice_endpointSelection(com.zeroc.Ice.EndpointSelectionType newType)
    {
        return (serveRecoPrx)_ice_endpointSelection(newType);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for how it selects endpoints.
     * @param b If <code>b</code> is <code>true</code>, only endpoints that use a secure transport are
     * used by the new proxy. If <code>b</code> is false, the returned proxy uses both secure and
     * insecure endpoints.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default serveRecoPrx ice_secure(boolean b)
    {
        return (serveRecoPrx)_ice_secure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the encoding used to marshal parameters.
     * @param e The encoding version to use to marshal request parameters.
     * @return A proxy with the specified encoding version.
     **/
    @Override
    default serveRecoPrx ice_encodingVersion(com.zeroc.Ice.EncodingVersion e)
    {
        return (serveRecoPrx)_ice_encodingVersion(e);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its endpoint selection policy.
     * @param b If <code>b</code> is <code>true</code>, the new proxy will use secure endpoints for invocations
     * and only use insecure endpoints if an invocation cannot be made via secure endpoints. If <code>b</code> is
     * <code>false</code>, the proxy prefers insecure endpoints to secure ones.
     * @return A proxy with the specified selection policy.
     **/
    @Override
    default serveRecoPrx ice_preferSecure(boolean b)
    {
        return (serveRecoPrx)_ice_preferSecure(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the router.
     * @param router The router for the new proxy.
     * @return A proxy with the specified router.
     **/
    @Override
    default serveRecoPrx ice_router(com.zeroc.Ice.RouterPrx router)
    {
        return (serveRecoPrx)_ice_router(router);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for the locator.
     * @param locator The locator for the new proxy.
     * @return A proxy with the specified locator.
     **/
    @Override
    default serveRecoPrx ice_locator(com.zeroc.Ice.LocatorPrx locator)
    {
        return (serveRecoPrx)_ice_locator(locator);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for collocation optimization.
     * @param b <code>true</code> if the new proxy enables collocation optimization; <code>false</code> otherwise.
     * @return A proxy with the specified collocation optimization.
     **/
    @Override
    default serveRecoPrx ice_collocationOptimized(boolean b)
    {
        return (serveRecoPrx)_ice_collocationOptimized(b);
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses twoway invocations.
     * @return A proxy that uses twoway invocations.
     **/
    @Override
    default serveRecoPrx ice_twoway()
    {
        return (serveRecoPrx)_ice_twoway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses oneway invocations.
     * @return A proxy that uses oneway invocations.
     **/
    @Override
    default serveRecoPrx ice_oneway()
    {
        return (serveRecoPrx)_ice_oneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch oneway invocations.
     * @return A proxy that uses batch oneway invocations.
     **/
    @Override
    default serveRecoPrx ice_batchOneway()
    {
        return (serveRecoPrx)_ice_batchOneway();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses datagram invocations.
     * @return A proxy that uses datagram invocations.
     **/
    @Override
    default serveRecoPrx ice_datagram()
    {
        return (serveRecoPrx)_ice_datagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, but uses batch datagram invocations.
     * @return A proxy that uses batch datagram invocations.
     **/
    @Override
    default serveRecoPrx ice_batchDatagram()
    {
        return (serveRecoPrx)_ice_batchDatagram();
    }

    /**
     * Returns a proxy that is identical to this proxy, except for compression.
     * @param co <code>true</code> enables compression for the new proxy; <code>false</code> disables compression.
     * @return A proxy with the specified compression setting.
     **/
    @Override
    default serveRecoPrx ice_compress(boolean co)
    {
        return (serveRecoPrx)_ice_compress(co);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection timeout setting.
     * @param t The connection timeout for the proxy in milliseconds.
     * @return A proxy with the specified timeout.
     **/
    @Override
    default serveRecoPrx ice_timeout(int t)
    {
        return (serveRecoPrx)_ice_timeout(t);
    }

    /**
     * Returns a proxy that is identical to this proxy, except for its connection ID.
     * @param connectionId The connection ID for the new proxy. An empty string removes the connection ID.
     * @return A proxy with the specified connection ID.
     **/
    @Override
    default serveRecoPrx ice_connectionId(String connectionId)
    {
        return (serveRecoPrx)_ice_connectionId(connectionId);
    }

    /**
     * Returns a proxy that is identical to this proxy, except it's a fixed proxy bound
     * the given connection.@param connection The fixed proxy connection.
     * @return A fixed proxy bound to the given connection.
     **/
    /*@Override
    default serveRecoPrx ice_fixed(com.zeroc.Ice.Connection connection)
    {
        return ice_fixed(connection);
    }*/

    static String ice_staticId()
    {
        return "::mp3::serveReco";
    }
}
