package com.yengic.components.extractor

import com.yengic.common.BaseSnomedFileReader
import com.yengic.common.SnomedRef

import javax.inject.Inject

/**
 * Created by l080747 on 28/04/2017.
 */


class RelationshipTypeExtractor {

    BaseSnomedFileReader baseFileReader

    SnomedRef sr

    @Inject
    RelationshipTypeExtractor(BaseSnomedFileReader baseFileReader, SnomedRef sr) {
        this.baseFileReader = baseFileReader
        this.sr = sr
    }

    def extract(filePath) {
        baseFileReader.readAndProcess(filePath, {
            values ->
                sr.setOfRefTermId << values[7]
        })
        print sr.setOfRefTermId
    }
}
