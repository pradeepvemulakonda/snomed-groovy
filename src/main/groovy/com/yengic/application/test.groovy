package com.yengic.application

import com.google.inject.Guice
import com.google.inject.Injector
import com.yengic.common.SnomedModule
import com.yengic.components.extractor.ConceptDescExtractor
import com.yengic.components.extractor.RelationshipTypeExtractor

/**
 * Created by l080747 on 28/04/2017.
 */

Injector injector = Guice.createInjector(new SnomedModule())
def conceptExtractor = injector.getInstance(ConceptDescExtractor.class)
def relExtractor = injector.getInstance(RelationshipTypeExtractor.class)

conceptExtractor.extract("/Users/Harita/IdeaProjects/snomed-groovy-new/src/main/resources/sct2_Description_Snapshot-en_INT_20170131.txt")
relExtractor.extract("/Users/Harita/IdeaProjects/snomed-groovy-new/src/main/resources/sct2_Description_Snapshot-en_INT_20170131.txt")

//print new com.yengic.util.JedisClient().connect().getDescription("126813005")

