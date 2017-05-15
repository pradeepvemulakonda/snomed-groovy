import com.yengic.util.RelToLabelConverter
import org.junit.Before
import org.junit.Test

import static org.junit.Assert.*

/**
 * Created by Harita on 14/5/17.
 */
class RelToLabelConverterTest {

    RelToLabelConverter converter

    @Before
    void setUp(){
        converter = RelToLabelConverter.instance
    }

    @Test
    void testConvert(){
        assertEquals("FINDING_SITE", converter.convert("Finding site (attribute)"))
    }

    @Test
    void testConvertWithSpace(){
        assertEquals("FINDING_SITE", converter.convert("Finding site (attribute and)"))
    }

    @Test
    void testConvertWithSpecialChar(){
        assertEquals("FINDING_SITE", converter.convert("Fi%nd\$ing 'site (attribute and)"))
    }

}

