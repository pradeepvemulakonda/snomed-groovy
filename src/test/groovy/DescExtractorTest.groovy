import com.yengic.common.BaseSnomedFileReader
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

    @Mock SnomedRef sr

    @InjectMocks ConceptDescExtractor conceptDescExtractor

    @Before
    void setUp() {
        when(baseFileReader.readAndProcess(anyString(), any(Closure.class))).then(new Answer<Object>() {
            @Override
            Object answer(InvocationOnMock invocation) throws Throwable {
                Closure closure = (Closure)invocation.arguments[1]
                String[] values = ["1234", "1234", "1234", "1234", "1234", "1234", "1234", "1234"]
                closure values
                return null
            }
        })

        when(sr.mapOfDesc).thenReturn([:])
        when(sr.setOfRefTermId).thenReturn([] as Set)
        when(sr.mapOfRef).thenReturn([:])
    }

    @Test
    void executeTest() {
        conceptDescExtractor.extract("test")
        verify(sr, times(2)).mapOfDesc
        verify(baseFileReader).readAndProcess(anyString(), any(Closure.class))

        Assert.assertEquals(1, sr.mapOfDesc.size())
    }
}
