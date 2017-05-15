package com.yengic.common;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

/**
 * Created by Pradeep on 15/05/2017.
 */

@RunWith(MockitoJUnitRunner)
class DescElementTest {

    String[] values
    DescElement descElement

    @Before
    void setUp() throws Exception {
        descElement =  DescElement.newInstance()
    }

    @Test
    void build() throws Exception {
        values = ["101013","20020131","1","900000000000207008","126813005","en","900000000000013009","Neoplasm of anterior","900000000000020002"]
        descElement.build(values)
        assertEquals(1, descElement.synonym.size())
        assertEquals(null, descElement.label)
        assertEquals(null, descElement.fsn)
    }

    @Test
    void buildWithFSN() throws Exception {
        values = ["101013","20020131","1","900000000000207008","126813005","en","900000000000003001","Neoplasm of anterior","900000000000020002"]
        descElement.build(values)
        assertEquals(0, descElement.synonym.size())
        assertEquals("NEOPLASM_OF_ANTERIOR", descElement.label)
        assertEquals("Neoplasm of anterior", descElement.fsn)
    }

    @Test
    void isFSN() throws Exception {
        assertTrue(descElement.isFSN("900000000000003001"))
    }

    @Test
    void isSynonym() throws Exception {
        assertTrue(descElement.isSynonym("900000000000013009"))
        assertFalse(descElement.isSynonym("900000000000003001"))
    }

}