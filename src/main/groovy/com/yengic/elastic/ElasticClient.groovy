package com.yengic.elastic

import org.elasticsearch.client.transport.TransportClient
import org.elasticsearch.common.settings.Settings
import org.elasticsearch.common.transport.InetSocketTransportAddress
import org.elasticsearch.transport.client.PreBuiltTransportClient

/**
 * Created by Harita on 20/5/17.
 */
class ElasticClient {

    TransportClient client

    def start() {
        client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
    }

    def shutdown() {
        client.close()
    }
}
