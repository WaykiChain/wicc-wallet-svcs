package com.waykichain.serialize.annotations;

/**
 *
 * Created at 8/13/16, 19:26.
 * @author <a href="sock.sqt@gmail.com">sockosg</a>
 * @since 1.0
 */
public enum SolidityType {

    UINT("uint"), UINT_8("uint8"), UINT_32("unit32"), UINT_256("uint256"),
    STRING("string"), BYTES("bytes"),
    BOOL("bool"),
    ADDRESS("address")
    ;

    private String solidityType;

    private SolidityType(String solidityType) {
        this.solidityType = solidityType;
    }

    @Override
    public String toString() {
        return this.solidityType;
    }

}
