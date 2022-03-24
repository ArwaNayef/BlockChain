/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchain;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONObject;

/**
 *
 * @author HP
 */
public class BlockChain {

    private static List<Block> blockChain = new ArrayList<>();

    public List<Block> getBlockChain() {
        return blockChain;
    }

    private void createGensisBlock() {
        blockChain.add(new Block(0, "0", "0"));
    }

    /*I decided to implement it since I need some info from the previous block when I add a new block;
    So a method is better than trying to rewrite the code everytime I need it*/
    public Block getLatest() {
        //in case we don't have a gensis block yet;
        if (blockChain.isEmpty()) {
            createGensisBlock();
        }
        return blockChain.get(blockChain.size() - 1);
    }

    //More like setting since changing after creting would crash;
    public void addBlock(String data) {
        Block prevBlock = getLatest();
        Block newBlock = new Block(prevBlock.getHieght() + 1, data, prevBlock.getHash());
        blockChain.add(newBlock);
    }

    //This is a simple getter that takes hieght which id the index and return it's block;
    public Block getBlock(int hieght) {
        return blockChain.get(hieght);

    }

    public void exploreBlocks() {
        //Printing all blocks using json 
        for (Block block : blockChain) {
            JSONObject jo = new JSONObject();
            jo.put("Hieght", block.getHieght());
            jo.put("Header", block.getHeader().toString());
            jo.put("data", block.getData());
            jo.put("Transaction Counter", block.getTransactionCount());
        }
    }


// Function to check validity of the blockchain
    public static Boolean validatate() {
        Block currentBlock;
        Block previousBlock;

        // Iterating through all the blocks
        for (int i = 1;
                i < blockChain.size();
                i++) {

            // Storing the current block
            // and the previous block
            currentBlock = blockChain.get(i);
            previousBlock = blockChain.get(i - 1);

            // Checking if the current hash
            // is equal to the
            // calculated hash or not
            if (!currentBlock.getHash()
                    .equals(
                            currentBlock
                                    .CalHash())) {
                System.out.println(
                        "Hashes are not equal");
                return false;
            }

            // Checking of the previous hash
            // is equal to the calculated
            // previous hash or not
            if (!previousBlock
                    .getHash()
                    .equals(
                            currentBlock
                                    .getHeader().getPreviousHash())) {
                System.out.println(
                        "Previous Hashes are not equal");
                return false;
            }
        }

        // If all the hashes are equal
        // to the calculated hashes,
        // then the blockchain is valid
        return true;
    }

}
