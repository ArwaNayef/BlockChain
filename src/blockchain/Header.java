package blockchain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author HP
 */
public class Header{
    //header components which is by itself a block component;
    public long timeStamp;
    private String merkleRoot;
    private int nonce = 0;
    public String previousHash;
    private int version;
    private int difficulty;
    
    //Simple constructor;
    public Header(long timeStamp, String merkleRoot, String previousHash, int version, int difficulty) {
        this.timeStamp = timeStamp;
        this.merkleRoot = merkleRoot;
        this.previousHash = previousHash;
        this.version = version;
        this.difficulty = difficulty;
        this.nonce=0;
    }
    //Setters & Getters which allow us adjust and change PRIVATE components that we can't access from other classes
    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMerkleRoot() {
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }

    public int getNonce() {
        return nonce;
    }

    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return "Header{" + "timeStamp=" + timeStamp + ",\n merkleRoot=" + merkleRoot + ",\n nonce=" + nonce + ",\n previousHash=" + previousHash + 
                ",\n version=" + version + ",\n difficulty=" + difficulty + '}';
    }
    
    
}
