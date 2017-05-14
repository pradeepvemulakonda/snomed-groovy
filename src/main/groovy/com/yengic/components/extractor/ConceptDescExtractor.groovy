package com.yengic.components.extractor

import com.yengic.common.BaseSnomedFileReader
import com.yengic.common.DescElement
import com.yengic.common.SnomedRef
import com.yengic.util.RelToLabelConverter
import sun.security.krb5.internal.crypto.Des

/**
 * Created by l080747 on 28/04/2017.
 */


class ConceptDescExtractor {

    def extract(filePath) {
        SnomedRef sr = SnomedRef.instance
        def baseFileReader = new BaseSnomedFileReader()
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
