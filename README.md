# Prerequisite
* Install solc

http://solidity.readthedocs.io/en/develop/installing-solidity.html
* Install web3j command line tools 
https://docs.web3j.io/command_line.html

# Generate abi and bin files

copy cps_token_contract.sol to current directory

```bash
solc --overwrite --abi  --bin cps_token_contract.sol  -o ./
```

CPSTestToken1.bin CPSTestToken1.abi will be generated in current directory

Or if you do not want to install solc, get bin and abi content from etherscan.io and edit CPSTestToken1.bin CPSTestToken1.abi accordingly

A sample abi and bin is provided in this repo and corresponding contract java source files are generated.

# Generate contract java source files

```bash
web3j solidity generate CPSTestToken1.bin CPSTestToken1.abi -o src/main/java -p web3j.cps

```

# Run this spring boot application 

Open this maven project in Eclipse or Idea or other IDEs

Run Application (Application.java)

Navigate to http://localhost:8080/ you can see all transaction logs of V9CPS

# How to filter transactions of a block for transfer transactions of a given contract

If a smart contract is deployed at contract address.
```java
String contractAddress = "0xFFAB690958a463EB859B6348279A2F5FDdB8Eba1";
```
And you want to retrieve all transfer transactions of a block by number or hash
```java
String blockHash = "0x571f4284f02b81109083241e744fa0aab68c96a0837fc53ff6b8ec8641899ce9";
BigInteger blockNo = BigInteger.valueOf(142679);
```

First, you need to create corresponding web3j objects
```java
String transferFuncHash = "";
Function transferFunc = null;
Web3j web3j = Web3j.build(new HttpService("http://47.88.61.217:8080"));

List<Type> inputParams = new ArrayList<>();
inputParams.add(new Address("0xFFAB690958a463EB859B6348279A2F5FDdB8Eba1"));
inputParams.add(new Uint256(0xff));
List<TypeReference<?>> outputParams = new ArrayList<>();
outputParams.add(new TypeReference<Bool>() {
    @Override
    public int compareTo(TypeReference<Bool> o) {
        return super.compareTo(o);
    }
});
transferFunc = new Function("transfer", inputParams, outputParams);
transferFuncHash = FunctionEncoder.encode(transferFunc);

```

Second, you need to get the EthBlock object.
```java
EthBlock ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNo), true).send();

```
Or
```java
EthBlock ethBlock = web3j.ethGetBlockByHash(blockHash, true).send();
```

Then, use a for loop to enumerate all transactions
```java
String miner = ethBlock.getBlock().getMiner();
BigInteger blockSize = ethBlock.getBlock().getSize();
BigInteger timestamp = ethBlock.getBlock().getTimestamp();
String parentBlock = ethBlock.getBlock().getParentHash();

for (EthBlock.TransactionResult transactionResult : ethBlock.getBlock().getTransactions()){
    EthBlock.TransactionObject txn = (EthBlock.TransactionObject) transactionResult.get();
    if(txn.getTo().equalsIgnoreCase(contractAddress)){//check if this txn is a txn of contract at contractAddress
        if (txn.getInput().substring(0, 10).equals(transferFuncHash.substring(0,10))){//check if this is a transfer transaction
            Address address = CPSDecoder.decodeAddress(txn.getInput().substring(10));
            Uint256 amount = CPSDecoder.decodeUint256(txn.getInput().substring(75));

            result += ("parent block="+parentBlock+", block size="+blockSize+", timestamp="+timestamp+", miner="+miner+", block hash="+txn.getBlockHash()+", block number="+ txn.getBlockNumber() + ", gasPrice="+txn.getGasPrice()+", gas="+txn.getGas()+" txn: "+ txn.getFrom()+" transfer to "+address.toString() + " amount="+amount.getValue().toString())+"\n";
        }
    }
}
```

Please note that we use a CPSDecoder class to decode input to Java objects, the source of CPSDecoder is 
```java
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

```