package com.example.divar3;

import Socket.Client;

public class ClientHolder {
    private static Client client;

    public static void setClient(Client client) {
        ClientHolder.client = client;
    }

    public static Client getClient() {
        return client;
    }
}
