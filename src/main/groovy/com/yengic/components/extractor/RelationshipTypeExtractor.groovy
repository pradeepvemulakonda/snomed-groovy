package com.yengic.components.extractor

import com.yengic.common.BaseSnomedFileReader
import com.yengic.common.SnomedRef

import javax.inject.Inject

/**
 * Created by l080747 on 28/04/2017.
 */


class RelationshipTypeExtractor {

    BaseSnomedFileReader baseFileReader


    @Inject
    RelationshipTypeExtractor(BaseSnomedFileReader baseFileReader) {
        this.baseFileReader = baseFileReader
    }

    def extract(filePath) {
        def sr = SnomedRef.instance
        baseFileReader.readAndProcess(filePath, {
            values ->
                sr.setOfRefTermId << values[7]
        })
    }
}
