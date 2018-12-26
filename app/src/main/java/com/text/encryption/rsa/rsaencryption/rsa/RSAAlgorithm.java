package com.text.encryption.rsa.rsaencryption.rsa;

import android.util.Log;
import com.text.encryption.rsa.rsaencryption.util.EncryptionLevel;
import java.math.BigInteger;
import java.security.SecureRandom;


public class RSAAlgorithm {

    private BigInteger privateKey;
    private BigInteger publicKey;
    private BigInteger n, d, e;

    public RSAAlgorithm(EncryptionLevel encryptionLevel) {

        int bits = 0;

        switch (encryptionLevel) {

            case LEVEL_I: {
                bits = 512;

                break;
            }

            case LEVEL_II:{
                bits = 1024;
                break;
            }
            case LEVEL_III:{
                bits = 2048;
                break;
            }

        }

        SecureRandom secureRandom = new SecureRandom();
        privateKey = new BigInteger(bits, 100, secureRandom);
        publicKey = new BigInteger(bits, 100, secureRandom);

        n = privateKey.multiply(publicKey);

        BigInteger m =(privateKey.subtract(BigInteger.ONE)).multiply(publicKey.subtract(BigInteger.ONE));
        boolean found_e = false;
        do {
            e = new BigInteger(bits, 50, secureRandom);

            /**
             * tạo số e < m sao cho e và m là hai số nguyên tố cùng nhau
             * m.gcd.equals(BigInteger.ONE) --> kiểm tra xem m và e có nguyên tố cùng nhau
             * e.compareTo(m) --> trả về -1 nếu e < m | trả về 0 nếu e = m | trả về 1 nếu e > m (trong trường hợp này kiểm dùng để kiểm tra xem e có nhỏ hơn m không)
             */

            if(m.gcd(e).equals(BigInteger.ONE) && e.compareTo(m) < 0) {

                found_e = true;

            }

        } while (!found_e);

        d = e.modInverse(m);
    }

    public RSAAlgorithm(String d, String n) {

        this.d = new BigInteger(d);
        this.n = new BigInteger(n);

    }

    //Mã hóa
    public String encrypt(String text) {

        return (new BigInteger(text.getBytes()).modPow(e, n)).toString();
    }

    //Giải mã
    public String decrypt(String text) {
        return new String((new BigInteger(text)).modPow(d, n).toByteArray());
    }

    public BigInteger getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(BigInteger privateKey) {
        this.privateKey = privateKey;
    }

    public BigInteger getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(BigInteger publicKey) {
        this.publicKey = publicKey;
    }

    public BigInteger getN() {
        return n;
    }

    public void setN(BigInteger n) {
        this.n = n;
    }

    public BigInteger getD() {
        return d;
    }

    public void setD(BigInteger d) {
        this.d = d;
    }

    public BigInteger getE() {
        return e;
    }

    public void setE(BigInteger e) {
        this.e = e;
    }
}
