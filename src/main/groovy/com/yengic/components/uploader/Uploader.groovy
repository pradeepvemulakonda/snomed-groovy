package com.yengic.components.uploader

import com.yengic.common.SnomedRef

/**
 * Created by l080747 on 16/05/2017.
 */
interface Uploader {
    def upload(SnomedRef snomedRef)
}