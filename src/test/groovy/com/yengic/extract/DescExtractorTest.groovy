package com.yengic.extract

import com.yengic.common.BaseSnomedFileReader
import com.yengic.common.DescElement
import com.yengic.common.SnomedRef
import com.yengic.components.extractor.ConceptDescExtractor
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.invocation.InvocationOnMock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.Answer

import static org.mockito.ArgumentMatchers.any
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.*

/**
 * Created by Harita on 14/5/17.
 */

@RunWith(MockitoJUnitRunner)
class DescExtractorTest {

    @Mock BaseSnomedFileReader baseFileReader

    @InjectMocks ConceptDescExtractor conceptDescExtractor

    @Before
    void setUp() {
        when(baseFileReader.readAndProcess(anyString(), any(Closure.class))).then(new Answer<Object>() {
            @Override
            Object answer(InvocationOnMock invocation) throws Throwable {
                Closure closure = (Closure)invocation.arguments[1]
                String[] values = ["101013","20020131","1","900000000000207008","126813005","en","900000000000013009","Neoplasm of anterior aspect of epiglottis","900000000000020002"]
                closure values
                return null
            }
        })

    }

    @Test
    void executeTest() {
        conceptDescExtractor.extract("test")
        verify(baseFileReader).readAndProcess(anyString(), any(Closure.class))
    }
}
