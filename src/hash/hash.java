package hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class hash {
    String passwordToHash ;
    public static String hashPassword(String passwordToHash) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(passwordToHash.getBytes());
        byte[] b = md.digest();
        StringBuffer sb = new StringBuffer();
        for (byte b1:b) {
            sb.append(Integer.toHexString(b1 & 0xff).toString());
        }
        //system.out.println(sb.toString());
        String hash = sb.toString();
        return hash;
    }
}
