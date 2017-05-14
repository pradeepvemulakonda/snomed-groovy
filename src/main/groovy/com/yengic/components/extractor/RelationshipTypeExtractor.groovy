package com.yengic.components.extractor

import com.yengic.common.BaseSnomedFileReader
import com.yengic.common.SnomedRef

/**
 * Created by l080747 on 28/04/2017.
 */


class RelationshipTypeExtractor {

    def extract(filePath) {
        SnomedRef sr = SnomedRef.instance
        def baseFileReader = new BaseSnomedFileReader()
        baseFileReader.readAndProcess(filePath, {
            values ->
                sr.setOfRefTermId << values[7]
        })
        print sr.setOfRefTermId
    }
}
