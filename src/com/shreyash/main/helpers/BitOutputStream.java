package com.shreyash.main.helpers;

import com.shreyash.main.Main;

import java.io.IOException;
import java.io.OutputStream;


public class BitOutputStream {

    private OutputStream out;
    private boolean[] buffer;
    private int count;

    public BitOutputStream(OutputStream out) {
        this.out = out;
        this.count = 1;
        this.buffer = new boolean[Main.BYTE_LENGTH];
    }

    public void write(BitSequence x) throws IOException {

        boolean[] sequence = x.getSequence();
        int index = sequence.length - 1;
        while(index >= 0) {
            this.buffer[8 - count] = sequence[index];
            //System.out.println("Buffer index " + (Main.BYTE_LENGTH - count) + " contains " + this.buffer[8 - count]);
            if (this.count == Main.BYTE_LENGTH) {
                int num = 0;
                for (int i = Main.BYTE_LENGTH - 1; i >= 0; i--) {
                    num = 2 * num + (this.buffer[i] ? 1 : 0);
                    //System.out.println("Number is: " + num);
                }
                //System.out.println("Writing to the file: " + num);
                this.out.write(num);
                this.count = 0;
            }
            this.count++;
            index--;
        }
    }

    public void close() throws IOException {
        if (this.count != 1) {
            //System.out.println("Count was not equal to 1");
            BitSequence fillerSequence = new BitSequence(Main.BYTE_LENGTH - this.count + 1);
            fillerSequence.setSequence(0);
            this.write(fillerSequence);
        }
        this.out.close();
    }

    public int getCount() {
        return count;
    }




}