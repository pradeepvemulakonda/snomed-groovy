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

    SnomedRef sr

    @Inject
    ConceptDescExtractor(BaseSnomedFileReader baseFileReader, SnomedRef sr) {
        this.baseFileReader = baseFileReader
        this.sr = sr
    }

    def extract(filePath) {
        def file = new File(filePath)
        baseFileReader.readAndProcess(file, {
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
