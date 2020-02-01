package org.mongodb.kafka.utils;

import org.bson.types.ObjectId;

public class HexStringConverter {

    /**
     * Create a byte Array from String of hexadecimal digits using Character conversion
     * @param hexString - Hexadecimal digits as String
     * @return Desired byte Array
     */
    public byte[] decodeHexString(String hexString) {
        if (hexString.length() % 2 == 1) {
            throw new IllegalArgumentException("Invalid hexadecimal String supplied.");
        }
        byte[] bytes = new byte[hexString.length() / 2];

        for (int i = 0; i < hexString.length(); i += 2) {
            bytes[i / 2] = hexToByte(hexString.substring(i, i + 2));
        }
        return bytes;
    }

    /**
     * Create a String of hexadecimal digits from a byte Array using Character conversion
     * @param byteArray - The byte Array
     * @return Desired String of hexadecimal digits in lower case
     */
    public String encodeHexString(byte[] byteArray) {
        StringBuffer hexStringBuffer = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            hexStringBuffer.append(byteToHex(byteArray[i]));
        }
        return hexStringBuffer.toString();
    }

    public String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    public byte hexToByte(String hexString) {
        int firstDigit = toDigit(hexString.charAt(0));
        int secondDigit = toDigit(hexString.charAt(1));
        return (byte) ((firstDigit << 4) + secondDigit);
    }

    private int toDigit(char hexChar) {
        int digit = Character.digit(hexChar, 16);
        if(digit == -1) {
            throw new IllegalArgumentException("Invalid Hexadecimal Character: "+ hexChar);
        }
        return digit;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        String objectId = "5e35c506159bf83f2e69ff63";
        System.out.println("String ObjectId     : " + objectId);
        HexStringConverter hexStringConverter = new HexStringConverter();
        byte[] objectIdBytes = hexStringConverter.decodeHexString(objectId);
        String hexString = hexStringConverter.encodeHexString(objectIdBytes);
        System.out.println("Converted hex string: " + hexString);
        System.out.println("Is it a valid ObjectId?");
        System.out.println(ObjectId.isValid(hexString));
    }
}
