package PortalTracker.Tracker.service.impl;

import PortalTracker.Tracker.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService {

	@org.springframework.beans.factory.annotation.Value("${token.signing.key}")
	String jwtSigningKey;

	@Override
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	@Override
	public String generateToken(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String userName = extractUserName(token);
		return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	<T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
		final Claims claims = (Claims) extractAllClaims(token);
		return claimsResolvers.apply(claims);
	}

	String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
		Date now = new Date();
		Date expirationDate = new Date(now.getTime() + 86400000);
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(now)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS256, getSigningKey())
				.compact();
	}


	boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Jws<Claims> extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
	}

	SecretKey getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(jwtSigningKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}