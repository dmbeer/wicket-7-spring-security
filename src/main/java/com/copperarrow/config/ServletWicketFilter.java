package com.copperarrow.config;

import org.apache.wicket.protocol.http.WicketFilter;

import javax.servlet.DispatcherType;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * Created by dbeer on 10/01/17.
 */
@WebFilter(value = "/*", initParams = {@WebInitParam(name = "applicationClassName", value = "com.copperarrow.WicketApplication"),
                            @WebInitParam(name = "filterMappingPattern", value = "/*")},
        dispatcherTypes = {DispatcherType.REQUEST, DispatcherType.ERROR})
public class ServletWicketFilter extends WicketFilter {
}
