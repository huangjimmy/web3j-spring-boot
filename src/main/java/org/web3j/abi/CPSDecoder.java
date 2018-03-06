package org.web3j.abi;

import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.generated.Uint160;
import org.web3j.abi.datatypes.generated.Uint256;

public class CPSDecoder extends TypeDecoder {
    public static Address decodeAddress(String input) {
        return new Address((Uint160)decodeNumeric(input, Uint160.class));
    }

    public static Uint256 decodeUint256(String input){
        return decodeNumeric(
                input,
                Uint256.class
        );
    }
}
