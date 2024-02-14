/**
 * Password Security Utility
 */

package com.project.application.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class SecurityPassword {
    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Hash Raw Password
    public static String hashPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    //Match Raw Password with Hashed Password
    public static Boolean matchPassword(String rawPassword, String hashedPassword) {
        return passwordEncoder.matches(rawPassword, hashedPassword);
    }
}
