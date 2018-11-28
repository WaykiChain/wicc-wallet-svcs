package com.waykichain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * <p><b>Created:</b> 16/08/16, 04:12 PM</p>
 * @author <a href="mailto:samuel.quintana@globant.com">samuel</a>
 * @since 0.1.0
 */
public class JsonRpcRequest {

    /** RPC Version. */
    @Getter
    private String jsonrpc = "2.0";
    /** JsonRPC Method name to call. */
    @Getter @Setter
    private String method;
    /** List of parameters to send. It can be a json serialized object or a simple array. */
    @Getter
    private List<Object> params = new ArrayList<>();
    /** Identifier of the call. */
    @Getter @Setter
    private Object id;

}
