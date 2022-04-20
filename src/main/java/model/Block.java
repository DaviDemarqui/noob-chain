package model;

import java.util.Date;

import util.DgFingerPrint;

public class Block {
	
	public String hash;
	public String previousHash;
	private String data;
	private Long timeStamp;
	private int nonce;
	
	public Block(String data, String previousHash) {
		this.data = data;
		this.previousHash = previousHash;
		this.timeStamp = new Date().getTime();
		
		this.hash = calcHash();
	}
	
	// - Calculate the hash based on block content;
	public String calcHash() {
		
		String calcHash = DgFingerPrint.apply256(
				previousHash+
				Long.toString(timeStamp)+
				Integer.toString(nonce)+
				data
				);
		return calcHash;
	}
	
	public void mineBlock(int difficulty) {
		
		// - Create string dificulty = 0;
		String target = new String(new char[difficulty]).replace('\0', '0');
		while (!hash.substring(0, difficulty).equals(target)) {
			nonce ++;
			hash = calcHash();
		}
		System.out.println("Block Mined!!! "+hash);
	}


}

