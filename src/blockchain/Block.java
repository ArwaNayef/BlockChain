/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author HP
 */
public class Block{
    //private int [] tarnsaction;
    private int hieght;
    //Header has some other important components
    private Header header;
    private String data;
    private int TransactionCount;
    private List<Integer> transactions = new ArrayList<Integer>();
    private String hash;
    
    //Constructor, Since it recieves some header data I assigned them to headers components;
    public Block(int hieght,String data, String prevHash) {
        this.hieght = hieght;
        this.header.setPreviousHash(prevHash);
        this.data = data;
        this.header.timeStamp=System.currentTimeMillis();
        this.hash=CalHash();
    }

    /*This method calcute hash using MessageDigest and SHA-256
        and then convert the result to hexidecimal and return it*/
    public String CalHash(){       
        try {
            String text=String.valueOf(hieght+header.getPreviousHash()+String.valueOf(header.timeStamp)+data);
            
            MessageDigest digest = MessageDigest.getInstance("SHA-256");      
            
            byte[] hash = digest.digest(text.getBytes("UTF-8"));           
            StringBuilder hexString = new StringBuilder(); // This will contain hash as hexidecimal
            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public String mineBlock(int prefix) {
    String prefixString = new String(new char[prefix]).replace('\0', '0');
    while (!hash.substring(0, prefix).equals(prefixString)) {
        header.setNonce(header.getNonce()+1);
        hash = CalHash();
    }
    return hash;
}
    //Setters & Getters which allow us adjust and change PRIVATE components that we can't access from other classes
    public int getHieght() {
        return hieght;
    }

    public void setHieght(int hieght) {
        this.hieght = hieght;
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getTransactionCount() {
        return TransactionCount;
    }

    public void setTransactionCount(int TransactionCount) {
        this.TransactionCount = TransactionCount;
    }

    public List<Integer> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Integer> transactions) {
        this.transactions = transactions;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String Hash) {
        this.hash = Hash;
    }

    @Override
    public String toString() {
        return "Block{" + "hieght=" + hieght + ", header=" + header + ", data=" + data + ", TransactionCount=" + TransactionCount + ", transactions=" + transactions + ", Hash=" + hash + '}';
    }  
}