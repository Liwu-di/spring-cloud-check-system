package fun.liwudi.graduatedesignuserinfomanage.helper;


import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * @author 李武第
 */
@Component
public class DecodeHelper {
    /**
     * base64加密
     * @param content 待加密内容
     * @return byte[]
     */
    public byte[] base64Encrypt(final String content) {
        return Base64.getEncoder().encode(content.getBytes());
    }

    /**
     * base64解密
     * @param encoderContent 已加密内容
     * @return byte[]
     */
    public byte[] base64Decrypt(final byte[] encoderContent) {
        return Base64.getDecoder().decode(encoderContent);
    }
}
