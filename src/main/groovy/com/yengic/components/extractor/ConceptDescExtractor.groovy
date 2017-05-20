package com.yengic.components.extractor

import com.yengic.common.BaseSnomedFileReader
import com.yengic.common.DescElement
import com.yengic.common.SnomedRef

import javax.inject.Inject

/**
 * Created by l080747 on 28/04/2017.
 */


class ConceptDescExtractor {

    BaseSnomedFileReader baseFileReader

    @Inject
    ConceptDescExtractor(BaseSnomedFileReader baseFileReader) {
        this.baseFileReader = baseFileReader
    }

    def extract(filePath) {
        def sr = SnomedRef.instance
        baseFileReader.readAndProcess(filePath, {
            values ->
                def conceptId = values[4]
                if (!sr.mapOfDesc.containsKey(conceptId)) {
                    def descElement = DescElement.newInstance()
                    descElement.build(values)
                    sr.mapOfDesc[conceptId] = descElement
                } else {
                    DescElement descElement = sr.mapOfDesc[conceptId]
                    descElement.build(values)
                }
        })
    }
}
