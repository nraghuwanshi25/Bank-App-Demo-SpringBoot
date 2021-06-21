package com.bank.app.security.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
import org.jboss.logging.Logger;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;

public class RsaUtil {

	private static Logger logger = Logger.getLogger(RsaUtil.class);

	public String decrypt(String data, String input) throws Exception {

		logger.info("RSA encrypted AES Key & IV" + input);
		logger.info("AES encrypted Data:" + data);

		Rsa rsa = new Rsa();

		String original = rsa.decrypt(input);
		logger.info("RSA decrypted values: " + original);

		String[] secret = original.split("\\|");
		String key = secret[0];
		String iv = secret[1];
		String passphrase = secret[2];
		logger.info("AES Key: " + key + " IV: " + iv + " Passphrase: " + passphrase);

		AesUtil aesUtil = new AesUtil(128, 1000);
		String originalText = aesUtil.decrypt(key, iv, passphrase, data);
		logger.info("AES Original Text: " + originalText);
		return originalText;

	}

	public static class Rsa {

		private PublicKey mPublicKey;
		private PrivateKey mPrivateKey;

		private Cipher mCipher;

		public Rsa() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, NoSuchPaddingException {

			String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyThTcsX8wK4k/ZvEOp+nLTpqJKP4UfeiaKnJ8kp3tKu75bGs36+YLc2dO8eHY50M0yeSYOwPvFA/bEsgrq/09raEY3A9eJVelKG2e5zU7XXJahTjex3s1TBqjazA+ofyVLEum48m9gtyzXS5iJYjwrJKROxvCpnDdwY4RZAWQpO99rJIUDl0pI2k9ag9tsVPqQVXtNMVXrpurzgFyi26ryrUgoJSXSZIIkiIaYzpPbBX/PK71nUxE3w1Gan73sMJIi4iSrk23TVue3Nzz0csNjq526yfNh8GWm0nvDXSQe0s7TO8NIDgAGu+aHa9O77wJYwtsZenujlH/v6eiKdYDwIDAQAB";

			String privateKey = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDJOFNyxfzAriT9m8Q6n6ctOmoko/hR96JoqcnySne0q7vlsazfr5gtzZ07x4djnQzTJ5Jg7A+8UD9sSyCur/T2toRjcD14lV6UobZ7nNTtdclqFON7HezVMGqNrMD6h/JUsS6bjyb2C3LNdLmIliPCskpE7G8KmcN3BjhFkBZCk732skhQOXSkjaT1qD22xU+pBVe00xVeum6vOAXKLbqvKtSCglJdJkgiSIhpjOk9sFf88rvWdTETfDUZqfvewwkiLiJKuTbdNW57c3PPRyw2OrnbrJ82HwZabSe8NdJB7SztM7w0gOAAa75odr07vvAljC2xl6e6OUf+/p6Ip1gPAgMBAAECggEBAIVC62konDLTYiYfYf7dOy9l72PiwBMw6HtebyMqlEqwLZl6aR4fQa6b7NTpWM3s9dBl9biAvg8eKlLDEpr+Z0J5MAZXEC7q5Z+T1bHZ9N5q0oCR4vHnqLCsBvVbAtco76GNUmuti8UJYfeVpUZDAN3xi+RHnWas3NnX4a6YNVXTKkn2YogGME7ANZixWeUdU5tnndxCwb8KKLoRbt76AFOkXxQi6wgfsKtbrlRCeHuSpeArp6BU/mgpOHS9pFk2Kr5phx8dzudV9cEqREClHOjIUsF2bFFvaUnvtJfjR7ukcv1OgPp+jy4QMYRM6H2KJX2ot7sMcl9PHOOPf2UpSjkCgYEA/dmrFV+KjTnXJ0LEmNc8coRWPeVZKD3w5qSYom9Ag1sMAi/J9WaPUT0aB7dSF2VlUhiqkw6pL6ayMNIKJ9c0kNjGoLVmwGpGAjqjAwG33jz4ygG5HWEOw42VJKW8gvRXiEbpUdEZYW7YW1repX01U6ZoLgvdBLpWETSHitAAtnUCgYEAyuyO/ZUiAvAxM4L9fIzesjxDgpA5464q+iQaGEZCkr8+1rVZnQF5OhTa1SKlGd+itAIJA6a72JQ1hQ49dRY+9NR1e1bUvIBLpZC6m+DMLsFLQkGrj+T6fgWkwmeOq1/kOiipbBlKQ8oRC3xA+jiaZ0EwxBUuOjOcq5bS9CZyq/MCgYAifxP6wZMIghW05iPlSUqTnVxC6QnmqBQ2o6OCX2/66cKGAYyGZruPJKiG5+il+Ud9kpCLj3B6Y0LP05tBssMX8/16FWyzNT3yS5mfbPpZnxuICBfp4CirdnAQHN2zRhUnFcvbLHkwfmm0cHLVNmCqz3BU4ZPVcqUDjYnl2UJfiQKBgHDW6JCUTExr+kAVuBV5VCCyqCvDPZ2g7R8Z0jTrZPurQysrvwAyoJGupR1d3Ycst5H3i8GvBsfhan1ptL0WOL32IroKR9PmV6pid5JUh9X8AHiAggFLKAvJ4ZuE8G3Vrdi1YbjNu2FEzQOg/ZTmwt1l/9MZoBIXPyiffwDrHFsHAoGAZOp6A3yNrttrVuuqCZbtgtzBCRsghCER/eW9NA9uyAU/RoND5SDr26Dsv4yg86kb8lVU++kljC3jHZf7z6+d8nmEXxxtX9lTXFR1nVMb6OqnVFy91YO0uBuWg4R0sa8HJ1T9XOxiVHBB86cBuM6fcOZrLilM5o/fEtJ33z+FCrU=";

			// String privateKey =
			// "MIIEowIBAAKCAQEA0BFqoD2z3mD9EmVXAFk0E3QN7WYVg8BpXGZOQ1k2biL2DXoJKidU2CFQANImv5HprpfLv3H+FsvEKq9OruwWa75lM6jEcwldZ+fcquZZVdRlp/F0AnEqUulBzfRlKDMkCHagIo0cezUzE7gsHY2WQSyfQwit1eqH4T4ceLvCd1PErkWIMjHEA3xjr1b00mkyFjq5dw0nOXrYL+HvOY4eImHjVMn9bf447GAYJz1/3+1kY16wW+UC2yhA8wzbzMYA5IqaOaIYD9CpZdDqdl4nvBLg91sjW0wDOl4MFPUYP/CS3XzFyJwBZQHgiTV8Njq3o88k5q7hGILHsrbgBp+NswIDAQABAoIBAFNI9W5I5Iu6NPK9H9Mi4cZ7qOuC9k+zoDj9Noyc1D3HvsfsNmjqL3sH8pcxh5MdG/XoCwWJ4pRLYi1X2N4J4HumTaoc/u2xLPNfiyPZap8BkuNjsfUcST3A4rdHAYrS1bSRj03lQCZNSDG3gekeeKuD6ggSHEtJQrS1gP4O4iuS4dGCD+JB3Ou4dG3a5jPBAs0E5Ka5b2jPqb3o4L9jCUJXNRGPH7NS7BFT0wO27b21YlWZpa/+KZgwtQ/NYt1tGR7VtyXhpp/3R3PCfP/AZqfoWqPg7VVYf8+FiZowD7SrlN9skcbOFv4lEkyJ4VFBt4vFCESNFVcLOve3b+Jy7zECgYEA/U65T3WchkAibuYZ6NtKikrxvcYuesOFowsvVRZV9UoS15lGOIXsqKdKaj4kALHy+IdUMpRlrkiHj21nrnj9yRisIAMtncCUvpwsEoiB+LL/2AkfKMXye0jSBlUqTmUTyBgNPOiGdUrfcQ3K7ZWlqjY57AvVB0vvdck3GvfkUosCgYEA0keXXztK29QuddAG/iP/eJJJmecWm/dWhpV5EXHi6TbssZL5ptho2e+UewX+49+YWJEVpwhnanLFuapkYe2yWmbAQT7PdoRhgOpHEJD5DkXA/PbTgAftoyaNhiqnOBUDAvi2nXZok4azDhqUMuxlsgX4GM028ykmlCKaYrhg3nkCgYEAqnKFqV0nlQKk6OC2wISG9OWXIy6u6faU/vAwyl18O9C7Ow4tPS2I4LepEDJ+QWV8bBWL53kJrqupZC6nHLTXeLC8adIZqNQEgvpNga8PXgz8sR76M5J3JtQKVy6JiDTlibf9eGWyzEw0bDtuuE4BKrbQZPBVSVKkcCqUhPYXUh8CgYA9sOk09DU7Fx3mTHiwuKjrm6diMozMhxzocRPKzWmv2ABK/H6nt1fTkFk0Wob0HZYZbb83eNy2OyPNG7NFk7G+meSPf7jn4WClrJXaw11+FXLQBC36otjSA66DjSHv7yIqDgOtYJ6/oUYIvj16TyZquxuVUydq4UjDLv+yNLueqQKBgA6W1UHBg1BzYbHCitziozkIIDsArbUgGm0G42773+q20uFoRbjjJ5wbmLoNZqYSRU70uRQDS5IPRp3u6V6tW+vXMekUzXu7wgnKsWoF2KQ2CPuut+sjiYZm4fCNYUxvDIkBFgtGuYQLQKY79tAnuKj3mbbPEoQFHxToU3HRI3NJ";
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
			mPublicKey = keyFactory.generatePublic(publicKeySpec);

			PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
			mPrivateKey = keyFactory.generatePrivate(privSpec);

			mCipher = Cipher.getInstance("RSA");
		}

		public byte[] encrypt(String plainText) throws InvalidKeyException, IllegalBlockSizeException,
				BadPaddingException, UnsupportedEncodingException {
			return encrypt(plainText.getBytes(StandardCharsets.UTF_8));
		}

		public byte[] encrypt(byte[] plain) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
				UnsupportedEncodingException {
			mCipher.init(Cipher.ENCRYPT_MODE, mPublicKey);
			return mCipher.doFinal(plain);
		}

		public String decrypt(String cipherText) throws InvalidKeyException, IllegalBlockSizeException,
				BadPaddingException, UnsupportedEncodingException {
			return decrypt(Base64.decodeBase64(cipherText.getBytes(StandardCharsets.UTF_8)));
		}

		public String decrypt(byte[] cipher) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException,
				UnsupportedEncodingException {
			mCipher.init(Cipher.DECRYPT_MODE, mPrivateKey);
			byte[] plain = mCipher.doFinal(cipher);

			return new String(plain, StandardCharsets.UTF_8);
		}
	}
//	public static void main(String[] args) throws Exception {
//		RsaUtil.Rsa rsaUtil = new RsaUtil.Rsa();
//		byte[] encrypt = rsaUtil.encrypt("nilesh");
//		String str = new String(rsaUtil.decrypt(encrypt));
//		System.out.println(str);
//		 
//	}

}
