package web3j.cps;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.EventValues;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import rx.Observable;
import rx.functions.Func1;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 3.2.0.
 */
public class CPSTestToken1 extends Contract {
    public static String BINARY = "606060405234156200001057600080fd5b60405162002baa38038062002baa833981016040528080518201919060200180518201919060200180519060200190919080519060200190919050508260039080519060200190620000649291906200016f565b5083600290805190602001906200007d9291906200016f565b5081600460006101000a81548160ff021916908360ff1602179055508060058190555080600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555033600660006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550336000806101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050506200021e565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f10620001b257805160ff1916838001178555620001e3565b82800160010185558215620001e3579182015b82811115620001e2578251825591602001919060010190620001c5565b5b509050620001f29190620001f6565b5090565b6200021b91905b8082111562000217576000816000905550600101620001fd565b5090565b90565b61297c806200022e6000396000f300606060405260043610610107576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff16806306fdde0314610144578063095ea7b3146101d257806318160ddd1461022c578063191723ed146102555780632194f3a21461028157806323b872dd146102d6578063286c241a1461034f578063313ce5671461039c578063323661f6146103cb57806359355736146103e0578063661884631461042d57806370a08231146104875780638da5cb5b146104d457806395d89b4114610529578063a201ed8b146105b7578063a9059cbb14610667578063be45fd62146106c1578063d73dd6231461073b578063dd62ed3e14610795575b600073ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614151561014257600080fd5b005b341561014f57600080fd5b610157610801565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561019757808201518184015260208101905061017c565b50505050905090810190601f1680156101c45780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34156101dd57600080fd5b610212600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919080359060200190919050506108a9565b604051808215151515815260200191505060405180910390f35b341561023757600080fd5b61023f61099b565b6040518082815260200191505060405180910390f35b341561026057600080fd5b61027f60048080359060200190919080359060200190919050506109a5565b005b341561028c57600080fd5b610294610cb8565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b34156102e157600080fd5b610335600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035906020019091905050610cde565b604051808215151515815260200191505060405180910390f35b341561035a57600080fd5b610386600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611394565b6040518082815260200191505060405180910390f35b34156103a757600080fd5b6103af6113e0565b604051808260ff1660ff16815260200191505060405180910390f35b34156103d657600080fd5b6103de6113f7565b005b34156103eb57600080fd5b610417600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190505061159e565b6040518082815260200191505060405180910390f35b341561043857600080fd5b61046d600480803573ffffffffffffffffffffffffffffffffffffffff169060200190919080359060200190919050506115ea565b604051808215151515815260200191505060405180910390f35b341561049257600080fd5b6104be600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050611872565b6040518082815260200191505060405180910390f35b34156104df57600080fd5b6104e76118bb565b604051808273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200191505060405180910390f35b341561053457600080fd5b61053c6118e0565b6040518080602001828103825283818151815260200191508051906020019080838360005b8381101561057c578082015181840152602081019050610561565b50505050905090810190601f1680156105a95780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b61064d60048080359060200190820180359060200190808060200260200160405190810160405280939291908181526020018383602002808284378201915050505050509190803590602001908201803590602001908080602002602001604051908101604052809392919081815260200183836020028082843782019150505050505091908035906020019091905050611988565b604051808215151515815260200191505060405180910390f35b341561067257600080fd5b6106a7600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035906020019091905050611e8e565b604051808215151515815260200191505060405180910390f35b610739600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803590602001909190803590602001908201803590602001908080601f01602080910402602001604051908101604052809392919081815260200183838082843782019150505050505091905050612207565b005b341561074657600080fd5b61077b600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091908035906020019091905050612544565b604051808215151515815260200191505060405180910390f35b34156107a057600080fd5b6107eb600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050612737565b6040518082815260200191505060405180910390f35b610809612928565b60028054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561089f5780601f106108745761010080835404028352916020019161089f565b820191906000526020600020905b81548152906001019060200180831161088257829003601f168201915b5050505050905090565b600081600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925846040518082815260200191505060405180910390a36001905092915050565b6000600554905090565b60003390508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff16141580156109e75750600082115b80156109f35750600083115b8015610a07575081610a0482611872565b10155b1515610a1257600080fd5b42600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004015411158015610aa657506000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010154145b1515610ab157600080fd5b42600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002018190555082600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206003018190555082600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206002015401600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004018190555081600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060010181905550610c1b81836127be565b8073ffffffffffffffffffffffffffffffffffffffff167f4a5ed3c7d7f33c8c80b3444f04527e6d3bee954c19dac37176e4aa1a86ce8728600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000206004015484604051808381526020018281526020019250505060405180910390a2505050565b600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b600080610ce961293c565b600073ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff1614151515610d2557600080fd5b600760008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548411151515610d7357600080fd5b600860008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548411151515610dfe57600080fd5b600660009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff161415610ea357600760008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548411151515610ea257600080fd5b5b610eac8561284e565b15610ffe578491506001604051805910610ec35750595b9080825280601f01601f191660200182016040525090508173ffffffffffffffffffffffffffffffffffffffff1663c0ee0b8a3386846040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b83811015610f9c578082015181840152602081019050610f81565b50505050905090810190601f168015610fc95780820380516001836020036101000a031916815260200191505b50945050505050600060405180830381600087803b1515610fe957600080fd5b6102c65a03f11515610ffa57600080fd5b5050505b611047600760008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205485612861565b600760008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506110d3600760008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548561287a565b600760008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061119c600860008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205485612861565b600860008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055506112a2600860008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205485612861565b600860008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508473ffffffffffffffffffffffffffffffffffffffff168673ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef866040518082815260200191505060405180910390a36001925050509392505050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401549050919050565b6000600460009054906101000a900460ff16905090565b60003390508073ffffffffffffffffffffffffffffffffffffffff16600073ffffffffffffffffffffffffffffffffffffffff16141580156114415750600061143f8261159e565b115b801561145557504261145282611394565b11155b151561146057600080fd5b6114728161146d8361159e565b612898565b8073ffffffffffffffffffffffffffffffffffffffff167ff2a470701c29165d36d10c35e36dac1dc397594484071f35785a55c8589be0fa600160008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600401546114f68461159e565b604051808381526020018281526020019250505060405180910390a2600160008273ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600080820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160009055600282016000905560038201600090556004820160009055505050565b6000600160008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020600101549050919050565b600080600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050808311156116fb576000600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550611786565b6117058184612861565b600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505b8373ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008873ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546040518082815260200191505060405180910390a3600191505092915050565b6000600760008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020549050919050565b6000809054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681565b6118e8612928565b60038054600181600116156101000203166002900480601f01602080910402602001604051908101604052809291908181526020018280546001816001161561010002031660029004801561197e5780601f106119535761010080835404028352916020019161197e565b820191906000526020600020905b81548152906001019060200180831161196157829003601f168201915b5050505050905090565b600080600080600061199861293c565b600094506000935060009250600092505b86831015611c0f57600073ffffffffffffffffffffffffffffffffffffffff1689848151811015156119d757fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1614158015611a225750611a208984815181101515611a1157fe5b9060200190602002015161284e565b155b1515611a2d57600080fd5b611a4d8984815181101515611a3e57fe5b9060200190602002015161284e565b15611bcd578883815181101515611a6057fe5b9060200190602002015191506001604051805910611a7b5750595b9080825280601f01601f191660200182016040525090508173ffffffffffffffffffffffffffffffffffffffff1663c0ee0b8a338a86815181101515611abd57fe5b90602001906020020151846040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b83811015611b6b578082015181840152602081019050611b50565b50505050905090810190601f168015611b985780820380516001836020036101000a031916815260200191505b50945050505050600060405180830381600087803b1515611bb857600080fd5b6102c65a03f11515611bc957600080fd5b5050505b849350611bf1858985815181101515611be257fe5b9060200190602002015161287a565b9450838510151515611c0257600080fd5b82806001019350506119a9565b600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548511151515611c5d57600080fd5b600092505b86831015611e7e57611cca600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548985815181101515611cbb57fe5b90602001906020020151612861565b600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550611d84600760008b86815181101515611d2257fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548985815181101515611d7557fe5b9060200190602002015161287a565b600760008b86815181101515611d9657fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508883815181101515611dec57fe5b9060200190602002015173ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef8a86815181101515611e5257fe5b906020019060200201516040518082815260200191505060405180910390a38280600101935050611c62565b6001955050505050509392505050565b600080611e9961293c565b600073ffffffffffffffffffffffffffffffffffffffff168573ffffffffffffffffffffffffffffffffffffffff1614151515611ed557600080fd5b600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548411151515611f2357600080fd5b611f2c8561284e565b1561207e578491506001604051805910611f435750595b9080825280601f01601f191660200182016040525090508173ffffffffffffffffffffffffffffffffffffffff1663c0ee0b8a3386846040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b8381101561201c578082015181840152602081019050612001565b50505050905090810190601f1680156120495780820380516001836020036101000a031916815260200191505b50945050505050600060405180830381600087803b151561206957600080fd5b6102c65a03f1151561207a57600080fd5b5050505b6120c7600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205485612861565b600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550612153600760008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548561287a565b600760008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508473ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef866040518082815260200191505060405180910390a360019250505092915050565b6000808311151561221757600080fd5b6122208461284e565b1561234c578390508073ffffffffffffffffffffffffffffffffffffffff1663c0ee0b8a3385856040518463ffffffff167c0100000000000000000000000000000000000000000000000000000000028152600401808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200183815260200180602001828103825283818151815260200191508051906020019080838360005b838110156122ea5780820151818401526020810190506122cf565b50505050905090810190601f1680156123175780820380516001836020036101000a031916815260200191505b50945050505050600060405180830381600087803b151561233757600080fd5b6102c65a03f1151561234857600080fd5b5050505b61239e83600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461286190919063ffffffff16565b600760003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000208190555061243383600760008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205461287a90919063ffffffff16565b600760008673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002081905550816040518082805190602001908083835b6020831015156124ac5780518252602082019150602081019050602083039250612487565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390208473ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167fe19260aff97b920c7df27010903aeb9c8d2be5d310a2c67824cf3f15396e4c16866040518082815260200191505060405180910390a450505050565b60006125cc600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548361287a565b600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008573ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055508273ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff167f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925600860003373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008773ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020546040518082815260200191505060405180910390a36001905092915050565b6000600860008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002060008373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16815260200190815260200160002054905092915050565b612807600760008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020019081526020016000205482612861565b600760008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505050565b600080823b905060008111915050919050565b600082821115151561286f57fe5b818303905092915050565b600080828401905083811015151561288e57fe5b8091505092915050565b6128e1600760008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020548261287a565b600760008473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001908152602001600020819055505050565b602060405190810160405280600081525090565b6020604051908101604052806000815250905600a165627a7a72305820bf88c33360cdd6af0b8cc8a39973e5ea6b1e12732c027c8e4720b6168d38f4ab0029";

    protected CPSTestToken1(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected CPSTestToken1(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public List<LockBalanceEventResponse> getLockBalanceEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LockBalance", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LockBalanceEventResponse> responses = new ArrayList<LockBalanceEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LockBalanceEventResponse typedResponse = new LockBalanceEventResponse();
            typedResponse.addressOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.releasetime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LockBalanceEventResponse> lockBalanceEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LockBalance", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LockBalanceEventResponse>() {
            @Override
            public LockBalanceEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LockBalanceEventResponse typedResponse = new LockBalanceEventResponse();
                typedResponse.addressOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.releasetime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<LockSubBalanceEventResponse> getLockSubBalanceEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("LockSubBalance", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<LockSubBalanceEventResponse> responses = new ArrayList<LockSubBalanceEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            LockSubBalanceEventResponse typedResponse = new LockSubBalanceEventResponse();
            typedResponse.addressOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.releasetime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<LockSubBalanceEventResponse> lockSubBalanceEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("LockSubBalance", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, LockSubBalanceEventResponse>() {
            @Override
            public LockSubBalanceEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                LockSubBalanceEventResponse typedResponse = new LockSubBalanceEventResponse();
                typedResponse.addressOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.releasetime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public List<UnlockBalanceEventResponse> getUnlockBalanceEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("UnlockBalance", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<UnlockBalanceEventResponse> responses = new ArrayList<UnlockBalanceEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            UnlockBalanceEventResponse typedResponse = new UnlockBalanceEventResponse();
            typedResponse.addressOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.releasetime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<UnlockBalanceEventResponse> unlockBalanceEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("UnlockBalance", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, UnlockBalanceEventResponse>() {
            @Override
            public UnlockBalanceEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                UnlockBalanceEventResponse typedResponse = new UnlockBalanceEventResponse();
                typedResponse.addressOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.releasetime = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public List<UnlockSubBalanceEventResponse> getUnlockSubBalanceEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("UnlockSubBalance", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<UnlockSubBalanceEventResponse> responses = new ArrayList<UnlockSubBalanceEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            UnlockSubBalanceEventResponse typedResponse = new UnlockSubBalanceEventResponse();
            typedResponse.addressOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.releasetime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<UnlockSubBalanceEventResponse> unlockSubBalanceEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("UnlockSubBalance", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, UnlockSubBalanceEventResponse>() {
            @Override
            public UnlockSubBalanceEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                UnlockSubBalanceEventResponse typedResponse = new UnlockSubBalanceEventResponse();
                typedResponse.addressOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.index = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.releasetime = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokens = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<TransferEventResponse> transferEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Transfer", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokens = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        final Event event = new Event("Approval", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        List<EventValues> valueList = extractEventParameters(event, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (EventValues eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.tokenOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.tokens = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Observable<ApprovalEventResponse> approvalEventObservable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        final Event event = new Event("Approval", 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(event));
        return web3j.ethLogObservable(filter).map(new Func1<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse call(Log log) {
                EventValues eventValues = extractEventParameters(event, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.tokenOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.tokens = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public RemoteCall<String> name() {
        Function function = new Function("name", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> approve(String _spender, BigInteger _value) {
        Function function = new Function(
                "approve", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> totalSupply() {
        Function function = new Function("totalSupply", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> lockBalance(BigInteger duration, BigInteger amount) {
        Function function = new Function(
                "lockBalance", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(duration), 
                new org.web3j.abi.datatypes.generated.Uint256(amount)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<String> fundsWallet() {
        Function function = new Function("fundsWallet", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transferFrom(String _from, String _to, BigInteger _value) {
        Function function = new Function(
                "transferFrom", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_from), 
                new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> releaseTimeOf(String _owner) {
        Function function = new Function("releaseTimeOf", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<BigInteger> decimals() {
        Function function = new Function("decimals", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint8>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> releaseLockedBalance() {
        Function function = new Function(
                "releaseLockedBalance", 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> lockedBalanceOf(String _owner) {
        Function function = new Function("lockedBalanceOf", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> decreaseApproval(String _spender, BigInteger _subtractedValue) {
        Function function = new Function(
                "decreaseApproval", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_subtractedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> balanceOf(String _owner) {
        Function function = new Function("balanceOf", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<String> owner() {
        Function function = new Function("owner", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<String> symbol() {
        Function function = new Function("symbol", 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteCall<TransactionReceipt> transferMultiple(List<String> _tos, List<BigInteger> _values, BigInteger count, BigInteger weiValue) {
        Function function = new Function(
                "transferMultiple", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.Address>(
                        org.web3j.abi.Utils.typeMap(_tos, org.web3j.abi.datatypes.Address.class)), 
                new org.web3j.abi.datatypes.DynamicArray<org.web3j.abi.datatypes.generated.Uint256>(
                        org.web3j.abi.Utils.typeMap(_values, org.web3j.abi.datatypes.generated.Uint256.class)), 
                new org.web3j.abi.datatypes.generated.Uint256(count)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value) {
        Function function = new Function(
                "transfer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<TransactionReceipt> transfer(String _to, BigInteger _value, byte[] _data, BigInteger weiValue) {
        Function function = new Function(
                "transfer", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_to), 
                new org.web3j.abi.datatypes.generated.Uint256(_value), 
                new org.web3j.abi.datatypes.DynamicBytes(_data)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function, weiValue);
    }

    public RemoteCall<TransactionReceipt> increaseApproval(String _spender, BigInteger _addedValue) {
        Function function = new Function(
                "increaseApproval", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_spender), 
                new org.web3j.abi.datatypes.generated.Uint256(_addedValue)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<BigInteger> allowance(String _owner, String _spender) {
        Function function = new Function("allowance", 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(_owner), 
                new org.web3j.abi.datatypes.Address(_spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public static RemoteCall<CPSTestToken1> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, BigInteger decimals, BigInteger totalSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint8(decimals), 
                new org.web3j.abi.datatypes.generated.Uint256(totalSupply)));
        return deployRemoteCall(CPSTestToken1.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static RemoteCall<CPSTestToken1> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String name, String symbol, BigInteger decimals, BigInteger totalSupply) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(name), 
                new org.web3j.abi.datatypes.Utf8String(symbol), 
                new org.web3j.abi.datatypes.generated.Uint8(decimals), 
                new org.web3j.abi.datatypes.generated.Uint256(totalSupply)));
        return deployRemoteCall(CPSTestToken1.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static CPSTestToken1 load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CPSTestToken1(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    public static CPSTestToken1 load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CPSTestToken1(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static class LockBalanceEventResponse {
        public String addressOwner;

        public BigInteger releasetime;

        public BigInteger amount;
    }

    public static class LockSubBalanceEventResponse {
        public String addressOwner;

        public BigInteger index;

        public BigInteger releasetime;

        public BigInteger amount;
    }

    public static class UnlockBalanceEventResponse {
        public String addressOwner;

        public BigInteger releasetime;

        public BigInteger amount;
    }

    public static class UnlockSubBalanceEventResponse {
        public String addressOwner;

        public BigInteger index;

        public BigInteger releasetime;

        public BigInteger amount;
    }

    public static class TransferEventResponse {
        public String from;

        public String to;

        public BigInteger tokens;
    }

    public static class ApprovalEventResponse {
        public String tokenOwner;

        public String spender;

        public BigInteger tokens;
    }

}
