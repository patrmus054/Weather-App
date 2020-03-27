package com.example.chat_project.Communication.local.client.repositories;

import android.net.nsd.NsdManager;
import android.net.nsd.NsdServiceInfo;

import com.example.chat_project.Communication.local.client.models.ClientConnectionInfo;

public class ClientDiscoveryListener implements NsdManager.DiscoveryListener {

    private final NsdManager nsdManager;

    public ClientDiscoveryListener(NsdManager nsdManager) {
        this.nsdManager = nsdManager;
    }

    // Called as soon as service discovery begins.
    @Override
    public void onDiscoveryStarted(String regType) {
        System.out.println("Listening Service discovery started");
    }

    @Override
    public void onServiceFound(NsdServiceInfo service) {
        // A service was found! Do something with it.
        System.out.println("Listening Service discovery success : " + service);
        System.out.println("Listening Host = " + service.getServiceName());
        System.out.println("Listening port = " + String.valueOf(service.getPort()));

        if (!service.getServiceType().equals(ClientConnectionInfo.SERVICE_TYPE)) {
            // Service type is the string containing the protocol and
            // transport layer for this service.
            System.out.println("Listening Unknown Service Type: " + service.getServiceType());
        } else if (service.getServiceName().equals(ClientConnectionInfo.SERVICE_NAME)) {
            // The name of the service tells the user what they'd be
            // connecting to. It could be "Bob's Chat App".
            System.out.println("Listening Same machine: " + ClientConnectionInfo.SERVICE_NAME);
        } else {
            System.out.println("Listening Diff Machine : " + service.getServiceName());
            // connect to the service and obtain serviceInfo
            ClientResolveListener resolveListener = new ClientResolveListener();
            this.nsdManager.resolveService(service, resolveListener);
        }
    }

    @Override
    public void onServiceLost(NsdServiceInfo service) {
        // When the network service is no longer available.
        // Internal bookkeeping code goes here.
        System.out.println("Listening service lost" + service);
    }

    @Override
    public void onDiscoveryStopped(String serviceType) {
        System.out.println("Listening Discovery stopped: " + serviceType);
    }

    @Override
    public void onStartDiscoveryFailed(String serviceType, int errorCode) {
        System.out.println("Listening failed: Error code:" + errorCode);
        this.nsdManager.stopServiceDiscovery(this);
    }

    @Override
    public void onStopDiscoveryFailed(String serviceType, int errorCode) {
        System.out.println("Listening failed: Error code:" + errorCode);
        this.nsdManager.stopServiceDiscovery(this);
    }
}
