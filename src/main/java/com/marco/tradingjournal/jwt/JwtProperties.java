package com.marco.tradingjournal.jwt;

import io.github.cdimascio.dotenv.Dotenv;

public class JwtProperties {
    private static final Dotenv dotenv = Dotenv.configure()
            .ignoreIfMissing()
            .load();

    public static final String SECRET = dotenv.get("JWT_SECRET");
    public static final long EXPIRATION_TIME = 864_000_00; // 1 giorno in ms
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}