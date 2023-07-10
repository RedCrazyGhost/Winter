package ghost.winter;

import ghost.winter.Dao.UserDAO;
import ghost.winter.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: RedCrazyGhost
 * @create: 2020-05-12 11:06
 **/
@Component
public class UserCookie {
    @Autowired
    UserDAO userDAO;

    private static byte[] key;

    static {
        try {
            key = produce();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }


    private static  byte[] produce() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");//密钥生成器
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();//生成密钥
        key = secretKey.getEncoded();//密钥字节数组]
        return key;

    }

    public String generateCookie(String username) {

        try {
            User user= userDAO.findById(username).get();
            String str = username + "-" + user.getStatus() + "-" + user.getCode();
            byte[] data = str.getBytes();
            SecretKey secretKey = new SecretKeySpec(key, "AES");//恢复密钥
            Cipher cipher = Cipher.getInstance("AES");//Cipher完成加密或解密工作类
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);//对Cipher初始化，解密模式
            byte[] cipherByte = cipher.doFinal(data);//加密data
            return parseByte2HexStr(cipherByte);
        } catch (Exception e) {
            return null;
        }
    }

    public String verificationcookie(String cookie) throws BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(key, "AES");//恢复密钥
        Cipher cipher = Cipher.getInstance("AES");//Cipher完成加密或解密工作类
        cipher.init(Cipher.DECRYPT_MODE, secretKey);//对Cipher初始化，解密模式
        byte[] cipherByte = cipher.doFinal(parseHexStr2Byte(cookie));//解密data
        return toStringHex1(parseByte2HexStr(cipherByte));
    }

    //二进制转换16进制
    private  String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    //16进制转二进制
    private  byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1){
            return null;}
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }
    // 转化十六进制编码为字符串
    private String toStringHex1(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            s = new String(baKeyword, StandardCharsets.UTF_8);// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

}
