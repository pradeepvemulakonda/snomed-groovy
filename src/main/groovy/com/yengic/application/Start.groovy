package com.yengic.application

import com.google.inject.Guice
import com.google.inject.Injector
import com.yengic.common.SnomedModule
import com.yengic.common.SnomedRef
import com.yengic.components.extractor.ConceptDescExtractor
import com.yengic.components.uploader.RelationshipUploader
import groovy.time.TimeCategory
import groovy.time.TimeDuration

start = new Date()
Injector injector = Guice.createInjector(new SnomedModule())
def conceptExtractor = injector.getInstance(ConceptDescExtractor.class)

conceptExtractor.extract("C:\\MavenCode\\git\\snomed-groovy\\files\\SnomedCT_InternationalRF2_Production_20170131T120000\\Snapshot\\Terminology\\sct2_Description_Snapshot-en_INT_20170131.txt")
SnomedRef snomedRef = SnomedRef.instance
//def indexCreator = injector.getInstance(IndexCreator.class)
//indexCreator.upload(snomedRef)
//def conceptUpdater = injector.getInstance(ConceptUploader.class)
//conceptUpdater.upload(snomedRef)
//
//def relExtractor = injector.getInstance(RelationshipTypeExtractor.class)
//relExtractor.extract("C:\\MavenCode\\git\\snomed-groovy\\files\\SnomedCT_InternationalRF2_Production_20170131T120000\\Snapshot\\Terminology\\sct2_Relationship_Snapshot_INT_20170131.txt")

def relUploader = injector.getInstance(RelationshipUploader.class)
relUploader.upload(snomedRef, "C:\\MavenCode\\git\\snomed-groovy\\files\\SnomedCT_InternationalRF2_Production_20170131T120000\\Snapshot\\Terminology\\sct2_Relationship_Snapshot_INT_20170131.txt")

now = new Date()
TimeDuration td = TimeCategory.minus( now, start )
print td
