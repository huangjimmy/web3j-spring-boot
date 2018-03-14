package web3j;

import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.common.collect.ImmutableList;
import org.bitcoinj.crypto.ChildNumber;
import org.bitcoinj.crypto.DeterministicKey;
import org.bitcoinj.crypto.HDUtils;
import org.bitcoinj.wallet.DeterministicKeyChain;
import org.bitcoinj.wallet.DeterministicSeed;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.*;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.tx.ManagedTransaction;
import web3j.cps.CPSTestToken1;

import static org.bitcoinj.wallet.DeterministicKeyChain.BIP44_ACCOUNT_ZERO_PATH;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        final Event event = new Event("LockFundEx",
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        System.out.println("LockFundEx="+EventEncoder.encode(event));

        if (args.length > 2){
            if(args[0].equalsIgnoreCase("deploy")){
                String bin = args[1];
                String wallet = args[2];
                if(args.length == 3){
                    System.out.println("Usage: web3j-spring-boot contract_bin_file ");
                    if(new File(bin).exists()){
                        try {
                            BufferedReader fr = new BufferedReader(new FileReader(bin));
                            CPSTestToken1.BINARY = fr.readLine();
                            fr.close();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    return;
                }
                String password = args[3];

                Web3j web3 = Web3j.build(new HttpService("https://ropsten.etherscan.io/api"));  // defaults to http://localhost:8545/
                try {
                    Credentials credentials = WalletUtils.loadCredentials(password, wallet);

                    String name = "CPSTestToken";
                    String symbol = "CPS";
                    BigInteger decimals = BigInteger.valueOf(8);
                    BigInteger totalSupply = new BigInteger("100000000000000000");
                    if(args.length > 4)name = args[4];
                    if(args.length > 5)symbol = args[5];
                    if(args.length > 6)decimals = new BigInteger(args[6]);
                    if(args.length > 7)totalSupply = new BigInteger(args[7]);

                    try {
                        CPSTestToken1 contract = CPSTestToken1.deploy(web3, credentials, BigInteger.valueOf(0), Contract.GAS_LIMIT, name, symbol, decimals, totalSupply).send();
                        System.out.println("Contract "+contract.getContractAddress() + " successfully deployed.");
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("contract deployment failed");
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Cannot load wallet");
                } catch (CipherException e) {
                    e.printStackTrace();
                    System.out.println("Cannot load wallet");
                }

                return;
            }
        }

        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }

}