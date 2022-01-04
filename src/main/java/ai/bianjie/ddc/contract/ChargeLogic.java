package ai.bianjie.ddc.contract;


import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Bytes4;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class ChargeLogic extends Contract {
    public static final String BINARY = "60806040523480156200001157600080fd5b50604051620021d7380380620021d78339810160408190526200003491620000de565b6200003f3362000071565b600180546001600160a01b039384166001600160a01b0319918216179091556002805492909316911617905562000116565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b80516001600160a01b0381168114620000d957600080fd5b919050565b60008060408385031215620000f257600080fd5b620000fd83620000c1565b91506200010d60208401620000c1565b90509250929050565b6120b180620001266000396000f3fe608060405234801561001057600080fd5b50600436106100f55760003560e01c8063715018a6116100975780639d8215a2116100665780639d8215a2146101e2578063c9c45a0f146101f5578063d213fe451461021d578063f2fde38b1461023057600080fd5b8063715018a6146101995780638a27a80d146101a15780638da5cb5b146101b457806396875190146101cf57600080fd5b806336351c7c116100d357806336351c7c1461013d5780636356918914610160578063651a50c01461017357806370a082311461018657600080fd5b806318160ddd146100fa5780632b2e05c11461011557806330c392391461012a575b600080fd5b610102610243565b6040519081526020015b60405180910390f35b610128610123366004611c13565b6102b6565b005b610128610138366004611c13565b610361565b61015061014b366004611c2e565b610403565b604051901515815260200161010c565b61015061016e366004611c82565b610549565b610150610181366004611c13565b61079f565b610102610194366004611c13565b6109b8565b610128610a27565b6101506101af366004611c2e565b610a5d565b6000546040516001600160a01b03909116815260200161010c565b6101286101dd366004611cc9565b610cf6565b6101506101f0366004611cc9565b610dad565b610208610203366004611cc9565b610fe1565b60405163ffffffff909116815260200161010c565b61015061022b366004611cfc565b611055565b61012861023e366004611c13565b6112e1565b600154604080516318160ddd60e01b815290516000926001600160a01b0316916318160ddd9160048083019260209291908290030181865afa15801561028d573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906102b19190611d15565b905090565b6000546001600160a01b031633146102e95760405162461bcd60e51b81526004016102e090611d2e565b60405180910390fd5b6001600160a01b03811661033f5760405162461bcd60e51b815260206004820152601d60248201527f6368617267653a206175746820746865207a65726f206164647265737300000060448201526064016102e0565b600280546001600160a01b0319166001600160a01b0392909216919091179055565b6000546001600160a01b0316331461038b5760405162461bcd60e51b81526004016102e090611d2e565b6001600160a01b0381166103e15760405162461bcd60e51b815260206004820152601d60248201527f6368617267653a206461746120746865207a65726f206164647265737300000060448201526064016102e0565b600180546001600160a01b0319166001600160a01b0392909216919091179055565b6000816104525760405162461bcd60e51b815260206004820181905260248201527f6368617267653a206e6f207265636861726765206973206e656365737361727960448201526064016102e0565b61045c338461137c565b6104a85760405162461bcd60e51b815260206004820152601e60248201527f6368617267653a206e6f207265636861726765207065726d697373696f6e000060448201526064016102e0565b6104b333848461144c565b6104ff5760405162461bcd60e51b815260206004820152601760248201527f6368617267653a207265636861726765206661696c656400000000000000000060448201526064016102e0565b6040518281526001600160a01b0384169033907f4ade20d82044693c0d3331ba1d2a482d103734f274166989491c8d30d3f2ecb19060200160405180910390a35060015b92915050565b6002546000906001600160a01b03166395c2a8d9336040516001600160e01b031960e084901b1681526001600160a01b039091166004820152602401602060405180830381865afa1580156105a2573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105c69190611d63565b6105e25760405162461bcd60e51b81526004016102e090611d85565b6002546001600160a01b031663333f4ad63360006040518363ffffffff1660e01b8152600401610613929190611dd2565b602060405180830381865afa158015610630573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106549190611d63565b6106705760405162461bcd60e51b81526004016102e090611e0d565b600154604051633220132360e01b81526001600160a01b0386811660048301526001600160e01b03198616602483015263ffffffff85166044830152909116906332201323906064016020604051808303816000875af11580156106d8573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906106fc9190611d63565b6107415760405162461bcd60e51b815260206004820152601660248201527518da185c99d94e88185919081999594819985a5b195960521b60448201526064016102e0565b604080516001600160e01b03198516815263ffffffff841660208201526001600160a01b038616917f929dc21675623ba3d42ef9e085962b7d88bf57ba159fe8f0a86d6785195e2acc910160405180910390a25060015b9392505050565b6002546000906001600160a01b03166395c2a8d9336040516001600160e01b031960e084901b1681526001600160a01b039091166004820152602401602060405180830381865afa1580156107f8573d6000803e3d6000fd5b505050506040513d601f19601f8201168201806040525081019061081c9190611d63565b6108385760405162461bcd60e51b81526004016102e090611d85565b6002546001600160a01b031663333f4ad63360006040518363ffffffff1660e01b8152600401610869929190611dd2565b602060405180830381865afa158015610886573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906108aa9190611d63565b6108c65760405162461bcd60e51b81526004016102e090611e0d565b60015460405161b8f760e41b81526001600160a01b03848116600483015290911690620b8f70906024016020604051808303816000875af115801561090f573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906109339190611d63565b61097b5760405162461bcd60e51b815260206004820152601960248201527818da185c99d94e8819195b195d19481999594819985a5b1959603a1b60448201526064016102e0565b6040516001600160a01b038316907fe1aaf522946f19bd4a0b6e67a2da36e45fcddca4a1a253a919ff7029a638ab1690600090a25060015b919050565b600154604051632a6c2e2760e11b81526001600160a01b03838116600483015260009216906354d85c4e90602401602060405180830381865afa158015610a03573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906105439190611d15565b6000546001600160a01b03163314610a515760405162461bcd60e51b81526004016102e090611d2e565b610a5b60006115a1565b565b6002546000906001600160a01b03166395c2a8d9336040516001600160e01b031960e084901b1681526001600160a01b039091166004820152602401602060405180830381865afa158015610ab6573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610ada9190611d63565b610af65760405162461bcd60e51b81526004016102e090611d85565b6002546001600160a01b031663333f4ad63360006040518363ffffffff1660e01b8152600401610b27929190611dd2565b602060405180830381865afa158015610b44573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610b689190611d63565b610b845760405162461bcd60e51b81526004016102e090611e0d565b6001600160a01b0383163b15158015610c06575060015460405163b7fa29f960e01b81526001600160a01b0385811660048301529091169063b7fa29f990602401602060405180830381865afa158015610be2573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610c069190611d63565b610c4d5760405162461bcd60e51b815260206004820152601860248201527718da185c99d94e881b9bdd08111110c818dbdb9d1c9858dd60421b60448201526064016102e0565b81610c9a5760405162461bcd60e51b815260206004820181905260248201527f6368617267653a206e6f207472616e73666572206973206e656365737361727960448201526064016102e0565b6000610ca784338561144c565b90508015610798576040518381526001600160a01b0385169033907fca2ce982d63c71a419517d389df253be4b0d6f4da85fe1491e49608b139ee0be9060200160405180910390a39392505050565b6000610d0233836115f1565b9050610d0f3384836116d9565b610d505760405162461bcd60e51b815260206004820152601260248201527118da185c99d94e881c185e4819985a5b195960721b60448201526064016102e0565b33604080516001600160e01b03198516815263ffffffff841660208201526001600160a01b03928316928616917fe5e4f0955699fa27be3f7aeb5a9e4ab78b6a37914fd44e5b08c4b78e56f04aa0910160405180910390a3505050565b6002546000906001600160a01b03166395c2a8d9336040516001600160e01b031960e084901b1681526001600160a01b039091166004820152602401602060405180830381865afa158015610e06573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610e2a9190611d63565b610e465760405162461bcd60e51b81526004016102e090611d85565b6002546001600160a01b031663333f4ad63360006040518363ffffffff1660e01b8152600401610e77929190611dd2565b602060405180830381865afa158015610e94573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610eb89190611d63565b610ed45760405162461bcd60e51b81526004016102e090611e0d565b6001546040516249f94760e51b81526001600160a01b039091169063093f28e090610f059086908690600401611e3c565b6020604051808303816000875af1158015610f24573d6000803e3d6000fd5b505050506040513d601f19601f82011682018060405250810190610f489190611d63565b610f905760405162461bcd60e51b815260206004820152601960248201527818da185c99d94e8819195b195d19481999594819985a5b1959603a1b60448201526064016102e0565b6040516001600160e01b0319831681526001600160a01b038416907f5903bc6f8f7bf2a96aab9641af90df87a19c37df7652f4031cc632d6a7500f4f9060200160405180910390a250600192915050565b60015460405163c9c45a0f60e01b81526000916001600160a01b03169063c9c45a0f906110149086908690600401611e3c565b602060405180830381865afa158015611031573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906107989190611e5f565b6002546000906001600160a01b03166395c2a8d9336040516001600160e01b031960e084901b1681526001600160a01b039091166004820152602401602060405180830381865afa1580156110ae573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906110d29190611d63565b6110ee5760405162461bcd60e51b81526004016102e090611d85565b6002546001600160a01b031663333f4ad63360006040518363ffffffff1660e01b815260040161111f929190611dd2565b602060405180830381865afa15801561113c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906111609190611d63565b61117c5760405162461bcd60e51b81526004016102e090611e0d565b816111c95760405162461bcd60e51b815260206004820181905260248201527f6368617267653a206e6f207472616e73666572206973206e656365737361727960448201526064016102e0565b6001546001600160a01b031663fe1e03356000336040516001600160e01b031960e085901b1681526001600160a01b03928316600482015291166024820152604481018590526064016020604051808303816000875af1158015611231573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906112559190611d63565b6112a15760405162461bcd60e51b815260206004820152601b60248201527f6368617267653a2073656c665265636861726765206661696c6564000000000060448201526064016102e0565b60405182815233906000907f4ade20d82044693c0d3331ba1d2a482d103734f274166989491c8d30d3f2ecb19060200160405180910390a3506001919050565b6000546001600160a01b0316331461130b5760405162461bcd60e51b81526004016102e090611d2e565b6001600160a01b0381166113705760405162461bcd60e51b815260206004820152602660248201527f4f776e61626c653a206e6577206f776e657220697320746865207a65726f206160448201526564647265737360d01b60648201526084016102e0565b611379816115a1565b50565b60006001600160a01b0382166113e05760405162461bcd60e51b8152602060048201526024808201527f6368617267653a20726563686172676520746f20746865207a65726f206164646044820152637265737360e01b60648201526084016102e0565b816001600160a01b0316836001600160a01b031614156114425760405162461bcd60e51b815260206004820181905260248201527f6368617267653a206e6f207265636861726765206973206e656365737361727960448201526064016102e0565b6107988383611709565b600154604051632a6c2e2760e11b81526001600160a01b03858116600483015260009284929116906354d85c4e90602401602060405180830381865afa15801561149a573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906114be9190611d15565b101561151a5760405162461bcd60e51b815260206004820152602560248201527f6368617267653a206163636f756e742062616c616e6365206973206e6f7420656044820152640dcdeeaced60db1b60648201526084016102e0565b60015460405163fe1e033560e01b81526001600160a01b0386811660048301528581166024830152604482018590529091169063fe1e0335906064016020604051808303816000875af1158015611575573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906115999190611d63565b949350505050565b600080546001600160a01b038381166001600160a01b0319831681178455604051919092169283917f8be0079c531659141344cd1fd0a4f28419497f9722a3daafe3b4186f6b6457e09190a35050565b60015460405163b7fa29f960e01b81526001600160a01b038481166004830152600092169063b7fa29f990602401602060405180830381865afa15801561163c573d6000803e3d6000fd5b505050506040513d601f19601f820116820180604052508101906116609190611d63565b6116a75760405162461bcd60e51b815260206004820152601860248201527718da185c99d94e881b9bdd08111110c818dbdb9d1c9858dd60421b60448201526064016102e0565b60015460405163c9c45a0f60e01b81526001600160a01b039091169063c9c45a0f906110149086908690600401611e3c565b600063ffffffff8216156116ff576116f883858463ffffffff1661144c565b9050610798565b5060019392505050565b60006117496040805160e0810182526060808252602082015290810160008152606060208201526040016000815260200160008152602001606081525090565b60025460405163fbcbc0f160e01b81526001600160a01b0386811660048301529091169063fbcbc0f190602401600060405180830381865afa158015611793573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526117bb9190810190611f5d565b5092935090918560408101606082016080830160a084018560018111156117e4576117e4611dbc565b60018111156117f5576117f5611dbc565b905285600181111561180957611809611dbc565b600181111561181a5761181a611dbc565b905285905285600281111561183157611831611dbc565b600281111561184257611842611dbc565b9052949094525060019250611855915050565b8160800151600181111561186b5761186b611dbc565b14801561188d575060018160a00151600181111561188b5761188b611dbc565b145b6118a95760405162461bcd60e51b81526004016102e090611d85565b6118e76040805160e0810182526060808252602082015290810160008152606060208201526040016000815260200160008152602001606081525090565b60025460405163fbcbc0f160e01b81526001600160a01b0386811660048301529091169063fbcbc0f190602401600060405180830381865afa158015611931573d6000803e3d6000fd5b505050506040513d6000823e601f3d908101601f191682016040526119599190810190611f5d565b5092935090918560408101606082016080830160a0840185600181111561198257611982611dbc565b600181111561199357611993611dbc565b90528560018111156119a7576119a7611dbc565b60018111156119b8576119b8611dbc565b90528590528560028111156119cf576119cf611dbc565b60028111156119e0576119e0611dbc565b90529490945250600192506119f3915050565b81608001516001811115611a0957611a09611dbc565b148015611a2b575060018160a001516001811115611a2957611a29611dbc565b145b611a6e5760405162461bcd60e51b815260206004820152601460248201527331b430b933b29d103a379034b990333937bd32b760611b60448201526064016102e0565b600282604001516002811115611a8657611a86611dbc565b1415611ad45760405162461bcd60e51b815260206004820152601e60248201527f6368617267653a206e6f207265636861726765207065726d697373696f6e000060448201526064016102e0565b600082604001516002811115611aec57611aec611dbc565b1480611b03575060608101518251611b0391611b5b565b80611b52575060608082015190830151611b1c91611b5b565b8015611b30575080518251611b3091611b5b565b8015611b525750600281604001516002811115611b4f57611b4f611dbc565b14155b95945050505050565b805182516000918491849114611b7657600092505050610543565b815160005b81811015611bef57828181518110611b9557611b9561203c565b602001015160f81c60f81b6001600160f81b031916848281518110611bbc57611bbc61203c565b01602001516001600160f81b03191614611bdd576000945050505050610543565b80611be781612052565b915050611b7b565b5060019695505050505050565b80356001600160a01b03811681146109b357600080fd5b600060208284031215611c2557600080fd5b61079882611bfc565b60008060408385031215611c4157600080fd5b611c4a83611bfc565b946020939093013593505050565b80356001600160e01b0319811681146109b357600080fd5b63ffffffff8116811461137957600080fd5b600080600060608486031215611c9757600080fd5b611ca084611bfc565b9250611cae60208501611c58565b91506040840135611cbe81611c70565b809150509250925092565b60008060408385031215611cdc57600080fd5b611ce583611bfc565b9150611cf360208401611c58565b90509250929050565b600060208284031215611d0e57600080fd5b5035919050565b600060208284031215611d2757600080fd5b5051919050565b6020808252818101527f4f776e61626c653a2063616c6c6572206973206e6f7420746865206f776e6572604082015260600190565b600060208284031215611d7557600080fd5b8151801515811461079857600080fd5b60208082526019908201527f6368617267653a206163636f756e742069732066726f7a656e00000000000000604082015260600190565b634e487b7160e01b600052602160045260246000fd5b6001600160a01b03831681526040810160038310611e0057634e487b7160e01b600052602160045260246000fd5b8260208301529392505050565b60208082526015908201527431b430b933b29d103737903832b936b4b9b9b4b7b760591b604082015260600190565b6001600160a01b039290921682526001600160e01b031916602082015260400190565b600060208284031215611e7157600080fd5b815161079881611c70565b634e487b7160e01b600052604160045260246000fd5b600082601f830112611ea357600080fd5b815167ffffffffffffffff80821115611ebe57611ebe611e7c565b604051601f8301601f19908116603f01168101908282118183101715611ee657611ee6611e7c565b81604052838152602092508683858801011115611f0257600080fd5b600091505b83821015611f245785820183015181830184015290820190611f07565b83821115611f355760008385830101525b9695505050505050565b8051600381106109b357600080fd5b8051600281106109b357600080fd5b600080600080600080600060e0888a031215611f7857600080fd5b875167ffffffffffffffff80821115611f9057600080fd5b611f9c8b838c01611e92565b985060208a0151915080821115611fb257600080fd5b611fbe8b838c01611e92565b9750611fcc60408b01611f3f565b965060608a0151915080821115611fe257600080fd5b611fee8b838c01611e92565b9550611ffc60808b01611f4e565b945061200a60a08b01611f4e565b935060c08a015191508082111561202057600080fd5b5061202d8a828b01611e92565b91505092959891949750929550565b634e487b7160e01b600052603260045260246000fd5b600060001982141561207457634e487b7160e01b600052601160045260246000fd5b506001019056fea26469706673582212206d43733aea891f26d2062982e988ee11ead6738cea272a1fec1475e5f3c2163464736f6c634300080b0033";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DELETEDDC = "deleteDDC";

    public static final String FUNC_DELETEFEE = "deleteFee";

    public static final String FUNC_OWNER = "owner";

    public static final String FUNC_PAY = "pay";

    public static final String FUNC_QUERYFEE = "queryFee";

    public static final String FUNC_RECHARGE = "recharge";

    public static final String FUNC_RENOUNCEOWNERSHIP = "renounceOwnership";

    public static final String FUNC_SELFRECHARGE = "selfRecharge";

    public static final String FUNC_SETAUTH = "setAuth";

    public static final String FUNC_SETFEE = "setFee";

    public static final String FUNC_SETICHARGEDATA = "setiChargeData";

    public static final String FUNC_SETTLEMENT = "settlement";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFEROWNERSHIP = "transferOwnership";

    public static final Event DELETEDDC_EVENT = new Event("DeleteDDC", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}));
    ;

    public static final Event DELETEFEE_EVENT = new Event("DeleteFee", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bytes4>() {}));
    ;

    public static final Event OWNERSHIPTRANSFERRED_EVENT = new Event("OwnershipTransferred", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}));
    ;

    public static final Event PAY_EVENT = new Event("Pay", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Bytes4>() {}, new TypeReference<Uint32>() {}));
    ;

    public static final Event RECHARGE_EVENT = new Event("Recharge", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event SETFEE_EVENT = new Event("SetFee", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Bytes4>() {}, new TypeReference<Uint32>() {}));
    ;

    public static final Event SETTLEMENT_EVENT = new Event("Settlement", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ChargeLogic(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ChargeLogic(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ChargeLogic(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ChargeLogic(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<DeleteDDCEventResponse> getDeleteDDCEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(DELETEDDC_EVENT, transactionReceipt);
        ArrayList<DeleteDDCEventResponse> responses = new ArrayList<DeleteDDCEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DeleteDDCEventResponse typedResponse = new DeleteDDCEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DeleteDDCEventResponse> deleteDDCEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DeleteDDCEventResponse>() {
            @Override
            public DeleteDDCEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(DELETEDDC_EVENT, log);
                DeleteDDCEventResponse typedResponse = new DeleteDDCEventResponse();
                typedResponse.log = log;
                typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DeleteDDCEventResponse> deleteDDCEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELETEDDC_EVENT));
        return deleteDDCEventFlowable(filter);
    }

    public List<DeleteFeeEventResponse> getDeleteFeeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(DELETEFEE_EVENT, transactionReceipt);
        ArrayList<DeleteFeeEventResponse> responses = new ArrayList<DeleteFeeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            DeleteFeeEventResponse typedResponse = new DeleteFeeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<DeleteFeeEventResponse> deleteFeeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, DeleteFeeEventResponse>() {
            @Override
            public DeleteFeeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(DELETEFEE_EVENT, log);
                DeleteFeeEventResponse typedResponse = new DeleteFeeEventResponse();
                typedResponse.log = log;
                typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<DeleteFeeEventResponse> deleteFeeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(DELETEFEE_EVENT));
        return deleteFeeEventFlowable(filter);
    }

    public List<OwnershipTransferredEventResponse> getOwnershipTransferredEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, transactionReceipt);
        ArrayList<OwnershipTransferredEventResponse> responses = new ArrayList<OwnershipTransferredEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, OwnershipTransferredEventResponse>() {
            @Override
            public OwnershipTransferredEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(OWNERSHIPTRANSFERRED_EVENT, log);
                OwnershipTransferredEventResponse typedResponse = new OwnershipTransferredEventResponse();
                typedResponse.log = log;
                typedResponse.previousOwner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.newOwner = (String) eventValues.getIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<OwnershipTransferredEventResponse> ownershipTransferredEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(OWNERSHIPTRANSFERRED_EVENT));
        return ownershipTransferredEventFlowable(filter);
    }

    public List<PayEventResponse> getPayEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(PAY_EVENT, transactionReceipt);
        ArrayList<PayEventResponse> responses = new ArrayList<PayEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            PayEventResponse typedResponse = new PayEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<PayEventResponse> payEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, PayEventResponse>() {
            @Override
            public PayEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(PAY_EVENT, log);
                PayEventResponse typedResponse = new PayEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<PayEventResponse> payEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PAY_EVENT));
        return payEventFlowable(filter);
    }

    public List<RechargeEventResponse> getRechargeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(RECHARGE_EVENT, transactionReceipt);
        ArrayList<RechargeEventResponse> responses = new ArrayList<RechargeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            RechargeEventResponse typedResponse = new RechargeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<RechargeEventResponse> rechargeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, RechargeEventResponse>() {
            @Override
            public RechargeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(RECHARGE_EVENT, log);
                RechargeEventResponse typedResponse = new RechargeEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<RechargeEventResponse> rechargeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(RECHARGE_EVENT));
        return rechargeEventFlowable(filter);
    }

    public List<SetFeeEventResponse> getSetFeeEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SETFEE_EVENT, transactionReceipt);
        ArrayList<SetFeeEventResponse> responses = new ArrayList<SetFeeEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SetFeeEventResponse typedResponse = new SetFeeEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SetFeeEventResponse> setFeeEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SetFeeEventResponse>() {
            @Override
            public SetFeeEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SETFEE_EVENT, log);
                SetFeeEventResponse typedResponse = new SetFeeEventResponse();
                typedResponse.log = log;
                typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.sig = (byte[]) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.amount = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SetFeeEventResponse> setFeeEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETFEE_EVENT));
        return setFeeEventFlowable(filter);
    }

    public List<SettlementEventResponse> getSettlementEvents(TransactionReceipt transactionReceipt) {
        List<EventValuesWithLog> valueList = extractEventParametersWithLog(SETTLEMENT_EVENT, transactionReceipt);
        ArrayList<SettlementEventResponse> responses = new ArrayList<SettlementEventResponse>(valueList.size());
        for (EventValuesWithLog eventValues : valueList) {
            SettlementEventResponse typedResponse = new SettlementEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.accAddr = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<SettlementEventResponse> settlementEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, SettlementEventResponse>() {
            @Override
            public SettlementEventResponse apply(Log log) {
                EventValuesWithLog eventValues = extractEventParametersWithLog(SETTLEMENT_EVENT, log);
                SettlementEventResponse typedResponse = new SettlementEventResponse();
                typedResponse.log = log;
                typedResponse.accAddr = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.ddcAddr = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<SettlementEventResponse> settlementEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(SETTLEMENT_EVENT));
        return settlementEventFlowable(filter);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String accAddr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new Address(160, accAddr)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteDDC(String ddcAddr) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETEDDC, 
                Arrays.<Type>asList(new Address(160, ddcAddr)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteFee(String ddcAddr, byte[] sig) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_DELETEFEE, 
                Arrays.<Type>asList(new Address(160, ddcAddr),
                new Bytes4(sig)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> pay(String from, byte[] sig) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_PAY, 
                Arrays.<Type>asList(new Address(160, from),
                new Bytes4(sig)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> queryFee(String ddcAddr, byte[] sig) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_QUERYFEE, 
                Arrays.<Type>asList(new Address(160, ddcAddr),
                new Bytes4(sig)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint32>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> recharge(String to, BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RECHARGE, 
                Arrays.<Type>asList(new Address(160, to),
                new Uint256(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_RENOUNCEOWNERSHIP, 
                Arrays.<Type>asList(), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> selfRecharge(BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SELFRECHARGE, 
                Arrays.<Type>asList(new Uint256(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setAuth(String authLogic) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETAUTH, 
                Arrays.<Type>asList(new Address(160, authLogic)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setFee(String ddcAddr, byte[] sig, BigInteger amount) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETFEE, 
                Arrays.<Type>asList(new Address(160, ddcAddr),
                new Bytes4(sig),
                new Uint32(amount)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setiChargeData(String chargeData) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETICHARGEDATA, 
                Arrays.<Type>asList(new Address(160, chargeData)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> settlement(String ddcAddr, BigInteger value) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_SETTLEMENT, 
                Arrays.<Type>asList(new Address(160, ddcAddr),
                new Uint256(value)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_TRANSFEROWNERSHIP, 
                Arrays.<Type>asList(new Address(160, newOwner)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ChargeLogic load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ChargeLogic(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ChargeLogic load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ChargeLogic(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ChargeLogic load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ChargeLogic(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ChargeLogic load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ChargeLogic(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ChargeLogic> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider, String chargeData, String authLogic) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, chargeData),
                new Address(160, authLogic)));
        return deployRemoteCall(ChargeLogic.class, web3j, credentials, contractGasProvider, BINARY, encodedConstructor);
    }

    public static RemoteCall<ChargeLogic> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider, String chargeData, String authLogic) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, chargeData),
                new Address(160, authLogic)));
        return deployRemoteCall(ChargeLogic.class, web3j, transactionManager, contractGasProvider, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ChargeLogic> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit, String chargeData, String authLogic) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, chargeData),
                new Address(160, authLogic)));
        return deployRemoteCall(ChargeLogic.class, web3j, credentials, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    @Deprecated
    public static RemoteCall<ChargeLogic> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit, String chargeData, String authLogic) {
        String encodedConstructor = FunctionEncoder.encodeConstructor(Arrays.<Type>asList(new Address(160, chargeData),
                new Address(160, authLogic)));
        return deployRemoteCall(ChargeLogic.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, encodedConstructor);
    }

    public static class DeleteDDCEventResponse extends BaseEventResponse {
        public String ddcAddr;
    }

    public static class DeleteFeeEventResponse extends BaseEventResponse {
        public String ddcAddr;

        public byte[] sig;
    }

    public static class OwnershipTransferredEventResponse extends BaseEventResponse {
        public String previousOwner;

        public String newOwner;
    }

    public static class PayEventResponse extends BaseEventResponse {
        public String from;

        public String ddcAddr;

        public byte[] sig;

        public BigInteger amount;
    }

    public static class RechargeEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }

    public static class SetFeeEventResponse extends BaseEventResponse {
        public String ddcAddr;

        public byte[] sig;

        public BigInteger amount;
    }

    public static class SettlementEventResponse extends BaseEventResponse {
        public String accAddr;

        public String ddcAddr;

        public BigInteger value;
    }
}
