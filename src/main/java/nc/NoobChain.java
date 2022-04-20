package nc;

import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Block;


public class NoobChain {
	
	public static ArrayList<Block> blockchain = new ArrayList<Block>();
	public static int difficulty = 5;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
				
		blockchain.add(new Block("Block1", "0"));
		System.out.println("Mining block1...");
		blockchain.get(0).mineBlock(difficulty);
				
		blockchain.add(new Block("Block2", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Mining block2...");
		blockchain.get(1).mineBlock(difficulty);
				
		blockchain.add(new Block("Block3", blockchain.get(blockchain.size()-1).hash));
		System.out.println("Mining block3...");
		blockchain.get(2).mineBlock(difficulty);
				
		System.out.println("\nBlockChain is VALID: " + isChainValid());
				
			//String blockchainJson = new GsonBuilder()
			//	.setPrettyPrinting()
			//		.create()
			//		.toJson(blockchain);
				
			//System.out.println("\nThe Block Chain: ");
			//System.out.println(blockchainJson);
			
		ObjectMapper mapperUtil = new ObjectMapper();
			
		try {
			String bcJson = mapperUtil.writeValueAsString(blockchain);
			System.out.println("The BlockChain:");
			System.out.println(bcJson);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
			
	}
			
	public static Boolean isChainValid() {
		
			Block currentBlock;
			Block previousBlock;
			String hashTarget = new String(new char[difficulty]).replace("\0", "0");
					
			// - Loop through blockChain to check hashes:
			for(int i=1; i < blockchain.size(); i++) {
			currentBlock = blockchain.get(i);
			previousBlock = blockchain.get(i-1);
						
			// - Compare registered hash and calculate hash:
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous hashes are not equal!");
				return false;
			}
			
						
			// - Compare previous hash and registered previous hash: 
			if(!previousBlock.hash.equals(currentBlock.previousHash)) {
				System.out.println("Previous hashes not equal!");
				return false;
			}
						
			// - Check if hash is solved:
			if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)) {
				System.out.println("This block hasn't been mined");
				return false;
			}
		}
		return true;
	}
	
}
