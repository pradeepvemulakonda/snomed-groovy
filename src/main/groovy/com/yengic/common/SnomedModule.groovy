package com.yengic.common

import com.google.inject.AbstractModule
import com.yengic.components.extractor.ConceptDescExtractor

/**
 * Created by Harita on 14/5/17.
 */
class SnomedModule extends AbstractModule{

    @Override
    protected void configure() {
        bind(BaseSnomedFileReader.class)
        bind(SnomedRef.class).asEagerSingleton()
    }
}
