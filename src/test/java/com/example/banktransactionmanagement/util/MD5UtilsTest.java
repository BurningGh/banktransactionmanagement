package com.example.banktransactionmanagement.util;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MD5UtilsTest {

    private String input;
    private String charset;

    @BeforeEach
    public void setUp() {
        input = "test";
        charset = "UTF-8";
    }

    @Test
    public void md5Hex_ValidInputAndCharset_ReturnsCorrectHash() {
        String expectedHash = "098f6bcd4621d373cade4e832627b4f6";
        String result = MD5Utils.md5Hex(input, charset);
        assertEquals(expectedHash, result);
    }

    @Test
    public void md5Hex_UnsupportedCharset_ThrowsRuntimeException() {
        String unsupportedCharset = "unsupported";
        assertThrows(RuntimeException.class, () -> {
            MD5Utils.md5Hex(input, unsupportedCharset);
        });
    }

    @Test
    public void md5Hex_EmptyInput_ReturnsCorrectHash() {
        String expectedHash = "d41d8cd98f00b204e9800998ecf8427e";
        String result = MD5Utils.md5Hex("", charset);
        assertEquals(expectedHash, result);
    }

    @Test
    public void md5Hex_EmptyCharset_ThrowsRuntimeException() {
        String emptyCharset = "";
        assertThrows(RuntimeException.class, () -> {
            MD5Utils.md5Hex(input, emptyCharset);
        });
    }

    @Test
    public void md5Hex_EmptyInputAndCharset_ThrowsRuntimeException() {
        String emptyInput = "";
        String emptyCharset = "";
        assertThrows(RuntimeException.class, () -> {
            MD5Utils.md5Hex(emptyInput, emptyCharset);
        });
    }
}
