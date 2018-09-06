package demo.jingdong.diary;

import com.google.crypto.tink.Aead;
import com.google.crypto.tink.KeysetHandle;
import com.google.crypto.tink.aead.AeadFactory;
import com.google.crypto.tink.aead.AeadKeyTemplates;
import com.google.crypto.tink.config.TinkConfig;

import java.security.GeneralSecurityException;

public class tink {
	public static void main(String[] args) throws GeneralSecurityException {
		TinkConfig.register();
		// 1. Generate the key material.
		KeysetHandle keysetHandle = KeysetHandle.generateNew(AeadKeyTemplates.AES128_GCM);

		// 2. Get the primitive.
		Aead aead = AeadFactory.getPrimitive(keysetHandle);

		byte[] plaintext = new String("helloworld").getBytes();
		byte[] aad = new String("zhangyuan").getBytes();
		byte[] aad2 = new String("liucx").getBytes();

		// 3. Use the primitive.
		byte[] ciphertext = aead.encrypt(plaintext, aad);

		System.out.println(ciphertext.toString());

		//4decrypto
		byte[] primitive = aead.decrypt(ciphertext,aad2);

		System.out.println(new String(primitive));
	}
}
