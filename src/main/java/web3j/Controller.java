package web3j;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.abi.*;
import org.web3j.abi.datatypes.*;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthBlock;
import org.web3j.protocol.core.methods.response.EthLog;
import org.web3j.protocol.core.methods.response.EthTransaction;
import org.web3j.protocol.http.HttpService;
import rx.Observable;
import web3j.cps.CPSTestToken1;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.web3j.tx.Contract.GAS_LIMIT;
import static org.web3j.tx.Contract.GAS_PRICE;

@RestController
public class Controller {

    String contractAddress = "0xFFAB690958a463EB859B6348279A2F5FDdB8Eba1";
    String transferFuncHash = "";
    Function transferFunc = null;
    Web3j web3j = Web3j.build(new HttpService("http://47.88.61.217:8080"));

    //a random private key that is publicly known
    //web3j requires a valid key pair, we only do readonly operations but we have to create a credentials otherwise we cannot
    //instantiate a valid contract object of type CPSTestToken1
    Credentials credentials = Credentials.create("3a1076bf45ab87712ad64ccb3b10217737f7faacbf2872e88fdd9a537d8fe266");
    CPSTestToken1 cps = CPSTestToken1.load(contractAddress, web3j, credentials, GAS_PRICE, GAS_LIMIT);

    //See CPSTestToken1.java for more Ethereum Contract Events
    Event lockFundExEvent = new Event("LockFundEx",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    String lockFundExEventHash = EventEncoder.encode(lockFundExEvent);

    Event unlockFundExEvent = new Event("UnlockFundEx",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    String unlockFundExEventHash = EventEncoder.encode(unlockFundExEvent);

    Event transferEvent = new Event("Transfer",
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Address>() {}),
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
    String transferEventHash = EventEncoder.encode(transferEvent);

    Observable<CPSTestToken1.TransferEventResponse> transferEventResponseObservable = cps.transferEventObservable(DefaultBlockParameterName.EARLIEST,
            DefaultBlockParameterName.LATEST);
    Observable<CPSTestToken1.UnlockFundExEventResponse>  unlockFundExEventResponseObservable = cps.unlockFundExEventObservable(DefaultBlockParameterName.EARLIEST,
    DefaultBlockParameterName.LATEST);


    public Controller(){

        //observe all V9CPS transfer events
        //
        //if you only want to observe events occurs after a certain block, say 5119110,
        //change cps.transferEventObservable(DefaultBlockParameterName.EARLIEST,
        //            DefaultBlockParameterName.LATEST);
        // to
        //cps.transferEventObservable(DefaultBlockParameter.valueOf(BigInteger.valueOf(5119110)),
        //            DefaultBlockParameterName.LATEST);
       transferEventResponseObservable.subscribe(event -> {
           //if block hash, block number, tx id are needed, pls customize transferEventObservable of CPSTestToken1.java.
           System.out.println("\nblock number: "+event.blockNumber+" block hash:"+event.blockHash+" txn:"+event.txnHash+"\n "+event.from + " transfer "+ event.value + " to "+event.to);
       });

        unlockFundExEventResponseObservable.subscribe(event -> {
            System.out.println("\nblock number: "+event.blockNumber+" block hash:"+event.blockHash+" txn:"+event.txnHash+"\n "+event.from + " unlock "+event.unlockAmount + " at "+event.unlockTimestamp +" deadline="+event.deadline);
       });

        try {
            EthTransaction transaction = web3j.ethGetTransactionByHash("0x38a8c186f928f6e4afafb0e471cc9b9366e2e92f562edc562a00ef4b75593a92").send();
            System.out.println("from="+transaction.getResult().getFrom());
            System.out.println("input="+transaction.getResult().getInput());
            System.out.println("gas="+transaction.getResult().getGas());
            System.out.println("block number="+transaction.getResult().getBlockHash());
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    }

    @RequestMapping("/txns_of_blockByHash/{blockHash}")
    public String txns(@PathVariable String blockHash){
        try {
            EthBlock ethBlock = web3j.ethGetBlockByHash(blockHash, false).send();
            System.out.println(ethBlock.getBlock().getTransactions());
            String result = "";
            for (EthBlock.TransactionResult transactionResult : ethBlock.getBlock().getTransactions()){
                EthBlock.TransactionHash transactionHash = (EthBlock.TransactionHash)transactionResult;
                result += "txn hash:"+transactionHash.get()+"\n";
            }
            return result+"\n\n txns \n\n"+txns(ethBlock.getBlock().getNumber());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    /**
     * retrieve a block by its block number and filter all transfer transactions of 0xFFAB690958a463EB859B6348279A2F5FDdB8Eba1 token
     * @param blockNo
     * @return
     */
    @RequestMapping("/txns_of_blockByNumber/{blockNo}")
    public String txns(@PathVariable BigInteger blockNo){
        String result = "\n";
        try {
            EthBlock ethBlock = web3j.ethGetBlockByNumber(DefaultBlockParameter.valueOf(blockNo), true).send();

            String miner = ethBlock.getBlock().getMiner();
            BigInteger blockSize = ethBlock.getBlock().getSize();
            BigInteger timestamp = ethBlock.getBlock().getTimestamp();
            String parentBlock = ethBlock.getBlock().getParentHash();

            for (EthBlock.TransactionResult transactionResult : ethBlock.getBlock().getTransactions()){
                EthBlock.TransactionObject txn = (EthBlock.TransactionObject) transactionResult.get();
                System.out.println(txn.getTo()+" == " +contractAddress);
                if(txn.getTo().equalsIgnoreCase(contractAddress)){//check if this txn is a txn of contract at contractAddress
                    if (txn.getInput().substring(0, 10).equals(transferFuncHash.substring(0,10))){//check if this is a transfer transaction
                        Address address = CPSDecoder.decodeAddress(txn.getInput().substring(10));
                        Uint256 amount = CPSDecoder.decodeUint256(txn.getInput().substring(75));

                        result += ("parent block="+parentBlock+", block size="+blockSize+", timestamp="+timestamp+", miner="+miner+", block hash="+txn.getBlockHash()+", block number="+ txn.getBlockNumber() + ", gasPrice="+txn.getGasPrice()+", gas="+txn.getGas()+" txn: "+ txn.getFrom()+" transfer to "+address.toString() + " amount="+amount.getValue().toString())+"\n";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @RequestMapping("/")
    public String index() {

        //the following contract address has a valid ERC20 smart contract deployed

        String result = "";

        try {
            BigInteger balance = cps.balanceOf("0x42d7d44a95aca5b49fcf749007a0e1dafa4427c3").send();
            result += "Balance of 0x42d7d44a95aca5b49fcf749007a0e1dafa4427c3="+balance;
            BigInteger decimals = cps.decimals().send();
            result += "\n\ndecimals="+decimals;

            EthFilter filterReq = new EthFilter(DefaultBlockParameterName.EARLIEST,
                    DefaultBlockParameterName.LATEST, contractAddress);
            org.web3j.protocol.core.methods.response.EthFilter filter = web3j.ethNewFilter(filterReq).send();
            EthLog ethLog = web3j.ethGetFilterLogs(filter.getFilterId()).send();
            for(EthLog.LogResult logResult : ethLog.getLogs()){//0xc07d0a5ac66f95424145a11a0f4776733362734d5e9fa8e6ecdff0cd5968b04d
                EthLog.LogObject logObject = (EthLog.LogObject) logResult.get();
                System.out.println(logObject);
                if(logObject.getTopics().size() < 1)continue;

                String eventHash =logObject.getTopics().get(0);

                if (eventHash.compareTo(lockFundExEventHash) == 0){

                    EventValues eventValues = cps.staticExtractEventParameters(lockFundExEvent, logObject);

                    CPSTestToken1.LockFundEventResponse typedResponse = new CPSTestToken1.LockFundEventResponse();
                    typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                    typedResponse.deadline = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                    typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();

                    result += "\n\nLockFundExEvent: from="+typedResponse.from+" deadline="+typedResponse.deadline+" amount="+typedResponse.amount+" block number="+logObject.getBlockNumber();
                }
                else if (eventHash.compareTo(unlockFundExEventHash) == 0){

                    EventValues eventValues = cps.staticExtractEventParameters(unlockFundExEvent, logObject);

                    CPSTestToken1.UnlockFundExEventResponse typedResponse = new CPSTestToken1.UnlockFundExEventResponse();
                    typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                    typedResponse.cycle = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                    typedResponse.unlockTimestamp = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                    typedResponse.deadline = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
                    typedResponse.unlockAmount = (BigInteger) eventValues.getNonIndexedValues().get(3).getValue();

                    result += "\n\nUnlockFundExEvent: from="+typedResponse.from+" unlockTimestamp="+typedResponse.unlockTimestamp+" deadline="+typedResponse.deadline+" amount="+typedResponse.unlockAmount+" block number="+logObject.getBlockNumber();
                }
                else if(eventHash.compareTo(transferEventHash) == 0){

                    EventValues eventValues = cps.staticExtractEventParameters(transferEvent, logObject);

                    CPSTestToken1.TransferEventResponse typedResponse = new CPSTestToken1.TransferEventResponse();
                    typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                    typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                    typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
