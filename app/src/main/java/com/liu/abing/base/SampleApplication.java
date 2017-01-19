package com.liu.abing.base;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * 项目名称：abing
 * 类描述：
 * 创建人：liubing
 * 创建时间：2017/1/19 11:27
 * 修改人：Administrator
 * 修改时间：2017/1/19 11:27
 * 修改备注：
 */
public class SampleApplication extends TinkerApplication {

    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.liu.abing.base.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
