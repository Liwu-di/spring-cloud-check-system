package fun.liwudi.commonpart.helper;

import io.micrometer.core.lang.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

/**
 * @author 李武第
 */
@Component
@Slf4j
public class EnDeCodeHelper {

    private char[] character = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
            'm','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E'
            ,'F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W',
            'X','Y','Z','1','2','3','4','5','6','7','8','9','0','\'','\"',';',',','.'
            ,'\\',' ','*','/','+','-','(',')','=','&','^','%','$','#','@','!','{','}','[',']',':'};

    private int getChange(char i){
        for(int k = 0; k<character.length;++k){
            if(character[k] == i){
                return k;
            }
        }
        return -1;
    }

    private int getReChange(char i){
        for(int k = 0; k<character.length;++k){
            if(character[k] == i){
                return k;
            }
        }
        return -1;
    }

    public String encode(@Nullable String s){
        if(StringUtils.isBlank(s)){
            return new String();
        }
        char[] chars = s.toCharArray();
        char[] newChars = new char[chars.length];
        int index;
        for(int i = 0 ; i < chars.length ; ++i){
            index = getChange(chars[i]);
            newChars[i] = character[(index + 9) % character.length];
        }
        return new String(newChars);
    }

    public String decode(@Nullable String s){
        if(StringUtils.isBlank(s)){
            return new String();
        }
        char[] chars = s.toCharArray();
        char[] newChars = new char[chars.length];
        int index;
        for(int i = 0 ; i < chars.length ; ++i){
            index = getReChange(chars[i]);
            newChars[i] = character[(index-9+character.length) % character.length];
        }
        return new String(newChars);

    }

    public static void main(String[] args) {
        String s = "{\n" +
                "    \"url\":\"http://localhost:12001/testGetOut\",\n" +
                "    \"parameter\":{\n" +
                "        \"key\":\"liwudi\"\n" +
                "    },\n" +
                "    \"headerParameter\":{\n" +
                "        \"token\":\"haha\"\n" +
                "    },\n" +
                "    \"bodyParameter\":{\n" +
                "        \"url\":\"haha\"\n" +
                "    }\n" +
                "}";

        EnDeCodeHelper enDeCodeHelper = new EnDeCodeHelper();
        System.out.println(enDeCodeHelper.encode(s));
        System.out.println(enDeCodeHelper.decode(enDeCodeHelper.encode(s)));
    }


}
