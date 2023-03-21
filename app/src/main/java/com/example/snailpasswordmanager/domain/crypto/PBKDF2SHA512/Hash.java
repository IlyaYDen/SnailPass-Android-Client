package com.example.snailpasswordmanager.domain.crypto.PBKDF2SHA512;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hash {
//https://gist.github.com/kcak11/86f73703eff5bbd2f7bd6b6b3efded34

        public static byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {

            try {
                SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
                PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
                SecretKey key = skf.generateSecret( spec );
                byte[] res = key.getEncoded( );
                return res;
            } catch ( NoSuchAlgorithmException | InvalidKeySpecException e ) {
                throw new RuntimeException( e );
            }
        }
    }
