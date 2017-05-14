package com.yengic.util

/**
 * Created by Harita on 14/5/17.
 */

@Singleton
class RelToLabelConverter {

    def convert(String term) {
        def stringBetweenBrackets = ~/\(.*\)/
        def modifiedTerm = term.replaceAll(stringBetweenBrackets, "")
        modifiedTerm.trim() replaceAll ~/[^A-Za-z\s]/, "" replaceAll ~/\s+/, "_" toUpperCase()
    }
}
