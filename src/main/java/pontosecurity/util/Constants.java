package pontosecurity.util;

import java.nio.charset.StandardCharsets;

import javax.crypto.SecretKey;

import io.jsonwebtoken.security.Keys;

public class Constants {
	public static final SecretKey CHAVE = Keys.hmacShaKeyFor(
			"4ap[9yb1W)HL0-|5P-Lkqu=uw.;_tC#&uOqO7WLm}@k%{oA;0O/4eU'i,i^8hb5#".getBytes(StandardCharsets.UTF_8));
	public static final String GOOGLE_SERVER_API_KEY = "AIzaSyDPoA4ZJnq-lxoGBs6P_Nu6Hm8UcqK5DPs";

}
