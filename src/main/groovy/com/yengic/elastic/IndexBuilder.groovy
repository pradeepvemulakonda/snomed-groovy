package com.yengic.elastic

import com.yengic.common.DescElement
import com.yengic.common.SnomedRef
import org.elasticsearch.action.bulk.*
import org.elasticsearch.common.unit.ByteSizeUnit
import org.elasticsearch.common.unit.ByteSizeValue
import org.elasticsearch.common.unit.TimeValue

import javax.inject.Inject
import java.util.concurrent.TimeUnit

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder

/**
 * Created by Harita on 20/5/17.
 */
class IndexBuilder {

    ElasticClient elasticClient
    BulkProcessor bulkProcessor

    @Inject
    IndexBuilder(ElasticClient client) {
        this.elasticClient = client
    }

    def build() {
        this.elasticClient.start()
        bulkProcessor = BulkProcessor.builder(
                elasticClient.client,
                new BulkProcessor.Listener() {
                    @Override
                    void beforeBulk(long executionId,
                                    BulkRequest request) {
                        println "in before"
                    }

                    @Override
                    void afterBulk(long executionId,
                                   BulkRequest request,
                                   BulkResponse response) {

                        println "in after"
                    }

                    @Override
                    void afterBulk(long executionId,
                                   BulkRequest request,
                                   Throwable failure) {
                        println "in after throwable"
                    }
                })
                .setBulkActions(10000)
                .setBulkSize(new ByteSizeValue(5, ByteSizeUnit.MB))
                .setFlushInterval(TimeValue.timeValueSeconds(5))
                .setConcurrentRequests(100)
                .setBackoffPolicy(
                BackoffPolicy.exponentialBackoff(TimeValue.timeValueMillis(100), 3))
                .build()
        return this
    }

    def process(SnomedRef snomedRef) {
        try {
            BulkRequestBuilder bulkRequest = elasticClient.client.prepareBulk()
            snomedRef.mapOfDesc.eachWithIndex { Map.Entry<String, DescElement> entry, int i ->
                def autoCompleteTerms = entry.value.synonym
                //autoCompleteTerms << entry.value.fsn
                def jsonObject = jsonBuilder()
                        .startObject()
                        .field("conceptId", entry.key)
                        .field("term", entry.value.fsn)
                            .startObject("term_suggest").field("input", autoCompleteTerms).endObject()
                        .endObject()

                bulkRequest.add(elasticClient.client.prepareIndex("snomed", "concept")
                        .setSource(jsonObject))
            }
            bulkProcessor.awaitClose(1, TimeUnit.MINUTES);
            BulkResponse response = bulkRequest.get()
            if (response.hasFailures()) {
                println response.buildFailureMessage()
                println "has failures"
            }

            println response.status()

            elasticClient.client.admin().indices().prepareRefresh().get()

        } finally {
            this.elasticClient.shutdown()
        }
    }

}
