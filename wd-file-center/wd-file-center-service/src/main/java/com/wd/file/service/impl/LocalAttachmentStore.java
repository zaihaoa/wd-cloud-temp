package com.wd.file.service.impl;


import com.wd.file.enums.AttachmentStoreTypeEnum;
import com.wd.file.service.AttachmentStore;

import java.io.InputStream;

/**
 * @author huangwenda
 * @date 2023/8/21 19:00
 **/
public class LocalAttachmentStore implements AttachmentStore {
    @Override
    public AttachmentStoreTypeEnum storeType() {
        return AttachmentStoreTypeEnum.LOCAL;
    }

    @Override
    public void upload(String filename, InputStream in) {

    }
}
