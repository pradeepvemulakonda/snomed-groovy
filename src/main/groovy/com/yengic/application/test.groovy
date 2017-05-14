package com.yengic.application

import com.yengic.components.extractor.ConceptDescExtractor
import com.yengic.components.extractor.RelationshipTypeExtractor

/**
 * Created by l080747 on 28/04/2017.
 */

new ConceptDescExtractor().extract("/Users/Harita/IdeaProjects/snomed-groovy-new/src/main/resources/sct2_Description_Snapshot-en_INT_20170131.txt")
new RelationshipTypeExtractor().extract("/Users/Harita/IdeaProjects/snomed-groovy-new/src/main/resources/sct2_Description_Snapshot-en_INT_20170131.txt")

//print new com.yengic.util.JedisClient().connect().getDescription("126813005")

