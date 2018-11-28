package com.waykichain.encrypt;

import org.apache.commons.codec.binary.Hex;
import org.kocakosm.pitaya.security.Digest;
import org.kocakosm.pitaya.security.Digests;

/**
 * <p><b>Created:</b> 15/08/16, 04:57 PM</p>
 *
 * @author <a href="mailto:sock.sqt@gmail.com">samuel</a>
 * @since 0.1.0
 */
public class KeccakUtils {

    public static String sha3(String input) {
        return sha3(input.getBytes());
    }

    public static String sha3(byte[] input) {
        Digest sha3 = Digests.keccak256();
        byte[] hash = sha3.digest(input);
        return Hex.encodeHexString(hash);
    }

}
