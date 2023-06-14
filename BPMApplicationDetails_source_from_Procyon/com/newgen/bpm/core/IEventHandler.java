
package com.newgen.bpm.core;

import com.newgen.bpm.response.BPMApplicationDetailsResponse;

public interface IEventHandler
{
    BPMApplicationDetailsResponse dispatchEvent(final CoreEvent p0) throws Exception;
}
