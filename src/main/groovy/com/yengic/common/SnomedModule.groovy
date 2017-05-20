package com.yengic.common

import com.google.inject.AbstractModule
import com.yengic.components.builder.Neo4jCommandExecutor
import com.yengic.components.extractor.ConceptDescExtractor
import com.yengic.components.uploader.ConceptUploader
import com.yengic.components.uploader.IndexCreator
import com.yengic.util.ThreadPoolExecutor

/**
 * Created by Harita on 14/5/17.
 */
class SnomedModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(BaseSnomedFileReader.class)
        bind(Neo4jCommandExecutor.class)
        bind(ThreadPoolExecutor.class)
        bind(IndexCreator.class)
        bind(ConceptUploader.class)
    }
}
