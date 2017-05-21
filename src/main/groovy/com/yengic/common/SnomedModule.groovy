package com.yengic.common

import com.google.inject.AbstractModule
import com.yengic.components.extractor.ConceptDescExtractor
import com.yengic.elastic.ElasticClient

/**
 * Created by Harita on 14/5/17.
 */
class SnomedModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(BaseSnomedFileReader.class)
        bind(ElasticClient.class).asEagerSingleton()
    }
}
