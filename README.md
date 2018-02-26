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