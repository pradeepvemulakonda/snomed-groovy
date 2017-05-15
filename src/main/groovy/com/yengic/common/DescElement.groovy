package com.yengic.common

import com.yengic.util.RelToLabelConverter

/**
 * Created by Harita on 14/5/17.
 */
class DescElement {
    public static final String FSN = "900000000000003001"
    public static final String SYNONYM = "900000000000013009"
    def label
    def synonym = []
    def fsn
    def description = []

    def build(values) {
        def descType = values[6]
        def term = values[7]
        if (isFSN(descType)) {
            this.fsn = term
            this.label = RelToLabelConverter.instance.convert(term)
        } else if (isSynonym(descType)) {
            this.synonym << term
        } else {
            this.description << term
        }
    }

    def isFSN(descId) {
        descId == FSN
    }

    def isSynonym(descId) {
        descId == SYNONYM
    }
}
