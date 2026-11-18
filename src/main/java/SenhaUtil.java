import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class SenhaUtil {

    public static String gerarHash(String senha) {
        try {
            byte[] salt = new byte[16];
            new SecureRandom().nextBytes(salt);

            PBEKeySpec spec = new PBEKeySpec(
                    senha.toCharArray(), salt, 210000, 256
            );

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            spec.clearPassword();

            return Base64.getEncoder().encodeToString(salt)
                    + ":" +
                    Base64.getEncoder().encodeToString(hash);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao gerar hash da senha.", e);
        }
    }

    public static boolean verificarSenha(String senhaDigitada, String senhaArmazenada) {
        try {
            String[] partes = senhaArmazenada.split(":");

            byte[] salt = Base64.getDecoder().decode(partes[0]);
            byte[] hashArmazenado = Base64.getDecoder().decode(partes[1]);

            PBEKeySpec spec = new PBEKeySpec(
                    senhaDigitada.toCharArray(), salt, 210000, 256
            );

            SecretKeyFactory factory =
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

            byte[] hashDigitado = factory.generateSecret(spec).getEncoded();
            spec.clearPassword();

            return java.security.MessageDigest.isEqual(
                    hashArmazenado, hashDigitado
            );

        } catch (Exception e) {
            return false;
        }
    }
}
