package google;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.config.TinkConfig;

import java.security.GeneralSecurityException;

/**
 * Tink是谷歌为了帮助开发人员交付安全的加密代码而开发的一个支持多语言的跨平台加密库。
 * Tink 致力于提供安全且易于使用的加密 API。
 * Tink 建立在现有的库之上，如 BoringSSL 和 Java Cryptography Architecture，同时对这些库中存在的弱点进行了加固。
 */
public class Tink {
	public static void main(String[] args) throws GeneralSecurityException {
		TinkConfig.register();
		// 1. Generate the key material.加密方法
		KeysetHandle keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES128_GCM);

		// 2. Get the primitive.得到一个加密器
		Aead aead = AeadFactory.getPrimitive(keysetHandle);

		byte[] plaintext = new String("helloworld").getBytes();
		byte[] aad = new String("zhangyuan").getBytes();
		//byte[] aad2 = new String("liucx").getBytes();

		// 3. Use the primitive.
		byte[] ciphertext = aead.encrypt(plaintext, aad);

		System.out.println(ciphertext.toString());

		//4decrypto
		byte[] primitive = aead.decrypt(ciphertext,aad);

		System.out.println(new String(primitive));
	}
}
